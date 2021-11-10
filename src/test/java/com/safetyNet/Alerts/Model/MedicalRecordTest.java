package com.safetyNet.Alerts.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Sub.Medication;

public class MedicalRecordTest {
	public static String firstNameA;
	public static String firstNameB;
	
	public static String lastNameA;
	public static String lastNameB;
	
	public static Date birthdateA;
	public static Date birthdateB;
	
	public static ArrayList<Medication> medicationsA;
	public static ArrayList<Medication> medicationsB;
	
	public static ArrayList<String> allergiesA;
	public static ArrayList<String> allergiesB;
	
	public static MedicalRecord medicalRecordTestA;
	public static MedicalRecord medicalRecordTestB;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public static void initBeforeAll() {
		firstNameA = "a";
		firstNameB = "b";
		
		lastNameA = "A";
		lastNameB = "B";
		
		birthdateA = new Date(1990, 10, 20, 20, 20, 20);
		birthdateB = new Date(1995, 10, 20, 20, 20, 20);
		
		medicationsA = new ArrayList<Medication>();
		medicationsA.add(new Medication("aznol",350));
		medicationsA.add(new Medication("hydrapermazol",100));
		medicationsB = new ArrayList<Medication>();
		
		allergiesA = new ArrayList<String>();
		allergiesB = new ArrayList<String>();
		allergiesB.add("peanut");
	}
	
	@BeforeEach
	public void initBeforeEach() {
		medicalRecordTestA = new MedicalRecord(firstNameA, lastNameA, birthdateA , medicationsA, allergiesA);
		medicalRecordTestB = new MedicalRecord(firstNameB, lastNameB, birthdateB , medicationsB, allergiesB);
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
	public void medicalRecordSetFirstName() {
		medicalRecordTestA.setFirstName(firstNameB);
		assertEquals(firstNameB, medicalRecordTestA.getFirstName());
	}
	@Test
	public void medicalRecordSetLastName() {
		medicalRecordTestA.setLastName(lastNameB);
		assertEquals(lastNameB, medicalRecordTestA.getLastName());
	}
	@Test
	public void medicalRecordSetBirthdate() {
		medicalRecordTestA.setBirthdate(birthdateB);
		assertEquals(birthdateB, medicalRecordTestA.getBirthdate());
	}
	@Test
	public void medicalRecordSetMedications() {
		medicalRecordTestA.setMedications(medicationsB);
		assertEquals(medicationsB.size(), medicalRecordTestA.getMedications().size());
		for(int i = 0; i < medicationsB.size(); i++) {
			assertEquals(medicationsB.get(i), medicalRecordTestA.getMedications().get(i));
		}
	}
	@Test
	public void medicalRecordSetAllergies() {
		medicalRecordTestA.setAllergies(allergiesB);
		assertEquals(allergiesB.size(), medicalRecordTestA.getAllergies().size());
		for(int i = 0; i < allergiesB.size(); i++) {
			assertEquals(allergiesB.get(i), medicalRecordTestA.getAllergies().get(i));
		}
	}
	
	@Test
	public void equalsTestNonEquals() {
		assertFalse(medicalRecordTestA.equals(medicalRecordTestB));
	}
	@Test
	public void equalsTestSameObject() {
		assertTrue(medicalRecordTestA.equals(medicalRecordTestA));
	}
	@Test
	public void equalsTestEquals() {
		medicalRecordTestA.setAllergies(allergiesB);
		assertFalse(medicalRecordTestA.equals(medicalRecordTestB));
		
		medicalRecordTestA.setBirthdate(birthdateB);
		assertFalse(medicalRecordTestA.equals(medicalRecordTestB));
		
		medicalRecordTestA.setFirstName(firstNameB);
		assertFalse(medicalRecordTestA.equals(medicalRecordTestB));
		
		medicalRecordTestA.setLastName(lastNameB);
		assertFalse(medicalRecordTestA.equals(medicalRecordTestB));
		
		medicalRecordTestA.setMedications(medicationsB);
		assertTrue(medicalRecordTestA.equals(medicalRecordTestB));
	}
}
