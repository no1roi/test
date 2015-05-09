package com.shuim.Controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shuim.domain.Test;
import com.shuim.service.TestService;

@Controller(value = "mainController")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
	@Resource(name = "testService")
	private TestService testService;

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		
		ArrayList<Test> listTest = testService.listTest();

		model.addAttribute("listTest", listTest);
		
		return "index";
		
	}
	
	@RequestMapping(value="/m", method=RequestMethod.GET)
	public String m(Model model) {
		
		ArrayList<Test> listTest = testService.listTest();

		model.addAttribute("listTest", listTest);
		
		return "mobile";
		
	}
}
