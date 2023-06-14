package com.example.ats.Controller;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.ats.Model.request.FileModel;

@Controller
public class FileUploadController {
	
   @Autowired
   ServletContext context; 
   
   String uploadPath = "src/main/resources/static/uploads/";

   @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
   public ModelAndView fileUploadPage() {
      FileModel file = new FileModel();
      ModelAndView modelAndView = new ModelAndView("/Student/StudentPanel", "command", file);
      return modelAndView;
   }

   @RequestMapping(value="/fileUploadPage", method = RequestMethod.POST)
   public String fileUpload(@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
      if (result.hasErrors()) {
         System.out.println("validation errors");
         return "fileUploadPage";
      } else {            
    	  String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//         MultipartFile multipartFile = file.getFile();
         String fileName = timeStamp +'_'+file.getFile().getOriginalFilename().replaceAll("[^a-zA-Z0-9.]", "");
         FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+fileName));
         System.out.println(fileName);
         model.addAttribute("fileName", fileName);
         return "fileSuccess";
      }
   }
   
   @RequestMapping(value="/download", method=RequestMethod.GET)
   public void downloadPDFResource( HttpServletRequest request, HttpServletResponse response, @Param(value="name") String name) {
	   Path file = Paths.get(uploadPath, name);
       if (Files.exists(file)) 
       {
           response.setContentType("application/pdf");
           response.addHeader("Content-Disposition", "attachment; filename="+file);
           try
           {
               Files.copy(file, response.getOutputStream());
               response.getOutputStream().flush();
           } 
           catch (IOException ex) {
               ex.printStackTrace();
           }
       }
   }
}