package com.sandeep.retailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandeep.retailstore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}