package com.safetyNet.Alerts.Model.Reply.Sub;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqFloodStationAddress {
	public final String address;
	public final List<ReqFirePeople> people;
	
	public ReqFloodStationAddress(String adress, Persons peoples, Medicalrecords records) {
		this.address = adress;
		
		people = new ArrayList<ReqFirePeople>();
		for(int i = 0; i < records.getMedicalrecords().size(); i++) {
			for(int j = 0; j < peoples.getPersons().size(); j++) {
				
				Person p = peoples.getPersons().get(j);
				Medicalrecord mr = records.getMedicalrecords().get(i);
				
				if(p.getFirstName().equals(mr.getFirstName()) &&
				   p.getLastName().equals(mr.getLastName())) {
					people.add(new ReqFirePeople(p,mr));
					break;
				}
			}
		}
	}
}
