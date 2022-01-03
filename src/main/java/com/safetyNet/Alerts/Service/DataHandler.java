package com.safetyNet.Alerts.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.Alerts.Model.Data;

/** 
 * Class handling data loading and data feeding of the program.
 * This class can be access from anywhere in the program.
 * It does not handle saving as it wasn't in the specifications
 * @author Mo-Bius
 */
public class DataHandler {

	private static final Logger logger = LogManager.getLogger("DataHandler");
    private static Data DATA = processData();
    
    //=======================================[getters]=======================================
    public static Data getData() {
    	return DATA;
    }

	//=======================================[Functions]=======================================
    /**
     * Will load the data from the json at ./src/main/resources/data.json
     * @return Data, a new dataset
     */
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
    
    /**
     * Reload the data from the json
     */
    public static void reloadFromJson() {
    	DATA = processData();
    }
    
    /**
     * Force the program to function with a specified set of data
     * @param data
     */
    public static void loadData(Data data) {
    	logger.info("FORCE FEEDING DATA");
    	DATA = data;
    }
}
