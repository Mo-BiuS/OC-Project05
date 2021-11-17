package com.safetyNet.Alerts.Model.Firestation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Firestations {
	private final List<Firestation> firestations;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Firestations(@JsonProperty("firestations") List<Firestation> firestations) {
		this.firestations = firestations;
	}
	
	public List<Firestation> getFirestations() {
		return firestations;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Firestations : \n");
		for(int i = 0 ; i < this.getFirestations().size(); i++)
			buf.append(this.getFirestations().get(i)+"\n");
		
		return buf.toString();
	}
}
