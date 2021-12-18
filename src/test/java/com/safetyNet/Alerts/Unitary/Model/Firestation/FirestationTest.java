package com.safetyNet.Alerts.Unitary.Model.Firestation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Firestation.Firestation;

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
	public void firestationGetAdress() {
		assertEquals(adressA, firestationA.getAddress());
	}
	@Test
	public void firestationGetStation() {
		assertEquals(stationA, firestationA.getStation());
	}
	
	@Test
	public void firestationSetAdress() {
		firestationA.setAddress(adressB);
		assertEquals(adressB, firestationA.getAddress());
	}
	@Test
	public void firestationSetStation() {

		firestationA.setStation(stationB);
		assertEquals(stationB, firestationA.getStation());
	}
	

	@Test
	public void hashTest() {
		assertTrue(firestationA.hashCode() != firestationB.hashCode());
		firestationB = new Firestation(adressA, stationA);
		assertTrue(firestationA.hashCode() == firestationB.hashCode());
		firestationA = new Firestation(null, stationA);
		assertTrue(firestationA.hashCode() == firestationA.hashCode());
		
		firestationA = new Firestation(null, stationA);
		firestationB = new Firestation(adressB, 0);
		assertTrue(firestationA.hashCode() != firestationB.hashCode());
	}
	@Test 
	public void compareTest(){
		assertFalse(firestationA.compare(firestationB));
		
		firestationB = new Firestation(adressA, stationA);
		assertTrue(firestationA.compare(firestationA));
		assertTrue(firestationA.compare(firestationB));
	}
}