package com.safetyNet.Alerts.Model.Firestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Firestation {
	private final String address;
	private final int station;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Firestation(@JsonProperty("address") String address, 
					   @JsonProperty("station") int station) {
		this.address = address;
		this.station = station;
	}
	
	public String getAddress() {
		return address;
	}
	public int getStation() {
		return station;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o == this)return true;
		
		if(o instanceof Firestation) {
			Firestation test = (Firestation)o;
			return (test.getAddress().equals(this.getAddress()) &&
					test.getStation() == this.getStation());
		}
		else return false;
	}
	
	@Override
	public String toString() {
		return "(address : "+this.getAddress()+", station : "+this.getStation()+")";
	}
}
