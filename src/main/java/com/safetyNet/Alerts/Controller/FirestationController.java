package com.safetyNet.Alerts.Controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Firestation.Firestations;
import com.safetyNet.Alerts.Service.DataHandler;

public class FirestationController {
	private final Firestations firestations = DataHandler.getData().getFirestations();
	
	@GetMapping("/firestation")
	public Firestations findAllFirestation() {
		return firestations;
	}

	@GetMapping("/firestation/{address}")
	public Firestations findFirestation(@PathVariable String address) {
		return firestations.getByAdress(address);
	}
	
	@PostMapping("/firestation")
	public boolean addFirestation(@RequestBody Firestation firestation) throws URISyntaxException{
		return firestations.add(firestation);
	}
	
	@PutMapping("/firestation")
	public boolean updateFirestation(@RequestBody Firestation firestation) {
		return firestations.replace(firestation);
	}
	
	@DeleteMapping(path="/firestation/{address}")
	public boolean deleteFirestation(@PathVariable String address) {
	    return firestations.delete(new Firestation(address, 0));
	}
}
