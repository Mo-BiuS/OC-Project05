package com.safetyNet.Alerts.Unitary.Service;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Reply.ReqChildAlert;
import com.safetyNet.Alerts.Model.Reply.ReqFire;
import com.safetyNet.Alerts.Model.Reply.ReqFirestation;
import com.safetyNet.Alerts.Model.Reply.ReqFloodStations;
import com.safetyNet.Alerts.Model.Reply.ReqPersonInfo;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqChildAlertAdult;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqChildAlertChild;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFirePeople;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFirestationPerson;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFloodStationAddress;
import com.safetyNet.Alerts.Service.DataHandler;
import com.safetyNet.Alerts.Service.RequestService;

public class RequestServiceTest {

	private static final Logger logger = LogManager.getLogger("RequestServiceTest");
	
	public static String adressA;
	public static String adressB;
	public static String adressC;
	
	public static int stationA;
	public static int stationB;
	public static int stationC;
	
	public static Firestation firestationA;
	public static Firestation firestationB;
	public static Firestation firestationC;
	
	public static String firstNameA;
	public static String firstNameB;
	public static String firstNameC;
	
	public static String lastNameA;
	public static String lastNameB;
	public static String lastNameC;
	
	public static Date birthdateA;
	public static Date birthdateB;
	public static Date birthdateC;
	
	public static ArrayList<String> medicationsA;
	public static ArrayList<String> medicationsB;
	public static ArrayList<String> medicationsC;
	
	public static ArrayList<String> allergiesA;
	public static ArrayList<String> allergiesB;
	public static ArrayList<String> allergiesC;
	
	public static Medicalrecord medicalRecordTestA;
	public static Medicalrecord medicalRecordTestB;
	public static Medicalrecord medicalRecordTestC;
	
	public static String city;
	
	public static String phoneA;
	public static String phoneB;
	public static String phoneC;
	
	public static String emailA;
	public static String emailB;
	public static String emailC;
	
	public static short zipA;
	public static short zipB;
	public static short zipC;
	
	public static Person personA;
	public static Person personB;
	public static Person personC;
	
	@BeforeAll
	public static void initData() throws ParseException {
		adressA = "a";
		adressB = adressA;
		adressC = "c";
		
		stationA = 1;
		stationB = 2;
		stationC = 3;
		
		firstNameA = "a";
		firstNameB = "b";
		firstNameC = "c";
		
		lastNameA = "A";
		lastNameB = "B";
		lastNameC = "C";
		
		birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/2005");
		birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995");
		birthdateC = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/1985");
		
		medicationsA = new ArrayList<String>();
		medicationsA.add("aznol:350");
		medicationsA.add("hydrapermazol:100");
		medicationsB = new ArrayList<String>();
		medicationsC = new ArrayList<String>();
		medicationsC.add("aznol:350");
		
		allergiesA = new ArrayList<String>();
		allergiesB = new ArrayList<String>();
		allergiesB.add("ork");
		allergiesC = new ArrayList<String>();
		allergiesC.add("peanut");
		
		city = "city";
		
		phoneA = "06";
		phoneB = "07";
		phoneB = "08";
		
		emailA = "A@A";
		emailB = "B@B";
		emailB = "C@C";
		
		zipA = 1;
		zipB = 2;
		zipB = 3;
		
		firestationA = new Firestation(adressA, stationA);
		firestationB = new Firestation(adressC, stationC);
		List<Firestation> lFirestation = new ArrayList<Firestation>();
		lFirestation.add(firestationA);
		lFirestation.add(firestationB);
		
		personA = new Person(firstNameA, lastNameA, adressA, city, zipA, phoneA, emailA);
		personB = new Person(firstNameB, lastNameB, adressB, city, zipB, phoneB, emailB);
		personC = new Person(firstNameC, lastNameC, adressC, city, zipC, phoneC, emailC);
		List<Person> lPerson = new ArrayList<Person>();
		lPerson.add(personA);
		lPerson.add(personB);
		lPerson.add(personC);
		
		medicalRecordTestA = new Medicalrecord(firstNameA, lastNameA, birthdateA , medicationsA, allergiesA);
		medicalRecordTestB = new Medicalrecord(firstNameB, lastNameB, birthdateB , medicationsB, allergiesB);
		medicalRecordTestC = new Medicalrecord(firstNameC, lastNameC, birthdateC , medicationsC, allergiesC);
		List<Medicalrecord> lMedicalrecord = new ArrayList<Medicalrecord>();
		lMedicalrecord.add(medicalRecordTestA);
		lMedicalrecord.add(medicalRecordTestB);
		lMedicalrecord.add(medicalRecordTestC);
		
		DataHandler.loadData(new Data(lPerson, lFirestation, lMedicalrecord));
	}
	
	@Test
	public void firestationTest() {
		logger.info("[TESTING] Testing firestationTest :");
		ReqFirestation req = RequestService.firestation(1);
		
		assertTrue(req.childCount == 1);
		assertTrue(req.adultCount == 1);
		
		boolean pA = false;
		boolean pB = false;
		for(ReqFirestationPerson p : req.persons) {
			if(p.firstName.equals(firstNameA) && p.lastName.equals(lastNameA))pA = true;
			else if(p.firstName.equals(firstNameB) && p.lastName.equals(lastNameB))pB = true;
		}
		assertTrue(pA);
		assertTrue(pB);
		
		logger.info("firestationTest result : "+req);
	}
	
	@Test
	public void childAlertTest() {
		logger.info("[TESTING] Testing childAlertTest :");
		ReqChildAlert req = RequestService.childAlert(adressA);
		
		assertTrue(req.adult.size() == 1);
		assertTrue(req.child.size() == 1);
		
		boolean pB = false;
		for(ReqChildAlertAdult p : req.adult) {
			if(p.firstName.equals(firstNameB) && p.lastName.equals(lastNameB))pB = true;
		}
		assertTrue(pB);
		
		boolean pA = false;
		for(ReqChildAlertChild p : req.child) {
			if(p.firstName.equals(firstNameA) && p.lastName.equals(lastNameA))pA = true;
		}
		assertTrue(pA);
		
		logger.info("childAlertTest result : "+req);
	}
	
	@Test
	public void phoneAlertTest() {
		logger.info("[TESTING] Testing phoneAlertTest :");
		ArrayList<String> req = (ArrayList<String>) RequestService.phoneAlert(1);
		
		assertTrue(req.size() == 2);
		
		boolean pA = false;
		for(String p : req) {
			if(p.equals(phoneA))pA = true;
		}
		assertTrue(pA);
		
		logger.info("phoneAlertTest result : "+req);
	}
	
	@Test
	public void fireTest() {
		logger.info("[TESTING] Testing fireTest :");
		ReqFire req = RequestService.fire(adressA);
		
		assertTrue(req.stationsNumber.size() == 1);
		assertTrue(req.people.size() == 2);
		
		boolean station = false;
		for(int p : req.stationsNumber) {
			if(p == stationA)station = true;
		}
		assertTrue(station);
		
		boolean pA = false;
		boolean pB = false;
		for(ReqFirePeople p : req.people) {
			if(p.firstName.equals(firstNameA) && p.lastName.equals(lastNameA))pA = true;
			else if(p.firstName.equals(firstNameB) && p.lastName.equals(lastNameB))pB = true;
		}
		assertTrue(pA);
		assertTrue(pB);
		
		logger.info("fireTest result : "+req);
	}
	
	@Test
	public void reqFloodStationsTest() { 
		logger.info("[TESTING] Testing reqFloodStationsTest :");
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(1);
		ReqFloodStations req = RequestService.reqFloodStations(list);
		
		assertTrue(req.address.size() == 2);
		
		boolean pA = false;
		boolean pB = false;
		for(ReqFloodStationAddress address : req.address) {
			for(ReqFirePeople p : address.people) {
				if(p.firstName.equals(firstNameA) && p.lastName.equals(lastNameA))pA = true;
				else if(p.firstName.equals(firstNameB) && p.lastName.equals(lastNameB))pB = true;
			}
		}
		assertTrue(pA);
		assertTrue(pB);
		
		logger.info("reqFloodStationsTest result : "+req);
	}
	
	@Test
	public void personInfoTest() { 
		logger.info("[TESTING] Testing personInfoTest :");
		ReqPersonInfo req = RequestService.personInfo(firstNameA,lastNameA);
		
		assertTrue(req.peoples.size() == 1);
		assertTrue(req.peoples.get(0).firstName.equals(firstNameA));
		assertTrue(req.peoples.get(0).lastName.equals(lastNameA));
		
		logger.info("personInfoTest result : "+req);
	}
	
	@Test
	public void communityEmailTest() { 
		
		logger.info("[TESTING] Testing communityEmailTest :");
		List<String> req = RequestService.communityEmail(city);
		
		assertTrue(req.size() == 3);
		assertTrue(req.contains(emailA));
		assertTrue(req.contains(emailB));
		assertTrue(req.contains(emailC));
		
		logger.info("communityEmailTest result : "+req);
	}
	
}
