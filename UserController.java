package com.example.ats.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ats.Bean.AssgnBean;
import com.example.ats.Service.AssignmentService;
import com.example.ats.Service.UserService;
@Controller
public class UserController {

	@Autowired
	private UserService userServ;

	@Autowired
	private AssignmentService assgnServ;
	
	@GetMapping("/ats")
	public String getIndex() {
		return "index";
	}

//		<===== Start Teacher Panel =====>

	@GetMapping("/Teacher")
	public String getTeacherLogin() {
		return "/Teacher/teacherLoginPage";
	}
	
	@GetMapping("/RegisterTeacher")
	public String getTeacherregister() {
		return "/Teacher/registerTeacher";
	}

	// admin will create a Teacher
	@GetMapping("/registerTeacher")
	public String addTeacher(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("confirmpassword") String confirmpassword) {
		return userServ.addNewTeacher(username, email, password, confirmpassword);
	}

	// Teacher login page will return Teacher panel
	@GetMapping("/loginTeacher")
	public String getTeacher(@RequestParam("username") String username, @RequestParam("password") String password) {
		return userServ.LoginTeacher(username, password);
	}

//	teacher update username
	@GetMapping("/getTeacherUpdName")
	public String getTeacherName() {
		return "/Teacher/updateTeacherName";
	}

	@GetMapping("/updateTeacherName")
	public String updTeacherUsername(@RequestParam("email") String email, @RequestParam("newusername") String newusername, @RequestParam("password") String password) {
		return userServ.updateTeacherUsername(email, newusername, password);
	}

	// teacher update password
	@GetMapping("/getTeacherUpdPass")
	public String getTeacherPass() {
		return "/Teacher/updateTeacherPass";
	}

	@GetMapping("/updateTeacherPassword")
	public String updTeacherPass(@RequestParam("email") String email, @RequestParam("newpassword") String newpassword, 
			@RequestParam("confirmpassword") String confirmpassword, @RequestParam("password") String password) {
		return userServ.updateTeacherPassword(email, newpassword, confirmpassword, password);
	}

	// forgot teacher password
	
	@GetMapping("/getForgotTeachPassName")
	public String getForgotTeachPassName() {
		return "/Teacher/forgotTeacherUsername";
	}
	
	@GetMapping("/getForgotTeacherPassword")
	public String getForgotTeacherPass(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword) {
		return userServ.forgotTeacherPassword(email, username, password, newpassword);
	}

	@GetMapping("/teach")
	public String getTeachhh() {
		return "/Teacher/TeacherPanel";
	}
	

//	<======= Assignment operations ======>

	@GetMapping("/fetchAssignment")
	public String fetchAssgn() {
		return "/Teacher/fetchAssgn";
	}

	@GetMapping("/fetchAll")
	public String fetchAllAssgn(Model model) {
		List<AssgnBean> assgns = assgnServ.findAllAssgn();
		model.addAttribute("assgns", assgns);
		return "/Teacher/fetchAssgn";
	}
	
	@GetMapping("/fetchOneAssignment")
	public String fetchOneAssignment() {
		return "/Teacher/fetchOne";
	}
// fatch One Assignment by rollno semester...
	
	@GetMapping("/fetchOne")
	public String fetchOne(Model model, @RequestParam("subject") String subject,
			@RequestParam("semester") String semester) {
		List<AssgnBean> list = assgnServ.findAllBySubjectAndSemester(subject, semester);
		model.addAttribute("assgns", list);
		return "/Teacher/fetchOne";
	}
	
	//fatch One Assignment by semester...
	
	@GetMapping("/fetchOneStdAssgn")
	public String showOneStdAssgn(Model model, 	@RequestParam("semester") String semester) {
		List<AssgnBean> list = assgnServ.findAllBySemester(semester);
		model.addAttribute("assgns", list);
		
		return "/Teacher/fetchAssgn";
		
	}

	@GetMapping("/delAssignment")
	public String delAssignment() {
		return "/Teacher/delOneAssignment";
	}

	@GetMapping("/deleteAssgn")
	public String deleteAssgn(@RequestParam("rollno") String rollno, @RequestParam("semester") String semester,
			@RequestParam("subject") String subject, @RequestParam("assignment") String assignment) {
		return assgnServ.delAssgn(rollno, semester, subject, assignment);
	}

}