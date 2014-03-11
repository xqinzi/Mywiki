package com.mywiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping("/helloworld")
public class TestController {

	
	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("helloworld");
		return mv;
	}
}
