package com.user.order.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orderTable")
public class Order {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int orderId;
	private LocalDateTime orderTime;
	private String inventoryName;
	private long orderQty;
	private String orderStatus;
	private LocalDateTime orderCancelTime;
	private long orderQtyAccepted;
	private long totalOrderAmount;
	
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime localDateTime) {
		this.orderTime = localDateTime;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public long getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(long orderQty) {
		this.orderQty = orderQty;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public LocalDateTime getOrderCancelTime() {
		return orderCancelTime;
	}
	public void setOrderCancelTime(LocalDateTime orderCancelTime) {
		this.orderCancelTime = orderCancelTime;
	}
	public long getOrderQtyAccepted() {
		return orderQtyAccepted;
	}
	public void setOrderQtyAccepted(long orderQtyAccepted) {
		this.orderQtyAccepted = orderQtyAccepted;
	}
	public long getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(long totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	
}
