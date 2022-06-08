package com.work.workhub.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("com.work.workhub")
public class ContextConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public MessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource messageSource
			= new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasename("classpath:messages/message");
		
		return messageSource;
	}
	
	
	
}