package com.safetyNet.Alerts.Model.Reply;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFirePeople;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqFire {
	public final List<ReqFirePeople> people;
	public final List<Integer> stationsNumer;
	public ReqFire(Medicalrecords records, Persons peoples, Firestations stations) {
		stationsNumer = new ArrayList<Integer>();
		stations.getFirestations().forEach(item -> stationsNumer.add(item.getStation()));
		
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
