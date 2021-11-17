package com.safetyNet.Alerts.Model.Medicalrecord;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Medicalrecords {
	private final List<Medicalrecord> medicalrecords;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Medicalrecords(@JsonProperty("medicalrecords") List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
	
	
	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}
	
	@Override
	public String toString() {
		return this.getMedicalrecords().toString();
	}
}
