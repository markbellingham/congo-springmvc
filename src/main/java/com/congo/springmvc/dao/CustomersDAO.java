package com.congo.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.springmvc.model.CongoCustomers;
import com.congo.springmvc.model.MusicRecordings;

public class CustomersDAO {
	
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private ArrayList<CongoCustomers> customers;
	
	// SQL Queries
	private static String FIND_ALL_CUSTOMERS = "SELECT * FROM congo_customers";
	private static String FIND_CUSTOMER_BY_EMAIL = "SELECT * FROM congo_customers WHERE email = ?"; 
	
	public ArrayList<CongoCustomers> findAllCustomers() {
		ArrayList<CongoCustomers> customers = new ArrayList<CongoCustomers>();
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALL_CUSTOMERS);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int custId = resultSet.getInt("custid");
				String fname = resultSet.getString("fname");
				String lname = resultSet.getString("lname");
				String email = resultSet.getString("email");
				CongoCustomers cust = new CongoCustomers(custId, fname, lname, email);
				customers.add(cust);
			}
			resultSet.close();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return customers;
	}
	
	public CongoCustomers findCustomerByEmail(String email) {
		CongoCustomers customer = new CongoCustomers();
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_CUSTOMER_BY_EMAIL);
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				customer.setCustId(resultSet.getInt("custId"));
				customer.setFname(resultSet.getString("fname"));
				customer.setLname(resultSet.getString("lname"));
				customer.setAddress1(resultSet.getString("address1"));
				customer.setAddress2(resultSet.getString("address2"));
				customer.setCity(resultSet.getString("city"));
				customer.setPostcode(resultSet.getString("postcode"));
				customer.setPhone(resultSet.getString("phone"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPassword(resultSet.getString("password"));
				customer.setAdmin(resultSet.getInt("admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return customer;
	}
	
	/**
	 * Method used by the other functions to create an ArrayList of the customers
	 * @param resultSet
	 * @return ArrayList of type CongoCustomer
	 * @throws SQLException
	 */
	private ArrayList<CongoCustomers> addToArray(ResultSet resultSet) throws SQLException {
		customers = new ArrayList<CongoCustomers>();
		while(resultSet.next()) {
			int custId = resultSet.getInt("custid");
			String fname = resultSet.getString("fname");
			String lname = resultSet.getString("lname");
			String address1 = resultSet.getString("address1");
			String address2 = resultSet.getString("address2");
			String city = resultSet.getString("city");
			String postcode = resultSet.getString("postcode");
			String phone = resultSet.getString("phone");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			int admin = resultSet.getInt("admin");
			CongoCustomers customer = new CongoCustomers(custId, fname, lname, address1, address2, city, postcode, phone, email, password, admin);
			customers.add(customer);
		}
		return customers;
	}
	
	/**
	 * Singleton Design Pattern
	 * means that only one instance of this DAO can exist at a time, eliminating the possibility of errors in the database
	 */
	private static CustomersDAO instance = null;
	
	private CustomersDAO() {}
	
	public static CustomersDAO getInstance() {
		if(instance == null) {
			instance = new CustomersDAO();
		}
		return instance;
	}

	/**
	 * Main method for testing the DAO
	 */
	public static void main(String[] args) {
		CustomersDAO cdao = CustomersDAO.getInstance();
		ArrayList<CongoCustomers> customers = cdao.findAllCustomers();
		System.out.println(customers);
	}
}
