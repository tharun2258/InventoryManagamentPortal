package com.user.Inventory.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="inventory")


public class Inventory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="inventoryName")
	private  String inventoryName;
	
	@Column(name="invQuantity") 
	private long invQuantity;
	private long invPrice;
	private String stockStatus;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public long getInvQuantity() {
		return invQuantity;
	}
	public void setInvQuantity(long invQuantity) {
		this.invQuantity = invQuantity;
	}
	public long getInvPrice() {
		return invPrice;
	}
	public void setInvPrice(long invPrice) {
		this.invPrice = invPrice;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	
}
