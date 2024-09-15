package com.user.registration.service;

import java.util.List;
import java.util.Map;

import com.user.registration.entity.User;

public interface UserService {
	
	public User createUser(User user);
	
	public List<User> getAllUsers();
	
	public User updateUser(User user , int userid);
	
	public User getByUserId(int userid);
	
	public void deleteUserById(int userid);
	
	public Map<String , Object> loginUser(String username , String password);
	
	public User forgotPassword(int userid , User user);
	
	public User getByUsernameAndEmailId(String username , String emailId);
	
	public User getByUserName(String username);

	
	
	

}
