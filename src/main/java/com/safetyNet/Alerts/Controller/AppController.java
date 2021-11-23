package com.safetyNet.Alerts.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Service.DataHandler;

@RestController
public class AppController {
	@RequestMapping(value = "/getString", method = RequestMethod.GET)
    public Data listeProduits() {
        return DataHandler.DATA;
    }
	
	@GetMapping("/firestation")
	@ResponseBody
	public List<Person> getFoos(@RequestParam(required = false) int stationNumber) { 
		Firestations concernedStation = DataHandler.DATA.getFirestations().getByStationEqualsTo(stationNumber);
		Persons concernedPeople = new Persons(new ArrayList<Person>());
		for(int i = 0; i < concernedStation.getFirestations().size(); i++) {
			concernedPeople = concernedPeople.concat(DataHandler.DATA.getPersons().getPersonByAdress(concernedStation.getFirestations().get(i).getAddress()));
		}
	    return concernedPeople.getPersons();
	}
}
