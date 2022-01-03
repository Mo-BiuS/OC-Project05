package com.safetyNet.Alerts.Model.Medicalrecord;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Represents a medical record from the data model.
 * @author Mo-Bius
 */
public class Medicalrecord {
	private  String firstName;
	private  String lastName;
	private  Date birthdate;
	private  ArrayList<String> medications;
	private  ArrayList<String> allergies;
	
	//=======================================[Constructor]=======================================
	/**
	 * Class constructor.
	 * @param firstName String
	 * @param lastName String	
	 * @param birthdate Date
	 * @param medications ArrayList<String> 
	 * @param allergies ArrayList<String> 
	 */
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

	//=======================================[getters/setters]=======================================
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
	
	public void setFirstName(String f) {
		firstName = f;
	}
	public void setLastName(String l) {
		lastName = l;
	}
	public void setBirthdate(Date b) {
		if(b != null) birthdate = (Date) b.clone();
	}
	public void setMedications(ArrayList<String> m) {
		medications = m;
	}
	public void setAllergies(ArrayList<String> a) {
		allergies = a;
	}

	//=======================================[Functions]=======================================
	/**
	 * Will return the actual age in year by comparing the birthdate and the computer date.
	 * @return int
	 */
	public int getAge() {
		return (int) (((System.currentTimeMillis()-this.getBirthdate().getTime()))/(365.25 * 24 * 60 * 60 * 1000));
	}
	
	/**
	 * Compare this entity with another of the same class to see if they are the same in the eye of the specifications
	 * @param medicalRecord 
	 * @return boolean
	 */
	public boolean compare(Medicalrecord medicalRecord) {
		return this.getFirstName().equals(medicalRecord.getFirstName()) &&
				this.getLastName().equals(medicalRecord.getLastName());
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
		return (o != null && 
			   (o instanceof Medicalrecord) &&
			   (o.hashCode() == this.hashCode()));
		
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
