package com.safetyNet.Alerts.Model;

public class Person {

	private String firstName;
	private String lastName;
	private String adress;
	private String city;
	private String phone;
	private String email;
	private short zip;
	
	public Person(String firstname, String lastName, String adress, String city, short zip, String phone, String email) {
		this.setFirstName(firstname);
		this.setLastName(lastName);
		this.setAdress(adress);
		this.setCity(city);
		this.setZip(zip);
		this.setPhone(phone);
		this.setEmail(email);
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setZip(short zip) {
		this.zip = zip;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAdress() {
		return adress;
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
	public short getZip() {
		return zip;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)return true;
		
		if(o instanceof Person) {
			Person test = (Person)o;
			return (test.getFirstName().equals(this.getFirstName()) &&
					test.getLastName().equals(this.getLastName()) &&
					test.getAdress().equals(this.getAdress()) &&
					test.getCity().equals(this.getCity()) &&
					test.getPhone().equals(this.getPhone()) &&
					test.getEmail().equals(this.getEmail()) &&
					test.getZip() == this.getZip());
		}
		else return false;
	}
}
