package com.example.ats.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ats.Bean.AssgnBean;
import com.example.ats.Service.AdminService;
import com.example.ats.Service.AssignmentService;
import com.example.ats.Service.StudentService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminServ;
	
	@Autowired
	private AssignmentService assgnServ;
	
	
	// for admin login page
	@GetMapping("/Admin")
	public String getTeacher() {
		return "/Admin/adminLogin";
	}
	
	@GetMapping("/getAdmin")
	public String getAdmin() {
		return "/Admin/AdminPanel";
	}
	
	
	// create admin 
	@GetMapping("/createAcc")
	public String createAcc(@RequestParam("username") String username, @RequestParam("email") String email, 
			@RequestParam("password") String password, @RequestParam("confirmpassword") String confirmpassword) {
		return adminServ.createAccount(username, email, password, confirmpassword);
	}
	
	
//	for admin forgot username and password
	@GetMapping("/getForgotAdminPassName")
	public String getForgotUsername() {
		return "/Admin/forgotAdminPassName";
	}

// forgot admin username
	@GetMapping("/forgotAdminUsername")
	public String forgotAdminUsername(@RequestParam("email") String email, @RequestParam("newusername") String newusername,
			@RequestParam("confirmusername") String confirmusername, @RequestParam("password") String password) {
		return adminServ.forgotAdminUsername(email, newusername, confirmusername, password);
	}

//	forgot admin password
	@GetMapping("/forgotAdminPassword")
	public String forgotAdminPassword(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("newpassword") String newpassword,
			@RequestParam("confirmpassword") String confirmpassword) {
		return adminServ.forgotAdminPassword(email, username, newpassword, confirmpassword);
	}

//	for admin to get updateusername panel
	@GetMapping("/getUpdateUsername")
	public String getUptUname() {
		return "/Admin/updateusername";
	}

//	for admin username update
	@GetMapping("/updateUsername")
	public String updateUsername( @RequestParam("username") String username,
			@RequestParam("newusername") String newusername, @RequestParam("password") String password) {
		return adminServ.updateName( username, newusername, password);
	}

//	for admin to get updatepassword panel
	@GetMapping("/getUpdatePassword")
	public String getUptUpass() {
		return "/Admin/updatepassword";
	}

//	for admin username update
	@GetMapping("/updatePassword")
	public String updatePassword( @RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword) {
		return adminServ.updatePass( username, password, newpassword, confirmpassword);
	}

	// admin login page will return admin panel
	@GetMapping("/loginAdmin")
	public String getAdminPan(@RequestParam("username") String username, @RequestParam("password") String password) {
		return adminServ.LoginAdm(username, password);
	}
	
	
	
//	<===== Teacher Operations ====>
	
//	delete teacher account
	@GetMapping("/getTeacherDeleteByAdmin")
	public String getTeacherDelete() {
		return "/Admin/deleteTeacherAcc";
	}
	
	@GetMapping("/deleteTeacherAccByAdmin")
	public String teacherDelete(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		return adminServ.deleteTeacherAcc(username, password);
	}
//	<==== Teacher Operations End ===>
	
	
//	<=== Assignments Operations ===>
	
	// fetch One Assignment by semester..
	
	@GetMapping("/showOne")
	public String showOne(Model model, @RequestParam("semester") String semester) {
		List<AssgnBean> list = assgnServ.findAllBySemester(semester);
		model.addAttribute("assgns", list);
		return "/Admin/fetchAllAssignments";
	}
	
//	fetch all assignments..
	@GetMapping("/fetchAllAdminAssgn")
	public String fetchAllAssgn(Model model) {
		List<AssgnBean> assgn = assgnServ.findAllAssgn();
		model.addAttribute("assgns", assgn);
		return "/Admin/AdminPanel";
	}
	
	// Show All Assignment by semester..
	
	@GetMapping("/showAllAdminAssgn")
	public String showAllAssgn(Model model) {
		List<AssgnBean> assgn = assgnServ.findAllAssgn();
		model.addAttribute("assgns", assgn);
		return "/Admin/fetchAllAssignments";
	}
	
	
//	delete assignments
	@GetMapping("/getDeleteAssignments")
	public String getDelAssgn() {
		return "/Admin/deleteAssignments";
	}
	
	@GetMapping("/deleteAssignments")
	public String deleteAssignments(@RequestParam("semester") String semester, @RequestParam("rollno") String rollno, 
			@RequestParam("subject") String subject) {
		return adminServ.deleteAssignments(semester, rollno, subject);
	}
//	<==== End Assignments Operations ====>
		
	
//	<===== Student Operations ====>

//	delete students account
	@GetMapping("/getDeleteStudents")
	public String getDelStudents() {
		return "/Admin/deleteStudents";
	}
	
	@GetMapping("/deleteStudentAcc")
	public String deleteStudentAcc(@RequestParam("rollno") String rollno) {
		return adminServ.delStudentsAcc(rollno);
	}
//	<==== end Student Operations ===>
	
	
//	<==== Files Operations ===>
	
//	delete files
	@GetMapping("/getDeleteFilesByAdmin")
	public String getDeleteFilesByAdmin() {
		return "/Admin/deleteFiles";
	}
	
	@GetMapping("/deleteStudentFile")
	public String deleteFile(@RequestParam("rollno") String rollno) {
		return adminServ.delFile(rollno);
	}
		
//	<==== End Files Operations ===>
	
}