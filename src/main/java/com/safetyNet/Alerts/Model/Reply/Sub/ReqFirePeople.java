package com.safetyNet.Alerts.Model.Reply.Sub;

import java.util.ArrayList;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/** 
 * Sub container to structure an URI
 * Used in : ReqFire
 * @author Mo-Bius
 */
@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqFirePeople {
	public final String firstName;
	public final String lastName;
	public final String phone;
	public final long birthdate;
	public final ArrayList<String> medications;
	public final ArrayList<String> allergies;
	
	public ReqFirePeople(Person p, Medicalrecord mr) {
		this.firstName = p.getFirstName();
		this.lastName = p.getLastName();
		this.phone = p.getPhone();
		this.birthdate = mr.getAge();
		this.medications = mr.getMedications();
		this.allergies = mr.getAllergies();
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + phone + " " + birthdate + " " + medications + " " + allergies;
	}
}
