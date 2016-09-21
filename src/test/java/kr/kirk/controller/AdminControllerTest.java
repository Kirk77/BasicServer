package kr.kirk.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

	private static Logger logger = LoggerFactory.getLogger(AdminControllerTest.class);
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void login() throws Exception {
		logger.info("admin login test ... {}", mvc);
	}
}
