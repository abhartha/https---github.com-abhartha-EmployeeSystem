package org.employee.system.ankit;

import static org.junit.Assert.*;

import org.employee.system.ankit.employeecontroller.CreateDataStore;
import org.employee.system.ankit.employeecontroller.EmployeeSystem;
import org.employee.system.ankit.utils.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;


public class EmployeeSystemTest {
	EmployeeSystem es;
	public EmployeeSystemTest() {
		
		new CreateDataStore(Constants.PATH_OF_JSON_TEST);
		es = new EmployeeSystem(Constants.PATH_OF_JSON_TEST);
	}
	
	@Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	/**
	 * Printing manager's hierarchy
	 */
	@Test
	public void PrintMangerTest() {
		
		es.printManager(4, 0);
		String output = systemOutRule.getLog();
		assertEquals("Peter Parker -> Tony Stark -> Spider Man(Inactive)  -> John Murray\n", output);
		
	}
	
	/**
	 * Printing tree hierarchy in case there is no employee with that id.
	 */
	
	@Test
	public void PrintMangerTest2() {
		
		es.printManager(7, 0);
		String output = systemOutRule.getLog();
		assertEquals("Error in printing hierarchy!\n", output);
		
	}
	/**
	 * Adding a new employee
	 */
	
	@Test
	public void AddEmployeeTest() {
		
		systemInMock.provideLines("abcd","efgh","1");
		es.addEmployee();
		String output = systemOutRule.getLog();
		if (output.contains("The new employee has added with employee id:")) {
			assert(true);
		} else {
			assert(false);
		}
	}
	/**
	 * Adding employee with wrong manager id.
	 */
	
	@Test
	public void AddEmployeeTest2() {
		
		systemInMock.provideLines("1","2","10");
		es.addEmployee();
		String output = systemOutRule.getLog();
		if (output.contains("There is no manager with this id in the record.")) {
			assert(true);
		} else {
			assert(false);
		}
	}
	
	/**
	 * Updating first Name
	 */
	
	@Test
	public void UpdateEmployeeTest() {
		
		systemInMock.provideLines("1","1","John");
		es.updateEmployee();
		String output = systemOutRule.getLog();
		if (output.contains("Updated First Name.")) {
			assert(true);
		} else {
			assert(false);
		}
	}
	
	/**
	 * Updating last name.
	 */
	@Test
	public void UpdateEmployeeTest2() {
		
		systemInMock.provideLines("1","2","John");
		es.updateEmployee();
		String output = systemOutRule.getLog();
		if (output.contains("Updated Last Name.")) {
			assert(true);
		} else {
			assert(false);
		}
	}
	
	/**
	 * Case when employee doesnt exist in the system.
	 */
	@Test
	public void UpdateEmployeeTest3() {
		
		systemInMock.provideLines("10","2","John");
		es.updateEmployee();
		String output = systemOutRule.getLog();
		if (output.contains("There is no employee with this id in the record.")) {
			assert(true);
		} else {
			assert(false);
		}
	}
}
