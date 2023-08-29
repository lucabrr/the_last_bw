package com.epicenergy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epicenergy.entity.Provincia;



@Configuration
public class ProvinciaConfig {
	@Bean
	@Scope("prototype")
	Provincia regione() {
		return new Provincia();
	}
}
