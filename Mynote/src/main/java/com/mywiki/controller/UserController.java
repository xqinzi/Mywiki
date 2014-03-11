package com.mywiki.controller;

import java.util.List;

import org.apache.catalina.util.MD5Encoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mywiki.model.User;
import com.mywiki.service.UserManager;
import com.mywiki.util.mail.Email;
/**
 * 订单的处理
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager um;
	
	@Autowired
	Email email;

	private final Log log = LogFactory.getLog(UserController.class);

	/**
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		Model model = new ExtendedModelMap();
		User u=new User();
		model.addAttribute("user",u);
		
		return new ModelAndView("user/add", model.asMap());
	}
	
	/**
	 * 增加游戏POST请求
	 * @param ptf
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user, Model model) {
		um.save(user);
		return "redirect:/user/list";
	}
	
	/**
	 * 查看商品分类
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		Model model = new ExtendedModelMap();

		List<User> lu=um.getAll();
		model.addAttribute("lu", lu);
		
		return new ModelAndView("user/list", model.asMap());
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public String sendMail() {
		return "/user/sendMail";
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail( @RequestParam(value = "mail", required = true) final String mail,
							@RequestParam(value = "subject", required = true) final String subject,
							@RequestParam(value = "content", required = true) final String content) {
		email.sendMailSimple(subject, content, mail);
		return "redirect:/user/list";
	}
}
