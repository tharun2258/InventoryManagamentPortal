package com.user.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.order.Entity.Order;
import com.user.order.POJO.Inventory;
import com.user.order.Repository.OrderRepository;
import com.user.order.exceptions.InvalidOrderQuantityException;
import com.user.order.fiegnclientInventory.InventoryFiegn;

@Service
public class OderServiceImpl implements OrderService {

	
	@Autowired
	private InventoryFiegn fiegn;
	
	@Autowired
	private OrderRepository orderRepo;


	
	@Override
	public Order createOrder(Order order) throws InvalidOrderQuantityException{
    Inventory inv = fiegn.getAllInventoryByName(order.getInventoryName());
    if(inv.getInvQuantity()>0 && order.getOrderQty()>0 && order.getOrderQty()<= inv.getInvQuantity()) {
    order.setOrderQty(order.getOrderQty());
    order.setOrderTime(LocalDateTime.now());
    order.setTotalOrderAmount(order.getOrderQty() * inv.getInvPrice());
    order.setOrderStatus("Order Placed!!! Waiting for Supplier to approve");    
    inv.setInvQuantity(inv.getInvQuantity()-order.getOrderQty());
    fiegn.updateInventoryById(inv, order.getInventoryName());
    return orderRepo.save(order);
    }
    else {
	return  null;
	
    
    }
	}

	@Override
	public List<Order> getAllOrder() {
		return orderRepo.findAll();
	}

	@Override
	public Order getOrderById(int orderId) {
		Order order = orderRepo.findById(orderId).get();
		return order;
	}

	@Override
	public Order updateOrderById(int orderId, Order order) {
		Order updateorder = orderRepo.findById(orderId).get();
		updateorder.setOrderQty(order.getOrderQty());
		updateorder.setInventoryName(order.getInventoryName());
		updateorder.setOrderStatus(order.getOrderStatus());
		return orderRepo.save(updateorder);
	}

	@Override
	public void deleteOrderById(int orderId) {
		orderRepo.deleteById(orderId);
	}

	@Override
	public Order findByInventoryName(String inventoryName) {
		return orderRepo.findByInventoryName(inventoryName);
	}

	@Override
	public Order cancelOrder(int orderId) {
		Order orderdetails = orderRepo.findById(orderId).get();
		Inventory inventory = fiegn.getAllInventoryByName(orderdetails.getInventoryName());
		inventory.setInvQuantity(inventory.getInvQuantity()+orderdetails.getOrderQty());
		fiegn.updateInventoryById(inventory, orderdetails.getInventoryName());
		orderdetails.setOrderStatus("cancelled");
		orderdetails.setOrderCancelTime(LocalDateTime.now());
		return orderRepo.save(orderdetails);
	}

	@Override
	public Order orderQtyAccept(int orderId,Order order) {
		Order orderdetails = orderRepo.findById(orderId).get();
		order.setOrderQtyAccepted(order.getOrderQtyAccepted());
		order.setOrderId(orderdetails.getOrderId());
		order.setInventoryName(orderdetails.getInventoryName());
		order.setOrderQty(orderdetails.getOrderQty());
		order.setOrderStatus("accepted");
		order.setOrderTime(orderdetails.getOrderTime());
		Inventory inventory = fiegn.getAllInventoryByName(orderdetails.getInventoryName());
		inventory.setInvQuantity(inventory.getInvQuantity()+orderdetails.getOrderQty()-order.getOrderQtyAccepted());
		fiegn.updateInventoryById(inventory, orderdetails.getInventoryName());
		return orderRepo.save(order);
	}

	
	
}
