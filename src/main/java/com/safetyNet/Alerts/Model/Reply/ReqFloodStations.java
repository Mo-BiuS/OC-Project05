package com.safetyNet.Alerts.Model.Reply;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFloodStationAddress;

public class ReqFloodStations {
	public final List<ReqFloodStationAddress> address;
	public ReqFloodStations(Persons concernedPeople, Medicalrecords records, List<String> uniqueAddress) {
		address = new ArrayList<ReqFloodStationAddress>();
		for(int i = 0; i < uniqueAddress.size();i++) {
			address.add(new ReqFloodStationAddress(
					uniqueAddress.get(i),
					concernedPeople.getPersonByAdress(uniqueAddress.get(i)),
					records));
		}
	}
	
}
