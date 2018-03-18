package org.employee.system.ankit;


import static org.junit.Assert.*;

import org.employee.system.ankit.employee.Employee;
import org.junit.Test;

public class EmployeeTest {
	Employee e = new Employee(1, "John", "Murray", 1, true);
	
	@Test
	public void getEmployeeObjectTest() {
		assertEquals("John", e.getEmployeeFirstName());
	}
	
	@Test
	public void getFullNameTest() {
		assertEquals("John Murray", e.getFullName());
	}
}