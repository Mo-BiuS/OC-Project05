package com.safetyNet.Alerts.Model.Firestation;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Represents a group of firestation from the data model.
 * @author Mo-Bius
 */
public class Firestations {
	private static final Logger logger = LogManager.getLogger("firestations");
	private  List<Firestation> firestations;
	
	//=======================================[Constructor]=======================================
	/**
	 * Class constructor.
	 * @param firestations List<Firestation>
	 */
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Firestations(@JsonProperty("firestations") List<Firestation> firestations) {
		this.firestations = firestations;
	}
	
	//=======================================[getters]=======================================
	public List<Firestation> getFirestations() {
		return firestations;
	}
	
	/**
	 * Getting all entity with the same address
	 * @param value, string representing an address
	 * @return Firestations, a sub list.
	 */
	public Firestations getByAdress(String value){
		
		logger.info("getting by address : "+value);
		
		List<Firestation> list = new ArrayList<Firestation>();
		for(int i = 0; i < firestations.size(); i++) {
			if(value.equals(firestations.get(i).getAddress()))list.add(firestations.get(i));
		}
		return new Firestations(list);
	}
	
	/**
	 * Getting all entity with the same station number
	 * @param value, int representing a station number
	 * @return Firestations, a sub list.
	 */
	public Firestations getByStation(int value){
		
		logger.info("getting by station : "+value);
		
		List<Firestation> list = new ArrayList<Firestation>();
		for(int i = 0; i < firestations.size(); i++) {
			if(value == firestations.get(i).getStation())list.add(firestations.get(i));
		}
		return new Firestations(list);
	}

	//=======================================[Functions]=======================================
	/**
	 * Concatenation of this list and another
	 * @param firestations, the other list to concat
	 * @return Firestations, a new list containing the two others.
	 */
	public Firestations concat(Firestations firestations) {
		List<Firestation> list = new ArrayList<Firestation>();
		list.addAll(this.getFirestations());
		list.addAll(firestations.getFirestations());
		return new Firestations(list);
	}
	
	/**
	 * Search to find if a firestation is inside this list.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param firestation
	 * @return boolean, true if found
	 */
	public boolean contain(Firestation firestation) {
		for(Firestation f : firestations)
			if(f.compare(firestation))return true;
		
		return false;
	}
	
	/**
	 * Find then replace a firestation by another.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param firestation
	 * @return boolean, true if success
	 */
	public boolean replace(Firestation firestation) {
		for(Firestation p : firestations)
			if(p.compare(firestation)) {
				p.setStation(firestation.getStation());
				return true;
			}
		return false;
	}
	
	/**
	 * Find then delete a firestation.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param firestation
	 * @return boolean, true if success
	 */
	public boolean delete(Firestation firestation) {
		for(Firestation p : firestations)
			if(p.compare(firestation)) {
				firestations.remove(p);
				return true;
			}
		return false;
	}
	
	/**
	 * Check if the firestation already exist and if not, add it to the list.
	 * It will do so by comparing the entity by the law of the specifications
	 * @param firestation
	 * @return boolean, true if success
	 */
	public boolean add(Firestation firestation) {
		if(!this.contain(firestation)) {
			firestations.add(firestation);
			return true;
		}
		else return false;
	}
	
	@Override
	public int hashCode() {
		return firestations.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		return (o != null && 
			   (o instanceof Firestations) &&
			   (o.hashCode() == this.hashCode()));
		
	}
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Firestations: [");
		for(int i = 0 ; i < this.getFirestations().size(); i++) {
			buf.append("\n"+this.getFirestations().get(i));
			if(i+1 < this.getFirestations().size())buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}
}
