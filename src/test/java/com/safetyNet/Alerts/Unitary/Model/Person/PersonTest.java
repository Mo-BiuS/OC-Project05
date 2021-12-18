package com.safetyNet.Alerts.Unitary.Model.Person;

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
	public void personSetFirstName() {
		personA.setFirstName(firstNameB);
		assertEquals(firstNameB, personA.getFirstName());
	}
	@Test
	public void personSetLastName() {
		personA.setLastName(lastNameB);
		assertEquals(lastNameB, personA.getLastName());
	}
	@Test
	public void personSetAdress() {
		personA.setAddress(adressB);
		assertEquals(adressB, personA.getAddress());
	}
	@Test
	public void personSetCity() {
		personA.setCity(cityB);
		assertEquals(cityB, personA.getCity());
	}
	@Test
	public void personSetZip() {
		personA.setZip(zipB);
		assertEquals(zipB, personA.getZip());
	}
	@Test
	public void personSetPhone() {
		personA.setPhone(phoneB);
		assertEquals(phoneB, personA.getPhone());
	}
	@Test
	public void personSetEmail() {
		personA.setEmail(emailB);
		assertEquals(emailB, personA.getEmail());
	}
	
	@Test
	public void hashTest() {
		assertTrue(personA.hashCode() != personB.hashCode());
		personB = new Person(firstNameA, lastNameA, adressA, cityA, zipA, phoneA, emailA);
		assertTrue(personA.hashCode() == personB.hashCode());
		personA = new Person(null, null, null, null, zipA, null, null);
		assertTrue(personA.hashCode() == personA.hashCode());
		
		personA = new Person(null, lastNameA, adressA, cityA, zipA, phoneA, emailA);
		personB = new Person(firstNameB, null, adressB, cityB, zipB, phoneB, emailB);
		assertTrue(personA.hashCode() != personB.hashCode());
	}
	@Test 
	public void compareTest(){
		assertFalse(personA.compare(personB));
		
		personB =  new Person(firstNameA, lastNameA, adressB, cityB, zipB, phoneB, emailB);
		assertTrue(personA.compare(personA));
		assertTrue(personA.compare(personB));
	}
}
