package com.safetyNet.Alerts.Model.Person;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Represents a group of person from the data model.
 * @author Mo-Bius
 */
public class Persons {

	private static final Logger logger = LogManager.getLogger("Persons");
	private List<Person> persons;
	
	//=======================================[Constructor]=======================================
	/**
	 * Class constructor.
	 * @param persons List<person>
	 */
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Persons(@JsonProperty("persons") List<Person> persons) {
		this.persons = persons;
	}

	//=======================================[getters]=======================================
	public List<Person> getPersons() {
		return persons;
	}
	
	/**
	 * Getting all entity with the same first name
	 * @param value, string representing a first name
	 * @return Persons, a sub list.
	 */
	public Persons getPersonByFirstName(String value){
		
		logger.info("getting by first name : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getFirstName()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	/**
	 * Getting all entity with the same last name
	 * @param value, string representing a last name
	 * @return Persons, a sub list.
	 */
	public Persons getPersonByLastName(String value){
		
		logger.info("getting by last name : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getLastName()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	/**
	 * Getting all entity with the same address
	 * @param value, string representing an address
	 * @return Persons, a sub list.
	 */
	public Persons getPersonByAdress(String value){
		
		logger.info("getting by address : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getAddress()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	/**
	 * Getting all entity with the same city
	 * @param value, string representing a city
	 * @return Persons, a sub list.
	 */
	public Persons getPersonByCity(String value){
		
		logger.info("getting by city : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getCity()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	/**
	 * Getting all entity with the same phone
	 * @param value, string representing a phone
	 * @return Persons, a sub list.
	 */
	public Persons getPersonByPhone(String value){
		
		logger.info("getting by phone : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getPhone()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	/**
	 * Getting all entity with the same email
	 * @param value, string representing an email
	 * @return Persons, a sub list.
	 */
	public Persons getPersonByEmail(String value){
		
		logger.info("getting by email : "+value);
		
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getEmail()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	/**
	 * Getting all entity with the same zip
	 * @param value, int representing a zip
	 * @return Persons, a sub list.
	 */
	public Persons getPersonByZip(int value){
		
		logger.info("getting by zip : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value == persons.get(i).getZip())list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	//=======================================[Functions]=======================================
	/**
	 * Concatenation of this list and another
	 * @param persons, the other list to concat
	 * @return Persons, a new list containing the two others.
	 */
	public Persons concat(Persons persons) {
		List<Person> list = new ArrayList<Person>();
		list.addAll(this.persons);
		list.addAll(persons.getPersons());
		return new Persons(list);
	}

	/**
	 * Search to find if a person is inside this list.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param person
	 * @return boolean, true if found
	 */
	public boolean contain(Person person) {
		for(Person p : persons)
			if(p.compare(person))return true;
		
		return false;
	}
	
	/**
	 * Find then replace a person by another.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param person
	 * @return boolean, true if success
	 */
	public boolean replace(Person person) {
		for(Person p : persons)
			if(p.compare(person)) {
				p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setEmail(person.getEmail());
				p.setPhone(person.getPhone());
				p.setZip(person.getZip());
				return true;
			}
		return false;
	}
	
	/**
	 * Find then delete a person.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param person
	 * @return boolean, true if success
	 */
	public boolean delete(Person person) {
		for(Person p : persons)
			if(p.compare(person)) {
				persons.remove(p);
				return true;
			}
		return false;
	}
	
	/**
	 * Check if the person already exist and if not, add it to the list.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param person
	 * @return boolean, true if success
	 */
	public boolean add(Person person) {
		if(!this.contain(person)) {
			persons.add(person);
			return true;
		}
		else return false;
	}
	
	@Override
	public int hashCode() {
		return persons.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		return (o != null && 
			   (o instanceof Persons) &&
			   (o.hashCode() == this.hashCode()));
		
	}
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Persons: [");
		for(int i = 0 ; i < this.getPersons().size(); i++) {
			buf.append("\n"+this.getPersons().get(i));
			if(i+1 < this.getPersons().size())buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}
}
