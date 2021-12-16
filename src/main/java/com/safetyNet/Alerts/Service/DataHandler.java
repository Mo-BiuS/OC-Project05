package com.safetyNet.Alerts.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.Alerts.Model.Data;

public class DataHandler {

	private static final Logger logger = LogManager.getLogger("DataHandler");
    private static Data DATA = processData();
    
    private static Data processData() {
    	
    	logger.info("Loading data.json : ");
    	
        Data DATA;
        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);
            
            DATA = mapper.readValue(new File("./src/main/resources/data.json"), Data.class);
            logger.info("Success ");
        } catch (Exception ex) {
        	logger.info("Failure " + ex.toString());
            ex.printStackTrace();
            DATA = null;
        }
        return DATA;
    }
    
    public static Data getData() {
    	return DATA;
    }
    
    public static void reloadFromJson() {
    	DATA = processData();
    }
    
    public static void loadData(Data data) {
    	logger.info("FORCE FEEDING DATA");
    	DATA = data;
    }
}
