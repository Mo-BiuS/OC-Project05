package com.safetyNet.Alerts.Model.MedicalRecord;

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
	public static Medicalrecords medicalRecords;
	
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
		
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		l.add(medicalRecordTestB);
		
		medicalRecords = new Medicalrecords(l);
	}
	
	@Test
	public void getList() {
		List<Medicalrecord> l = new ArrayList<Medicalrecord>();
		l.add(medicalRecordTestA);
		assertTrue(l != medicalRecords.getMedicalrecords());
		l.add(medicalRecordTestB);
		assertTrue(l.hashCode() == medicalRecords.getMedicalrecords().hashCode());
		
		assertTrue(medicalRecords.getMedicalrecords().hashCode() == medicalRecords.getMedicalrecords().hashCode());
	}
}
