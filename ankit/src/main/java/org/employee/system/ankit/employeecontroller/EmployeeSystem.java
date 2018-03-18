package org.employee.system.ankit.employeecontroller;

import java.util.Scanner;

import org.employee.system.ankit.App;
import org.employee.system.ankit.employee.Employee;

/**
 * This is our controller class, that invokes methods from other classes. All
 * the user input and function calls are done in this class.
 * 
 * @author Ankit Bharthan
 *
 */
@SuppressWarnings("resource")
public class EmployeeSystem {

    private String path;
	
	public EmployeeSystem(String path) {
		this.path = path;
	}
	/**
	 * Adding a new employee.
	 */
	public void addEmployee() {
		System.out.println("Adding new Employee");
		System.out.println("Enter employee Firstname");
		String firstName, lastName;
		long managerId;
		Scanner scanner = new Scanner(System.in);

		firstName = scanner.nextLine();

		System.out.println("Enter employee Lastname");
		lastName = scanner.nextLine();

		System.out.println("Enter employee ManagerId");
		try {
			managerId = scanner.nextLong();
			/*
			 * If Manager id is not the in the record or managerId and
			 * employeeId are not the same. This means manager is not valid.
			 */
			if (!App.employeeMap.containsKey(managerId) && (managerId != App.IdCount + 1)) {
				System.out.println("\nThere is no manager with this id in the record.\nCannot add this employee.\n");
				return;
			}
		} catch (Exception e) {
			System.out.println("Invalid Manager ID.");
			return;
		}
		Employee e = new Employee(++App.IdCount, firstName, lastName, managerId, true);
		App.employeeMap.put(App.IdCount, e);
		System.out.println("The new employee has added with employee id: " + App.IdCount);
		
		Thread t = new Thread(new JsonParallerWriter(e, "new", path));
		t.start();

	}

	/**
	 * Method to get employee details.
	 */
	public void getEmployee() {
		try {
			System.out.println("Enter Employee ID to get details: ");
			Scanner scanner = new Scanner(System.in);
			long employeeId = scanner.nextLong();
			if (!App.employeeMap.containsKey(employeeId)) {
				System.out.println("\nThere is no employee with this id in the record\n");
				return;
			}
			System.out.println(App.employeeMap.get(employeeId).toString());
		} catch (Exception e) {
			System.out.println("\nInvalid employee Id.\n");
		}
	}

	/**
	 * Method to update employee records.
	 */
	public void updateEmployee() {
		System.out.println("\nEnter Employee ID to Update details: ");
		Scanner scanner = new Scanner(System.in);
		long employeeId;
		try {
			employeeId = scanner.nextLong();
		} catch (Exception e) {
			System.out.println("\nInvalid employee Id.\n");
			return;
		}

		if (!App.employeeMap.containsKey(employeeId)) {
			System.out.println("\nThere is no employee with this id in the record\n");
			return;
		}

		Employee e = App.employeeMap.get(employeeId);
		System.out.println("\nWhat do you want to update?\n1. First Name\n2. Last Name\n3. Manager Id\n");
		int ch;
		try {
			ch = scanner.nextInt();
		} catch (Exception ex) {
			System.out.println("\nInvalid Input.\n");
			return;
		}
		switch (ch) {
		case 1:
			System.out.println("\nEnter new first name: ");
			String newName = scanner.next();
			e.setEmployeeFirstName(newName);
			App.employeeMap.put(employeeId, e);
			
			Thread t = new Thread(new JsonParallerWriter(e, "update", path));
			t.start();
			
			System.out.println("\nUpdated First Name. ");
			break;

		case 2:
			System.out.println("\nEnter new Last name: ");
			String newLastName = scanner.next();
			e.setEmployeeLastName(newLastName);
			App.employeeMap.put(employeeId, e);
			
			Thread t1 = new Thread(new JsonParallerWriter(e, "update", path));
			t1.start();
			
			System.out.println("\nUpdated Last Name. ");
			break;

		case 3:
			System.out.println("\nEnter new Manager's Id: ");
			long newManagerId;
			try {
				newManagerId = scanner.nextLong();
			} catch (Exception ex) {
				System.out.println("\nInvalid Employee Id.\n");
				return;
			}
			if (!App.employeeMap.containsKey(newManagerId)) {
				System.out.println("\nThere is no manager with this id in the record\n");
				return;
			}

			e.setManagerId(newManagerId);
			App.employeeMap.put(employeeId, e);
			
			Thread t2 = new Thread(new JsonParallerWriter(e, "update", path));
			t2.start();
			
			System.out.println("\nUpdated Manager's Id. ");
			break;

		default:
			System.out.println("\nWrong option. ");

		}
	}

	/**
	 * Method to print employee hierarchy.
	 */
	public void printHierarchy() {
		try {
			System.out.println("\nEnter Employee ID to get hierarchy: ");
			Scanner scanner = new Scanner(System.in);
			long employeeId = scanner.nextLong();
			if (!App.employeeMap.containsKey(employeeId)) {
				System.out.println("\nThere is no employee with this id in the record\n");
				return;
			}
			System.out.println("The Hierarchy is\n");
			printManager(employeeId, 0);
		} catch (Exception e) {
			System.out.println("\nInvalid Employee Id.\n");
		}
	}

	/**
	 * Method to print manager's name from an employeeId.
	 * 
	 * @param employeeId
	 *            is id of employee, whose manager is needed.
	 * @count keep count of hierarchy.
	 */
	public void printManager(long employeeId, int count) {
		try {
			if (employeeId == App.employeeMap.get(employeeId).getManagerId()) {
				System.out.println(App.employeeMap.get(employeeId).getFullName());
				if (!App.employeeMap.get(employeeId).isActive()) {
					System.out.print("(Inactive) ");
				}
				return;
			} else if (count > 15) {
				System.out.println("\nThere is a loop in manager employee relationship.\nPlease correct it!");
				return;
			} else {
				System.out.print(App.employeeMap.get(employeeId).getFullName());
				if (!App.employeeMap.get(employeeId).isActive()) {
					System.out.print("(Inactive) ");
				}
				System.out.print(" -> ");
				printManager(App.employeeMap.get(employeeId).getManagerId(), ++count);
			}
		} catch (Exception e) {
			System.out.println("Error in printing hierarchy!");
		}
	}

	/**
	 * We do not support deleting employee record. we can set the status of
	 * retired or terminated employee to inactive.
	 */
	public void inactivateEmployee() {
		try {
			System.out.println("\nEnter Employee ID to Inactivate: ");
			Scanner scanner = new Scanner(System.in);
			long employeeId = scanner.nextLong();

			if (!App.employeeMap.containsKey(employeeId)) {
				System.out.println("\nThere is no employee with this id in the record\n");
				return;
			}

			Employee e = App.employeeMap.get(employeeId);
			e.setActive(false);
			App.employeeMap.put(employeeId, e);

			Thread t = new Thread(new JsonParallerWriter(e, "update", path));
			t.start();

			System.out.println("\nInactivated the employee. ");

		} catch (Exception e) {
			System.out.println("\nInvalid Employee Id.\n");
		}
	}
}
