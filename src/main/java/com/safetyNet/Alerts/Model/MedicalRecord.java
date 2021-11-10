package com.safetyNet.Alerts.Model;

import java.util.ArrayList;
import java.util.Date;

import com.safetyNet.Alerts.Model.Sub.Medication;

public class MedicalRecord {
	private String firstName;
	private String lastName;
	private Date birthdate;
	private ArrayList<Medication> medications;
	private ArrayList<String> allergies;
	
	public MedicalRecord(String firstName, String lastName, Date birthdate, ArrayList<Medication> medications, ArrayList<String> allergies) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthdate(birthdate);
		this.setMedications(medications);
		this.setAllergies(allergies);
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public void setMedications(ArrayList<Medication> medications) {
		this.medications = medications;
	}
	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public ArrayList<Medication> getMedications() {
		return medications;
	}
	public ArrayList<String> getAllergies() {
		return allergies;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)return true;
		
		if(o instanceof MedicalRecord) {
			MedicalRecord test = (MedicalRecord)o;
			return (test.getFirstName().equals(this.getFirstName()) &&
					test.getLastName().equals(this.getLastName()) &&
					test.getBirthdate().equals(this.getBirthdate()) &&
					test.getMedications().equals(this.getMedications()) &&
					test.getAllergies().equals(this.getAllergies()));
		}
		else return false;
	}
}
