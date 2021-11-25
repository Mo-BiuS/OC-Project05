package com.safetyNet.Alerts.Model.Reply.Sub;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;

public class ReqChildAlertChild {
	public final String firstName;
	public final String lastName;
	public final long age;
	
	public ReqChildAlertChild(Medicalrecord medicalRecord) {
		this.firstName = medicalRecord.getFirstName();
		this.lastName = medicalRecord.getLastName();
		this.age = (long) (((System.currentTimeMillis()-medicalRecord.getBirthdate().getTime()))/(365.25 * 24 * 60 * 60 * 1000));
	}
}
