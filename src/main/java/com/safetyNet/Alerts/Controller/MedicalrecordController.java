package com.safetyNet.Alerts.Controller;

import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Service.DataHandler;

/** 
 * class containing all the mapping of Medicalrecords
 * @author Mo-Bius
 */
@RestController
public class MedicalrecordController {
	
	private static final Logger logger = LogManager.getLogger("MedicalrecordController");
	private final Medicalrecords medicalRecords = DataHandler.getData().getMedicalrecords();
	
	/**
	 * Mapping to get all medicalrecords
	 * @return Medicalrecords
	 */
	@GetMapping("/medicalrecord")
	public Medicalrecords findAllMedicalrecords() {
		logger.info("Requesting : /medicalRecord");
		return medicalRecords;
	}

	/**
	 * Mapping to get a specific medicalrecord
	 * @return Medicalrecords
	 */
	@GetMapping("/medicalrecord/{firstName}/{lastName}")
	public Medicalrecords findMedicalrecords(@PathVariable String firstName, @PathVariable String lastName) {
		logger.info("Requesting : /medicalRecord/{firstName}/{lastName}");
		return medicalRecords.getMedicalrecordByFirstName(firstName).getMedicalrecordByLastName(lastName);
	}
	
	/**
	 * Mapping to add a medicalrecord
	 * @return true if successful
	 */
	@PostMapping("/medicalrecord")
	public boolean addMedicalrecords(@RequestBody Medicalrecord medicalRecord) throws URISyntaxException{
		logger.info("Requesting : POST /medicalRecord");
		return medicalRecords.add(medicalRecord);
	}
	
	/**
	 * Mapping to update a medicalrecord
	 * @return true if successful
	 */
	@PutMapping("/medicalrecord")
	public boolean updateMedicalrecords(@RequestBody Medicalrecord medicalRecord) {
		logger.info("Requesting : PUT /medicalRecord");
		return medicalRecords.replace(medicalRecord);
	}
	
	/**
	 * Mapping to delete a medicalrecord
	 * @return true if successful
	 */
	@DeleteMapping(path="/medicalrecord/{firstName}/{lastName}")
	public boolean deleteMedicalrecords(@PathVariable String firstName, @PathVariable String lastName) {
		logger.info("Requesting : DELETE /medicalRecord/{firstName}/{lastName}");
		return medicalRecords.delete(new Medicalrecord(firstName, lastName, null, null, null));
	}
}
