package com.safetyNet.Alerts.Model.MedicalRecord;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;

public class MedicalrecordsTest {
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
		
		birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/2000");
		birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995");
		birthdateC = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/1990");
		
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
	}
	
	@BeforeEach
	public void initBeforeEach() {
		medicalRecordTestA = new Medicalrecord(firstNameA, lastNameA, birthdateA , medicationsA, allergiesA);
		medicalRecordTestB = new Medicalrecord(firstNameB, lastNameB, birthdateB , medicationsB, allergiesB);
		medicalRecordTestC = new Medicalrecord(firstNameC, lastNameC, birthdateC , medicationsC, allergiesC);
		
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
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByFirstName(firstNameA).hashCode());
	}
	@Test
	public void getMedicalrecordByLastName() {
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByLastName(lastNameA).hashCode());
	}

	@Test
	public void getMedicalrecordByMedication() {
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByMedication("aznol:350").hashCode());
	}
	@Test
	public void getMedicalrecordByAllergies() {
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestB);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByAllergies("ork").hashCode());
	}
	
	@Test
	public void getMedicalrecordByBirthdayEqualTo() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995");

		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestB);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByBirthdayEqualTo(date).hashCode());
	}
	@Test
	public void getMedicalrecordByBirthdayInferiorTo() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1996");

		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByBirthdayInferiorTo(date).hashCode());
	}
	@Test
	public void getMedicalrecordByBirthdaySuperiorTo() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1996");

		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestB);
		
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecordByBirthdaySuperiorTo(date).hashCode());
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
