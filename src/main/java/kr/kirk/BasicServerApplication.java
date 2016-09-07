package kr.kirk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class BasicServerApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(BasicServerApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}
}
