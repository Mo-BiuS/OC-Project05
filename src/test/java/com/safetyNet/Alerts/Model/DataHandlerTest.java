package com.safetyNet.Alerts.Model;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataHandlerTest {
	@Test
	public void init() {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		    mapper.setDateFormat(df);
		    
		    DataHandler data = mapper.readValue(new File("./src/main/resources/data.json"), DataHandler.class);

		    System.out.println(data);

		} catch (Exception ex) {
		    ex.printStackTrace();
		    assertTrue(false);
		}
	}
}
