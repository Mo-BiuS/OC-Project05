package com.safetyNet.Alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetyNet.Alerts.Service.BusinessService;

@SpringBootTest
class AlertsApplicationTests {

	@Autowired
	BusinessService bs;
	
	@Test
	void contextLoads() {
	}
}
