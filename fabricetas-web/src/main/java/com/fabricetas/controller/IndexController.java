package com.fabricetas.controller;


import com.fabricetas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView handleRequest() throws Exception {
		ModelAndView model = new ModelAndView("UserList");
		model.addObject("userList", userService.findAll());
		return model;
	}

}
