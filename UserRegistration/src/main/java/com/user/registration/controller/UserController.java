package com.user.registration.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.registration.entity.User;
import com.user.registration.securityconfig.CustomUserDetailsService;
import com.user.registration.service.UserServiceImpl;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserServiceImpl userservice;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	
	@PostConstruct
	public void init() throws IOException {
		userservice.initRoleAndUser();
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user2 = userservice.createUser(user);
		if(user2 != null) {
		return new ResponseEntity<User>(user2 ,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userservice.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getUserById/{userid}")
	public ResponseEntity<User> getAllUsers(@PathVariable("userid") int userid){
		User byUserId = userservice.getByUserId(userid);
		return new ResponseEntity<User>(byUserId, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/byusername/{username}")
	public ResponseEntity<User> getUserByname( @PathVariable("username") String username){
		User byUsername= userservice.getByUserName(username);
		return new ResponseEntity<User>(byUsername, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateUserById/{userid}")
	public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable("userid") int userid){
		User byUserId = userservice.updateUser(user, userid);
		return new ResponseEntity<User>(byUserId, HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/deleteUserById/{userid}")
	public ResponseEntity<User> deleteUserById( @PathVariable("userid") int userid){
		userservice.deleteUserById(userid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user ){
		String username =user.getUsername();
		String password = user.getPassword();
		
	    Map<String,Object> loginUser = userservice.loginUser(username , password);
	    if(loginUser!= null) {
		return new ResponseEntity<>(loginUser,HttpStatus.OK);
		
	    }
	    else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	

	
	@GetMapping("/userdetails")
	public ResponseEntity<User> getuserbyNameAndEmailid( @RequestHeader("username") String username , @RequestHeader("emailId") String emailId){
		User byUsername= userservice.getByUsernameAndEmailId(username, emailId);
		return new ResponseEntity<User>(byUsername, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/resetpassowrd/{userid}")
	public ResponseEntity<User> forgotPassword(@PathVariable("userid") int userid, @RequestBody User user){
	
		User forgotPassword = userservice.forgotPassword(userid, user);
		
		return new ResponseEntity<User>(forgotPassword, HttpStatus.OK);
	}
	
}
