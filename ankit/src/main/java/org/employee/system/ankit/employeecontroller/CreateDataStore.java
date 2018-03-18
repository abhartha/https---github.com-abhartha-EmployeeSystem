package org.employee.system.ankit.employeecontroller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.employee.system.ankit.dataaccess.ReadJson;
import org.json.simple.JSONArray;

/**
 * Class to create Json file for the first time or to load the existing JSON file.
 * @author Ankit Bharthan
 *
 */
public class CreateDataStore {

	public CreateDataStore(String path) {
		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			System.out.println("Employee records file already exists");
			new ReadJson(path);
		} else {
			// tries to create new file in the system
			try {
				f.createNewFile();
				JSONArray obj = new JSONArray();
				try (FileWriter file = new FileWriter(path)) {

		            file.write(obj.toJSONString());
		            file.flush();

		        } catch (IOException e) {
		            e.printStackTrace();
		        }

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("System invoked for the first time, created records file");
		}
	}
}
