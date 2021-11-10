package com.safetyNet.Alerts.Model.Sub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicationTest {
	public static String productNameA;
	public static String productNameB;
	
	public static float posologyInMgA;
	public static float posologyInMgB;
	
	public static Medication medicationA;
	public static Medication medicationB;
	
	@BeforeAll
	public static void initBeforeAll() {
		productNameA = "a";
		productNameB = "b";
		
		posologyInMgA = 1;
		posologyInMgB = 2;
	}
	
	@BeforeEach
	public void initBeforeEach() {
		medicationA = new Medication(productNameA, posologyInMgA);
		medicationB = new Medication(productNameB, posologyInMgB);
	}
	
	
	@Test
	public void medicalRecordGetProductName() {
		assertEquals(productNameA, medicationA.getProductName());
	}
	@Test
	public void medicalRecordGetPosologyInMg() {
		assertEquals(posologyInMgA, medicationA.getPosologyInMg());
	}
	
	
	@Test
	public void medicalRecordSetProductName() {
		medicationA.setProductName(productNameB);
		assertEquals(productNameB, medicationA.getProductName());
	}
	@Test
	public void medicalRecordSetPosologyInMg() {
		medicationA.setPosologyInMg(posologyInMgB);
		assertEquals(posologyInMgB, medicationA.getPosologyInMg());
	}
	
	@Test
	public void equalsTestNonEquals() {
		assertFalse(medicationA.equals(medicationB));
	}
	@Test
	public void equalsTestSameObject() {
		assertTrue(medicationA.equals(medicationA));
	}
	@Test
	public void equalsTestEquals() {
		medicationA.setProductName(productNameB);
		assertFalse(medicationA.equals(medicationB));
		
		medicationA.setPosologyInMg(posologyInMgB);
		assertTrue(medicationA.equals(medicationA));
	}
}
