package com.safetyNet.Alerts;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.BusinessService;

@SpringBootApplication
public class AlertsApplication implements CommandLineRunner{

	@Autowired
	private BusinessService bs;
	
	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
