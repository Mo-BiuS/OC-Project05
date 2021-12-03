package com.safetyNet.Alerts.Model.Person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonsTest {
	public static String firstNameA;
	public static String firstNameB;
	public static String firstNameC;
	
	public static String lastNameA;
	public static String lastNameB;
	public static String lastNameC;
	
	public static String adressA;
	public static String adressB;
	public static String adressC;
	
	public static String cityA;
	public static String cityB;
	public static String cityC;
	
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
		firstNameA = "a";
		firstNameB = "b";
		firstNameB = "c";
		
		lastNameA = "A";
		lastNameB = "B";
		lastNameB = "C";
		
		adressA = "1A";
		adressB = "2B";
		adressB = "2C";
		
		cityA = "cA";
		cityB = "cB";
		cityB = "cC";
		
		phoneA = "06";
		phoneB = "07";
		phoneB = "08";
		
		emailA = "A@A";
		emailB = "B@B";
		emailB = "C@C";
		
		zipA = 1;
		zipB = 2;
		zipB = 3;
	}
	
	@BeforeEach
	public void initBeforeEach() {
		personA = new Person(firstNameA, lastNameA, adressA, cityA, zipA, phoneA, emailA);
		personB = new Person(firstNameB, lastNameB, adressB, cityB, zipB, phoneB, emailB);
		personC = new Person(firstNameC, lastNameC, adressC, cityC, zipC, phoneC, emailC);
		
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
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByFirstName(firstNameA).hashCode());
	}
	@Test
	public void getPersonByLastName(){
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByLastName(lastNameA).hashCode());
	}
	@Test
	public void getPersonByAdress(){
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByAdress(adressA).hashCode());
	}
	@Test
	public void getPersonByCity(){
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByCity(cityA).hashCode());
	}
	@Test
	public void getPersonByPhone(){
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByPhone(phoneA).hashCode());
	}
	@Test
	public void getPersonByEmail(){
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByEmail(emailA).hashCode());
	}
	@Test
	public void getPersonByZip(){
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l.hashCode() == persons.getPersonByZip(zipA).hashCode());
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
