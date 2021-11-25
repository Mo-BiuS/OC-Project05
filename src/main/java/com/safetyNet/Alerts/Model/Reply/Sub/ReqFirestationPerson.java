package com.safetyNet.Alerts.Model.Reply.Sub;

import com.safetyNet.Alerts.Model.Person.Person;

public class ReqFirestationPerson {
	public final String firstName;
	public final String lastName;
	public final String address;
	public final String phone;
	
	public ReqFirestationPerson(Person person){
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
	}
}
