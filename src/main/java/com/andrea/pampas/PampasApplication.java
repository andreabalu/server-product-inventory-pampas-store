package com.andrea.pampas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PampasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PampasApplication.class, args);
	}
}
