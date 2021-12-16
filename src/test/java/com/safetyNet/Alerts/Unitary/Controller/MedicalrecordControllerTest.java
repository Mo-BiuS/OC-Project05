package com.safetyNet.Alerts.Unitary.Controller;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Controller.MedicalrecordController;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Service.DataHandler;

public class MedicalrecordControllerTest {
	MedicalrecordController medicalrecordController = new MedicalrecordController();;
	
	@BeforeEach
	public void initPerTest() {
		DataHandler.reloadFromJson();
	}
	
	@Test
	public void findAllMedicalrecords() {
		List<Medicalrecord> medicalRecords = medicalrecordController.findAllMedicalrecords().getMedicalrecords();
		assertTrue(medicalRecords.size() == 23);
		assertTrue(medicalRecords.get(0).getFirstName().equals("John"));
		assertTrue(medicalRecords.get(0).getLastName().equals("Boyd"));
		assertTrue(medicalRecords.get(22).getFirstName().equals("Eric"));
		assertTrue(medicalRecords.get(22).getLastName().equals("Cadigan"));
	}
	
	@Test
	public void findMedicalrecords() {
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords("John", "Boyd").getMedicalrecords();
		assertTrue(medicalRecords.size() == 1);
		assertTrue(medicalRecords.get(0).getFirstName().equals("John"));
		assertTrue(medicalRecords.get(0).getLastName().equals("Boyd"));
	}
	@Test
	public void addPersonTest() throws URISyntaxException {
		Medicalrecord p = new Medicalrecord("a", "b", null, null, null);
		medicalrecordController.addMedicalrecords(p);
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords("a", "b").getMedicalrecords();
		assertTrue(medicalRecords.size() == 1);
		assertTrue(medicalRecords.get(0).getFirstName().equals("a"));
		assertTrue(medicalRecords.get(0).getLastName().equals("b"));
	}
	@Test
	public void updatePersonTest() throws URISyntaxException {
		Medicalrecord p = new Medicalrecord("John", "Boyd", new Date(0), new ArrayList<String>(), new ArrayList<String>());
		medicalrecordController.updateMedicalrecords(p);
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords("John", "Boyd").getMedicalrecords();
		assertTrue(medicalRecords.size() == 1);
		assertTrue(medicalRecords.get(0).getFirstName().equals("John"));
		assertTrue(medicalRecords.get(0).getLastName().equals("Boyd"));
		assertTrue(medicalRecords.get(0).getBirthdate().equals(p.getBirthdate()));
		assertTrue(medicalRecords.get(0).getAllergies().equals(p.getAllergies()));
		assertTrue(medicalRecords.get(0).getMedications().equals(p.getMedications()));
	}
	@Test
	public void deletePersonTest() throws URISyntaxException {
		medicalrecordController.deleteMedicalrecords("John", "Boyd");
		List<Medicalrecord> medicalRecords = medicalrecordController.findMedicalrecords("John", "Boyd").getMedicalrecords();
		assertTrue(medicalRecords.size() == 0);
	}
}
