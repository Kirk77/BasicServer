package kr.kirk.controller;

import kr.kirk.auth.RestAuthEntryPoint;
import kr.kirk.auth.UserServiceImpl;
import kr.kirk.config.SecurityConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@Import({UserServiceImpl.class, RestAuthEntryPoint.class, SecurityConfig.class})
public class AdminControllerTest {

	private static Logger logger = LoggerFactory.getLogger(AdminControllerTest.class);
	
	@Autowired
	private MockMvc mvc;
	
	@Value("${management.shell.auth.simple.user.name}")
	private String remoteShellAdminID;
	@Value("${management.shell.auth.simple.user.password}")
	private String remoteShellAdminPassword;
	
	@Before
	public void login() throws Exception {
		logger.info("admin login test ...");

	}
	
	@Test
	public void someTest() {
		
	}
}
