package com.api.streaming.repository;

import java.util.Optional;

import com.api.streaming.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role,Integer>{

    public Optional<Role> findByName(String name);

    
}
