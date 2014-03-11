package com.mywiki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mywiki.model.User;
import com.mywiki.service.UserManager;

@Controller
public class DisplaytagController {
	
	@Autowired
	private UserManager um;
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		Model model = new ExtendedModelMap();
		
		List<User> userlist=um.getAll();
		
		model.addAttribute("userlist", userlist);

		return new ModelAndView("displaytag", model.asMap()); 
	}
}
