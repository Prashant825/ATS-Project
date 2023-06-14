package com.example.ats.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ats.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	boolean existsByUsername(String username);

	User findByEmailAndPassword(String email, String password);

	boolean existsByUsernameAndEmail(String tname, String temail);

	User findByUsernameAndEmail(String tname, String temail);

	boolean existsByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);

	boolean existsByUsernameAndPassword(String username, String password);

	User findByUsernameAndPassword(String username, String password);
}
