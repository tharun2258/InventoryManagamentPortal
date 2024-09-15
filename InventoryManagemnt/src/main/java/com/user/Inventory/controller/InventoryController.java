package com.user.Inventory.controller;

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

import com.user.Inventory.Entity.Inventory;
import com.user.Inventory.service.InventoryServiceImpl;

@RestController
@RequestMapping("/inventory")
@CrossOrigin("http://localhost:4200")
public class InventoryController {
	
	@Autowired
	private InventoryServiceImpl invService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory){
		
		Inventory createinventory = invService.createInventory(inventory);
		if(createinventory!= null) {
			return new ResponseEntity<Inventory>(createinventory,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Inventory>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Inventory>> getAllInventory(){
		List<Inventory> allInventory = invService.getAllInventory();
		if(allInventory!= null) {
			return new ResponseEntity<>(allInventory,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<List<Inventory>>(HttpStatus.CONFLICT);
		}
	}
	


	@GetMapping("/getbyId/{id}")
	public ResponseEntity<Inventory> getAllInventoryById(@PathVariable int id){
		Inventory inventoryById = invService.getInventoryById(id);
		if(inventoryById!= null) {
			return new ResponseEntity<>(inventoryById,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<Inventory>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/updatebyId/{inventoryName}")
	public ResponseEntity<Inventory> updateInventoryById(@RequestBody Inventory inventory,@PathVariable String inventoryName){
		Inventory updateInventoryById = invService.updateInventoryById(inventory,inventoryName);
		if(updateInventoryById!= null) {
			return new ResponseEntity<>(updateInventoryById,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<Inventory>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/deletebyId/{id}")
	public void deleteInventoryById(@PathVariable int id){
		invService.deleteInventoryById(id);
        
		}
	
	@GetMapping("/getbyName/{inventoryName}")
	public ResponseEntity<Inventory> getAllInventoryByName(@PathVariable String inventoryName){
		Inventory inventoryByName = invService.getInventoryByName(inventoryName);
		if(inventoryByName!= null) {
			return new ResponseEntity<>(inventoryByName,HttpStatus.ACCEPTED);
		}
		else{
        return new ResponseEntity<Inventory>(HttpStatus.CONFLICT);
		}
	}
	}

