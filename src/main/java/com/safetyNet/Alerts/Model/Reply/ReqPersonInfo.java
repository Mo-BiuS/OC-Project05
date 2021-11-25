package com.safetyNet.Alerts.Model.Reply;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqPersonInfoPeople;

public class ReqPersonInfo {

	public final List<ReqPersonInfoPeople> peoples;
	
	public ReqPersonInfo(Persons concernedPeople, Medicalrecords records) {
		peoples = new ArrayList<ReqPersonInfoPeople>();
		for(int i = 0; i < records.getMedicalrecords().size(); i++) {
			for(int j = 0; j < concernedPeople.getPersons().size(); j++) {
				
				Person p = concernedPeople.getPersons().get(j);
				Medicalrecord mr = records.getMedicalrecords().get(i);
				
				if(p.getFirstName().equals(mr.getFirstName()) &&
				   p.getLastName().equals(mr.getLastName())) {
					peoples.add(new ReqPersonInfoPeople(p,mr));
					break;
				}
			}
		}
	}

}
