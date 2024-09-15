package com.user.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.registration.entity.Role;
import com.user.registration.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public Role createRole(Role role) {
		return roleRepo.save(role);
	}

}
