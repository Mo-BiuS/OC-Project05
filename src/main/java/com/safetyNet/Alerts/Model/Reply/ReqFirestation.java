package com.safetyNet.Alerts.Model.Reply;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFirestationPerson;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqFirestation {
	public final List<ReqFirestationPerson> persons;
	public final int adultCount;
	public final int childCount;
	
	public ReqFirestation(Persons persons, int adultCount, int childCount) {
		this.persons = new ArrayList<ReqFirestationPerson>();
		persons.getPersons().forEach(item -> this.persons.add(new ReqFirestationPerson(item)));
		this.adultCount = adultCount;
		this.childCount = childCount;
	}
}
