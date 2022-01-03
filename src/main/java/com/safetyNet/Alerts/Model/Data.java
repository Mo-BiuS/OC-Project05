package com.safetyNet.Alerts.Model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Model.Person.Persons;

/** 
 * Class containing and handling data
 * @author Mo-Bius
 */
public final class Data {
	
	private static final Logger logger = LogManager.getLogger("Data");
	
	private final Persons persons;
	private final Firestations firestations;
	private final Medicalrecords medicalrecords;
	
	//=======================================[Constructor]=======================================
	/**
	 * Class constructor.
	 * @param persons List<Person>
	 * @param firestations List<Firestation>
	 * @param medicalrecords List<Medicalrecord>
	 */
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Data(@JsonProperty("persons") List<Person> persons, 
					   @JsonProperty("firestations")List<Firestation> firestations, 
					   @JsonProperty("medicalrecords") List<Medicalrecord> medicalrecords) {
		
		this.persons = new Persons(persons);
		this.firestations = new Firestations(firestations);
		this.medicalrecords = new Medicalrecords(medicalrecords);
		
		logger.info("Loading new dataset : "+persons.size()+" persons / "+firestations.size()+" firestations / "+medicalrecords.size()+" medicalRecords");
	}

	//=======================================[getters]=======================================
	public Persons getPersons() {
		logger.info("Requesting persons");
		return this.persons;
	}
	public Firestations getFirestations() {
		logger.info("Requesting firestations");
		return this.firestations;
	}
	public Medicalrecords getMedicalrecords() {
		logger.info("Requesting medicalrecords");
		return this.medicalrecords;
	}

	//=======================================[Functions]=======================================
	@Override
	public String toString() {
		return  "{"+this.persons+","+
				this.firestations+","+
				this.medicalrecords+"}";
	}
}
