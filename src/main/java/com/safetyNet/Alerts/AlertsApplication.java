package com.safetyNet.Alerts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class containing the main. This bad boy lunch the whole app in one single line! ;D
 * @author Mo-Bius
 *
 */
@SpringBootApplication
public class AlertsApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
