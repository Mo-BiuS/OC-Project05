package com.safetyNet.Alerts.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Model.Reply.ReqChildAlert;
import com.safetyNet.Alerts.Model.Reply.ReqFire;
import com.safetyNet.Alerts.Model.Reply.ReqFirestation;
import com.safetyNet.Alerts.Model.Reply.ReqFloodStations;
import com.safetyNet.Alerts.Model.Reply.ReqPersonInfo;

public class RequestService {

	private static final Logger logger = LogManager.getLogger("RequestService");
	
	private static final Date eighteenYear;
	static {
		eighteenYear = new Date();
		eighteenYear.setTime( (long) (System.currentTimeMillis() - ( 18 * 365.25 * 24 * 60 * 60 * 1000)) ); //18 years ago
	}
	
	public static ReqFirestation firestation(int stationNumber) { 

		logger.info("Treating : /firestation?stationNumber="+stationNumber);
		
		Firestations concernedStation = DataHandler.getData().getFirestations().getByStation(stationNumber);
		Persons concernedPeople = new Persons(new ArrayList<Person>());
		for(int i = 0; i < concernedStation.getFirestations().size(); i++) {
			concernedPeople = concernedPeople.concat(DataHandler.getData().getPersons().getPersonByAdress(concernedStation.getFirestations().get(i).getAddress()));
		}
		
		Medicalrecords peopleAges = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < concernedPeople.getPersons().size(); i++) {
			peopleAges = peopleAges.concat(DataHandler.getData().getMedicalrecords()
												.getMedicalrecordByFirstName(concernedPeople.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(concernedPeople.getPersons().get(i).getLastName()));
		}
		
		int adultCount  = peopleAges.getMedicalrecordByBirthdaySuperiorTo(eighteenYear).getMedicalrecords().size();
		int childCount = peopleAges.getMedicalrecords().size()-adultCount;
		
	    return new ReqFirestation(concernedPeople,adultCount, childCount);
	}
	
	public static ReqChildAlert childAlert(String address) { 
		

		logger.info("Treating : /childAlert?address="+address);
		
		Persons peopleList = DataHandler.getData().getPersons().getPersonByAdress(address);
		Medicalrecords childList = new Medicalrecords(new ArrayList<Medicalrecord>());
		Medicalrecords adultList = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < peopleList.getPersons().size(); i++) {
			Medicalrecords peoplesSubList = DataHandler.getData().getMedicalrecords()
												.getMedicalrecordByFirstName(peopleList.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(peopleList.getPersons().get(i).getLastName());
			

			childList = childList.concat(peoplesSubList.getMedicalrecordByBirthdayInferiorTo(eighteenYear));
			adultList = adultList.concat(peoplesSubList.getMedicalrecordByBirthdaySuperiorTo(eighteenYear));
			adultList = adultList.concat(peoplesSubList.getMedicalrecordByBirthdayEqualTo(eighteenYear));
		}

		return new ReqChildAlert(childList,adultList);
	}
	
	public static List<String> phoneAlert( int stationNumber) { 
		
		logger.info("Treating : /phoneAlert?stationNumber="+stationNumber);
		
		ArrayList<String> reply = new ArrayList<String>();
		
		DataHandler.getData().getFirestations().getByStation(stationNumber)
			.getFirestations().forEach(
				firestation -> DataHandler.getData().getPersons().getPersonByAdress(firestation.getAddress()).getPersons().forEach(
						person -> reply.add(person.getPhone())));
		
		return reply;
	}
	
	public static ReqFire fire(String address) { 
		
		logger.info("Treating : /fire?address="+address);
		
		Firestations stations = DataHandler.getData().getFirestations().getByAdress(address);
		Persons peoples =  DataHandler.getData().getPersons().getPersonByAdress(address);
		Medicalrecords records = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < peoples.getPersons().size(); i++)
			records = records.concat(DataHandler.getData().getMedicalrecords()
												.getMedicalrecordByFirstName(peoples.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(peoples.getPersons().get(i).getLastName()));
		
		return new ReqFire(records, peoples, stations);
	}
	
	public static ReqFloodStations reqFloodStations(List<Integer> stations) { 
		
		logger.info("Treating : /flood/stations?stations="+stations);
		
		Firestations concernedStation = new Firestations(new ArrayList<Firestation>());
		for(int i = 0; i < stations.size(); i++)
			concernedStation = concernedStation.concat(DataHandler.getData().getFirestations().getByStation(stations.get(i)));
		Persons concernedPeople = new Persons(new ArrayList<Person>());
		for(int i = 0; i < concernedStation.getFirestations().size(); i++)
			concernedPeople = concernedPeople.concat(DataHandler.getData().getPersons().getPersonByAdress(concernedStation.getFirestations().get(i).getAddress()));
		Medicalrecords records = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < concernedPeople.getPersons().size(); i++)
			records = records.concat(DataHandler.getData().getMedicalrecords()
												.getMedicalrecordByFirstName(concernedPeople.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(concernedPeople.getPersons().get(i).getLastName()));
		
		HashSet<String> uniqueAddress = new HashSet<String>();
		concernedPeople.getPersons().forEach(item -> uniqueAddress.add(item.getAddress()));
		
		return new ReqFloodStations(concernedPeople, records, new ArrayList<String>(uniqueAddress));
	}
	
	public static ReqPersonInfo personInfo(String firstName, String lastName) {
		
		logger.info("Treating : /personInfo?firstName="+firstName+"&lastName="+lastName);
		
		Persons concernedPeople = DataHandler.getData().getPersons().getPersonByLastName(lastName);
		Medicalrecords records = DataHandler.getData().getMedicalrecords().getMedicalrecordByLastName(lastName);
		
		if(firstName != null) {
			concernedPeople = concernedPeople.getPersonByFirstName(firstName);
			records = records.getMedicalrecordByFirstName(firstName);
		}
		
		return new ReqPersonInfo(concernedPeople, records);
	}
	
	public static List<String> communityEmail(String city) {
		
		logger.info("Treating : /communityEmail?city="+city);
		
		Persons concernedPeople = DataHandler.getData().getPersons().getPersonByCity(city);

		HashSet<String> reply = new HashSet<String>();
		concernedPeople.getPersons().forEach(item -> reply.add(item.getEmail()));
		
		return new ArrayList<String>(reply);
	}
}
