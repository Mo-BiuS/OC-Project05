package com.safetyNet.Alerts.Controller;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Service.DataHandler;

public class FirestationControllerTest {
	FirestationController firestationController = new FirestationController();;
	
	@BeforeEach
	public void initPerTest() {
		DataHandler.reloadFromJson();
	}
	
	@Test
	public void findAllFirestation() {
		List<Firestation> firestations = firestationController.findAllFirestation().getFirestations();
		assertTrue(firestations.size() == 13);
		assertTrue(firestations.get(0).getAddress().equals("1509 Culver St"));
		assertTrue(firestations.get(0).getStation() == 3);
		assertTrue(firestations.get(12).getAddress().equals("951 LoneTree Rd"));
		assertTrue(firestations.get(12).getStation() == 2);
	}
	
	@Test
	public void findFirestation() {
		List<Firestation> firestations = firestationController.findFirestation("1509 Culver St").getFirestations();
		assertTrue(firestations.size() == 1);
		assertTrue(firestations.get(0).getAddress().equals("1509 Culver St"));
		assertTrue(firestations.get(0).getStation() == 3);
	}
	@Test
	public void addFirestation() throws URISyntaxException {
		Firestation p = new Firestation("a", 0);
		firestationController.addFirestation(p);
		List<Firestation> firestations = firestationController.findFirestation("a").getFirestations();
		assertTrue(firestations.size() == 1);
		assertTrue(firestations.get(0).getAddress().equals("a"));
		assertTrue(firestations.get(0).getStation() == 0);
	}
	@Test
	public void updateFirestation() throws URISyntaxException {
		Firestation p = new Firestation("1509 Culver St", 20);
		firestationController.updateFirestation(p);
		List<Firestation> firestations = firestationController.findFirestation("1509 Culver St").getFirestations();
		assertTrue(firestations.size() == 1);
		assertTrue(firestations.get(0).getAddress().equals("1509 Culver St"));
		assertTrue(firestations.get(0).getStation() == 20);
	}
	@Test
	public void deleteFirestation() throws URISyntaxException {
		firestationController.deleteFirestation("1509 Culver St");
		List<Firestation> firestations = firestationController.findFirestation("1509 Culver St").getFirestations();
		assertTrue(firestations.size() == 0);
	}
}
