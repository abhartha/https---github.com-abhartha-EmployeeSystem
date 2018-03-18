package org.employee.system.ankit;

import java.util.Scanner;

import org.employee.system.ankit.employee.Employee;
import org.employee.system.ankit.employeecontroller.CreateDataStore;
import org.employee.system.ankit.employeecontroller.EmployeeSystem;
import org.employee.system.ankit.utils.Constants;

import java.util.concurrent.ConcurrentHashMap;
/**
 * Employee System!.
 * This is the main class.
 * @author: Ankit Bharthan
 * @version: 1.0
 * @since: 17.03.2018
 *
 */
public class App 
{
	/**
	 * employeeMap is a global static variable to keep track of all the employee records.
	 * EmployeeId is Key here and Employee record is the value.
	 */
	public static ConcurrentHashMap<Long, Employee> employeeMap = new ConcurrentHashMap<Long, Employee>();
	
	/**
	 * IdCount is the global counter for total employees in the record, if new employee is added employee Id is incremented by 1.
	 */
	public static long IdCount = 0;
	
    public static void main( String[] args )
    {
    	/*
    	 * For the first run we check if there is already an existing employee.json file. 
    	 * If the file exist we populate the values in employeeMap and IdCount.
    	 * Otherwise we just create a new file.
    	 */
    	new CreateDataStore(Constants.PATH_OF_JSON);

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System. in);
		boolean loop = true;
		
		EmployeeSystem es = new EmployeeSystem(Constants.PATH_OF_JSON);
		while (loop) {
			scanner.reset();
			System.out.println("\nPlease choose from the following options:");
			System.out.println("1. Add a new employee.");
			System.out.println("2. Get details of an employee.");
			System.out.println("3. Update details of an employee.");
			System.out.println("4. Print hiearchy of an employee.");
			System.out.println("5. Remove/Inactivate an employee.");
			System.out.println("6. Exit.");
			System.out.println("\nEnter your choice: ");
			
			int ch = 0;
			try {
				ch = scanner.nextInt();
			} catch(Exception e) {
				scanner.nextLine();
			}
			switch(ch) {
				case 1: 
					es.addEmployee();
					break;
				case 2:
					es.getEmployee();
					break;
				case 3:
					es.updateEmployee();
					break;
				case 4:
					es.printHierarchy();
					break;
				case 5:
					es.inactivateEmployee();
					break;
				case 6:
					loop = false;
					break;
				default:
					System.out.println("Invalid Option Selected");
			}
		}
    }
}
