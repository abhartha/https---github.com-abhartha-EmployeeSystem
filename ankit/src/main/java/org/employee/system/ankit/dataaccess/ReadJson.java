package org.employee.system.ankit.dataaccess;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.employee.system.ankit.App;
import org.employee.system.ankit.employee.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * This class is to read the json file.
 * @author Ankit Bharthan
 *
 */
public class ReadJson {
	
	public ReadJson(String filepath) {
		this.getEmployeeObject(filepath);
	}
	
	public void getEmployeeObject(String filepath) {
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(filepath));
            JSONArray msg = (JSONArray) obj;
            
            /*
             * max variable finds the maximum id from the json file, this value is also the total number of employees in the system.
             */
            long max = 0;
            for (int i = 0; i < msg.size(); i++) {
            	JSONObject jsonObject = (JSONObject) msg.get(i);
            	long employeeId = (Long) jsonObject.get("employeeId");
            	String firstName = (String) jsonObject.get("firstName");
            	String lastName = (String) jsonObject.get("lastName");
            	boolean isActive = (Boolean) jsonObject.get("isActive");
            	long managerId = (Long) jsonObject.get("managerId");
            	
            	Employee e = new Employee(employeeId, firstName, lastName, managerId, isActive);
            	
            	if (employeeId > max) {
            		max = employeeId;
            	}
            	App.employeeMap.put(employeeId, e);
            }
            
            /*
             * Updated the value of global IdCount variable.
             */
            App.IdCount = max;
            System.out.println("JSON Read Success.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
}
