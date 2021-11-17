package com.safetyNet.Alerts.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;


public final class DataHandler {
	private final Persons persons;
	private final Firestations firestations;
	private final Medicalrecords medicalrecords;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public DataHandler(@JsonProperty("persons") List<Person> persons, 
					   @JsonProperty("firestations")List<Firestation> firestations, 
					   @JsonProperty("medicalrecords") List<Medicalrecord> medicalrecords) {
		this.persons = new Persons(persons);
		this.firestations = new Firestations(firestations);
		this.medicalrecords = new Medicalrecords(medicalrecords);
	}

	@Override
	public String toString() {
		return  this.persons+"\n"+
				this.firestations+"\n"+
				this.medicalrecords;
	}
}
