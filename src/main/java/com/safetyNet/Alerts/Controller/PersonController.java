package com.safetyNet.Alerts.Controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
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
	public Persons findAllBooks() {
		return persons;
	}

	@GetMapping("/person/{firstName}/{lastName}")
	public Persons findBookById(@PathVariable String firstName, @PathVariable String lastName) {
		return persons.getPersonByFirstName(firstName).getPersonByLastName(lastName);
	}
	
	@PostMapping("/person")
	public ResponseEntity<Person> addBook(@RequestBody Person person) throws URISyntaxException{
		persons.add(person);
		return ResponseEntity.created(new URI("/rest/v1/books/" + person.getFirstName() + person.getLastName())).body(person);
	}
	
	@PutMapping("/person")
	public ResponseEntity<Void> updateBook(@RequestBody Person person) {
		persons.replace(person);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/person/{firstName}/{lastName}")
	public ResponseEntity<Void> deleteBookById(@PathVariable String firstName, @PathVariable String lastName) {
	    persons.delete(new Person(firstName, lastName, null, null, 0, null, null));
	    return ResponseEntity.ok().build();

	}
}
