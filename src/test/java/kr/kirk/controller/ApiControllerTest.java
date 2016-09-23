package kr.kirk.controller;

import java.util.HashMap;

import kr.kirk.auth.AuthRequest;
import kr.kirk.auth.RestAuthEntryPoint;
import kr.kirk.auth.UserServiceImpl;
import kr.kirk.config.RedisConfig;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
@Import({UserServiceImpl.class, RestAuthEntryPoint.class, SecurityConfig.class, RedisConfig.class})
public class ApiControllerTest {

	private static Logger logger = LoggerFactory.getLogger(ApiControllerTest.class);

	@Autowired
	private MockMvc mvc;

	@Value("${management.shell.auth.simple.user.name}")
	private String remoteShellAdminID;
	@Value("${management.shell.auth.simple.user.password}")
	private String remoteShellAdminPassword;
	
	private ObjectMapper om = new ObjectMapper();
	private HashMap<String, Object> authMap;
	
	@Before
	public void login() throws Exception {
		logger.info("api login test ... {}");

		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername(remoteShellAdminID);
		authRequest.setPassword(remoteShellAdminPassword);
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders
					.post("/api/login")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(om.writeValueAsString(authRequest)));
		
		MvcResult mvcReturn = result
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andReturn();
		
		authMap = om.readValue(mvcReturn.getResponse().getContentAsString(),
				new TypeReference<HashMap<String, Object>>() {});
	}

	@Test
	public void ping() throws Exception {
		logger.info("api ping test ... {}", authMap.get("token"));
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders
				.post("/api/ping")
					// BASIC AUTH ( disabled )
				//.header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString( (remoteShellAdminID + ":" + remoteShellAdminPassword).getBytes()))
				.header("x-auth-token", authMap.get("token"))
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("pong"));
	}
}
