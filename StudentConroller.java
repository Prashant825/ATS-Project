package com.example.ats.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ats.Bean.AssgnBean;
import com.example.ats.Model.request.FileModel;
import com.example.ats.Service.AssignmentService;
import com.example.ats.Service.StudentService;

@Controller
public class StudentConroller {

	@Autowired
	private StudentService studServ;
	
	@Autowired
	private AssignmentService assgnServ;
	
	String uploadPath = "src/main/resources/static/uploads/";
	
	@GetMapping("/Student")
	public String getRegisterStudentForm() {
		return "/Student/LoginPage";
	}
	
//to submit assignment from student panel
	@GetMapping("/getSubmitAssgn")
	public String getSubmitAssgn() {
		return "/Student/StudentPanel";
	}
	
	
	@GetMapping("/RegisterForm")
	public String GetRegisForm() {
		return "/Student/registerStudent";
	}
	
	//Student Register..
	@GetMapping("/registerStudent")
	public String addStudent(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		return studServ.addNewStudent(username, password);
	}

	//Student Login..
	@GetMapping("/LoginStudent")
	public String getStudent(@RequestParam("rollno") String rollno, @RequestParam("password") String password) {
		return studServ.loginStd(rollno, password);
	}
	
	@PostMapping("/addAssignment")
	public String addAssignment(@Validated FileModel file, BindingResult result, ModelMap model, @RequestParam("roll") String roll, 
			@RequestParam("session") String session,
			@RequestParam("semester") String semester, @RequestParam("subject") String subject,
			@RequestParam("assignment") String assignment, @RequestParam("teachername") String teachername,
			@RequestParam("date") String date) throws IOException{
		String fileName = "";
		if (result.hasErrors()) {
	         System.out.println("validation errors");
	      } else {            
	    	  String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	         fileName = timeStamp +'_'+file.getFile().getOriginalFilename().replaceAll("[^a-zA-Z0-9.]", "");
	         FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+fileName));
	      }
		
		return assgnServ.addAssgn( roll, session, semester, subject, assignment, teachername, date, fileName);
	}
	
	
//	forgot API's
	@GetMapping("/getForgotStudentPassName")
	public String getForgotNamePass() {
		return "/Student/forgotPage";
	}
	
	@GetMapping("/forgotStdName")
	public String forgotName(@RequestParam("newrollno") String newrollno,
			@RequestParam("confirmrollno") String confirmrollno, @RequestParam("password") String password) {
		return studServ.forgotName(newrollno,confirmrollno, password);
	}
	
	@GetMapping("/forgotStdPass")
	public String forgotPass(@RequestParam("rollno") String rollno,
			@RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword) {
		return studServ.forgotPass(rollno, newpassword, confirmpassword);
	}
	
	
	@GetMapping("/stud")
	public String getStud() {
		
		return "/Student/StudentPanel";
	}
	
//	<===== update student panel ====>
	
	@GetMapping("/getUpdateStdUsername")
	public String  getStdUsernameUpdate() {
		return "/Student/updateStdName";
	}
	
	@GetMapping("/updateStudentName")
	public String updateStudentName(@RequestParam("rollno") String rollno,@RequestParam("username") String username, 
			@RequestParam("newusername") String newusername,@RequestParam("password") String password) {
		return studServ.updateStudentName(rollno,username,newusername, password);
	}
	
	@GetMapping("/getUpdateStdPassword")
	public String getStdPassword() {
		return "/Student/updateStdPass";
	}
	
	@GetMapping("/updateStdPassword")
	public String updateStudentpass(@RequestParam("rollno") String rollno, @RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword,@RequestParam("confirmpassword") String confirmpassword) {
		return studServ.updateStudentPass(rollno, password, newpassword, confirmpassword);
	}
	
	
//	update student roll no
	@GetMapping("/getUpdateStdRollno")
	public String getUpdateStdRollno() {
		return "/Student/updateStdRollno";
	}
	
	@GetMapping("/updateStdRollno")
	public String updateStdRollno(@RequestParam("rollno") String rollno, @RequestParam("newrollno") String newrollno,
			 @RequestParam("password") String password) {
		return studServ.updateStdRollno(rollno, newrollno, password);
	}
	
	
	
//	assignments operations
	
	@GetMapping("/getFetchOneStdAssgn")
	public String getOneStdAssgn() {
		return "/Student/fetchOneStdAssgn";
	}
	// fatch all Assignment by rollno and semester...
	
	@GetMapping("/showOneStdAssgn")
	public String showOneStdAssgn(Model model, @RequestParam("rollno") String rollno ,@RequestParam("semester") String semester) {
		List<AssgnBean> list = studServ.findAllByRollnoAndSemester(rollno,semester);
		model.addAttribute("assgns", list);
		
		return "/Student/fetchAllStdAssgn";
		
	}
	
	// fetch all assgn by Roll no
	@GetMapping("/getFetchAllStdAssgn")
	public String getFetchAllStdAssgn() {
		return "/Student/fetchAllStdAssgn";
	}
	
	@GetMapping("/fetchAllStdAssgn")
	public String fetchAllAssgnByStudent(Model model, @RequestParam("rollno") String rollno,@RequestParam("subject") String subject) {
		List<AssgnBean> list = studServ.findAllByRollnoAndSubject(rollno,subject);
		model.addAttribute("assgns", list);
		return "/Student/fetchOneStdAssgn";
	}
	
}
