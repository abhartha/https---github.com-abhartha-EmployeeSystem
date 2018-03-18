package org.employee.system.ankit.employeecontroller;

import org.employee.system.ankit.dataaccess.WriteJson;
import org.employee.system.ankit.employee.Employee;
/**
 * Class to writer record parallely in to JSON file, after we have updated the employeeMap.
 * @author Ankit Bharthan
 *
 */
public class JsonParallerWriter implements Runnable {
	
	/**
	 * Employee record
	 */
	private Employee emp;
	/**
	 * type has 2 values, "new" and "update".
	 * Based on the value we decide which method to call in the run method.
	 */
	private String type;
	
	/**
	 * Path of JSON file to write.
	 */
	private String path;
	
    public JsonParallerWriter(Employee e, String type, String path) {
       this.emp = e;
       this.type = type;
       this.path = path;
    }

	@Override
	public void run() {
		synchronized(this) {
			if (type.equals("new")) {
				new WriteJson(path).addNewEmployee(emp);
			} else {
				new WriteJson(path).UpdateEmployee(emp);
			}
		}
	}
	
}
