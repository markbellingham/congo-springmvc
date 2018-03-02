package com.congo.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.springmvc.model.MusicCategories;

public class MusicCategoriesDAO {
	
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	// SQL Queries
	private static String FIND_ALL_CATEGORIES = "SELECT * FROM Music_Categories";
	
	public ArrayList<MusicCategories> findAllCategories() {
		ArrayList<MusicCategories> recordings = new ArrayList<MusicCategories>();
		try {
			Connection conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALL_CATEGORIES);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				MusicCategories record = new MusicCategories(id, name);
				recordings.add(record);
			}
			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordings;
	}
	
	/**
	 * Singleton Design Pattern
	 * means that only one instance of this DAO can exist at a time, eliminating the possibility of errors in the database
	 */
	private static MusicCategoriesDAO instance = null;
	
	private MusicCategoriesDAO() {}
	
	public static MusicCategoriesDAO getInstance() {
		if(instance == null) {
			instance = new MusicCategoriesDAO();
		}
		return instance;
	}

	/**
	 * Main method for testing the DAO
	 */
	public static void main(String[] args) {
		MusicCategoriesDAO cdao = MusicCategoriesDAO.getInstance();
		ArrayList<MusicCategories> categories = cdao.findAllCategories();
		System.out.println(categories);
	}
}
