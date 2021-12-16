package com.safetyNet.Alerts.Model.Reply.Sub;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqChildAlertChild {
	public final String firstName;
	public final String lastName;
	public final long age;
	
	public ReqChildAlertChild(Medicalrecord medicalRecord) {
		this.firstName = medicalRecord.getFirstName();
		this.lastName = medicalRecord.getLastName();
		this.age = (long) (((System.currentTimeMillis()-medicalRecord.getBirthdate().getTime()))/(365.25 * 24 * 60 * 60 * 1000));
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + age;
	}
}
