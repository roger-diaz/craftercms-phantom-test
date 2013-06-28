package org.craftercms.tests.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index() {
		return "home";
	}
	
	@RequestMapping(value="/checkFile", method = RequestMethod.GET)
	public ModelAndView checkFile() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("exists", "true");
		
		return new ModelAndView("checkFile",model);
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam MultipartFile file, WebRequest webRequest, Model model) {
		
		 	String orgFileName = file.getOriginalFilename();
	        String filePath = System.getProperty("upload.path") + orgFileName;
	        ModelMap modelMap = new ModelMap();
	        File dest = new File(filePath);
	        try {
	        	file.transferTo(dest);
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	            modelMap.addAttribute("result", "File uploaded failed: " + orgFileName);
	            return new ModelAndView("upload", modelMap);
	        } catch (IOException e) {
	            e.printStackTrace();
	            modelMap.addAttribute("result", "File uploaded failed: " + orgFileName);
	            return new ModelAndView("upload", modelMap);
	        }


	        modelMap.addAttribute("result", "File uploaded: " + orgFileName);
	        return new ModelAndView("upload", modelMap);
	}
}
