package com.user.order.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.order.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
 public Order findByInventoryName(String inventoryName);

}
