package com.example.ats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ats.Model.Student;

public interface StdRepository extends JpaRepository<Student, Integer> {


	boolean existsByPassword(String password);

		
	Student findByPasswordAndRollno( String password, String rollno);

	Student findByRollnoAndPassword(String rollno, String password);

	boolean existsByRollnoAndPassword(String rollno, String password);

	Student PasswordAndRollno(String password, String rollno);

	

	Student findByPassword(String password);


	boolean existsByRollno(String rollno);


	Student findByRollno(String rollno);


	

	boolean existsByUsernameAndPassword(String username, String password);
	
	
	

}
