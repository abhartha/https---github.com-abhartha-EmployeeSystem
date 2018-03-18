package org.employee.system.ankit.employee;

/**
 * This is our Employee class, with all the values relevent to an employee.
 * For now I just maintain few values.
 * @author Ankit Bharthan
 *
 */
public class Employee {
	/**
	 * employeeId is unique for each employee. And is assigned in increment of 1.
	 */
	long employeeId;
	
	/**
	 * First Name of employee.
	 */
	String employeeFirstName;
	/**
	 * Last Name of Employee.
	 */
	String employeeLastName;
	
	/**
	 * Employee id of the manager that the employee reports to.
	 */
	long managerId;
	
	/**
	 * If the employe is no longer working, its record is not deleted it is just set to inactive.
	 */
	boolean isActive;
	
	public Employee(long employeeId, String employeeFN, String employeeLN, long managerId, boolean isActive) {
		this.employeeId = employeeId;
		employeeFirstName = employeeFN;
		employeeLastName = employeeLN;
		this.managerId = managerId;
		this.isActive = isActive;
	}
	
	public long getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public long getManagerId() {
		return managerId;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Employee ID: ");
		sb.append(this.getEmployeeId());
		sb.append("\nName: ");
		sb.append(this.getEmployeeFirstName());
		sb.append(" ");
		sb.append(this.getEmployeeLastName());
		sb.append("\n");
		sb.append("Manager ID: ");
		sb.append(this.getManagerId());
		sb.append("\nIs Active: ");
		sb.append(this.isActive());
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String getFullName() {
		return this.employeeFirstName + " " + this.employeeLastName;
 	}
}
