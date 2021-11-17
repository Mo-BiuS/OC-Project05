package com.safetyNet.Alerts.Model.Person;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

	private final String firstName;
	private final String lastName;
	private final String address;
	private final String city;
	private final String phone;
	private final String email;
	private final int zip;
	
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
	
	@Override
	public int hashCode() {
		String firstName = this.getFirstName();
		if(firstName == null)firstName = "";
		
		String lastName = this.getLastName();
		if(lastName == null)lastName = "";
		
		String address = this.getAddress();
		if(address == null)address = "";
		
		String city = this.getCity();
		if(city == null)city = "";
		
		String phone = this.getPhone();
		if(phone == null)phone = "";
		
		String email = this.getEmail();
		if(email == null)email = "";
		
		return firstName.hashCode()*
				lastName.hashCode()*
				address.hashCode()*
				city.hashCode()*
				phone.hashCode()*
				email.hashCode()*
			   getZip();
	}
	@Override
	public boolean equals(Object o) {
		if(o == this)return true;
		
		if(o instanceof Person) {
			Person test = (Person)o;
			return (test.getFirstName().equals(this.getFirstName()) &&
					test.getLastName().equals(this.getLastName()) &&
					test.getAddress().equals(this.getAddress()) &&
					test.getCity().equals(this.getCity()) &&
					test.getPhone().equals(this.getPhone()) &&
					test.getEmail().equals(this.getEmail()) &&
					test.getZip() == this.getZip());
		}
		else return false;
	}
	@Override
	public String toString() {
		return  "firstName : "+this.getFirstName()+", "+
				"lastName :"+this.getLastName()+", "+
				"address :"+this.getAddress()+", "+
				"city :"+this.getCity()+", "+
				"zip :"+this.getZip()+", "+
				"phone :"+this.getPhone()+", "+
				"email :"+this.getEmail();
	}
}
