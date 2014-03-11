package com.mywiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mywiki.model.User;

@Controller
public class LoginController {
	
	@Autowired(required=false)
	@Qualifier("beanValidator")
	Validator validator;    		//这个需要在applicationContext.xml中配置bean注射进来
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String get(ModelMap modelMap){
		User user=new User();
		modelMap.addAttribute(user);
		return "login";
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@ModelAttribute("user")User user,BindingResult errors,ModelMap modelMap){
		
		validator.validate(user, errors);//这个是对前台提交的数据进行验证
		if(errors.hasErrors()){
			modelMap.addAttribute(user);
			return "login";
		}
		return "success";
		
	}
}
