package com.safetyNet.Alerts.Model.Firestation;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirestationsTest {
	public static String adressA;
	public static String adressB;
	
	public static int stationA;
	public static int stationB;
	
	public static Firestation firestationA;
	public static Firestation firestationB;
	public static Firestations firestations;
	
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
		
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		l.add(firestationB);
		
		firestations = new Firestations(l);
	}
	
	@Test
	public void getList() {
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		assertTrue(l != firestations.getFirestations());
		l.add(firestationB);
		assertTrue(l.hashCode() == firestations.getFirestations().hashCode());
		
		assertTrue(firestations.getFirestations().hashCode() == firestations.getFirestations().hashCode());
	}
}
