package com.safetyNet.Alerts.Model.Firestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Firestation {
	private String address;
	private int station;
	
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
	public int hashCode() {
		int value = 1;
		
		String address = this.getAddress();
		if(address != null) value *= address.hashCode();
		
		int station = this.getStation();
		if(station != 0)value*=station;
		
		return value;
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
	
	public boolean compare(Firestation firestation) {
		return this.getAddress().equals(firestation.getAddress());
	}
	
	@Override
	public String toString() {
		return "{ \"address\" : "+this.getAddress()+", \"station\" : "+this.getStation()+"}";
	}
}
