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
	
private final Medicalrecords medicalRecords = DataHandler.DATA.getMedicalrecords();
	
	@GetMapping("/medicalRecord")
	public Medicalrecords findAllPerson() {
		return medicalRecords;
	}

	@GetMapping("/medicalRecord/{firstName}/{lastName}")
	public Medicalrecords findPerson(@PathVariable String firstName, @PathVariable String lastName) {
		return medicalRecords.getMedicalrecordByFirstName(firstName).getMedicalrecordByLastName(lastName);
	}
	
	@PostMapping("/medicalRecord")
	public boolean addPerson(@RequestBody Medicalrecord medicalRecord) throws URISyntaxException{
		return medicalRecords.add(medicalRecord);
	}
	
	@PutMapping("/medicalRecord")
	public boolean updatePerson(@RequestBody Medicalrecord medicalRecord) {
		return medicalRecords.replace(medicalRecord);
	}
	
	@DeleteMapping(path="/medicalRecord/{firstName}/{lastName}")
	public boolean deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
	    return medicalRecords.delete(new Medicalrecord(firstName, lastName, null, null, null));
	}
}
