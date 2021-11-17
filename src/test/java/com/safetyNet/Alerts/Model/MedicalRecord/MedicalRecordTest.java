package com.safetyNet.Alerts.Model.MedicalRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;

public class MedicalRecordTest {
	public static String firstNameA;
	public static String firstNameB;
	
	public static String lastNameA;
	public static String lastNameB;
	
	public static Date birthdateA;
	public static Date birthdateB;
	
	public static ArrayList<String> medicationsA;
	public static ArrayList<String> medicationsB;
	
	public static ArrayList<String> allergiesA;
	public static ArrayList<String> allergiesB;
	
	public static Medicalrecord medicalRecordTestA;
	public static Medicalrecord medicalRecordTestB;
	
	@BeforeAll
	public static void initBeforeAll() throws ParseException {
		firstNameA = "a";
		firstNameB = "b";
		
		lastNameA = "A";
		lastNameB = "B";
		
		birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/1999");
		birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1985");
		
		medicationsA = new ArrayList<String>();
		medicationsA.add("aznol:350");
		medicationsA.add("hydrapermazol:100");
		medicationsB = new ArrayList<String>();
		
		allergiesA = new ArrayList<String>();
		allergiesB = new ArrayList<String>();
		allergiesB.add("peanut");
	}
	
	@BeforeEach
	public void initBeforeEach() {
		medicalRecordTestA = new Medicalrecord(firstNameA, lastNameA, birthdateA , medicationsA, allergiesA);
		medicalRecordTestB = new Medicalrecord(firstNameB, lastNameB, birthdateB , medicationsB, allergiesB);
	}
	
	
	@Test
	public void medicalRecordGetFirstName() {
		assertEquals(firstNameA, medicalRecordTestA.getFirstName());
	}
	@Test
	public void medicalRecordGetLastName() {
		assertEquals(lastNameA, medicalRecordTestA.getLastName());
	}
	@Test
	public void medicalRecordGetBirthdate() {
		assertEquals(birthdateA, medicalRecordTestA.getBirthdate());
	}
	@Test
	public void medicalRecordGetMedications() {
		assertEquals(medicationsA.size(), medicalRecordTestA.getMedications().size());
		for(int i = 0; i < medicationsA.size(); i++) {
			assertEquals(medicationsA.get(i), medicalRecordTestA.getMedications().get(i));
		}
	}
	@Test
	public void medicalRecordGetAllergies() {
		assertEquals(allergiesA.size(), medicalRecordTestA.getAllergies().size());
		for(int i = 0; i < allergiesA.size(); i++) {
			assertEquals(allergiesA.get(i), medicalRecordTestA.getAllergies().get(i));
		}
	}
	
	@Test
	public void equalsTest() {
		assertFalse(medicalRecordTestA.equals(new Object()));
		
		assertFalse(medicalRecordTestA.equals(medicalRecordTestB));
		assertFalse(medicalRecordTestB.equals(medicalRecordTestA));
		
		medicalRecordTestB = new Medicalrecord(firstNameA, lastNameA, birthdateA , medicationsA, allergiesA);
		assertTrue(medicalRecordTestA.equals(medicalRecordTestB));
		assertTrue(medicalRecordTestA.equals(medicalRecordTestA));
	}
	@Test
	public void hashTest() {
		assertTrue(medicalRecordTestA.hashCode() != medicalRecordTestB.hashCode());
		medicalRecordTestB = new Medicalrecord(firstNameA, lastNameA, birthdateA , medicationsA, allergiesA);
		assertTrue(medicalRecordTestA.hashCode() == medicalRecordTestB.hashCode());
		medicalRecordTestA = new Medicalrecord(null, null, null , null, null);
		assertTrue(medicalRecordTestA.hashCode() == medicalRecordTestA.hashCode());
	}
}
