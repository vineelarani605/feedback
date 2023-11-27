package com.techwl.feedo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techwl.feedo.user.entities.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	UserDetails findByEmail(String email);

	UserDetails findByEmailAndPassword(String email, String password);

	UserDetails findById(int id);

	UserDetails findByName(String name);

	List<UserDetails> findAll();
}
