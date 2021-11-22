package com.safetyNet.Alerts.Model.Person;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Persons {

	private final List<Person> persons;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Persons(@JsonProperty("persons") List<Person> persons) {
		this.persons = persons;
	}

	public List<Person> getPersons() {
		return persons;
	}
	
	public List<Person> getPersonByFirstName(String value){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getFirstName()))list.add(persons.get(i));
		}
		return list;
	}
	public List<Person> getPersonByLastName(String value){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getLastName()))list.add(persons.get(i));
		}
		return list;
	}
	public List<Person> getPersonByAdress(String value){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getAddress()))list.add(persons.get(i));
		}
		return list;
	}
	public List<Person> getPersonByCity(String value){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getCity()))list.add(persons.get(i));
		}
		return list;
	}
	public List<Person> getPersonByPhone(String value){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getPhone()))list.add(persons.get(i));
		}
		return list;
	}
	public List<Person> getPersonByEmail(String value){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value.equals(persons.get(i).getEmail()))list.add(persons.get(i));
		}
		return list;
	}
	public List<Person> getPersonByZip(int value){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < persons.size(); i++) {
			if(value == persons.get(i).getZip())list.add(persons.get(i));
		}
		return list;
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
