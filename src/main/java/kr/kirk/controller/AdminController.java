package kr.kirk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/admin/login")
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "logout", required = false) String logout,
						Model model) {
		String msg = "";
		if (error != null) {
			msg = "invalid username and password.";
		}
		if (logout != null) {
			msg = "logout ok";
		}
		model.addAttribute("loginmsg", msg);
		return "admin/login";
	}
	
	@RequestMapping(value = "/admin/home")
	public String login_ok(Model model) {
		logger.info("admin login successful...");
		model.addAttribute("msg", "admin login successful...");
		return "home";
	}
}
