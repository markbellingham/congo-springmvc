package com.congo.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.springmvc.model.CongoCustomers;
import com.congo.springmvc.model.OrderDetails;

public class CustomersDAO {
	
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private ArrayList<CongoCustomers> customers;
	
	// SQL Queries
	private static String FIND_ALL_CUSTOMERS = "SELECT * FROM congo_customers";
	private static String FIND_CUSTOMER_BY_EMAIL = "SELECT * FROM congo_customers WHERE email = ?";
	private static String INSERT_NEW_CUSTOMER = "INSERT INTO congo_customers "
			+ "(fname, lname, address1, address2, city, postcode, phone, email, password, admin) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String FIND_ALL_MY_ORDERS = "SELECT * FROM congo_orders o, congo_order_details d, Music_Recordings r "
			+ "WHERE o.custid = ? AND o.orderid = d.orderid AND d.recording_id = r.recording_id ORDER BY o.order_date";
	
	
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
	
	public boolean insertNewCustomer(CongoCustomers c) {
		boolean t = true;
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(INSERT_NEW_CUSTOMER);
			statement.setString(1, c.getFname());
			statement.setString(2, c.getLname());
			statement.setString(3, c.getAddress1());
			statement.setString(4, c.getAddress2());
			statement.setString(5, c.getCity());
			statement.setString(6, c.getPostcode());
			statement.setString(7, c.getPhone());
			statement.setString(8, c.getEmail());
			statement.setString(9, c.getPassword());
			statement.setInt(10, 0);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return t;
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
	
	public ArrayList<OrderDetails> findAllMyOrders(int custId) {
		ArrayList<OrderDetails> orders = new ArrayList<OrderDetails>();
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALL_MY_ORDERS);
			statement.setInt(1, custId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				OrderDetails order = new OrderDetails();
				order.setArtistName(resultSet.getString("artist_name"));
				order.setRecordingId(resultSet.getInt("recording_id"));
				order.setTitle(resultSet.getString("title"));
				order.setPrice(resultSet.getFloat("price"));
				order.setOrderQuantity(resultSet.getInt("order_quantity"));
				float totalPrice = order.getPrice() * order.getOrderQuantity();
				order.setTotalPrice(totalPrice);
				order.setOrderDate(resultSet.getString("order_date"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return orders;
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
