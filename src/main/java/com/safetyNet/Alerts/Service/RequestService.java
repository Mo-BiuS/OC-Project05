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

/** 
 * Class handling the main data request of the specification
 * It is exclusively used in /src/main/java/Controller/AppController.java
 * @author Mo-Bius
 */
public class RequestService {

	private static final Logger logger = LogManager.getLogger("RequestService");
	
	private static final Date eighteenYear;
	static {
		eighteenYear = new Date();
		eighteenYear.setTime( (long) (System.currentTimeMillis() - ( 18 * 365.25 * 24 * 60 * 60 * 1000)) ); //18 years ago
	}
	
	/**
	 * The specifications describe this request as follow :
	 * 
	 * Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante. 
	 * Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste 
	 * doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus, 
	 * elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou 
	 * moins) dans la zone desservie.
	 * 
	 * @param stationNumber
	 * @return ReqFirestation, a structured answer in a class
	 */
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
	
	/**
	 * The specifications describe this request as follow :
	 * 
	 * Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse. 
	 * La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres 
	 * membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide
	 * 
	 * @param address
	 * @return ReqChildAlert, a structured answer in a class
	 */
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
	
	/**
	 * The specifications describe this request as follow :
	 * 
	 * ette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de 
	 * pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques. 
	 * 
	 * @param stationNumber
	 * @return List<String>
	 */
	public static List<String> phoneAlert( int stationNumber) { 
		
		logger.info("Treating : /phoneAlert?stationNumber="+stationNumber);
		
		ArrayList<String> reply = new ArrayList<String>();
		
		DataHandler.getData().getFirestations().getByStation(stationNumber)
			.getFirestations().forEach(
				firestation -> DataHandler.getData().getPersons().getPersonByAdress(firestation.getAddress()).getPersons().forEach(
						person -> reply.add(person.getPhone())));
		
		return reply;
	}
	
	/**
	 * The specifications describe this request as follow :
	 * 
	 * Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne 
	 * de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents 
	 * médicaux (médicaments, posologie et allergies) de chaque personne. 
	 * 
	 * @param address
	 * @return ReqFire, a structured answer in a class
	 */
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
	
	/**
	 * The specifications describe this request as follow :
	 * 
	 * Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les 
	 * personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et 
	 * faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom. 
	 * 
	 * @param stations
	 * @return ReqFloodStations, a structured answer in a class
	 */	public static ReqFloodStations reqFloodStations(List<Integer> stations) { 
		
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
	
	/**
	 * The specifications describe this request as follow :
	 * 
	 * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, 
	 * posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent 
	 * toutes apparaître. 
	 * 
	 * @param firstName
	 * @param lastName
	 * @return ReqPersonInfo, a structured answer in a class
	 */
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
	
	/**
	 * The specifications describe this request as follow :
	 * 
	 * Cette url doit retourner les adresses mail de tous les habitants de la ville.
	 * 
	 * @param city
	 * @return List<String>
	 */
	public static List<String> communityEmail(String city) {
		
		logger.info("Treating : /communityEmail?city="+city);
		
		Persons concernedPeople = DataHandler.getData().getPersons().getPersonByCity(city);

		HashSet<String> reply = new HashSet<String>();
		concernedPeople.getPersons().forEach(item -> reply.add(item.getEmail()));
		
		return new ArrayList<String>(reply);
	}
}
