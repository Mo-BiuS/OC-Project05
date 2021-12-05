package com.safetyNet.Alerts.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.Alerts.Model.Data;

public class DataHandler {
    public static Data DATA = processData();
    
    private static Data processData() {
        Data DATA;
        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);
            
            DATA = mapper.readValue(new File("./src/main/resources/data.json"), Data.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            DATA = null;
        }
        return DATA;
    }
    
    public static void reload() {
    	DATA = processData();
    }
}
