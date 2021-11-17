package com.safetyNet.Alerts.Model.Person;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonsTest {
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
	public static Persons persons;
	
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
		
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		l.add(personB);
		
		persons = new Persons(l);
	}
	
	@Test
	public void getList() {
		List<Person> l = new ArrayList<Person>();
		l.add(personA);
		assertTrue(l != persons.getPersons());
		l.add(personB);
		assertTrue(l.hashCode() == persons.getPersons().hashCode());
		
		assertTrue(persons.getPersons().hashCode() == persons.getPersons().hashCode());
	}
}
