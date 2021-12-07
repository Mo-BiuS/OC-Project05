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
	
//	Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante. 
//	Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste 
//	doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus, 
//	elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou 
//	moins) dans la zone desservie.
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
		
		int adultCount  = peopleAges.getMedicalrecordByBirthdaySuperiorTo(eighteenYear).getMedicalrecords().size();
		int childCount = peopleAges.getMedicalrecords().size()-adultCount;
		
	    return new ReqFirestation(concernedPeople,adultCount, childCount);
	}
	
//	Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse. 
//	La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres 
//	membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide
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
	
//	Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de 
//	pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques. 
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
	
//	Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne 
//	de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents 
//	médicaux (médicaments, posologie et allergies) de chaque personne. 
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
	
//	Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les 
//	personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et 
//	faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom. 
	@GetMapping("/flood/stations")
	@ResponseBody
	public ReqFloodStations reqFloodStations(@RequestParam(required = true) List<Integer> stations) { 
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
	
//	Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, 
//	posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent 
//	toutes apparaître. 
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
	
//	Cette url doit retourner les adresses mail de tous les habitants de la ville.
	@GetMapping("/communityEmail")
	@ResponseBody
	public List<String> communityEmail(@RequestParam(required = true) String city) {
		Persons concernedPeople = DataHandler.DATA.getPersons().getPersonByCity(city);

		HashSet<String> reply = new HashSet<String>();
		concernedPeople.getPersons().forEach(item -> reply.add(item.getEmail()));
		
		return new ArrayList<String>(reply);
	}
}
