package com.safetyNet.Alerts.Controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Model.Reply.ChildAlert;
import com.safetyNet.Alerts.Model.Reply.ReqFirestation;
import com.safetyNet.Alerts.Service.DataHandler;

@RestController
public class AppController {
	private static Date eighteenYear;
	static {
		eighteenYear = new Date();
		eighteenYear.setTime( (long) (System.currentTimeMillis() - ( 18 * 365.25 * 24 * 60 * 60 * 1000)) ); //18 years ago
	}
	
	@RequestMapping(value = "/getString", method = RequestMethod.GET)
    public Data listeProduits() {
        return DataHandler.DATA;
    }
	
	@GetMapping("/firestation")
	@ResponseBody
	public ReqFirestation firestation(@RequestParam(required = false) int stationNumber) { 
		Firestations concernedStation = DataHandler.DATA.getFirestations().getByStationEqualsTo(stationNumber);
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
	public ChildAlert childAlert(@RequestParam(required = false) String address) { 
		
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

		return new ChildAlert(childList,adultList);
	}
}
