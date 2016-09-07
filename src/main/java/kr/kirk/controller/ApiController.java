package kr.kirk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@RequestMapping(value = "/ping")
	public String ping() {
		return "pong";
	}
}
