package com.epicenergy.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.epicenergy.service.ProvinciaAndComuneService;

@Component
public class RunnerTest implements CommandLineRunner {
	@Autowired
	ProvinciaAndComuneService rs;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("running...");

		rs.salvaProvinceNelDB();
		rs.salvaComuniNelDB();
	}

}
