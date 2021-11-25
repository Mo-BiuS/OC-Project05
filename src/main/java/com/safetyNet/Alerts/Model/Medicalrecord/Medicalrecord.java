package com.safetyNet.Alerts.Model.Medicalrecord;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Medicalrecord {
	private final String firstName;
	private final String lastName;
	private final Date birthdate;
	private final ArrayList<String> medications;
	private final ArrayList<String> allergies;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Medicalrecord(@JsonProperty("firstName") String firstName, 
						 @JsonProperty("lastName") String lastName, 
						 @JsonProperty("birthdate") Date birthdate, 
						 @JsonProperty("medications") ArrayList<String> medications, 
						 @JsonProperty("allergies") ArrayList<String> allergies) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.medications=medications;
		this.allergies=allergies;
		
		if(birthdate == null) this.birthdate = null;
		else this.birthdate = (Date) birthdate.clone();
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Date getBirthdate() {
		if(birthdate == null) return null;
		else return (Date) birthdate.clone();
	}
	public ArrayList<String> getMedications() {
		return medications;
	}
	public ArrayList<String> getAllergies() {
		return allergies;
	}
	public long getAge() {
		return (long) (((System.currentTimeMillis()-this.getBirthdate().getTime()))/(365.25 * 24 * 60 * 60 * 1000));
	}
	
	@Override
	public int hashCode() {
		
		int value = 1;
		
		String firstName = this.getFirstName();
		if(firstName != null)value*=firstName.hashCode();
		
		String lastName = this.getLastName();
		if(lastName != null)value*=lastName.hashCode();
		
		Date birthdate = this.getBirthdate();
		if(birthdate != null)value*=birthdate.hashCode();
		
		ArrayList<String> medications = this.getMedications();
		if(medications != null)value*=medications.hashCode();
		
		ArrayList<String> allergies = this.getAllergies();
		if(allergies != null)value*=allergies.hashCode();

		return value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)return true;
		
		if(o instanceof Medicalrecord) {
			Medicalrecord test = (Medicalrecord)o;
			return (test.getFirstName().equals(this.getFirstName()) &&
					test.getLastName().equals(this.getLastName()) &&
					test.getBirthdate().equals(this.getBirthdate()) &&
					test.getMedications().equals(this.getMedications()) &&
					test.getAllergies().equals(this.getAllergies()));
		}
		else return false;
	}
	
	@Override
	public String toString() {
		return 	"{ \"firstName\" : "+this.getFirstName()+
				", \"lastName\" : "+this.getLastName()+
				", \"birthdate\" : "+this.getBirthdate()+
				", \"medications\" : "+this.getMedications()+
				", \"allergies\" : "+this.getAllergies()+"}";
	}
}
