package com.example.ats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ats.Model.Admin;
import com.example.ats.Model.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	boolean existsByEmailAndPassword(String email, String password);

	Admin findByEmailAndPassword(String email, String password);

	boolean existsByEmailAndUsername(String email, String username);

	Admin findByEmailAndUsername(String email, String username);

	boolean existsByEmailAndUsernameAndPassword(String email, String username, String password);

	boolean existsByUsernameAndPassword(String username, String password);

	boolean existsByPassword(String password);

	Admin findByPassword(String password);

	
}
