package com.epicenergy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epicenergy.entity.Comune;



@Configuration
public class ComuneConfig {
	@Bean
	@Scope("prototype")
	public Comune comune() {
		return new Comune();
	}
}
