package com.safetyNet.Alerts.Integration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.DataHandler;

@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {
	public static String adressAB;
	public static String adressC;
	
	public static int stationA;
	public static int stationB;
	
	public static Firestation firestationA;
	public static Firestation firestationB;
	
	public static String firstNameA;
	public static String firstNameB;
	public static String firstNameC;
	
	public static String lastNameA;
	public static String lastNameB;
	public static String lastNameC;
	
	public static Date birthdateA;
	public static Date birthdateB;
	public static Date birthdateC;
	
	public static ArrayList<String> medicationsA;
	public static ArrayList<String> medicationsB;
	public static ArrayList<String> medicationsC;
	
	public static ArrayList<String> allergiesA;
	public static ArrayList<String> allergiesB;
	public static ArrayList<String> allergiesC;
	
	public static Medicalrecord medicalRecordTestA;
	public static Medicalrecord medicalRecordTestB;
	public static Medicalrecord medicalRecordTestC;
	
	public static String city;
	
	public static String phoneA;
	public static String phoneB;
	public static String phoneC;
	
	public static String emailA;
	public static String emailB;
	public static String emailC;
	
	public static short zipAB;
	public static short zipC;
	
	public static Person personA;
	public static Person personB;
	public static Person personC;
	
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public static void init() throws ParseException {
		birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/2005");
		birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995");
		birthdateC = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/1985");
		
		medicationsA = new ArrayList<String>();
		medicationsA.add("aznol:350");
		medicationsA.add("hydrapermazol:100");
		medicationsB = new ArrayList<String>();
		medicationsC = new ArrayList<String>();
		medicationsC.add("aznol:350");
		
		allergiesA = new ArrayList<String>();
		allergiesB = new ArrayList<String>();
		allergiesB.add("ork");
		allergiesC = new ArrayList<String>();
		allergiesC.add("peanut");
		
		medicalRecordTestA = new Medicalrecord(firstNameA = "a", lastNameA = "A", birthdateA, medicationsA, allergiesA);
		medicalRecordTestB = new Medicalrecord(firstNameB = "b", lastNameB = "B", birthdateB, medicationsB, allergiesB);
		medicalRecordTestC = new Medicalrecord(firstNameC = "c", lastNameC = "C", birthdateC, medicationsC, allergiesC);
		
		personA = new Person(firstNameA, lastNameA, adressAB = "1A", city = "city", zipAB = 1, phoneA = "06", emailA = "a@a");
		personB = new Person(firstNameB, lastNameB, adressAB, city, zipAB = 1, phoneB = "07", emailB = "b@b");
		personC = new Person(firstNameC, lastNameC, adressC = "1B", city, zipC = 2, phoneC = "08", emailC = "c@c");
		
		firestationA = new Firestation(adressAB, stationA = 1);
		firestationB = new Firestation(adressC, stationB = 2);

		List<Firestation> lFirestation = new ArrayList<Firestation>();
		lFirestation.add(firestationA);
		lFirestation.add(firestationB);
		
		List<Person> lPerson = new ArrayList<Person>();
		lPerson.add(personA);
		lPerson.add(personB);
		lPerson.add(personC);
		
		List<Medicalrecord> lMedicalrecord = new ArrayList<Medicalrecord>();
		lMedicalrecord.add(medicalRecordTestA);
		lMedicalrecord.add(medicalRecordTestB);
		lMedicalrecord.add(medicalRecordTestC);
		
		DataHandler.loadData(new Data(lPerson, lFirestation, lMedicalrecord));
	}
	
	@Test
	public void firestation() throws Exception {
		this.mockMvc.perform(get("http://localhost:9090/firestation?stationNumber="+stationA))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(firstNameA)))
					.andExpect(content().string(containsString(lastNameA)))
					.andExpect(content().string(containsString(firstNameB)))
					.andExpect(content().string(containsString(lastNameB)))
					.andExpect(content().string(containsString("\"adultCount\":1")))
					.andExpect(content().string(containsString("\"childCount\":1")));
	}
	
	@Test
	public void childAlert() throws Exception {
		this.mockMvc.perform(get("http://localhost:9090/childAlert?address="+adressAB))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(firstNameA)))
					.andExpect(content().string(containsString(lastNameA)))
					.andExpect(content().string(containsString(firstNameB)))
					.andExpect(content().string(containsString(lastNameB)));
	}
	
	@Test
	public void phoneAlert() throws Exception {
		this.mockMvc.perform(get("http://localhost:9090/phoneAlert?stationNumber="+stationA))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(phoneA)))
					.andExpect(content().string(containsString(phoneB)));
	}
	
	@Test
	public void fire() throws Exception {
		this.mockMvc.perform(get("http://localhost:9090/fire?address="+adressAB))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(firstNameA)))
					.andExpect(content().string(containsString(lastNameA)))
					.andExpect(content().string(containsString(firstNameB)))
					.andExpect(content().string(containsString(lastNameB)))
					.andExpect(content().string(containsString("\"stationsNumber\":["+stationA)));
	}
	
	@Test
	public void reqFloodStations() throws Exception {
		
		this.mockMvc.perform(get("http://localhost:9090/flood/stations/"+stationA))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(firstNameA)))
					.andExpect(content().string(containsString(lastNameA)))
					.andExpect(content().string(containsString(firstNameB)))
					.andExpect(content().string(containsString(lastNameB)));
	}
	
	@Test
	public void personInfo() throws Exception {
		this.mockMvc.perform(get("http://localhost:9090/personInfo?firstName="+firstNameA+"&lastName="+lastNameA))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(firstNameA)))
					.andExpect(content().string(containsString(lastNameA)));
	}
	
	@Test
	public void communityEmail() throws Exception {
		this.mockMvc.perform(get("http://localhost:9090/communityEmail?city="+city))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(emailA)))
					.andExpect(content().string(containsString(emailB)))
					.andExpect(content().string(containsString(emailC)));
	}
}
