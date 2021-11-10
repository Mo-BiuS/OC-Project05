package com.safetyNet.Alerts.Model;

public class Firestation {
	private String adress;
	private int station;
	
	
	public Firestation(String adress, int i) {
		this.setAdress(adress);
		this.setStation(i);
	}
	
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setStation(int i) {
		this.station = i;
	}
	
	
	public String getAdress() {
		return adress;
	}
	public int getStation() {
		return station;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o == this)return true;
		
		if(o instanceof Firestation) {
			Firestation test = (Firestation)o;
			return (test.getAdress().equals(this.getAdress()) &&
					test.getStation() == this.getStation());
		}
		else return false;
	}
}
