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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.Alerts.Model.Data;
import com.safetyNet.Alerts.Model.Firestation.Firestation;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecord;
import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Person.Person;
import com.safetyNet.Alerts.Service.DataHandler;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalrecordControllerTest {
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
	public static Medicalrecords medicalRecords;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public static void init() throws ParseException {
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
		
		birthdateA = new SimpleDateFormat("dd/MM/yyyy").parse("19/01/2000");
		birthdateB = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/1995");
		birthdateC = new SimpleDateFormat("dd/MM/yyyy").parse("22/01/1990");
		
		medicalRecordTestA = new Medicalrecord(firstNameA = "a", lastNameA = "A", birthdateA, medicationsA, allergiesA);
		medicalRecordTestB = new Medicalrecord(firstNameB = "b", lastNameB = "A", birthdateB, medicationsB, allergiesB);
		medicalRecordTestC = new Medicalrecord(firstNameC = "c", lastNameC = "A", birthdateC, medicationsC, allergiesC);

		List<Firestation> lFirestation = new ArrayList<Firestation>();
		List<Person> lPerson = new ArrayList<Person>();
		List<Medicalrecord> lMedicalrecord = new ArrayList<Medicalrecord>();
		lMedicalrecord.add(medicalRecordTestA);
		lMedicalrecord.add(medicalRecordTestB);
		
		DataHandler.loadData(new Data(lPerson, lFirestation, lMedicalrecord));
	}
	
	@Test
	public void medicalrecordTest() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .post("http://localhost:9090/medicalrecord/")
			      .content(asJsonString(medicalRecordTestC))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(get("http://localhost:9090/medicalrecord/"+firstNameC+"/"+lastNameC))
				  .andDo(print())
				  .andExpect(status().isOk())
				  .andExpect(content().string(containsString(firstNameC)))
				  .andExpect(content().string(containsString(lastNameC)));
		
		mockMvc.perform( MockMvcRequestBuilders
			      .put("http://localhost:9090/medicalrecord")
			      .content(asJsonString(new Medicalrecord(firstNameA, lastNameA, birthdateC, medicationsC, allergiesC)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
		
		mockMvc.perform( MockMvcRequestBuilders.delete("http://localhost:9090/medicalrecord/"+firstNameB+"/"+lastNameB))
		          .andExpect(status().isOk());
		
		mockMvc.perform(get("http://localhost:9090/medicalrecord"))
				  .andDo(print())
				  .andExpect(status().isOk())
				  .andExpect(content().string(containsString(firstNameA)))
				  .andExpect(content().string(containsString(lastNameA)))
				  .andExpect(content().string(containsString(firstNameC)))
				  .andExpect(content().string(containsString(lastNameC)));
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
