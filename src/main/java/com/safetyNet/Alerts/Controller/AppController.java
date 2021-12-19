package com.safetyNet.Alerts.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.Alerts.Model.Reply.ReqChildAlert;
import com.safetyNet.Alerts.Model.Reply.ReqFire;
import com.safetyNet.Alerts.Model.Reply.ReqFirestation;
import com.safetyNet.Alerts.Model.Reply.ReqFloodStations;
import com.safetyNet.Alerts.Model.Reply.ReqPersonInfo;
import com.safetyNet.Alerts.Service.RequestService;

@RestController
public class AppController {
	
	private static final Logger logger = LogManager.getLogger("AppController");
	
//	Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante. 
//	Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste 
//	doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus, 
//	elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou 
//	moins) dans la zone desservie.
	@RequestMapping("/firestation")
	@ResponseBody
	public ReqFirestation firestation(int stationNumber) { 
		logger.info("Requesting : /firestation?stationNumber="+stationNumber);
		ReqFirestation req = RequestService.firestation(stationNumber);
		logger.info("Returning : "+req);
	    return req;
	}
	
//	Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse. 
//	La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres 
//	membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide
	@RequestMapping("/childAlert")
	@ResponseBody
	public ReqChildAlert childAlert(String address) {
		logger.info("Requesting : /childAlert?address="+address);
		ReqChildAlert req = RequestService.childAlert(address);
		logger.info("Returning : "+req);
	    return req;
	}
	
//	Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de 
//	pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques. 
	@RequestMapping("/phoneAlert")
	@ResponseBody
	public List<String> phoneAlert(int stationNumber) { 
		logger.info("Requesting : /phoneAlert?stationNumber="+stationNumber);
		List<String> req = RequestService.phoneAlert(stationNumber);
		logger.info("Returning : "+req);
	    return req;
	}
	
//	Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne 
//	de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents 
//	médicaux (médicaments, posologie et allergies) de chaque personne. 
	@RequestMapping("/fire")
	@ResponseBody
	public ReqFire fire(String address) { 
		logger.info("Requesting : /fire?address="+address);
		ReqFire req = RequestService.fire(address);
		logger.info("Returning : "+req);
	    return req;
	}
	
//	Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les 
//	personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et 
//	faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom. 
	@RequestMapping("/flood/stations/{stations}")
	@ResponseBody
	public ReqFloodStations reqFloodStations(@PathVariable List<Integer> stations) { 
		logger.info("Requesting : /flood/stations?stations="+stations);
		ReqFloodStations req = RequestService.reqFloodStations(stations);
		logger.info("Returning : "+req);
	    return req;
	}
	
//	Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, 
//	posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent 
//	toutes apparaître. 
	@RequestMapping("/personInfo")
	@ResponseBody
	public ReqPersonInfo personInfo(String firstName, String lastName) {
		logger.info("Requesting : /personInfo?firstName="+firstName+"&lastName="+lastName);
		ReqPersonInfo req = RequestService.personInfo(firstName,lastName);
		logger.info("Returning : "+req);
	    return req;
	}
	
//	Cette url doit retourner les adresses mail de tous les habitants de la ville.
	@RequestMapping("/communityEmail")
	@ResponseBody
	public List<String> communityEmail(@RequestParam(required = true) String city) {
		logger.info("Requesting : /communityEmail?city="+city);
		List<String> req = RequestService.communityEmail(city);
		logger.info("Returning : "+req);
	    return req;
	}
}
