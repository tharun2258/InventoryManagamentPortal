package com.user.Inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.Inventory.Entity.Inventory;
import com.user.Inventory.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService{

	
	@Autowired
	private InventoryRepository invRepo;
	

//	@Autowired
//	private OrderFiegn fiegn;
	//create all methods for CRUD operations
	@Override
	public Inventory createInventory(Inventory inventory) {
		return invRepo.save(inventory);
	}

	@Override
	public List<Inventory> getAllInventory() {
		return invRepo.findAll();
	}

	@Override
	public Inventory getInventoryById(int id) {
		Inventory inventory = invRepo.findById(id).get();
		return inventory;
	}

	@Override
	public Inventory updateInventoryById(Inventory inventory, String inventoryName) {
		Inventory inv = invRepo.findByInventoryName(inventoryName);
		inv.setInventoryName(inventory.getInventoryName());
		inv.setInvPrice(inventory.getInvPrice());
		inv.setInvQuantity(inventory.getInvQuantity());
		inv.setStockStatus(inventory.getStockStatus());
        Inventory updateInv = invRepo.save(inv);
		return updateInv;
	}

	@Override
	public void deleteInventoryById(int id) {
      invRepo.deleteById(id);		
	}

	@Override
	public Inventory getInventoryByName(String inventoryName) {
		return invRepo.findByInventoryName(inventoryName);
	}

}
