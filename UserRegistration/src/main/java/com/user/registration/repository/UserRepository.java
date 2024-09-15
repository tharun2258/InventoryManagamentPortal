package com.user.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.registration.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
    public User findByUsername(String username);
	
	public User findByUsernameAndPassword(String username , String password);
	
	public User findByUsernameAndEmailid(String username , String password);
	
	

}
