package com.safetyNet.Alerts.Unitary.Controller;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Controller.MedicalrecordController;
import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.DataHandler;

public class MedicalrecordControllerTest {
	MedicalrecordController medicalrecordController = new MedicalrecordController();
	private static final Logger logger = LogManager.getLogger("MedicalrecordControllerTest");
	
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
	public static Medicalrecords medicalRecords;
	
	@BeforeAll
	public static void initBeforeAll() throws ParseException {
		firstNameA = "a";
		firstNameB = "b";
		firstNameC = "c";
		
		lastNameA = "A";
		lastNameB = "B";
		lastNameC = "C";
		
		birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/2005");
		birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995");
		birthdateC = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1985");
		
		medicationsA = new ArrayList<String>();
		medicationsA.add("aznol:350");
		medicationsA.add("hydrapermazol:100");
		medicationsB = new ArrayList<String>();
		medicationsC = new ArrayList<String>();
		
		allergiesA = new ArrayList<String>();
		allergiesB = new ArrayList<String>();
		allergiesB.add("peanut");
		allergiesC = new ArrayList<String>();
		
		medicalRecordTestA = new Medicalrecord(firstNameA,lastNameA,birthdateA,medicationsA,allergiesA);
		medicalRecordTestB = new Medicalrecord(firstNameB,lastNameB,birthdateB,medicationsB,allergiesB);
		medicalRecordTestC = new Medicalrecord(firstNameC,lastNameC,birthdateC,medicationsC,allergiesC);
		
		ArrayList<Person> p = new ArrayList<Person>();
		ArrayList<Firestation> f =  new ArrayList<Firestation>();
		ArrayList<Medicalrecord> m = new ArrayList<Medicalrecord>();
		
	
		m.add(medicalRecordTestA);
		m.add(medicalRecordTestB);
		
		DataHandler.loadData(new Data(p,f,m));
	
	}
	
	@BeforeEach
	public void initPerTest() {		
		ArrayList<Person> p = new ArrayList<Person>();
		ArrayList<Firestation> f =  new ArrayList<Firestation>();
		ArrayList<Medicalrecord> m = new ArrayList<Medicalrecord>();
		
		m.add(medicalRecordTestA);
		m.add(medicalRecordTestB);
		
		DataHandler.loadData(new Data(p,f,m));
	}
	
	@Test
	public void findAllMedicalrecords() {
		logger.info("[TESTING] Testing findAllMedicalrecords :");
		List<Medicalrecord> medicalRecords = medicalrecordController.findAllMedicalrecords().getMedicalrecords();
		assertTrue(medicalRecords.size() == 2);
		assertTrue(medicalRecords.contains(medicalRecordTestA));
		assertTrue(medicalRecords.contains(medicalRecordTestB));
	}
	
	@Test
	public void findMedicalrecords() {
		logger.info("[TESTING] Testing findMedicalrecords :");
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords(firstNameA, lastNameA).getMedicalrecords();
		assertTrue(medicalRecords.size() == 1);
		assertTrue(medicalRecords.contains(medicalRecordTestA));
	}
	@Test
	public void addMedicalrecordTest() throws URISyntaxException {
		logger.info("[TESTING] Testing addMedicalrecordTest :");
		medicalrecordController.addMedicalrecords(medicalRecordTestC);
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords(firstNameC, lastNameC).getMedicalrecords();
		assertTrue(medicalRecords.size() == 1);
		assertTrue(medicalRecords.contains(medicalRecordTestC));
	}
	@Test
	public void updateMedicalrecordTest() throws URISyntaxException {
		logger.info("[TESTING] Testing updateMedicalrecordTest :");
		Medicalrecord p = new Medicalrecord(firstNameA, lastNameA, new Date(0), new ArrayList<String>(), new ArrayList<String>());
		medicalrecordController.updateMedicalrecords(p);
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords(firstNameA, lastNameA).getMedicalrecords();
		assertTrue(medicalRecords.size() == 1);
		assertTrue(medicalRecords.get(0).getFirstName().equals(firstNameA));
		assertTrue(medicalRecords.get(0).getLastName().equals(lastNameA));
		assertTrue(medicalRecords.get(0).getBirthdate().equals(p.getBirthdate()));
		assertTrue(medicalRecords.get(0).getAllergies().equals(p.getAllergies()));
		assertTrue(medicalRecords.get(0).getMedications().equals(p.getMedications()));
	}
	@Test
	public void deleteMedicalrecordTest() throws URISyntaxException {
		logger.info("[TESTING] Testing deleteMedicalrecordTest :");
		medicalrecordController.deleteMedicalrecords(firstNameA, lastNameA);
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords(firstNameA, lastNameA).getMedicalrecords();
		assertTrue(medicalRecords.size() == 0);
	}
}
