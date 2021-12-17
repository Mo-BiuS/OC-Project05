package com.safetyNet.Alerts.Unitary.Model.Firestation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Firestation.Firestations;

public class FirestationsTest {
	private static final Logger logger = LogManager.getLogger("FirestationsTest");
	
	
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
		firestationA = Mockito.mock(Firestation.class);
		firestationB = Mockito.mock(Firestation.class);
		firestationC = Mockito.mock(Firestation.class);
		
		Mockito.when(firestationA.getStation()).thenReturn(stationA =1);
		Mockito.when(firestationB.getStation()).thenReturn(stationB =2);
		Mockito.when(firestationC.getStation()).thenReturn(stationC =3);
		
		Mockito.when(firestationA.getAddress()).thenReturn(adressA ="a");
		Mockito.when(firestationB.getAddress()).thenReturn(adressB ="b");
		Mockito.when(firestationC.getAddress()).thenReturn(adressC ="c");
		
		Mockito.when(firestationA.compare(firestationA)).thenReturn(true);
	}
	
	@BeforeEach
	public void initBeforeEach() {
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
		logger.info("[TESTING] Testing getByAdressTest :");
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		assertTrue(l.hashCode() == firestations.getByAdress(adressA).hashCode());
		logger.info("getByAdressTest result : "+new Firestations(l));
	}
	
	@Test
	public void getByStation() {
		logger.info("[TESTING] Testing getByStation :");
		List<Firestation> l = new ArrayList<Firestation>();
		l.add(firestationA);
		assertTrue(l.hashCode() == firestations.getByStation(stationA).hashCode());
		logger.info("getByStation result : "+new Firestations(l));
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
