package com.safetyNet.Alerts.Model.Reply.Sub;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/** 
 * Sub container to structure an URI
 * Used in : ReqChildAlert
 * @author Mo-Bius
 */
@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqChildAlertAdult {
	public final String firstName;
	public final String lastName;
	
	public ReqChildAlertAdult(Medicalrecord person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
