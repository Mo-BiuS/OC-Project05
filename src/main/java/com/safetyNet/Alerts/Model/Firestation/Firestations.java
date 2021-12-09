package com.safetyNet.Alerts.Model.Firestation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Firestations {
	private  List<Firestation> firestations;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Firestations(@JsonProperty("firestations") List<Firestation> firestations) {
		this.firestations = firestations;
	}
	
	public List<Firestation> getFirestations() {
		return firestations;
	}
	
	public Firestations getByAdress(String value){
		List<Firestation> list = new ArrayList<Firestation>();
		for(int i = 0; i < firestations.size(); i++) {
			if(value.equals(firestations.get(i).getAddress()))list.add(firestations.get(i));
		}
		return new Firestations(list);
	}
	
	public Firestations getByStation(int value){
		List<Firestation> list = new ArrayList<Firestation>();
		for(int i = 0; i < firestations.size(); i++) {
			if(value == firestations.get(i).getStation())list.add(firestations.get(i));
		}
		return new Firestations(list);
	}

	public Firestations concat(Firestations firestations) {
		List<Firestation> list = new ArrayList<Firestation>();
		list.addAll(this.getFirestations());
		list.addAll(firestations.getFirestations());
		return new Firestations(list);
	}
	
	public boolean contain(Firestation firestation) {
		for(Firestation f : firestations)
			if(f.compare(firestation))return true;
		
		return false;
	}
	
	public boolean replace(Firestation firestation) {
		return this.delete(firestation) && this.add(firestation);
	}
	public boolean delete(Firestation firestation) {
		for(Firestation p : firestations)
			if(p.compare(firestation)) {
				firestations.remove(p);
				return true;
			}
		return false;
	}
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
