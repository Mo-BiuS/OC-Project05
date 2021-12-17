package com.safetyNet.Alerts.Unitary.Model.Person;

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

import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;

public class PersonsTest {
	private static final Logger logger = LogManager.getLogger("PersonsTest");
	
	public static String firstNameA;
	public static String firstNameB;
	public static String firstNameC;
	
	public static String lastNameA;
	public static String lastNameB;
	public static String lastNameC;
	
	public static String adressA;
	public static String adressB;
	public static String adressC;
	
	public static String city;
	
	public static String phoneA;
	public static String phoneB;
	public static String phoneC;
	
	public static String emailA;
	public static String emailB;
	public static String emailC;
	
	public static short zipA;
	public static short zipB;
	public static short zipC;
	
	public static Person personA;
	public static Person personB;
	public static Person personC;
	public static Persons persons;
	
	@BeforeAll
	public static void initBeforeAll() {
		personA = Mockito.mock(Person.class);
		personB = Mockito.mock(Person.class);
		personC = Mockito.mock(Person.class);
		
		Mockito.when(personA.getFirstName()).thenReturn(firstNameA = "a");
		Mockito.when(personB.getFirstName()).thenReturn(firstNameB = "b");
		Mockito.when(personC.getFirstName()).thenReturn(firstNameB = "c");
		
		Mockito.when(personA.getLastName()).thenReturn(lastNameA = "A");
		Mockito.when(personB.getLastName()).thenReturn(lastNameB = "B");
		Mockito.when(personC.getLastName()).thenReturn(lastNameB = "C");
		
		Mockito.when(personA.getAddress()).thenReturn(adressA = "1A");
		Mockito.when(personB.getAddress()).thenReturn(adressB = "2B");
		Mockito.when(personC.getAddress()).thenReturn(adressC = "2C");
		
		Mockito.when(personA.getCity()).thenReturn(city = "c");
		Mockito.when(personB.getCity()).thenReturn(city);
		Mockito.when(personC.getCity()).thenReturn(city);
		
		Mockito.when(personA.getPhone()).thenReturn(phoneA = "06");
		Mockito.when(personB.getPhone()).thenReturn(phoneB = "07");
		Mockito.when(personC.getPhone()).thenReturn(phoneB = "08");
		
		Mockito.when(personA.getEmail()).thenReturn(emailA = "A@A");
		Mockito.when(personB.getEmail()).thenReturn(emailB = "B@B");
		Mockito.when(personC.getEmail()).thenReturn(emailB = "C@C");
		
		Mockito.when(personA.getZip()).thenReturn((int) (zipA = 1));
		Mockito.when(personB.getZip()).thenReturn((int) (zipB = 2));
		Mockito.when(personC.getZip()).thenReturn((int) (zipB = 3));
		
		Mockito.when(personA.compare(personA)).thenReturn(true);
	}
	
	@BeforeEach
	public void initBeforeEach() {
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		l.add(personB);
		
		persons = new Persons(l);
	}
	
	@Test
	public void getPersonsTest() {
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertFalse(l.hashCode() == persons.getPersons().hashCode());
		l.add(personB);
		assertTrue(l.hashCode() == persons.getPersons().hashCode());
		l.add(personC);
		assertFalse(l.hashCode() == persons.getPersons().hashCode());
	}

	@Test
	public void getPersonByFirstName(){
		logger.info("[TESTING] Testing getPersonByFirstName :");
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByFirstName(firstNameA).hashCode());
		logger.info("getPersonByFirstName result : "+new Persons(l));
	}
	@Test
	public void getPersonByLastName(){
		logger.info("[TESTING] Testing getPersonByLastName :");
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByLastName(lastNameA).hashCode());
		logger.info("getPersonByLastName result : "+new Persons(l));
	}
	@Test
	public void getPersonByAdress(){
		logger.info("[TESTING] Testing getPersonByAdress :");
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByAdress(adressA).hashCode());
		logger.info("getPersonByAdress result : "+new Persons(l));
	}
	@Test
	public void getPersonByCity(){
		logger.info("[TESTING] Testing getPersonByCity :");
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		l.add(personB);
		assertTrue(l.hashCode() == persons.getPersonByCity(city).hashCode());
		logger.info("getPersonByCity result : "+new Persons(l));
	}
	@Test
	public void getPersonByPhone(){
		logger.info("[TESTING] Testing getPersonByPhone :");
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByPhone(phoneA).hashCode());
		logger.info("getPersonByPhone result : "+new Persons(l));
	}
	@Test
	public void getPersonByEmail(){
		logger.info("[TESTING] Testing getPersonByEmail :");
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByEmail(emailA).hashCode());
		logger.info("getPersonByEmail result : "+new Persons(l));
	}
	@Test
	public void getPersonByZip(){
		logger.info("[TESTING] Testing getPersonByZip :");
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByZip(zipA).hashCode());
		logger.info("getPersonByZip result : "+new Persons(l));
	}
	

	
	@Test
	public void concatTest() {
		List<Person> l1 = new ArrayList<Person>();
		l1.add(personA);
		Persons persons1 = new Persons(l1);

		List<Person> l2 = new ArrayList<Person>();
		l2.add(personB);
		Persons persons2 = new Persons(l2);

		List<Person> l3 = new ArrayList<Person>();
		l3.add(personA);
		l3.add(personB);
		Persons persons3 = new Persons(l3);
		
		assertTrue(persons1.concat(persons2).hashCode() == persons3.hashCode());
	}
	
	@Test
	public void containTest() {
		assertFalse(persons.contain(personC));
		assertTrue(persons.contain(personA));
	}
	@Test
	public void replaceTest() {
		assertFalse(persons.replace(personC));
		assertTrue(persons.replace(personA));
	}
	@Test
	public void deleteTest() {
		assertFalse(persons.delete(personC));
		assertTrue(persons.delete(personA));
	}
	@Test
	public void addTest() {
		assertFalse(persons.add(personA));
		assertTrue(persons.add(personC));
	}
}
