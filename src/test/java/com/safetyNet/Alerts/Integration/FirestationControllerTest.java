package com.safetyNet.Alerts.Integration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.DataHandler;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {

	public static String adressA;
	public static String adressB;
	public static String adressC;
	
	public static int stationA;
	public static int stationB;
	public static int stationC;
	
	public static Firestation firestationA;
	public static Firestation firestationB;
	public static Firestation firestationC;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public static void init() throws ParseException {
		firestationA = new Firestation(adressA = "A", stationA = 1);
		firestationB = new Firestation(adressB = "B", stationB = 1);
		firestationC = new Firestation(adressC = "C", stationC = 2);

		List<Firestation> lFirestation = new ArrayList<Firestation>();
		lFirestation.add(firestationA);
		lFirestation.add(firestationB);
		List<Person> lPerson = new ArrayList<Person>();
		List<Medicalrecord> lMedicalrecord = new ArrayList<Medicalrecord>();
		
		DataHandler.loadData(new Data(lPerson, lFirestation, lMedicalrecord));
	}
	
	@Test
	public void firestionTest() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .post("http://localhost:9090/firestations/")
			      .content(asJsonString(firestationC))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(get("http://localhost:9090/firestations/"+adressC))
				  .andDo(print())
				  .andExpect(status().isOk())
				  .andExpect(content().string(containsString(adressC)));
		
		mockMvc.perform( MockMvcRequestBuilders
			      .put("http://localhost:9090/firestations")
			      .content(asJsonString(new Firestation(adressA, 54321)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
		
		mockMvc.perform( MockMvcRequestBuilders.delete("http://localhost:9090/firestations/"+adressB))
		          .andExpect(status().isOk());
		
		mockMvc.perform(get("http://localhost:9090/firestations"))
				  .andDo(print())
				  .andExpect(status().isOk())
				  .andExpect(content().string(containsString(adressA)))
				  .andExpect(content().string(containsString(adressC)));
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
