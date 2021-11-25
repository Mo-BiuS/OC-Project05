package com.safetyNet.Alerts.Model.Reply.Sub;

import java.util.ArrayList;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;

public class ReqFirePeople {
	public final String firstName;
	public final String lastName;
	public final String phone;
	public final long birthdate;
	public final ArrayList<String> medications;
	public final ArrayList<String> allergies;
	
	public ReqFirePeople(Person p, Medicalrecord mr) {
		this.firstName = p.getFirstName();
		this.lastName = p.getLastName();
		this.phone = p.getPhone();
		this.birthdate = (long) (((System.currentTimeMillis()-mr.getBirthdate().getTime()))/(365.25 * 24 * 60 * 60 * 1000));
		this.medications = mr.getMedications();
		this.allergies = mr.getAllergies();
	}
}
