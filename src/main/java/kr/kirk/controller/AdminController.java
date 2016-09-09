package kr.kirk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("name", "kirk");
		return "home";
	}
	
	@RequestMapping(value = "/admin/login")
	public String login() {
		logger.info("admin login...");
		return "admin/login";
	}
	
	@RequestMapping(value = "/admin/login_ok")
	public String login_ok() {
		logger.info("admin login successful...");
		return "home";
	}
}
