package com.safetyNet.Alerts.Model.Reply.Sub;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;

public class ChildAlertAdult {
	public final String firstName;
	public final String lastName;
	
	public ChildAlertAdult(Medicalrecord person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
	}
}
