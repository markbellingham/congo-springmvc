package com.congo.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.springmvc.model.MusicRecordings;

public class MusicRecordingsDAO {
	
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	// SQL Queries
	private static String FIND_ALL_RECORDINGS = "SELECT * FROM Music_Recordings";
	private static String FIND_RECORDINGS_BY_CATEGORY = "SELECT * FROM Music_Recordings WHERE category = ?";
	
	
	public ArrayList<MusicRecordings> findAllRecordings() {
		ArrayList<MusicRecordings> recordings = new ArrayList<MusicRecordings>();
		try {
			Connection conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALL_RECORDINGS);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int recordingId = resultSet.getInt("recording_id");
				String artistName = resultSet.getString("artist_name");
				String title = resultSet.getString("title");
				String category = resultSet.getString("category");
				int num_tracks = resultSet.getInt("num_tracks");
				float price = resultSet.getFloat("price");
				MusicRecordings record = new MusicRecordings(recordingId, artistName, title, category, num_tracks, price);
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
	private static MusicRecordingsDAO instance = null;
	
	private MusicRecordingsDAO() {}
	
	public static MusicRecordingsDAO getInstance() {
		if(instance == null) {
			instance = new MusicRecordingsDAO();
		}
		return instance;
	}

	/**
	 * Main method for testing the DAO
	 */
	public static void main(String[] args) {
		MusicRecordingsDAO cdao = MusicRecordingsDAO.getInstance();
		ArrayList<MusicRecordings> customers = cdao.findAllRecordings();
		System.out.println(customers);
	}
}
