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
import org.mockito.Mockito;

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
	
	public static String adressAB;
	public static String adressC;
	
	public static int stationA;
	public static int stationB;
	
	public static Firestation firestationA;
	public static Firestation firestationB;
	
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
		medicalRecordTestA = Mockito.mock(Medicalrecord.class);
		medicalRecordTestB = Mockito.mock(Medicalrecord.class);
		medicalRecordTestC = Mockito.mock(Medicalrecord.class);
		
		firestationA = Mockito.mock(Firestation.class);
		firestationB = Mockito.mock(Firestation.class);
		
		personA = Mockito.mock(Person.class);
		personB = Mockito.mock(Person.class);
		personC = Mockito.mock(Person.class);
		
		Mockito.when(personA.getFirstName()).thenReturn(firstNameA = "a");
		Mockito.when(personB.getFirstName()).thenReturn(firstNameB = "b");
		Mockito.when(personC.getFirstName()).thenReturn(firstNameB = "c");
		
		Mockito.when(personA.getLastName()).thenReturn(lastNameA = "A");
		Mockito.when(personB.getLastName()).thenReturn(lastNameB = "B");
		Mockito.when(personC.getLastName()).thenReturn(lastNameB = "C");
		
		Mockito.when(personA.getAddress()).thenReturn(adressAB = "1A");
		Mockito.when(personB.getAddress()).thenReturn(adressAB);
		Mockito.when(personC.getAddress()).thenReturn(adressC = "2C");
		
		Mockito.when(personA.getCity()).thenReturn(city = "city");
		Mockito.when(personB.getCity()).thenReturn(city);
		Mockito.when(personC.getCity()).thenReturn(city);
		
		Mockito.when(personA.getPhone()).thenReturn(phoneA = "06");
		Mockito.when(personB.getPhone()).thenReturn(phoneB = "07");
		Mockito.when(personC.getPhone()).thenReturn(phoneB = "08");
		
		Mockito.when(personA.getEmail()).thenReturn(emailA = "A@A");
		Mockito.when(personB.getEmail()).thenReturn(emailB = "B@B");
		Mockito.when(personC.getEmail()).thenReturn(emailB = "C@C");
		
		Mockito.when(personA.getZip()).thenReturn((int) (zipA = 1));
		Mockito.when(personB.getZip()).thenReturn((int) (zipB = 2));
		Mockito.when(personC.getZip()).thenReturn((int) (zipB = 3));
		
		Mockito.when(firestationA.getStation()).thenReturn(stationA =1);
		Mockito.when(firestationB.getStation()).thenReturn(stationB =2);
		
		Mockito.when(firestationA.getAddress()).thenReturn(adressAB);
		Mockito.when(firestationB.getAddress()).thenReturn(adressC);
		
		Mockito.when(medicalRecordTestA.getFirstName()).thenReturn(firstNameA ="a");
		Mockito.when(medicalRecordTestB.getFirstName()).thenReturn(firstNameB ="b");
		Mockito.when(medicalRecordTestC.getFirstName()).thenReturn(firstNameC ="c");
		
		Mockito.when(medicalRecordTestA.getLastName()).thenReturn(lastNameA ="A");
		Mockito.when(medicalRecordTestB.getLastName()).thenReturn(lastNameB ="B");
		Mockito.when(medicalRecordTestC.getLastName()).thenReturn(lastNameC ="C");
		
		Mockito.when(medicalRecordTestA.getBirthdate()).thenReturn(birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/2005"));
		Mockito.when(medicalRecordTestB.getBirthdate()).thenReturn(birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995"));
		Mockito.when(medicalRecordTestC.getBirthdate()).thenReturn(birthdateC = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/1985"));
		
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
		
		Mockito.when(medicalRecordTestA.getMedications()).thenReturn(medicationsA);
		Mockito.when(medicalRecordTestB.getMedications()).thenReturn(medicationsB);
		Mockito.when(medicalRecordTestC.getMedications()).thenReturn(medicationsC);
		
		Mockito.when(medicalRecordTestA.getAllergies()).thenReturn(allergiesA);
		Mockito.when(medicalRecordTestB.getAllergies()).thenReturn(allergiesB);
		Mockito.when(medicalRecordTestC.getAllergies()).thenReturn(allergiesC);
		
		
		List<Firestation> lFirestation = new ArrayList<Firestation>();
		lFirestation.add(firestationA);
		lFirestation.add(firestationB);
		
		List<Person> lPerson = new ArrayList<Person>();
		lPerson.add(personA);
		lPerson.add(personB);
		lPerson.add(personC);
		
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
		logger.info("[RESULT] firestationTest : "+req);
		
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
	}
	
	@Test
	public void childAlertTest() {
		logger.info("[TESTING] Testing childAlertTest :");
		ReqChildAlert req = RequestService.childAlert(adressAB);
		logger.info("[RESULT] childAlertTest : "+req);
		
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
	}
	
	@Test
	public void phoneAlertTest() {
		logger.info("[TESTING] Testing phoneAlertTest :");
		ArrayList<String> req = (ArrayList<String>) RequestService.phoneAlert(1);
		logger.info("[RESULT] phoneAlertTest : "+req);
		
		assertTrue(req.size() == 2);
		
		boolean pA = false;
		for(String p : req) {
			if(p.equals(phoneA))pA = true;
		}
		assertTrue(pA);
	}
	
	@Test
	public void fireTest() {
		logger.info("[TESTING] Testing fireTest :");
		ReqFire req = RequestService.fire(adressAB);
		logger.info("[RESULT] fireTest : "+req);
		
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
	}
	
	@Test
	public void reqFloodStationsTest() { 
		logger.info("[TESTING] Testing reqFloodStationsTest :");
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(stationA);
		list.add(0);
		ReqFloodStations req = RequestService.reqFloodStations(list);
		logger.info("[RESULT] reqFloodStationsTest : "+req);
		
		assertTrue(req.address.size() == 1);
		
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
	}
	
	@Test
	public void personInfoTest() { 
		logger.info("[TESTING] Testing personInfoTest :");
		ReqPersonInfo req = RequestService.personInfo(firstNameA,lastNameA);
		logger.info("[RESULT] personInfoTest : "+req);
		
		assertTrue(req.peoples.size() == 1);
		assertTrue(req.peoples.get(0).firstName.equals(firstNameA));
		assertTrue(req.peoples.get(0).lastName.equals(lastNameA));
		assertTrue(req.peoples.get(0).mail.equals(emailA));
		assertTrue(req.peoples.get(0).address.equals(adressAB));
	}
	
	@Test
	public void communityEmailTest() { 
		
		logger.info("[TESTING] Testing communityEmailTest :");
		List<String> req = RequestService.communityEmail(city);
		logger.info("[RESULT] communityEmailTest : "+req);
		assertTrue(req.size() == 3);
	}
	
}
