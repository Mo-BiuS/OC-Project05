package com.safetyNet.Alerts.Model.Reply;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFloodStationAddress;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/** 
 * Structured answer to the /flood/stations uri
 * @author Mo-Bius
 */
@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqFloodStations {
	public final List<ReqFloodStationAddress> address;
	
	/**
	 * The data send should be the concerned data, the sorting of said data is handled by the constructor
	 * @param concernedPeople
	 * @param records
	 * @param uniqueAddress
	 */
	public ReqFloodStations(Persons concernedPeople, Medicalrecords records, List<String> uniqueAddress) {
		address = new ArrayList<ReqFloodStationAddress>();
		for(int i = 0; i < uniqueAddress.size();i++) {
			address.add(new ReqFloodStationAddress(
					uniqueAddress.get(i),
					concernedPeople.getPersonByAdress(uniqueAddress.get(i)),
					records));
		}
	}
	
	@Override
	public String toString() {
		String s = "\nAddress :\n";
		for(ReqFloodStationAddress i : address)s+=(i+"\n");
		return s;
	}
}
