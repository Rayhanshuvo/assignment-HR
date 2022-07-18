package com.leads.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class EmployeeController {

	private List<Employee> employees;
	private EmployeeDbUtil employeeDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public EmployeeController() throws Exception {
		employees = new ArrayList<>();
		
		employeeDbUtil = EmployeeDbUtil.getInstance();
	}
	
	public List<Employee> getEmployee() {
		return employees;
	}

	//List Employee functionality 
	
	public void loadEmployee() {

		logger.info("Loading employees");
		
		employees.clear();

		try {
			
		
			employees = employeeDbUtil.getEmployee();
			
		} catch (Exception exc) {

			logger.log(Level.SEVERE, "Error loading employee", exc);

			addErrorMessage(exc);
		}
	}
	
	//Add Employee
		
	public String addEmployee(Employee theEmployee) {

		logger.info("Adding Employee: " + theEmployee);

		try {
			
		
			employeeDbUtil.addEmployee(theEmployee);
			
		} catch (Exception exc) {
	
			logger.log(Level.SEVERE, "Error adding employee", exc);

			addErrorMessage(exc);

			return null;
		}
		
		return "list-employee?faces-redirect=true";
	}
	
	
//Update Employee
	
	public String loadEmployee(int employeeId) {
		
		logger.info("loading employee: " + employeeId);
		
		try {


			Employee theEmployee = employeeDbUtil.getEmployee(employeeId);
		
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("employee", theEmployee);	
			
		} catch (Exception exc) {

			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-employee-form.xhtml";
	}	
	
	public String updateEmployee(Employee theEmployee) {

		logger.info("updating employee: " + theEmployee);
		
		try {
			
	
			employeeDbUtil.updateEmployee(theEmployee);
			
		} catch (Exception exc) {

			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-employee?faces-redirect=true";		
	}
	
	//Delete Employee
	
	public String deleteEmployee(int employeeId) {

		logger.info("Deleting employee id: " + employeeId);
		
		try {

		
			employeeDbUtil.deleteEmployee(employeeId);
			
		} catch (Exception exc) {

			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-employee";	
	}	
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
