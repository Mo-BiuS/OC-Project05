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
import com.safetyNet.Alerts.Model.Person.Persons;
import com.safetyNet.Alerts.Service.DataHandler;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
	public static String firstNameA;
	public static String firstNameB;
	public static String firstNameC;
	
	public static String lastNameA;
	public static String lastNameB;
	public static String lastNameC;
	
	public static String adressAB;
	public static String adressC;
	
	public static String city;
	
	public static String phoneA;
	public static String phoneB;
	public static String phoneC;
	
	public static String emailA;
	public static String emailB;
	public static String emailC;
	
	public static short zipA;
	public static short zipB;
	public static short zipC;
	
	public static Person personA;
	public static Person personB;
	public static Person personC;
	public static Persons persons;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public static void init() throws ParseException {	
		personA = new Person(firstNameA = "a", lastNameA = "A", adressAB = "AA", city = "city", zipA = 1, phoneA = "06", emailA = "a@a");
		personB = new Person(firstNameB = "b", lastNameB = "A", adressAB = "AA", city, zipB = 2, phoneB = "07", emailB = "b@b");
		personC = new Person(firstNameC = "c", lastNameC = "A", adressC = "AB", city, zipC = 3, phoneC = "08", emailC = "c@c");

		List<Firestation> lFirestation = new ArrayList<Firestation>();
		List<Person> lPerson = new ArrayList<Person>();
		lPerson.add(personA);
		lPerson.add(personB);
		List<Medicalrecord> lMedicalrecord = new ArrayList<Medicalrecord>();
		
		DataHandler.loadData(new Data(lPerson, lFirestation, lMedicalrecord));
	}
	
	@Test
	public void personTest() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .post("http://localhost:9090/person/")
			      .content(asJsonString(personC))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(get("http://localhost:9090/person/"+firstNameC+"/"+lastNameC))
				  .andDo(print())
				  .andExpect(status().isOk())
				  .andExpect(content().string(containsString(firstNameC)))
				  .andExpect(content().string(containsString(lastNameC)));
		
		mockMvc.perform( MockMvcRequestBuilders
			      .put("http://localhost:9090/person")
			      .content(asJsonString(new Person(firstNameA, lastNameA, adressC, city, zipC, phoneC, emailC)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
		
		mockMvc.perform( MockMvcRequestBuilders.delete("http://localhost:9090/person/"+firstNameB+"/"+lastNameB))
		          .andExpect(status().isOk());
		
		mockMvc.perform(get("http://localhost:9090/person"))
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
