package kr.kirk.controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class ApiControllerTest {

	private static Logger logger = LoggerFactory.getLogger(ApiControllerTest.class);

	@Autowired
	private MockMvc mvc;


	@Test
	public void login() throws Exception {
		logger.info("login test ... {}", mvc);
	}

	@Test
	public void ping() throws Exception {
		logger.info("ping test ... {}", mvc);
	}
}
