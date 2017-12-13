package com.maxdavis.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.maxdavis.cursomc.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dBService;

	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dBService.instantiateTestDataBase();
		return true;
	}
}
