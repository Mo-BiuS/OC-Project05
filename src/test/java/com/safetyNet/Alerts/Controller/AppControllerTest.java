package com.safetyNet.Alerts.Controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import com.safetyNet.Alerts.Model.Reply.ReqChildAlert;
import com.safetyNet.Alerts.Model.Reply.ReqFire;
import com.safetyNet.Alerts.Model.Reply.ReqFirestation;
import com.safetyNet.Alerts.Model.Reply.ReqFloodStations;
import com.safetyNet.Alerts.Model.Reply.ReqPersonInfo;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqChildAlertAdult;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqChildAlertChild;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFirePeople;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFirestationPerson;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqFloodStationAddress;

public class AppControllerTest {

	AppController app = new AppController();
	
	@Test
	public void firestationTest() {
		ReqFirestation req = app.firestation(1);
		
		assertTrue(req.childCount == 1);
		assertTrue(req.adultCount == 5);
		
		boolean reginoldWalker = false;
		for(ReqFirestationPerson p : req.persons) {
			if(p.firstName.equals("Reginold") && p.lastName.equals("Walker"))reginoldWalker = true;
		}
		assertTrue(reginoldWalker);
	}
	
	@Test
	public void childAlertTest() {
		ReqChildAlert req = app.childAlert("1509 Culver St");
		
		assertTrue(req.adult.size() == 3);
		assertTrue(req.child.size() == 2);
		
		boolean johnBoyd = false;
		for(ReqChildAlertAdult p : req.adult) {
			if(p.firstName.equals("John") && p.lastName.equals("Boyd"))johnBoyd = true;
		}
		assertTrue(johnBoyd);
		
		boolean rogerBoyd = false;
		for(ReqChildAlertChild p : req.child) {
			if(p.firstName.equals("Roger") && p.lastName.equals("Boyd"))rogerBoyd = true;
		}
		assertTrue(rogerBoyd);
	}
	
	@Test
	public void phoneAlertTest() {
		ArrayList<String> req = (ArrayList<String>) app.phoneAlert(1);
		
		assertTrue(req.size() == 6);
		
		boolean boyd = false;
		for(String p : req) {
			if(p.equals("841-874-6512"))boyd = true;
		}
		assertTrue(boyd);
	}
	
	@Test
	public void fireTest() {
		ReqFire req = app.fire("1509 Culver St");
		
		assertTrue(req.stationsNumer.size() == 1);
		assertTrue(req.people.size() == 5);
		
		boolean station = false;
		for(int p : req.stationsNumer) {
			if(p == 3)station = true;
		}
		assertTrue(station);
		
		boolean johnBoyd = false;
		for(ReqFirePeople p : req.people) {
			if(p.firstName.equals("John") && p.lastName.equals("Boyd"))johnBoyd = true;
		}
		assertTrue(johnBoyd);
	}
	
	@Test
	public void reqFloodStationsTest() { 
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(1);
		ReqFloodStations req = app.reqFloodStations(list);
		
		assertTrue(req.address.size() == 6);
		
		boolean peterDuncan = false;
		boolean jamiePeters = false;
		boolean jonanathanMarrack = false;
		
		for(ReqFloodStationAddress address : req.address) {
			for(ReqFirePeople p : address.people) {
				if(p.firstName.equals("Peter") && p.lastName.equals("Duncan"))peterDuncan = true;
				if(p.firstName.equals("Jamie") && p.lastName.equals("Peters"))jamiePeters = true;
				if(p.firstName.equals("Jonanathan") && p.lastName.equals("Marrack"))jonanathanMarrack = true;
			}
		}
		assertTrue(peterDuncan);
		assertTrue(jamiePeters);
		assertTrue(jonanathanMarrack);
	}
	
	@Test
	public void personInfoTest() { 
		ReqPersonInfo req = app.personInfo("Peter","Duncan");
		
		assertTrue(req.peoples.size() == 1);
		assertTrue(req.peoples.get(0).firstName.equals("Peter"));
		assertTrue(req.peoples.get(0).lastName.equals("Duncan"));
	}
	
	@Test
	public void communityEmailTest() { 
		List<String> req = app.communityEmail("Culver");
		
		boolean emailA = false;
		boolean emailB = false;
		boolean emailC = false;
		for(String p : req) {
			if(p.equals("jaboyd@email.com"))emailA = true;
			if(p.equals("drk@email.com"))emailB = true;
			if(p.equals("tenz@email.com"))emailC = true;
		}
		assertTrue(emailA);
		assertTrue(emailB);
		assertTrue(emailC);
	}
}
