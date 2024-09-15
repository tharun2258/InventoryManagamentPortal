package com.user.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.registration.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRolename(String rolename);

}
