package kr.kirk.controller;

import kr.kirk.auth.AuthRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ApiControllerTest {

	private static Logger logger = LoggerFactory.getLogger(ApiControllerTest.class);

	@Autowired
	private MockMvc mvc;

	@Value("${management.shell.auth.simple.user.name}")
	private String remoteShellAdminID;
	@Value("${management.shell.auth.simple.user.password}")
	private String remoteShellAdminPassword;
	
	private ObjectMapper om = new ObjectMapper();
	
	@Test
	public void login() throws Exception {
		logger.info("api login test ... {}", mvc);

		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername(remoteShellAdminID);
		authRequest.setPassword(remoteShellAdminPassword);
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders
					.post("/api/login")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(om.writeValueAsString(authRequest)));
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void ping() throws Exception {
		logger.info("api ping test ... {}", mvc);
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders
				.post("/api/ping")
				.header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString( (remoteShellAdminID + ":" + remoteShellAdminPassword).getBytes()))
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
