package com.user.order.service;

import java.util.List;

import com.user.order.Entity.Order;
import com.user.order.exceptions.InvalidOrderQuantityException;

public interface OrderService {

	
	public Order createOrder(Order order) throws InvalidOrderQuantityException;
	
	public List<Order> getAllOrder();
	
	public Order getOrderById(int orderId);
	
	public Order updateOrderById(int orderId, Order order);
	
	public void deleteOrderById(int orderId);
	
	public Order findByInventoryName(String inventoryName);
	
	public Order cancelOrder(int orderId );
	
	public Order orderQtyAccept(int orderId ,Order order);
}
