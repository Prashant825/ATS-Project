package com.example.ats.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ats.Bean.AssgnBean;
import com.example.ats.Model.User;
import com.example.ats.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	
	
//	teacher service function
	
	public String addNewTeacher(String username, String email, String password, String confirmpassword) {
		if (userRepo.existsByEmailAndPassword(email, password)) {
				return "/Teacher/existTeacher";
		}
		else {
			User user = new User(email, username, password);
			userRepo.save(user);
			return "/Teacher/SuccessRegister";
		}
	}

	public String LoginTeacher(String username, String password) {
		if (userRepo.existsByUsernameAndPassword(username, password)) {
				return "/Teacher/TeacherPanel";
		}
		return "/Teacher/notFoundTeacher";
	}

	public String forgotTeacherPassword(String email, String username, String password, String newpassword) {
		if (userRepo.existsByEmail(email) && userRepo.existsByUsername(username) && newpassword != null && password.equals(newpassword)) {
			User user = null;
			user = userRepo.findByEmailAndPassword(email, password);
			user.setPassword(newpassword);
			userRepo.save(user);
			return "updateSuccess";
		}
		return "notFound";
	}

	public String updateTeacherUsername(String email, String newusername, String password) {
		User user = null;
		if (userRepo.existsByEmailAndPassword(email, password) && newusername != null) {
			user = userRepo.findByEmailAndPassword(email, password);
			user.setUsername(newusername);
			userRepo.save(user);
			return "/Teacher/updateSuccess";
		}
		return "/Teacher/notFoundTeacher";
	}

	public String updateTeacherPassword(String email, String newpassword, String confirmpassword, String password) {
		User user = null;
		if(userRepo.existsByEmailAndPassword(email, password) && newpassword!=null && newpassword.equals(confirmpassword)) {
			user = userRepo.findByEmailAndPassword(email, password);
			user.setPassword(newpassword);
			userRepo.save(user);
			return "/Teacher/updateSuccess";
		}
		return "/Teacher/notFoundTeacher";
	}

	

}
