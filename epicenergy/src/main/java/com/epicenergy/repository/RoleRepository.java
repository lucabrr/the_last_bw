package com.epicenergy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicenergy.entity.ERole;
import com.epicenergy.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
