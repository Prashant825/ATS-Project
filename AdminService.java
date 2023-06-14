package com.example.ats.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ats.Model.Admin;
import com.example.ats.Model.Assignment;
import com.example.ats.Model.Student;
import com.example.ats.Model.User;
import com.example.ats.Repository.AdminRepository;
import com.example.ats.Repository.AssignmentRepository;
import com.example.ats.Repository.StdRepository;
import com.example.ats.Repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private StdRepository stdRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AssignmentRepository assgnRepo;
	
	public String LoginAdm(String username, String password) {
		if (adminRepo.existsByUsernameAndPassword(username, password)) {
				return "/Admin/AdminPanel";
		}
		return "/Admin/notFoundAdmin";
	}

	public String updateName( String username, String newusername, String password) {
		Admin admin = null;
		if (adminRepo.existsByPassword( password) && newusername!= null) {
			admin = adminRepo.findByPassword(password);
			admin.setUsername(newusername);
			adminRepo.save(admin);
			return "/Admin/updateSuccess";
		}
		return "/Admin/notFound";
	}

	public String updatePass( String username, String password, String newpassword, String confirmpasssword) {
		Admin admin = null;
		if (adminRepo.existsByPassword( password) && newpassword!=null && newpassword.equals(confirmpasssword)) {
			admin = adminRepo.findByPassword(password);
			admin.setPassword(newpassword);
			adminRepo.save(admin);
			return "/Admin/updateSuccess";
		}
		return "/Admin/notFound";
	}

	public String forgotAdminUsername(String email, String newusername, String confirmusername, String password) {
		if (adminRepo.existsByEmailAndPassword(email, password) && newusername!=null && newusername.equals(confirmusername)) {
			Admin admin = null;
			admin = adminRepo.findByEmailAndPassword(email, password);
			admin.setUsername(newusername);
			adminRepo.save(admin);
			return "/Admin/updateSuccess";
		}
		return "/Admin/notFoundAdmin";
	}

	public String forgotAdminPassword(String email, String username, String newpassword, String confirmpassword) {
		if (adminRepo.existsByEmailAndUsername(email, username) && newpassword!=null && newpassword.equals(confirmpassword)) {
			Admin admin = null;
			admin = adminRepo.findByEmailAndUsername(email, username);
			admin.setPassword(newpassword);
			adminRepo.save(admin);
			return "/Admin/updateSuccess";
		}
		return "/Admin/notFoundAdmin";
	}

	// create admin account
	public String createAccount(String username, String email, String password, String confirmpassword) {
		if (adminRepo.existsByEmailAndUsernameAndPassword(email, username, password)) {
			return "/Admin/existTeacher";
	}
	Admin admin = new Admin(username, email, password);
	adminRepo.save(admin);
	return "/Admin/adminLogin";
	}
	
//	<==== Student Operations ====>

//	delete student accounts
	public String delStudentsAcc(String rollno) {
		if(stdRepo.existsByRollno(rollno)) {
			for(Student std : stdRepo.findAll()) {
				stdRepo.delete(std);
			}
			return "/Admin/deleteSuccess";
		}
		return "/Admin/notFound";
	}

//	delete assignments
	public String deleteAssignments(String semester, String rollno, String subject) {
		if(assgnRepo.existsBySemester(semester) && assgnRepo.existsByRollno(rollno) && assgnRepo.existsBySubject(subject)){
				
			for(Assignment assgn : assgnRepo.findAll()) {
				assgnRepo.delete(assgn);
			}
			return "/Admin/deleteSuccess";
				
		}
		return "/Admin/notFound";
	}

	
//	<==== Teacher Account Operations ===>

	public String deleteTeacherAcc(String username,String password) {
		User user = null;
		if(userRepo.existsByUsernameAndPassword(username, password)) {
			 user = userRepo.findByUsernameAndPassword(username, password);
				userRepo.delete(user);
				return "/Admin/deleteSuccess";	
		}
		return "/Admin/notFound";
	}

	public String delFile(String rollno) {
		if(assgnRepo.existsByRollno(rollno)) {
			
			for(Assignment assgn : assgnRepo.findAll()) {
				assgnRepo.delete(assgn);;
			}
		return "/Admin/deleteSuccess";
		}
	
	return "/Admin/notFound";
	}
	
}