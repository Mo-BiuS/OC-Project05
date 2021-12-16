package com.safetyNet.Alerts.Unitary.Controller;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetyNet.Alerts.Controller.PersonController;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.DataHandler;

public class PersonControllerTest {

	PersonController personController = new PersonController();;
	
	@BeforeEach
	public void initPerTest() {
		DataHandler.reloadFromJson();
	}
	
	@Test
	public void getAllPersonTest() {
		List<Person> persons = personController.findAllPerson().getPersons();
		assertTrue(persons.size() == 23);
		assertTrue(persons.get(0).getFirstName().equals("John"));
		assertTrue(persons.get(0).getLastName().equals("Boyd"));
		assertTrue(persons.get(22).getFirstName().equals("Eric"));
		assertTrue(persons.get(22).getLastName().equals("Cadigan"));
	}
	
	@Test
	public void findPersonTest() {
		List<Person> persons = personController.findPerson("John", "Boyd").getPersons();
		assertTrue(persons.size() == 1);
		assertTrue(persons.get(0).getFirstName().equals("John"));
		assertTrue(persons.get(0).getLastName().equals("Boyd"));
	}
	@Test
	public void addPersonTest() throws URISyntaxException {
		Person p = new Person("a", "b", null, null, 0, null, null);
		personController.addPerson(p);
		List<Person> persons = personController.findPerson("a", "b").getPersons();
		assertTrue(persons.size() == 1);
		assertTrue(persons.get(0).getFirstName().equals("a"));
		assertTrue(persons.get(0).getLastName().equals("b"));
	}
	@Test
	public void updatePersonTest() throws URISyntaxException {
		Person p = new Person("John", "Boyd", "a", "b", 20, "c", "d");
		personController.updatePerson(p);
		List<Person> persons = personController.findPerson("John", "Boyd").getPersons();
		assertTrue(persons.size() == 1);
		assertTrue(persons.get(0).getFirstName().equals("John"));
		assertTrue(persons.get(0).getLastName().equals("Boyd"));
		assertTrue(persons.get(0).getAddress().equals("a"));
		assertTrue(persons.get(0).getCity().equals("b"));
		assertTrue(persons.get(0).getZip() == 20);
		assertTrue(persons.get(0).getPhone().equals("c"));
		assertTrue(persons.get(0).getEmail().equals("d"));
	}
	@Test
	public void deletePersonTest() throws URISyntaxException {
		personController.deletePerson("John", "Boyd");
		List<Person> persons = personController.findPerson("John", "Boyd").getPersons();
		assertTrue(persons.size() == 0);
	}
}
