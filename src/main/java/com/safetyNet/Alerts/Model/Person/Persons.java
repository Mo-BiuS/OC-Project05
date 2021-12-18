package com.safetyNet.Alerts.Model.Person;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Persons {

	private static final Logger logger = LogManager.getLogger("Persons");
	private List<Person> persons;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Persons(@JsonProperty("persons") List<Person> persons) {
		this.persons = persons;
	}

	public List<Person> getPersons() {
		return persons;
	}
	
	public Persons getPersonByFirstName(String value){
		
		logger.info("getting by first name : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getFirstName()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	public Persons getPersonByLastName(String value){
		
		logger.info("getting by last name : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getLastName()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	public Persons getPersonByAdress(String value){
		
		logger.info("getting by address : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getAddress()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	public Persons getPersonByCity(String value){
		
		logger.info("getting by city : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getCity()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	public Persons getPersonByPhone(String value){
		
		logger.info("getting by phone : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getPhone()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	public Persons getPersonByEmail(String value){
		
		logger.info("getting by email : "+value);
		
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getEmail()))list.add(persons.get(i));
		}
		return new Persons(list);
	}
	public Persons getPersonByZip(int value){
		
		logger.info("getting by zip : "+value);
		
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value == persons.get(i).getZip())list.add(persons.get(i));
		}
		return new Persons(list);
	}
	
	public Persons concat(Persons persons) {
		List<Person> list = new ArrayList<Person>();
		list.addAll(this.persons);
		list.addAll(persons.getPersons());
		return new Persons(list);
	}

	public boolean contain(Person person) {
		for(Person p : persons)
			if(p.compare(person))return true;
		
		return false;
	}
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
	public boolean delete(Person person) {
		for(Person p : persons)
			if(p.compare(person)) {
				persons.remove(p);
				return true;
			}
		return false;
	}
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
