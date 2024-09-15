package com.user.Inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.Inventory.Entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

	public Inventory findByInventoryName(String inventoryName);
}
