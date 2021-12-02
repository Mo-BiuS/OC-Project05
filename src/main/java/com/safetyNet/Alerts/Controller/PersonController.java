package com.safetyNet.Alerts.Controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Service.DataHandler;

@RestController
public class PersonController {
	
	private final Persons persons = DataHandler.DATA.getPersons();
	
	@GetMapping("/person")
	public Persons findAllPerson() {
		return persons;
	}

	@GetMapping("/person/{firstName}/{lastName}")
	public Persons findPerson(@PathVariable String firstName, @PathVariable String lastName) {
		return persons.getPersonByFirstName(firstName).getPersonByLastName(lastName);
	}
	
	@PostMapping("/person")
	public boolean addPerson(@RequestBody Person person) throws URISyntaxException{
		return persons.add(person);
	}
	
	@PutMapping("/person")
	public boolean updatePerson(@RequestBody Person person) {
		return persons.replace(person);
	}
	
	@DeleteMapping(path="/person/{firstName}/{lastName}")
	public boolean deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
	    return persons.delete(new Person(firstName, lastName, null, null, 0, null, null));
	}
}
