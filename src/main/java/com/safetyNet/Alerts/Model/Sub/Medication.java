package com.safetyNet.Alerts.Model.Sub;

public class Medication {
	private String productName;
	private float posologyInMg;
	
	
	public Medication(String productName, float posologyInMg) {
		this.setProductName(productName);
		this.setPosologyInMg(posologyInMg);
	}
	
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPosologyInMg(float posologyInMg) {
		this.posologyInMg = posologyInMg;
	}
	
	
	public String getProductName() {
		return productName;
	}
	public float getPosologyInMg() {
		return posologyInMg;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)return true;
		
		if(o instanceof Medication) {
			Medication test = (Medication)o;
			return (test.getProductName() == this.getProductName() &&
					test.getPosologyInMg() == this.getPosologyInMg());
		}
		else return false;
	}
}
