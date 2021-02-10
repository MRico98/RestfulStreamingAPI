package com.api.streaming.repository;

import java.util.Optional;

import com.api.streaming.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    public Optional<User> findByEmailAndPassword(String email, String password);

    public Optional<User> findByEmail(String email);
    
}
