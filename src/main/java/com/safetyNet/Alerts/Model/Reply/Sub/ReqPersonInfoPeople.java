package com.safetyNet.Alerts.Model.Reply.Sub;

import java.util.ArrayList;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;

public class ReqPersonInfoPeople {
	public final String firstName;
	public final String lastName;
	public final String address;
	public final long age;
	public final ArrayList<String> medications;
	public final ArrayList<String> allergies;
	
	public ReqPersonInfoPeople(Person p, Medicalrecord mr) {
		firstName = p.getFirstName();
		lastName = p.getLastName();
		address = p.getAddress();
		age = mr.getAge();
		medications = mr.getMedications();
		allergies = mr.getAllergies();
	}

}
