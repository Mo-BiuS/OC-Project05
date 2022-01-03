package com.safetyNet.Alerts.Model.Firestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Represents a firestation from the data model.
 * @author Mo-Bius
 */
public class Firestation {
	private String address;
	private int station;
	
	//=======================================[Constructor]=======================================
	/**
	 * Class constructor.
	 * @param address String
	 * @param station Int
	 */
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Firestation(@JsonProperty("address") String address, 
					   @JsonProperty("station") int station) {
		this.address = address;
		this.station = station;
	}
	
	//=======================================[getters/setters]=======================================
	public String getAddress() {
		return address;
	}
	public int getStation() {
		return station;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setStation(int station) {
		this.station = station;
	}
	
	//=======================================[Functions]=======================================
	/**
	 * Compare this entity with another of the same class to see if they are the same in the eye of the specifications
	 * @param firestation 
	 * @return boolean
	 */
	public boolean compare(Firestation firestation) {
		return this.getAddress().equals(firestation.getAddress());
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
		return (o != null && 
			   (o instanceof Firestation) &&
			   (o.hashCode() == this.hashCode()));
		
	}
	@Override
	public String toString() {
		return "{ \"address\" : "+this.getAddress()+", \"station\" : "+this.getStation()+"}";
	}
}
