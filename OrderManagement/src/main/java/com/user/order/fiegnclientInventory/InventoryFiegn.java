package com.user.order.fiegnclientInventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.order.POJO.Inventory;


@FeignClient(name = "InventoryManagemnt", url = "http://localhost:8081/inventory")
public interface InventoryFiegn {
	
	
	@GetMapping("/getbyName/{inventoryName}")
	public Inventory getAllInventoryByName(@PathVariable String inventoryName);
	
	@PutMapping("/updatebyId/{inventoryName}")
	public Inventory updateInventoryById(@RequestBody Inventory inventory ,@PathVariable String inventoryName);

}
