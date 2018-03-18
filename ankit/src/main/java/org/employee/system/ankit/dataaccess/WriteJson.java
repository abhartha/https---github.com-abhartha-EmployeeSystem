package org.employee.system.ankit.dataaccess;

import java.io.FileReader;
import java.io.FileWriter;

import org.employee.system.ankit.employee.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This file is used to Write and update values in Json file.
 * @author Ankit Bharthan
 *
 */

@SuppressWarnings("unchecked")
public class WriteJson {
	String filepath;
	
	public WriteJson(String filepath) {
		this.filepath = filepath;
	}
	
	/**
	 * Adding a new record in JSON file.
	 * @param e is the new employee record.
	 */
	public boolean addNewEmployee(Employee e) {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(filepath));
			JSONArray jsonArray = (JSONArray) obj;

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("employeeId", (Long) e.getEmployeeId());
			jsonObject.put("firstName", e.getEmployeeFirstName());
			jsonObject.put("lastName", e.getEmployeeLastName());
			jsonObject.put("isActive", (Boolean) e.isActive());
			jsonObject.put("managerId", (Long)e.getManagerId());

			FileWriter file = new FileWriter(filepath);

			jsonArray.add(jsonObject);

			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Function to update the employee record.
	 * @param e is the updated employee record.
	 */
	public boolean UpdateEmployee(Employee e) {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(filepath));
			JSONArray jsonArray = (JSONArray) obj;

			for (int i = 0; i < jsonArray.size(); i++) {
            	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            	if ((Long)jsonObject.get("employeeId") == (e.getEmployeeId())) {
            		jsonObject.put("firstName", e.getEmployeeFirstName());
            		jsonObject.put("lastName", e.getEmployeeLastName());
            		jsonObject.put("managerId", e.getManagerId());
            		jsonObject.put("isActive", e.isActive());
            	}
            }
			
			FileWriter file = new FileWriter(filepath);
			
			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}
