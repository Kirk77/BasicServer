package kr.kirk.config;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import redis.embedded.RedisServer;

@Configuration
@EnableRedisHttpSession 
public class RedisConfig {

	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Value("${spring.redis.host}")
	private String redisHost;
	
	private static RedisServer redisServer;
	
	 @Bean
     public JedisConnectionFactory connectionFactory() throws IOException, URISyntaxException {
		 
		 redisServer =  RedisServer.builder().port(redisPort).setting("maxheap 1024M").build();
		 redisServer.start();
		 
		 JedisConnectionFactory jcf = new JedisConnectionFactory();
		 jcf.setPort(redisPort);
		 jcf.setHostName(redisHost);
		 
		 return jcf;
     }
	 
	 @PreDestroy
	 public void destroy() throws InterruptedException {
		 redisServer.stop();
	 }
}
