package org.employee.system.ankit;

import static org.junit.Assert.*;

import org.employee.system.ankit.dataaccess.ReadJson;
import org.employee.system.ankit.dataaccess.WriteJson;
import org.employee.system.ankit.employee.Employee;
import org.employee.system.ankit.utils.Constants;
import org.junit.Test;

public class WriteJsonTest {
	Employee e = new Employee(8, "John", "Murray", 8, true);
	
	@Test
	public void UpdateJsonTesting() {
		new ReadJson(Constants.PATH_OF_JSON_TEST);
	}
	
	@Test
	public void AddNewEmployee() {
		assertEquals(true, new WriteJson(Constants.PATH_OF_JSON_TEST).addNewEmployee(e));
	}
	
	@Test
	public void UpdateTest() {
		Employee e = new Employee (8, "John", "Murray", 1, true);
		assertEquals(true, new WriteJson(Constants.PATH_OF_JSON_TEST).UpdateEmployee(e));
	}
}
