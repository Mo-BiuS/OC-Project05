package com.safetyNet.Alerts.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Sub.Medication;

public class FirestationTest {

	public static String adressA;
	public static String adressB;
	
	public static int stationA;
	public static int stationB;
	
	public static Firestation firestationA;
	public static Firestation firestationB;
	
	@BeforeAll
	public static void initBeforeAll() {
		adressA = "a";
		adressB = "b";
		
		stationA = 1;
		stationB = 2;
	}
	
	@BeforeEach
	public void initBeforeEach() {
		firestationA = new Firestation(adressA, stationA);
		firestationB = new Firestation(adressB, stationB);
	}
	
	
	@Test
	public void medicalRecordGetAdress() {
		assertEquals(adressA, firestationA.getAdress());
	}
	@Test
	public void medicalRecordGetStation() {
		assertEquals(stationA, firestationA.getStation());
	}
	
	
	@Test
	public void medicalRecordSetAdress() {
		firestationA.setAdress(adressB);
		assertEquals(adressB, firestationA.getAdress());
	}
	@Test
	public void medicalRecordSetStation() {
		firestationA.setStation(stationB);
		assertEquals(stationB, firestationA.getStation());
	}
	
	@Test
	public void equalsTestNonEquals() {
		assertFalse(firestationA.equals(firestationB));
	}
	@Test
	public void equalsTestSameObject() {
		assertTrue(firestationA.equals(firestationA));
	}
	@Test
	public void equalsTestEquals() {
		firestationA.setAdress(adressB);
		assertFalse(firestationA.equals(firestationB));
		
		firestationA.setStation(stationB);
		assertTrue(firestationA.equals(firestationA));
	}
}