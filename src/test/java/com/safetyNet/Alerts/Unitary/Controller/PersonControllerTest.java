package com.safetyNet.Alerts.Unitary.Controller;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Controller.PersonController;
import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.DataHandler;

public class PersonControllerTest {
	PersonController personController = new PersonController();
	private static final Logger logger = LogManager.getLogger("PersonControllerTest");
	
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
	
	@BeforeAll
	public static void initBeforeAll() throws ParseException {
		firstNameA = "a";
		firstNameB = "b";
		firstNameC = "c";
		
		lastNameA = "A";
		lastNameB = "B";
		lastNameC = "C";
		
		adressA = "1A";
		adressB = "2B";
		adressC = "3C";
		
		city = "c";
		
		phoneA = "06";
		phoneB = "07";
		phoneC = "08";
		
		emailA = "A@A";
		emailB = "B@B";
		emailC = "C@C";
		
		zipA = 1;
		zipB = 2;
		zipC = 3;
		
		personA = new Person(firstNameA, lastNameA, adressA, city, zipA, phoneA, emailA);
		personB = new Person(firstNameB, lastNameB, adressB, city, zipB, phoneB, emailB);
		personC = new Person(firstNameC, lastNameC, adressC, city, zipC, phoneC, emailC);
		
		ArrayList<Person> p = new ArrayList<Person>();
		ArrayList<Firestation> f =  new ArrayList<Firestation>();
		ArrayList<Medicalrecord> m = new ArrayList<Medicalrecord>();
		
		p.add(personA);
		p.add(personB);
		
		DataHandler.loadData(new Data(p,f,m));
	}
	@BeforeEach
	public void initPerTest() {
		ArrayList<Person> p = new ArrayList<Person>();
		ArrayList<Firestation> f =  new ArrayList<Firestation>();
		ArrayList<Medicalrecord> m = new ArrayList<Medicalrecord>();
		
		p.add(personA);
		p.add(personB);
		
		DataHandler.loadData(new Data(p,f,m));
	}
	
	@Test
	public void getAllPersonTest() {
		logger.info("[TESTING] Testing getAllPersonTest :");
		List<Person> persons = personController.findAllPerson().getPersons();
		assertTrue(persons.size() == 2);
		assertTrue(persons.contains(personA));
		assertTrue(persons.contains(personB));
	}
	
	@Test
	public void findPersonTest() {
		logger.info("[TESTING] Testing findPersonTest :");
		List<Person> persons = personController.findPerson(firstNameA, lastNameA).getPersons();
		assertTrue(persons.size() == 1);
		assertTrue(persons.contains(personA));
	}
	@Test
	public void addPersonTest() throws URISyntaxException {
		logger.info("[TESTING] Testing addPersonTest :");
		personController.addPerson(personC);
		List<Person> persons = personController.findPerson(firstNameC, lastNameC).getPersons();
		assertTrue(persons.size() == 1);
		assertTrue(persons.contains(personC));
	}
	@Test
	public void updatePersonTest() throws URISyntaxException {
		logger.info("[TESTING] Testing updatePersonTest :");
		Person p = new Person(firstNameA, lastNameA, "a", "b", 20, "c", "d");
		personController.updatePerson(p);
		List<Person> persons = personController.findPerson(firstNameA, lastNameA).getPersons();
		assertTrue(persons.size() == 1);
		assertTrue(persons.get(0).getFirstName().equals(firstNameA));
		assertTrue(persons.get(0).getLastName().equals(lastNameA));
		assertTrue(persons.get(0).getAddress().equals("a"));
		assertTrue(persons.get(0).getCity().equals("b"));
		assertTrue(persons.get(0).getZip() == 20);
		assertTrue(persons.get(0).getPhone().equals("c"));
		assertTrue(persons.get(0).getEmail().equals("d"));
	}
	@Test
	public void deletePersonTest() throws URISyntaxException {
		logger.info("[TESTING] Testing deletePersonTest :");
		personController.deletePerson(firstNameA, lastNameA);
		List<Person> persons = personController.findPerson(firstNameA, lastNameA).getPersons();
		assertTrue(persons.size() == 0);
	}
}
