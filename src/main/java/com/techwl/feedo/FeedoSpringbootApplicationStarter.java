package com.techwl.feedo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.techwl.feedo.daos.UserSurveyDAO;
import com.techwl.feedo.handlers.ResponseHandler;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "com.techwl.feedo.*" })
public class FeedoSpringbootApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(FeedoSpringbootApplicationStarter.class, args);
		System.out.println("Feedo application started. Have a great day.");

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000").maxAge(3600).allowedMethods("POST")
						.allowedHeaders("*").allowCredentials(false).exposedHeaders("*");
			}
		};
	}

	@Bean
	public ResponseHandler getResponseHandler() {
		return new ResponseHandler();
	}

	/**
	 * Registering the DAO
	 * 
	 * @return
	 */
	@Bean
	public UserSurveyDAO getUserSurveyDAO() {
		return new UserSurveyDAO();
	}

}
