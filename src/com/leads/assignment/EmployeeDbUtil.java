package com.leads.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeDbUtil {

	private static EmployeeDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/employee";
	
	public static EmployeeDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new EmployeeDbUtil();
		}
		
		return instance;
	}
	
	private EmployeeDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Employee> getEmployee() throws Exception {

		List<Employee> employees = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from employee order by last_name";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String empId = myRs.getString("emp_id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String title = myRs.getString("title");
				String division = myRs.getString("division");
				String building = myRs.getString("building");
				String room = myRs.getString("room");
		
				Employee tempStudent = new Employee(id,empId,firstName,lastName,title,division,building,room);

	
				employees.add(tempStudent);
			}
			
			return employees;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addEmployee(Employee employee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into employee (emp_id, first_name, last_name, division,building,room) values (?,?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, employee.getEmpId());
			myStmt.setString(2, employee.getFirstName());
			myStmt.setString(3, employee.getLastName());
			myStmt.setString(4, employee.getDivision());
			myStmt.setString(5, employee.getBuilding());
			myStmt.setString(6, employee.getRoom());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public Employee getEmployee(int empId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from employee where id=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, empId);
			
			myRs = myStmt.executeQuery();

			Employee theEmp = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String emp_id = myRs.getString("emp_id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String title = myRs.getString("title");
				String division = myRs.getString("division");
				String building = myRs.getString("building");
				String room = myRs.getString("room");

				theEmp = new Employee(id,emp_id,firstName,lastName,title,division,building,room);
			}
			else {
				throw new Exception("Could not find student id: " + empId);
			}

			return theEmp;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateEmployee(Employee employee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
        String firstName= employee.getFirstName();
        Employee emp=checkingEmployeeRecordsByFirstName(firstName,employee.getId());
        
        if(emp!=null) {
        	try {
    			myConn = getConnection();

    			
    			String sql = "update employee "
    						+ " set id=?, emp_id=?, first_name=?, last_name=?,title=?, division=?, building=?, room=?"
    						+ " where id=?";

    			myStmt = myConn.prepareStatement(sql);

    			// set params
    			myStmt.setInt(1, employee.getId());
    			myStmt.setString(2, employee.getEmpId());
    			myStmt.setString(3, employee.getFirstName());
    			myStmt.setString(4, employee.getLastName());
    			myStmt.setString(5, employee.getTitle());
    			myStmt.setString(6, employee.getDivision());
    			myStmt.setString(7, employee.getBuilding());
    			myStmt.setString(8, employee.getRoom());
    			myStmt.setInt(9, employee.getId());
    			myStmt.execute();
    		}
        	
        
	
		finally {
			close (myConn, myStmt);
		}
        	}
		
	}
	
	private Employee checkingEmployeeRecordsByFirstName(String firstName, int id) throws Exception {
		
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Employee employee = new Employee();
		try {
			myConn = getConnection();
			String sql = "select * from employee where first_name=? AND id=?";
			myStmt = myConn.prepareStatement(sql);
			  myStmt.setString(1, firstName);
            myStmt.setInt(2, id);
          
			myRs = myStmt.executeQuery();
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id1 = myRs.getInt("id");
				String empId = myRs.getString("emp_id");
				String firstName1 = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String title = myRs.getString("title");
				String division = myRs.getString("division");
				String building = myRs.getString("building");
				String room = myRs.getString("room");
				employee = new Employee(id1,empId,firstName1,lastName,title,division,building,room);
			
			}
			return employee;
			
		}
		
		finally {
			
			
		}
	}

	public void deleteEmployee(int studentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from employee where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	
	public void insertAllXmlDataToDatabase(List<Employee> employees) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		  for (Employee emp : employees) {
				
				try {
					myConn = getConnection();

					String sql = "insert into employee (emp_id,first_name, last_name,title, division,building,room) values (?, ?, ?, ?, ?, ?, ?)";

					myStmt = myConn.prepareStatement(sql);

					// set params
					myStmt.setString(1, emp.getEmpId());
					myStmt.setString(2, emp.getFirstName());
					myStmt.setString(3, emp.getLastName());
					myStmt.setString(4, emp.getTitle());
					myStmt.setString(5, emp.getDivision());
					myStmt.setString(6, emp.getBuilding());
					myStmt.setString(7, emp.getRoom());
					
					myStmt.execute();			
				}
				finally {
					close (myConn, myStmt);
				}
	   
	        }
		
	
		
		
	}

	
}
