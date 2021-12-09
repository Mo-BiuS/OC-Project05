package com.safetyNet.Alerts.Controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Service.DataHandler;

public class MedicalrecordController {
	
private final Medicalrecords medicalRecords = DataHandler.getData().getMedicalrecords();
	
	@GetMapping("/medicalRecord")
	public Medicalrecords findAllMedicalrecords() {
		return medicalRecords;
	}

	@GetMapping("/medicalRecord/{firstName}/{lastName}")
	public Medicalrecords findMedicalrecords(@PathVariable String firstName, @PathVariable String lastName) {
		return medicalRecords.getMedicalrecordByFirstName(firstName).getMedicalrecordByLastName(lastName);
	}
	
	@PostMapping("/medicalRecord")
	public boolean addMedicalrecords(@RequestBody Medicalrecord medicalRecord) throws URISyntaxException{
		return medicalRecords.add(medicalRecord);
	}
	
	@PutMapping("/medicalRecord")
	public boolean updateMedicalrecords(@RequestBody Medicalrecord medicalRecord) {
		return medicalRecords.replace(medicalRecord);
	}
	
	@DeleteMapping(path="/medicalRecord/{firstName}/{lastName}")
	public boolean deleteMedicalrecords(@PathVariable String firstName, @PathVariable String lastName) {
	    return medicalRecords.delete(new Medicalrecord(firstName, lastName, null, null, null));
	}
}
