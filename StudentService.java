package com.example.ats.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ats.Bean.AssgnBean;
import com.example.ats.Model.Assignment;
import com.example.ats.Model.Student;
import com.example.ats.Model.User;
import com.example.ats.Repository.AssignmentRepository;
import com.example.ats.Repository.StdRepository;

@Service
public class StudentService {

	@Autowired
	private StdRepository studRepo;
	
	@Autowired
	private AssignmentRepository assgnRepo;
	
	
	
	public String addNewStudent(String username, String password) {
		if (studRepo.existsByUsernameAndPassword(username, password)) {
			return "/Student/existStudent";
	}
	else {
		Student student = new Student(username,password);
		studRepo.save(student);
		return "/Student/SuccessRegister";
	}
		
	}
	

	public String loginStd(String rollno, String password) {
		if(studRepo.existsByRollno(rollno)) {
			if(studRepo.existsByPassword(password)) {
				return "/Student/StudentPanel";
			}
		}
		return "/Student/notFoundStudent";
	}
	
	public String forgotName(String newrollno, String confirmrollno, String password) {
		if(studRepo.existsByPassword( password) && newrollno!=null) {
			Student std = null;
			std= studRepo.findByPassword(password);
			std.setRollno(newrollno);
			studRepo.save(std);
			return "/Student/updateSuccess";
		} 
		return "/Student/notFoundStudent";
	}

	public String forgotPass (String rollno, String newpassword, String confirmpassword) {
		Student student = null;
		if(studRepo.existsByRollno(rollno) && newpassword!=null) {
			student = studRepo.findByRollno(rollno);
			student.setPassword(newpassword);
			studRepo.save(student);
			return "/Student/updateSuccess";
		}
		return "/Student/notFoundStudent";
	}
	
	
	public String updateStudentName(String rollno,String username,String newusername, String password) {
		Student student=null;
		if(studRepo.existsByRollnoAndPassword(rollno, password) && newusername!=null) {
			student= studRepo.findByPasswordAndRollno(password, rollno);
			student.setUsername(newusername);
			studRepo.save(student);
			return "/Student/updateSuccess";
		}
		return "/Student/notFoundStudent";
	}

	public String updateStudentPass(String rollno,  String password, String newpassword, String confirmpassword) {
		Student std=null;
		if(studRepo.existsByRollnoAndPassword(rollno, password) && newpassword!=null) {
			std = studRepo.findByRollnoAndPassword(rollno, password);
			std.setPassword(newpassword);
			studRepo.save(std);
			return "/Student/updateSuccess";
		}
		else {
			return "/Student/notFoundStudent";
		}
	}

//	update rollno 
	public String updateStdRollno(String rollno, String newrollno, String password) {
		Student std = null;
		if(studRepo.existsByRollnoAndPassword(rollno,  password)) {
			if(studRepo.existsByRollno(newrollno)) {
				return "/Student/studentAlreadyExist";
			} else {
				std = studRepo.findByPassword(password);
				std.setRollno(newrollno);
				studRepo.save(std);
				return "/Student/updateSuccess";	
			}
		}
		return "/Student/notFoundStudent";
	}


	
//	<===== Student Assignments Operations ====>

	

	public List<AssgnBean> findAllByRollnoAndSubject(String rollno,String subject) {
		List<AssgnBean> list = new ArrayList<AssgnBean>();
		for (Assignment assgn : assgnRepo.findAllByRollnoAndSubject(rollno,subject)) {
			list.add(new AssgnBean(assgn));
		}
		return list;
		
	}


	public List<AssgnBean> findAllByRollnoAndSemester(String rollno, String semester) {
		List<AssgnBean> list = new ArrayList<AssgnBean>();
		for(Assignment assgn : assgnRepo.findAllByRollnoAndSemester(rollno, semester)) {
			
			list.add(new AssgnBean(assgn));
		}
		return list;
		
	}


	
}
