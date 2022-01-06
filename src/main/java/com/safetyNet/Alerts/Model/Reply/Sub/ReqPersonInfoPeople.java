package com.safetyNet.Alerts.Model.Reply.Sub;

import java.util.ArrayList;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/** 
 * Sub container to structure an URI
 * Used in : ReqPersonInfo
 * @author Mo-Bius
 */
@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqPersonInfoPeople {
	public final String firstName;
	public final String lastName;
	public final String address;
	public final String mail;
	public final long age;
	public final ArrayList<String> medications;
	public final ArrayList<String> allergies;
	
	public ReqPersonInfoPeople(Person p, Medicalrecord mr) {
		firstName = p.getFirstName();
		lastName = p.getLastName();
		address = p.getAddress();
		mail = p.getEmail();
		age = mr.getAge();
		medications = mr.getMedications();
		allergies = mr.getAllergies();
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + address + " " + age + " " + medications + " " + allergies;
	}
}
