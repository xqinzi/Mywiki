package com.yxpai.webapp.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HomeController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Model model = new ExtendedModelMap();
		
		System.out.println(request.getAttribute("credentials"));
		System.out.println(request.getRemoteUser());
		
		return new ModelAndView("home", model.asMap());
	}
}
