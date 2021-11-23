package com.safetyNet.Alerts.Service;

import org.springframework.stereotype.Component;

import com.safetyNet.Alerts.Model.Person.Person;

@Component
public class BusinessService {
	public Person getHelloWorld() {
		return new Person(null, null, null, null, (short) 1, null, null);
	}
}
