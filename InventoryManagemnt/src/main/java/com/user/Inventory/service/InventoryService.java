package com.user.Inventory.service;

import java.util.List;

import com.user.Inventory.Entity.Inventory;

public interface InventoryService {
	
	public Inventory createInventory(Inventory inventory);
	
	public List<Inventory> getAllInventory();
	
	public Inventory getInventoryById(int id);
	
	public Inventory updateInventoryById(Inventory inventory ,String inventoryName);
	
	public void deleteInventoryById(int id);
	
	public Inventory getInventoryByName(String inventoryName);

}
