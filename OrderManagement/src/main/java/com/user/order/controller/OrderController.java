package com.user.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.order.Entity.Order;
import com.user.order.POJO.Inventory;
import com.user.order.exceptions.InvalidOrderQuantityException;
import com.user.order.fiegnclientInventory.InventoryFiegn;
import com.user.order.service.OderServiceImpl;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {

	
	@Autowired
	private OderServiceImpl orderService;
	
	@Autowired
	private InventoryFiegn fiegn;
	
	@PostMapping("/create")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) throws InvalidOrderQuantityException{
		Order saveorder = orderService.createOrder(order);
		
		if(saveorder!= null) {
			return new ResponseEntity<Order>(saveorder,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Order>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> allOrders = orderService.getAllOrder();
		if(allOrders!= null) {
			return new ResponseEntity<>(allOrders,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<List<Order>>(HttpStatus.CONFLICT);
		}
	}
	


	@GetMapping("/getbyId/{orderId}")
	public ResponseEntity<Order> getAllOrderById(@PathVariable("orderId") int orderId){
		Order orderById = orderService.getOrderById(orderId);
		if(orderById!= null) {
			return new ResponseEntity<>(orderById,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<Order>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/updatebyId/{orderId}")
	public ResponseEntity<Order> updateInventoryById(@RequestBody Order order,@PathVariable int orderId){
		Order updateOrderById = orderService.updateOrderById(orderId,order);
		if(updateOrderById!= null) {
			return new ResponseEntity<>(updateOrderById,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<Order>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/deletebyId/{orderId}")
	public void deleteInventoryById(@PathVariable int orderId){
		orderService.deleteOrderById(orderId);
        
		}
	
//	@GetMapping("/getbyName/{inventoryName}")
//	public ResponseEntity<Order> getAllOrderByName(@PathVariable String inventoryName){
//		Order byInventoryName = orderService.findByInventoryName(inventoryName);
//		return new ResponseEntity<Order> (byInventoryName,HttpStatus.ACCEPTED);
//	}
	
	@PutMapping("/cancelById/{orderId}")
	public ResponseEntity<Order> cancelOrderById(@PathVariable int orderId){
		Order updateOrderById = orderService.cancelOrder(orderId);
		if(updateOrderById!= null) {
			return new ResponseEntity<>(updateOrderById,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<Order>(HttpStatus.CONFLICT);
		}
	}
	
	
	@PutMapping("/orderAccepted/{orderId}")
	public ResponseEntity<Order> orderAccepted(@RequestBody Order order,@PathVariable int orderId){
		Order updateOrderById = orderService.orderQtyAccept(orderId,order);
		if(updateOrderById!= null) {
			return new ResponseEntity<>(updateOrderById,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<Order>(HttpStatus.CONFLICT);
		}
	}

}
