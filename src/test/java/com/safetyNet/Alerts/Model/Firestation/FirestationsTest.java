package com.safetyNet.Alerts.Model.Firestation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirestationsTest {
	public static String adressA;
	public static String adressB;
	public static String adressC;
	
	public static int stationA;
	public static int stationB;
	public static int stationC;
	
	public static Firestation firestationA;
	public static Firestation firestationB;
	public static Firestation firestationC;
	public static Firestations firestations;
	
	@BeforeAll
	public static void initBeforeAll() {
		adressA = "a";
		adressB = "b";
		adressC = "c";
		
		stationA = 1;
		stationB = 2;
		stationC = 3;
	}
	
	@BeforeEach
	public void initBeforeEach() {
		firestationA = new Firestation(adressA, stationA);
		firestationB = new Firestation(adressB, stationB);
		firestationC = new Firestation(adressC, stationC);
		
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		l.add(firestationB);
		
		firestations = new Firestations(l);
	}
	
	@Test
	public void getFirestationsTest() {
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		assertFalse(l.hashCode() == firestations.getFirestations().hashCode());
		l.add(firestationB);
		assertTrue(l.hashCode() == firestations.getFirestations().hashCode());
		l.add(firestationC);
		assertFalse(l.hashCode() == firestations.getFirestations().hashCode());
	}
	
	@Test
	public void getByAdressTest() {
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		assertTrue(l.hashCode() == firestations.getByAdress(adressA).hashCode());
	}
	
	@Test
	public void getByStation() {
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		assertTrue(l.hashCode() == firestations.getByStation(stationA).hashCode());
	}
	
	@Test
	public void concatTest() {
		List<Firestation> l1 = new ArrayList<Firestation>();
		l1.add(firestationA);
		Firestations firestations1 = new Firestations(l1);

		List<Firestation> l2 = new ArrayList<Firestation>();
		l2.add(firestationB);
		Firestations firestations2 = new Firestations(l2);

		List<Firestation> l3 = new ArrayList<Firestation>();
		l3.add(firestationA);
		l3.add(firestationB);
		Firestations firestations3 = new Firestations(l3);
		
		assertTrue(firestations1.concat(firestations2).hashCode() == firestations3.hashCode());
	}
	
	@Test
	public void containTest() {
		assertFalse(firestations.contain(firestationC));
		assertTrue(firestations.contain(firestationA));
	}
	@Test
	public void replaceTest() {
		assertFalse(firestations.replace(firestationC));
		assertTrue(firestations.replace(firestationA));
	}
	@Test
	public void deleteTest() {
		assertFalse(firestations.delete(firestationC));
		assertTrue(firestations.delete(firestationA));
	}
	@Test
	public void addTest() {
		assertFalse(firestations.add(firestationA));
		assertTrue(firestations.add(firestationC));
	}
}
