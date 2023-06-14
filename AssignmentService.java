package com.example.ats.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ats.Bean.AssgnBean;
import com.example.ats.Model.Assignment;
import com.example.ats.Repository.AssignmentRepository;
import com.example.ats.Repository.UserRepository;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepository assgnRepo;

	public String addAssgn( String rollno,String session, String semester, String subject, String assignment,
			String teachername, String date, String fileName) {
		if (assgnRepo.existsByRollno(rollno) && assgnRepo.existsByAssignment(assignment)
				&& assgnRepo.existsBySemester(semester) 
				&& assgnRepo.existsBySubject(subject)) {
			return "/Student/assgnExist";
		}
		Assignment assgn = new Assignment(rollno,session, semester, subject, assignment,teachername, date, fileName);
		assgnRepo.save(assgn);
		return "/Student/assgnSuccess";
	}

//	find all
	public List<AssgnBean> findAllAssgn() {
		List<AssgnBean> list = new ArrayList<AssgnBean>();
		for (Assignment assgns : assgnRepo.findAll()) {
			list.add(new AssgnBean(assgns));
		}
		return list;
	}
	

	public String delAssgn(String rollno, String semester, String subject, String assignment) {
		if (assgnRepo.existsByRollnoAndSemesterAndSubjectAndAssignment(rollno, semester, subject, assignment)) {
			Assignment assgn = null;
			assgn = assgnRepo.findByRollnoAndSemesterAndSubjectAndAssignment(rollno, semester, subject, assignment);
			assgnRepo.delete(assgn);
			return "/Teacher/delSuccess";
		}
		return "/Teacher/notFoundAssgn";
	}

//	// Show All Assignment..
//	public List<AssgnBean> fetchAllAssgn() {
//		List<AssgnBean> list = new ArrayList<AssgnBean>();
//		for (Assignment assgn : assgnRepo.findAll()) {
//			list.add(new AssgnBean(assgn));
//		}
//		return list;
//	
//	}

	public List<AssgnBean> findAllBySemester(String semester) {
		List<AssgnBean> list = new ArrayList<AssgnBean>();
		for (Assignment assgn : assgnRepo.findAllBySemester(semester)) {
			list.add(new AssgnBean(assgn));
		}
		return list;
	}
	
//	find by semester and subject..
	public List<AssgnBean> findAllBySubjectAndSemester(String subject,String semester) {
		List<AssgnBean> list = new ArrayList<AssgnBean>();
		for (Assignment assgn : assgnRepo.findAllBySubjectAndSemester(subject,semester)) {
			
			if(assgnRepo.existsBySubjectAndSemester(subject, semester)) {
			list.add(new AssgnBean(assgn));
			}
		}
		return list;
	}

	
}


