package com.safetyNet.Alerts.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.safetyNet.Alerts.Service.DataHandler;

@RestController
public class AppController {
	private static Date eighteenYear;
	static {
		eighteenYear = new Date();
		eighteenYear.setTime( (long) (System.currentTimeMillis() - ( 18 * 365.25 * 24 * 60 * 60 * 1000)) ); //18 years ago
	}
	
	@GetMapping("/firestation")
	@ResponseBody
	public ReqFirestation firestation(@RequestParam(required = true) int stationNumber) { 
		Firestations concernedStation = DataHandler.DATA.getFirestations().getByStation(stationNumber);
		Persons concernedPeople = new Persons(new ArrayList<Person>());
		for(int i = 0; i < concernedStation.getFirestations().size(); i++) {
			concernedPeople = concernedPeople.concat(DataHandler.DATA.getPersons().getPersonByAdress(concernedStation.getFirestations().get(i).getAddress()));
		}
		
		Medicalrecords peopleAges = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < concernedPeople.getPersons().size(); i++) {
			peopleAges = peopleAges.concat(DataHandler.DATA.getMedicalrecords()
												.getMedicalrecordByFirstName(concernedPeople.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(concernedPeople.getPersons().get(i).getLastName()));
		}
		
		int childCount = peopleAges.getMedicalrecordByBirthdaySuperiorTo(eighteenYear).getMedicalrecords().size();
		int adultCount = peopleAges.getMedicalrecords().size()-childCount;
		
	    return new ReqFirestation(concernedPeople,adultCount, childCount);
	}
	
	@GetMapping("/childAlert")
	@ResponseBody
	public ReqChildAlert childAlert(@RequestParam(required = true) String address) { 
		
		Persons peopleList = DataHandler.DATA.getPersons().getPersonByAdress(address);
		Medicalrecords childList = new Medicalrecords(new ArrayList<Medicalrecord>());
		Medicalrecords adultList = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < peopleList.getPersons().size(); i++) {
			Medicalrecords peoplesSubList = DataHandler.DATA.getMedicalrecords()
												.getMedicalrecordByFirstName(peopleList.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(peopleList.getPersons().get(i).getLastName());
			

			childList = childList.concat(peoplesSubList.getMedicalrecordByBirthdayInferiorTo(eighteenYear));
			adultList = adultList.concat(peoplesSubList.getMedicalrecordByBirthdaySuperiorTo(eighteenYear));
			adultList = adultList.concat(peoplesSubList.getMedicalrecordByBirthdayEqualTo(eighteenYear));
		}

		return new ReqChildAlert(childList,adultList);
	}
	
	@GetMapping("/phoneAlert")
	@ResponseBody
	public List<String> phoneAlert(@RequestParam(required = true) int stationNumber) { 
		ArrayList<String> reply = new ArrayList<String>();
		
		DataHandler.DATA.getFirestations().getByStation(stationNumber)
			.getFirestations().forEach(
				firestation -> DataHandler.DATA.getPersons().getPersonByAdress(firestation.getAddress()).getPersons().forEach(
						person -> reply.add(person.getPhone())));
		
		return reply;
	}
	
	@GetMapping("/fire")
	@ResponseBody
	public ReqFire fire(@RequestParam(required = true) String address) { 
		Firestations stations = DataHandler.DATA.getFirestations().getByAdress(address);
		Persons peoples =  DataHandler.DATA.getPersons().getPersonByAdress(address);
		Medicalrecords records = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < peoples.getPersons().size(); i++)
			records = records.concat(DataHandler.DATA.getMedicalrecords()
												.getMedicalrecordByFirstName(peoples.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(peoples.getPersons().get(i).getLastName()));
		
		return new ReqFire(records, peoples, stations);
	}
	
	@GetMapping("/flood/stations")
	@ResponseBody
	public ReqFloodStations ReqFloodStations(@RequestParam(required = true) List<Integer> stations) { 
		Firestations concernedStation = new Firestations(new ArrayList<Firestation>());
		for(int i = 0; i < stations.size(); i++)
			concernedStation = concernedStation.concat(DataHandler.DATA.getFirestations().getByStation(stations.get(i)));
		Persons concernedPeople = new Persons(new ArrayList<Person>());
		for(int i = 0; i < concernedStation.getFirestations().size(); i++)
			concernedPeople = concernedPeople.concat(DataHandler.DATA.getPersons().getPersonByAdress(concernedStation.getFirestations().get(i).getAddress()));
		Medicalrecords records = new Medicalrecords(new ArrayList<Medicalrecord>());
		for(int i = 0; i < concernedPeople.getPersons().size(); i++)
			records = records.concat(DataHandler.DATA.getMedicalrecords()
												.getMedicalrecordByFirstName(concernedPeople.getPersons().get(i).getFirstName())
												.getMedicalrecordByLastName(concernedPeople.getPersons().get(i).getLastName()));
		
		HashSet<String> uniqueAddress = new HashSet<String>();
		concernedPeople.getPersons().forEach(item -> uniqueAddress.add(item.getAddress()));
		
		return new ReqFloodStations(concernedPeople, records, new ArrayList<String>(uniqueAddress));
	}
	
	@GetMapping("/personInfo")
	@ResponseBody
	public ReqPersonInfo personInfo(@RequestParam(required = false) String firstName, @RequestParam(required = true) String lastName) {
		
		Persons concernedPeople = DataHandler.DATA.getPersons().getPersonByLastName(lastName);
		Medicalrecords records = DataHandler.DATA.getMedicalrecords().getMedicalrecordByLastName(lastName);
		
		if(firstName != null) {
			concernedPeople = concernedPeople.getPersonByFirstName(firstName);
			records = records.getMedicalrecordByFirstName(firstName);
		}
		
		return new ReqPersonInfo(concernedPeople, records);
	}
	
	@GetMapping("/communityEmail")
	@ResponseBody
	public List<String> communityEmail(@RequestParam(required = true) String city) {
		Persons concernedPeople = DataHandler.DATA.getPersons().getPersonByCity(city);

		HashSet<String> reply = new HashSet<String>();
		concernedPeople.getPersons().forEach(item -> reply.add(item.getEmail()));
		
		return new ArrayList<String>(reply);
	}
}
