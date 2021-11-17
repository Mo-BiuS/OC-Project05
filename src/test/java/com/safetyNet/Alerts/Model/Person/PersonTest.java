package com.safetyNet.Alerts.Model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Model.Person.Person;

public class PersonTest {

	public static String firstNameA;
	public static String firstNameB;
	
	public static String lastNameA;
	public static String lastNameB;
	
	public static String adressA;
	public static String adressB;
	
	public static String cityA;
	public static String cityB;
	
	public static String phoneA;
	public static String phoneB;
	
	public static String emailA;
	public static String emailB;
	
	public static short zipA;
	public static short zipB;
	
	public static Person personA;
	public static Person personB;
	
	@BeforeAll
	public static void initBeforeAll() {
		firstNameA = "a";
		firstNameB = "b";
		
		lastNameA = "A";
		lastNameB = "B";
		
		adressA = "1A";
		adressB = "2B";
		
		cityA = "cA";
		cityB = "cB";
		
		phoneA = "06";
		phoneB = "07";
		
		emailA = "A@A";
		emailB = "B@B";
		
		zipA = 1;
		zipB = 2;
	}
	
	@BeforeEach
	public void initBeforeEach() {
		personA = new Person(firstNameA, lastNameA, adressA, cityA, zipA, phoneA, emailA);
		personB = new Person(firstNameB, lastNameB, adressB, cityB, zipB, phoneB, emailB);
	}
	
	
	@Test
	public void personGetFirstName() {
		assertEquals(firstNameA, personA.getFirstName());
	}
	@Test
	public void personGetLastName() {
		assertEquals(lastNameA, personA.getLastName());
	}
	@Test
	public void personGetAdress() {
		assertEquals(adressA, personA.getAddress());
	}
	@Test
	public void personGetCity() {
		assertEquals(cityA, personA.getCity());
	}
	@Test
	public void personGetZip() {
		assertEquals(zipA, personA.getZip());
	}
	@Test
	public void personGetPhone() {
		assertEquals(phoneA, personA.getPhone());
	}
	@Test
	public void personGetEmail() {
		assertEquals(emailA, personA.getEmail());
	}
	
	@Test
	public void equalsTestNonEquals() {
		assertFalse(personA.equals(personB));
	}
	@Test
	public void equalsTestOtherObject() {
		assertFalse(personA.equals(new Object()));
	}
	@Test
	public void equalsTestSameObject() {
		assertTrue(personA.equals(personA));
	}
	@Test
	public void equalsTestEquals() {
		assertFalse(personA.equals(personB));
		personB = new Person(firstNameA, lastNameA, adressA, cityA, zipA, phoneA, emailA);
		assertTrue(personA.equals(personA));
	}
}
