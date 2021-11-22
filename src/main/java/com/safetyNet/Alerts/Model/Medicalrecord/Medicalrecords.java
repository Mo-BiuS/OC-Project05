package com.safetyNet.Alerts.Model.Medicalrecord;

import java.util.ArrayList;
import java.util.Date;
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
	
	public List<Medicalrecord> getMedicalrecordByFirstName(String value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.equals(medicalrecords.get(i).getFirstName()))list.add(medicalrecords.get(i));
		}
		return list;
	}
	public List<Medicalrecord> getMedicalrecordByLastName(String value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.equals(medicalrecords.get(i).getLastName()))list.add(medicalrecords.get(i));
		}
		return list;
	}
	public List<Medicalrecord> getMedicalrecordByBirthdayEqualTo(Date value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() == medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return list;
	}
	public List<Medicalrecord> getMedicalrecordByBirthdayInferiorTo(Date value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() < medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return list;
	}
	public List<Medicalrecord> getMedicalrecordByBirthdaySuperiorTo(Date value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() > medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return list;
	}
	public List<Medicalrecord> getMedicalrecordByMedication(String value){
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
		return list;
	}
	public List<Medicalrecord> getMedicalrecordByAllergies(String value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			for(int j = 0; j < medicalrecords.get(i).getAllergies().size(); j++) 
			{
				if(value.equals(medicalrecords.get(i).getAllergies().get(i))) {
					list.add(medicalrecords.get(i));
					break;
				}
			}
		}
		return list;
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
