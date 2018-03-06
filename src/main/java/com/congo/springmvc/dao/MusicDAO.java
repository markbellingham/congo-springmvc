package com.congo.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.springmvc.model.MusicCategories;
import com.congo.springmvc.model.MusicRecordings;

public class MusicDAO {
	
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	// SQL Queries
	private static String FIND_ALL_ALBUMS = "SELECT * FROM Music_Recordings ORDER BY artist_name";
	private static String FIND_ALL_CATEGORIES = "SELECT * FROM Music_Categories";
	private static String FIND_ALBUMS_BY_CATEGORY = "SELECT * FROM Music_Recordings WHERE category = ? ORDER BY artist_name";
	private static String FIND_ALBUMS_BY_PRICE = "SELECT * FROM Music_Recordings WHERE price >= ? AND price <= ? ORDER BY price, artist_name";
	
	
	public ArrayList<MusicRecordings> findAllRecordings() {
		ArrayList<MusicRecordings> albums = new ArrayList<MusicRecordings>();
		try {
			Connection conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALL_ALBUMS);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int recordingId = resultSet.getInt("recording_id");
				String artistName = resultSet.getString("artist_name");
				String title = resultSet.getString("title");
				String category = resultSet.getString("category");
				int num_tracks = resultSet.getInt("num_tracks");
				float price = resultSet.getFloat("price");
				MusicRecordings record = new MusicRecordings(recordingId, artistName, title, category, num_tracks, price);
				albums.add(record);
			}
			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albums;
	}
	
	public ArrayList<MusicCategories> findAllCategories() {
		ArrayList<MusicCategories> categories = new ArrayList<MusicCategories>();
		try {
			Connection conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALL_CATEGORIES);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				MusicCategories category = new MusicCategories(id, name);
				categories.add(category);
			}
			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public ArrayList<MusicRecordings> findRecordingsByCategory(String selection) {
		ArrayList<MusicRecordings> albums = new ArrayList<MusicRecordings>();
		try {
			Connection conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALBUMS_BY_CATEGORY);
			statement.setString(1, selection);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int recordingId = resultSet.getInt("recording_id");
				String artistName = resultSet.getString("artist_name");
				String title = resultSet.getString("title");
				String category = resultSet.getString("category");
				int num_tracks = resultSet.getInt("num_tracks");
				float price = resultSet.getFloat("price");
				MusicRecordings record = new MusicRecordings(recordingId, artistName, title, category, num_tracks, price);
				albums.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return albums; 
	}
	
	public ArrayList<MusicRecordings> findRecordingsByPrice(int selectedPrice) {
		ArrayList<MusicRecordings> albums = new ArrayList<MusicRecordings>();
		try {
			float lowerPrice = (float) selectedPrice - 2;
			float higherPrice = (float) selectedPrice;
			Connection conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALBUMS_BY_PRICE);
			statement.setFloat(1, lowerPrice);
			statement.setFloat(2, higherPrice);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int recordingId = resultSet.getInt("recording_id");
				String artistName = resultSet.getString("artist_name");
				String title = resultSet.getString("title");
				String category = resultSet.getString("category");
				int num_tracks = resultSet.getInt("num_tracks");
				float price = resultSet.getFloat("price");
				MusicRecordings record = new MusicRecordings(recordingId, artistName, title, category, num_tracks, price);
				albums.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albums;
	}
	
	
	/**
	 * Singleton Design Pattern
	 * means that only one instance of this DAO can exist at a time, eliminating 
	 * the possibility of errors from concurrent threads accessing the database
	 */
	private static MusicDAO instance = null;
	
	private MusicDAO() {}
	
	public static MusicDAO getInstance() {
		if(instance == null) {
			instance = new MusicDAO();
		}
		return instance;
	}

	/**
	 * Main method for testing the DAO
	 */
	public static void main(String[] args) {
		MusicDAO cdao = MusicDAO.getInstance();
		ArrayList<MusicRecordings> customers = cdao.findAllRecordings();
		System.out.println(customers);
	}
}
