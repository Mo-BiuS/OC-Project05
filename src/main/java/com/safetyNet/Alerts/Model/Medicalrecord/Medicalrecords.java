package com.safetyNet.Alerts.Model.Medicalrecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Represents a group of medical record from the data model.
 * @author Mo-Bius
 */
public class Medicalrecords {
	private static final Logger logger = LogManager.getLogger("Medicalrecords");
	private  List<Medicalrecord> medicalrecords;
	
	//=======================================[Constructor]=======================================
	/**
	 * Class constructor.
	 * @param medicalrecords List<Medicalrecord>
	 */
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Medicalrecords(@JsonProperty("medicalrecords") List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
	
	//=======================================[getters]=======================================
	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}
	
	/**
	 * Getting all entity with the same first name
	 * @param value, string representing a first name
	 * @return Medicalrecords, a sub list.
	 */
	public Medicalrecords getMedicalrecordByFirstName(String value){
		
		logger.info("getting by first name : "+value);
		
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.equals(medicalrecords.get(i).getFirstName()))list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	
	/**
	 * Getting all entity with the same last name
	 * @param value, string representing a last name
	 * @return Medicalrecords, a sub list.
	 */
	public Medicalrecords getMedicalrecordByLastName(String value){
		
		logger.info("getting by last name : "+value);
		
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.equals(medicalrecords.get(i).getLastName()))list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	
	/**
	 * Getting all entity with the same birthday
	 * @param value, date representing a birthday
	 * @return Medicalrecords, a sub list.
	 */
	public Medicalrecords getMedicalrecordByBirthdayEqualTo(Date value){
		
		logger.info("getting by birthday = to "+value);
		
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() == medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	
	/**
	 * Getting all entity with a birthday inferior to the asked value
	 * @param value, date representing a birthday
	 * @return Medicalrecords, a sub list.
	 */
	public Medicalrecords getMedicalrecordByBirthdayInferiorTo(Date value){
		
		logger.info("getting by birthday < to : "+value);
		
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() < medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	
	/**
	 * Getting all entity with a birthday superior to the asked value
	 * @param value, date representing a birthday
	 * @return Medicalrecords, a sub list.
	 */
	public Medicalrecords getMedicalrecordByBirthdaySuperiorTo(Date value){
		
		logger.info("getting by birthday > to "+value);
		
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() > medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	
	/**
	 * Getting all entity containing the same medication
	 * @param value, string representing a medication
	 * @return Medicalrecords, a sub list.
	 */
	public Medicalrecords getMedicalrecordByMedication(String value){
		
		logger.info("getting by medication : "+value);
		
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			for(int j = 0; j < medicalrecords.get(i).getMedications().size(); j++) 
			{
				if(value.equals(medicalrecords.get(i).getMedications().get(i))) {
					list.add(medicalrecords.get(i));
					break;
				}
			}
		}
		return new Medicalrecords(list);
	}
	
	/**
	 * Getting all entity containing the same allergie
	 * @param value, string representing a allergie
	 * @return Medicalrecords, a sub list.
	 */
	public Medicalrecords getMedicalrecordByAllergies(String value){
		
		logger.info("getting by allergies : "+value);
		
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			for(int j = 0; j < medicalrecords.get(i).getAllergies().size(); j++) 
			{
				if(value.equals(medicalrecords.get(i).getAllergies().get(j))) {
					list.add(medicalrecords.get(i));
					break;
				}
			}
		}
		return new Medicalrecords(list);
	}
	
	//=======================================[Functions]=======================================
	/**
	 * Concatenation of this list and another
	 * @param medicalrecords, the other list to concat
	 * @return Medicalrecords, a new list containing the two others.
	 */
	public Medicalrecords concat(Medicalrecords medicalrecords) {
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		list.addAll(this.medicalrecords);
		list.addAll(medicalrecords.getMedicalrecords());
		return new Medicalrecords(list);
	}
	
	/**
	 * Search to find if a medicalrecord is inside this list.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param medicalrecord
	 * @return boolean, true if found
	 */
	public boolean contain(Medicalrecord medicalrecord) {
		for(Medicalrecord m : medicalrecords)
			if(m.compare(medicalrecord))return true;
		
		return false;
	}
	
	/**
	 * Find then replace a medicalrecord by another.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param medicalrecord
	 * @return boolean, true if success
	 */
	public boolean replace(Medicalrecord medicalrecord) {
		for(Medicalrecord p : medicalrecords)
			if(p.compare(medicalrecord)) {
				p.setAllergies(medicalrecord.getAllergies());
				p.setBirthdate(medicalrecord.getBirthdate());
				p.setMedications(medicalrecord.getMedications());
				return true;
			}
		return false;
	}
	
	/**
	 * Find then delete a medicalrecord.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param medicalrecord
	 * @return boolean, true if success
	 */
	public boolean delete(Medicalrecord medicalrecord) {
		for(Medicalrecord p : medicalrecords)
			if(p.compare(medicalrecord)) {
				medicalrecords.remove(p);
				return true;
			}
		return false;
	}
	
	/**
	 * Check if the medicalrecord already exist and if not, add it to the list.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param medicalrecord
	 * @return boolean, true if success
	 */
	public boolean add(Medicalrecord medicalrecord) {
		if(!this.contain(medicalrecord)) {
			medicalrecords.add(medicalrecord);
			return true;
		}
		else return false;
	}
	
	@Override
	public int hashCode() {
		return medicalrecords.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		return (o != null && 
			   (o instanceof Medicalrecords) &&
			   (o.hashCode() == this.hashCode()));
		
	}
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Medicalrecords: [");
		for(int i = 0 ; i < this.getMedicalrecords().size(); i++) {
			buf.append("\n"+this.getMedicalrecords().get(i));
			if(i+1 < this.getMedicalrecords().size())buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}
}
