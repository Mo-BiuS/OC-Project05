package com.safetyNet.Alerts.Model.Person;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Persons {

	private final List<Person> persons;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Persons(@JsonProperty("persons") List<Person> persons) {
		this.persons = persons;
	}

	public List<Person> getPersons() {
		return persons;
	}
	
	@Override
	public String toString() {
		return this.getPersons().toString();
	}
}
