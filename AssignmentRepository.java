package com.example.ats.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ats.Model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{

	boolean existsByAssignment(String assignment);

	boolean existsByRollno(String rollno);

	boolean existsBySemester(String semester);

	

	boolean existsBySubject(String subject);

	boolean existsByDate(String date);

	Assignment findByRollnoAndSemesterAndSubjectAndAssignment(String rollno, String semester, String subject, String assignment);

	boolean existsByRollnoAndSemesterAndSubjectAndAssignment(String rollno, String semester, String subject,
			String assignment);

	boolean existsByRollnoAndSemester(String rollno, String semester);

	 

	List<Assignment> findAllByRollnoAndSemester(String rollno, String semester);

	List<Assignment> findAllByRollno(String rollno);

	List<Assignment> findAllBySemester(String semester);

	List<Assignment> findAllBySubjectAndSemester(String subject, String semester);

	boolean existsBySubjectAndSemester(String subject, String semester);

	 List<Assignment>  findAllByRollnoOrSubject(String rollno, String subject);

	List<Assignment> findAllByRollnoAndSubject(String rollno, String subject);

	
}
