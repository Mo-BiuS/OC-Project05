package com.safetyNet.Alerts.Model.Medicalrecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Medicalrecords {
	private  List<Medicalrecord> medicalrecords;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Medicalrecords(@JsonProperty("medicalrecords") List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
	
	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}
	
	public Medicalrecords getMedicalrecordByFirstName(String value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.equals(medicalrecords.get(i).getFirstName()))list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	public Medicalrecords getMedicalrecordByLastName(String value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.equals(medicalrecords.get(i).getLastName()))list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	public Medicalrecords getMedicalrecordByBirthdayEqualTo(Date value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() == medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	public Medicalrecords getMedicalrecordByBirthdayInferiorTo(Date value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() < medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	public Medicalrecords getMedicalrecordByBirthdaySuperiorTo(Date value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			if(value.getTime() > medicalrecords.get(i).getBirthdate().getTime())list.add(medicalrecords.get(i));
		}
		return new Medicalrecords(list);
	}
	public Medicalrecords getMedicalrecordByMedication(String value){
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
		return new Medicalrecords(list);
	}
	public Medicalrecords getMedicalrecordByAllergies(String value){
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		for(int i = 0; i < medicalrecords.size(); i++) {
			for(int j = 0; j < medicalrecords.get(i).getAllergies().size(); j++) 
			{
				if(value.equals(medicalrecords.get(i).getAllergies().get(j))) {
					list.add(medicalrecords.get(i));
					break;
				}
			}
		}
		return new Medicalrecords(list);
	}
	
	public Medicalrecords concat(Medicalrecords medicalrecords) {
		List<Medicalrecord> list = new ArrayList<Medicalrecord>();
		list.addAll(this.medicalrecords);
		list.addAll(medicalrecords.getMedicalrecords());
		return new Medicalrecords(list);
	}
	
	public boolean contain(Medicalrecord medicalrecord) {
		for(Medicalrecord m : medicalrecords)
			if(m.compare(medicalrecord))return true;
		
		return false;
	}
	public boolean replace(Medicalrecord medicalrecord) {
		return this.delete(medicalrecord) && this.add(medicalrecord);
	}
	public boolean delete(Medicalrecord medicalrecord) {
		for(Medicalrecord p : medicalrecords)
			if(p.compare(medicalrecord)) {
				medicalrecords.remove(p);
				return true;
			}
		return false;
	}
	public boolean add(Medicalrecord medicalrecord) {
		if(!this.contain(medicalrecord)) {
			medicalrecords.add(medicalrecord);
			return true;
		}
		else return false;
	}
	@Override
	public int hashCode() {
		return medicalrecords.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		return (o != null && 
			   (o instanceof Medicalrecords) &&
			   (o.hashCode() == this.hashCode()));
		
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
