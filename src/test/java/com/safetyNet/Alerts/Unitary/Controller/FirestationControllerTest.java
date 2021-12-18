package com.safetyNet.Alerts.Unitary.Controller;

import static org.junit.Assert.assertTrue;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.safetyNet.Alerts.Controller.FirestationController;
import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.DataHandler;


public class FirestationControllerTest {
	FirestationController firestationController = new FirestationController();
	private static final Logger logger = LogManager.getLogger("FirestationControllerTest");
	
	
	public static String adressA;
	public static String adressB;
	public static String adressC;
	
	public static int stationA;
	public static int stationB;
	public static int stationC;
	
	public static Firestation firestationA;
	public static Firestation firestationABis;
	public static Firestation firestationB;
	public static Firestation firestationC;
	
	@BeforeAll
	public static void initBeforeAll() {
		firestationA = new Firestation(adressA ="a", stationA =1);
		firestationB = new Firestation(adressB ="b", stationB =2);
		firestationC = new Firestation(adressC ="c", stationC =3);
		firestationABis = new Firestation(adressA, stationB);
		
		ArrayList<Person> p = new ArrayList<Person>();
		ArrayList<Firestation> f =  new ArrayList<Firestation>();
		f.add(firestationA);
		f.add(firestationB);
		ArrayList<Medicalrecord> m = new ArrayList<Medicalrecord>();
		
		DataHandler.loadData(new Data(p,f,m));
	}
	
	@BeforeEach
	public void initPerTest() {
		ArrayList<Person> p = new ArrayList<Person>();
		ArrayList<Firestation> f =  new ArrayList<Firestation>();
		f.add(firestationA);
		f.add(firestationB);
		ArrayList<Medicalrecord> m = new ArrayList<Medicalrecord>();
		
		DataHandler.loadData(new Data(p,f,m));
	}
	
	@Test
	public void findAllFirestation() {
		logger.info("[TESTING] Testing findAllFirestation :");
		List<Firestation> firestations = firestationController.findAllFirestation().getFirestations();
		assertTrue(firestations.size() == 2);
		assertTrue(firestations.contains(firestationA));
		assertTrue(firestations.contains(firestationB));
	}
	
	@Test
	public void findFirestation() {
		logger.info("[TESTING] Testing findFirestation :");
		List<Firestation> firestations = firestationController.findFirestation(adressA).getFirestations();
		assertTrue(firestations.size() == 1);
		assertTrue(firestations.contains(firestationA));
	}
	@Test
	public void addFirestation() throws URISyntaxException {
		logger.info("[TESTING] Testing addFirestation :");
		firestationController.addFirestation(firestationC);
		List<Firestation> firestations = firestationController.findFirestation(adressC).getFirestations();
		assertTrue(firestations.size() == 1);
		assertTrue(firestations.contains(firestationC));
	}
	@Test
	public void updateFirestation() throws URISyntaxException {
		logger.info("[TESTING] Testing updateFirestation :");
		assertTrue(firestationController.updateFirestation(firestationABis));
		List<Firestation> firestations = firestationController.findFirestation(adressA).getFirestations();
		assertTrue(firestations.size() == 1);
		assertTrue(firestations.contains(firestationABis));
	}
	@Test
	public void deleteFirestation() throws URISyntaxException {
		logger.info("[TESTING] Testing deleteFirestation :");
		assertTrue(firestationController.deleteFirestation(adressA));
		List<Firestation> firestations = firestationController.findFirestation(adressA).getFirestations();
		assertTrue(firestations.size() == 0);
	}
}
