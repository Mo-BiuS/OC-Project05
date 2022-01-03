package com.safetyNet.Alerts.Model.Person;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Represents a person from the data model.
 * @author Mo-Bius
 */
public class Person {

	private  String firstName;
	private  String lastName;
	private  String address;
	private  String city;
	private  String phone;
	private  String email;
	private  int zip;
	
	//=======================================[Constructor]=======================================
	/**
	 * Class constructor.
	 * @param firstName String
	 * @param lastName String	
	 * @param address String
	 * @param city String
	 * @param zip int
	 * @param phone String	
	 * @param email String
	 */
	@Autowired
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Person(@JsonProperty("firstName") String firstName, 
				  @JsonProperty("lastName") String lastName, 
				  @JsonProperty("address") String address, 
				  @JsonProperty("city") String city, 
				  @JsonProperty("zip") int zip, 
				  @JsonProperty("phone") String phone, 
				  @JsonProperty("email") String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	//=======================================[getters/setters]=======================================
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public int getZip() {
		return zip;
	}
	
	public void setFirstName(String f) {
		firstName=f;
	}
	public void setLastName(String l) {
		lastName=l;
	}
	public void setAddress(String a) {
		address=a;
	}
	public void setCity(String c) {
		city=c;
	}
	public void setPhone(String p) {
		phone=p;
	}
	public void setEmail(String e) {
		email=e;
	}
	public void setZip(int z) {
		zip=z;
	}
	
	//=======================================[Functions]=======================================
	/**
	 * Compare this entity with another of the same class to see if they are the same in the eye of the specifications
	 * @param person 
	 * @return boolean
	 */
	public boolean compare(Person person) {
		return this.getFirstName().equals(person.getFirstName()) &&
				this.getLastName().equals(person.getLastName());
	}
	@Override
	public int hashCode() {
		int value = 1;
		
		String firstName = this.getFirstName();
		if(firstName != null)value*=firstName.hashCode();
		
		String lastName = this.getLastName();
		if(lastName != null)value*=lastName.hashCode();
		
		String address = this.getAddress();
		if(address != null)value*=address.hashCode();
		
		String city = this.getCity();
		if(city != null)value*=city.hashCode();
		
		String phone = this.getPhone();
		if(phone != null)value*=phone.hashCode();
		
		String email = this.getEmail();
		if(email != null)value*=email.hashCode();
		
		int zip = this.getZip();
		if(zip != 0)value*=zip;
		
		return value;
	}
	@Override
	public boolean equals(Object o) {
		return (o != null && 
			   (o instanceof Person) &&
			   (o.hashCode() == this.hashCode()));
		
	}
	@Override
	public String toString() {
		return  "{ \"firstName\" : "+this.getFirstName()+", "+
				"\"lastName\" :"+this.getLastName()+", "+
				"\"address\" :"+this.getAddress()+", "+
				"\"city\" :"+this.getCity()+", "+
				"\"zip\" :"+this.getZip()+", "+
				"\"phone\" :"+this.getPhone()+", "+
				"\"email\" :"+this.getEmail()+"}";
	}
}
