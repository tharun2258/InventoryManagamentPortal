package com.user.order.POJO;

public class Inventory {

    private int Id;
	
    private  String inventoryName;
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