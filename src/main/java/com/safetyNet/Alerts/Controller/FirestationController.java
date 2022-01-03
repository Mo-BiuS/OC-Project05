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

import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Service.DataHandler;

/** 
 * class containing all the mapping of firestations
 * @author Mo-Bius
 */
@RestController
public class FirestationController {
	
	private static final Logger logger = LogManager.getLogger("FirestationController");
	private final Firestations firestations = DataHandler.getData().getFirestations();
	
	/**
	 * Mapping to get all firestations
	 * @return Firestations
	 */
	@GetMapping("/firestations")
	public Firestations findAllFirestation() {
		logger.info("Requesting : /firestation");
		return firestations;
	}

	/**
	 * Mapping to get a specific firestation
	 * @return Firestations
	 */
	@GetMapping("/firestations/{address}")
	public Firestations findFirestation(@PathVariable String address) {
		logger.info("Requesting : /firestation/{address}");
		return firestations.getByAdress(address);
	}
	
	/**
	 * Mapping to add a firestation
	 * @return true if successful
	 */
	@PostMapping("/firestations")
	public boolean addFirestation(@RequestBody Firestation firestation) throws URISyntaxException{
		logger.info("Requesting : POST /firestation");
		return firestations.add(firestation);
	}
	
	/**
	 * Mapping to update a firestation
	 * @return true if successful
	 */
	@PutMapping("/firestations")
	public boolean updateFirestation(@RequestBody Firestation firestation) {
		logger.info("Requesting : PUT /firestation");
		return firestations.replace(firestation);
	}
	
	/**
	 * Mapping to delete a firestation
	 * @return true if successful
	 */
	@DeleteMapping(path="/firestations/{address}")
	public boolean deleteFirestation(@PathVariable String address) {
		logger.info("Requesting : DELETE /firestation/"+address);
	    return firestations.delete(new Firestation(address, 0));
	}
}
