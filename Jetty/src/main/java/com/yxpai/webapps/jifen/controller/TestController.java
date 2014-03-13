package com.yxpai.webapps.jifen.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yxpai.webapps.jifen.model.User;
import com.yxpai.webapps.jifen.service.UserService;

/**
 * 测试Controller
 */
@Controller("test")
@RequestMapping("/test")
public class TestController{
	
	@Autowired
	UserService us;
	
	@RequestMapping(value = "/testjson",method = RequestMethod.GET)
	public ModelAndView testjson() throws Exception {
		ModelAndView model = new ModelAndView("jsonView");
		model.addObject("code", 200);
		model.addObject("trade_no", "我的测试");
		return model;
	}
	
	@RequestMapping(value = "/testhtml",method = RequestMethod.GET)
	public ModelAndView testhtml() throws Exception {
		Model model = new ExtendedModelMap();
		model.addAttribute("code", 200);
		model.addAttribute("trade_no", "我的测试");
		return new ModelAndView("html", model.asMap());
	}
	
	@RequestMapping(value = "/testxml",method = RequestMethod.GET)
	public ModelAndView testxml() throws Exception {
		ModelAndView model = new ModelAndView("xStreamMarshallingView");
		model.addObject("code", 200);
		model.addObject("trade_no", "我的测试");
		return model;
	}
	
	@RequestMapping(value = "/testinsert",method = RequestMethod.GET)
	public ModelAndView testinsert() throws Exception {
		ModelAndView model = new ModelAndView("jsonView");
		User user = new User();
		try{
			user.setUuid(UUID.randomUUID().toString());
			user.setEmail("mywiki95@gmail.com");
			user.setFirstName("zhang");
			user.setLastName("zongyao");
			user.setNickname("mywiki");
			user.setPassword("redhat");
			user.setPhoneNumber("18321665575");
			user = us.save(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addObject("code", 200);
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value = "/testget",method = RequestMethod.GET)
	public ModelAndView testget(@RequestParam(value = "uuid", required = false) String uuid) throws Exception {
		ModelAndView model = new ModelAndView("xStreamMarshallingView");
		uuid = "c84b52f5-2cfa-41b1-a9a1-8a79314fbfda";
		User user = us.get(uuid);
		model.addObject("code", 200);
		model.addObject("xxx", 200);
		model.addObject("user", user);
		return model;
	}
}
