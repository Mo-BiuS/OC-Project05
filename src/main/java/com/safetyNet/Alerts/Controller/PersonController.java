package com.safetyNet.Alerts.Controller;

import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger logger = LogManager.getLogger("PersonController");
	private final Persons persons = DataHandler.getData().getPersons();
	
	@GetMapping("/person")
	public Persons findAllPerson() {
		logger.info("Requesting : /person");
		return persons;
	}

	@GetMapping("/person/{firstName}/{lastName}")
	public Persons findPerson(@PathVariable String firstName, @PathVariable String lastName) {
		logger.info("Requesting : /person/{firstName}/{lastName}");
		return persons.getPersonByFirstName(firstName).getPersonByLastName(lastName);
	}
	
	@PostMapping("/person")
	public boolean addPerson(@RequestBody Person person) throws URISyntaxException{
		logger.info("Requesting : POST /person");
		return persons.add(person);
	}
	
	@PutMapping("/person")
	public boolean updatePerson(@RequestBody Person person) {
		logger.info("Requesting : PUT /person");
		return persons.replace(person);
	}
	
	@DeleteMapping(path="/person/{firstName}/{lastName}")
	public boolean deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
		logger.info("Requesting : DELETE /person/{firstName}/{lastName}");
	    return persons.delete(new Person(firstName, lastName, null, null, 0, null, null));
	}
}
