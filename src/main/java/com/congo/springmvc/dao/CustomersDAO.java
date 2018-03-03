package com.congo.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.springmvc.model.CongoCustomers;

public class CustomersDAO {
	
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	// SQL Queries
	private static String FIND_ALL_CUSTOMERS = "SELECT * FROM congo_customers";
	
	public ArrayList<CongoCustomers> findAllCustomers() {
		ArrayList<CongoCustomers> customers = new ArrayList<CongoCustomers>();
		try {
			Connection conn = new DBConnection().openConnection();
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
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
