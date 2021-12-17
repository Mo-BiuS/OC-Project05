package com.safetyNet.Alerts.Unitary.Model.MedicalRecord;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import org.mockito.Mockito;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;

public class MedicalrecordsTest {
	private static final Logger logger = LogManager.getLogger("MedicalrecordsTest");
	
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
		medicalRecordTestA = Mockito.mock(Medicalrecord.class);
		medicalRecordTestB = Mockito.mock(Medicalrecord.class);
		medicalRecordTestC = Mockito.mock(Medicalrecord.class);
		
		Mockito.when(medicalRecordTestA.getFirstName()).thenReturn(firstNameA ="a");
		Mockito.when(medicalRecordTestB.getFirstName()).thenReturn(firstNameB ="b");
		Mockito.when(medicalRecordTestC.getFirstName()).thenReturn(firstNameC ="c");
		
		Mockito.when(medicalRecordTestA.getLastName()).thenReturn(lastNameA ="A");
		Mockito.when(medicalRecordTestB.getLastName()).thenReturn(lastNameB ="B");
		Mockito.when(medicalRecordTestC.getLastName()).thenReturn(lastNameC ="C");
		
		Mockito.when(medicalRecordTestA.getBirthdate()).thenReturn(birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/2000"));
		Mockito.when(medicalRecordTestB.getBirthdate()).thenReturn(birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995"));
		Mockito.when(medicalRecordTestC.getBirthdate()).thenReturn(birthdateC = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/1990"));
		
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
		
		Mockito.when(medicalRecordTestA.compare(medicalRecordTestA)).thenReturn(true);
	}
	
	@BeforeEach
	public void initBeforeEach() {
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		l.add(medicalRecordTestB);
		
		medicalRecords = new Medicalrecords(l);
	}
	
	@Test
	public void getMedicalrecordsTest() {
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertFalse(l.hashCode() == medicalRecords.getMedicalrecords().hashCode());
		l.add(medicalRecordTestB);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecords().hashCode());
		l.add(medicalRecordTestC);
		assertFalse(l.hashCode() == medicalRecords.getMedicalrecords().hashCode());
	}
	
	@Test
	public void getMedicalrecordByFirstNameTest() {
		logger.info("[TESTING] Testing getMedicalrecordByFirstNameTest :");
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByFirstName(firstNameA).hashCode());
		logger.info("getMedicalrecordByFirstNameTest result : "+new Medicalrecords(l));
	}
	@Test
	public void getMedicalrecordByLastName() {
		logger.info("[TESTING] Testing getMedicalrecordByLastName :");
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByLastName(lastNameA).hashCode());
		logger.info("getMedicalrecordByLastName result : "+new Medicalrecords(l));
	}

	@Test
	public void getMedicalrecordByMedication() {
		logger.info("[TESTING] Testing getMedicalrecordByMedication :");
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByMedication("aznol:350").hashCode());
		logger.info("getMedicalrecordByMedication result : "+new Medicalrecords(l));
	}
	@Test
	public void getMedicalrecordByAllergies() {
		logger.info("[TESTING] Testing getMedicalrecordByAllergies :");
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestB);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByAllergies("ork").hashCode());
		logger.info("getMedicalrecordByAllergies result : "+new Medicalrecords(l));
	}
	
	@Test
	public void getMedicalrecordByBirthdayEqualTo() throws ParseException {
		logger.info("[TESTING] Testing getMedicalrecordByBirthdayEqualTo :");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995");

		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestB);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByBirthdayEqualTo(date).hashCode());
		logger.info("getMedicalrecordByBirthdayEqualTo result : "+new Medicalrecords(l));
	}
	@Test
	public void getMedicalrecordByBirthdayInferiorTo() throws ParseException {
		logger.info("[TESTING] Testing getMedicalrecordByBirthdayInferiorTo :");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1996");

		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByBirthdayInferiorTo(date).hashCode());
		logger.info("getMedicalrecordByBirthdayInferiorTo result : "+new Medicalrecords(l));
	}
	@Test
	public void getMedicalrecordByBirthdaySuperiorTo() throws ParseException {
		logger.info("[TESTING] Testing getMedicalrecordByBirthdaySuperiorTo :");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1996");

		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestB);
		
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByBirthdaySuperiorTo(date).hashCode());
		logger.info("getMedicalrecordByBirthdaySuperiorTo result : "+new Medicalrecords(l));
	}
	
	@Test
	public void concatTest() {
		List<Medicalrecord> l1 = new ArrayList<Medicalrecord>();
		l1.add(medicalRecordTestA);
		Medicalrecords medicalRecords1 = new Medicalrecords(l1);

		List<Medicalrecord> l2 = new ArrayList<Medicalrecord>();
		l2.add(medicalRecordTestB);
		Medicalrecords medicalRecords2 = new Medicalrecords(l2);

		List<Medicalrecord> l3 = new ArrayList<Medicalrecord>();
		l3.add(medicalRecordTestA);
		l3.add(medicalRecordTestB);
		Medicalrecords medicalRecords3 = new Medicalrecords(l3);
		
		assertTrue(medicalRecords1.concat(medicalRecords2).hashCode() == medicalRecords3.hashCode());
	}
	
	@Test
	public void containTest() {
		assertFalse(medicalRecords.contain(medicalRecordTestC));
		assertTrue(medicalRecords.contain(medicalRecordTestA));
	}
	@Test
	public void replaceTest() {
		assertFalse(medicalRecords.replace(medicalRecordTestC));
		assertTrue(medicalRecords.replace(medicalRecordTestA));
	}
	@Test
	public void deleteTest() {
		assertFalse(medicalRecords.delete(medicalRecordTestC));
		assertTrue(medicalRecords.delete(medicalRecordTestA));
	}
	@Test
	public void addTest() {
		assertFalse(medicalRecords.add(medicalRecordTestA));
		assertTrue(medicalRecords.add(medicalRecordTestC));
	}
}
