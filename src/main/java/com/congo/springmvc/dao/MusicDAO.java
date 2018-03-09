package com.congo.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.congo.springmvc.model.MusicCategories;
import com.congo.springmvc.model.MusicRecordings;
import com.congo.springmvc.model.MusicTracks;

public class MusicDAO {
	
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private Connection conn = null;
	private ArrayList<MusicRecordings> albums = null;
	
	// SQL Query Strings
	private static String FIND_ALL_ALBUMS = "SELECT * FROM Music_Recordings ORDER BY artist_name";
	private static String FIND_ALL_CATEGORIES = "SELECT * FROM Music_Categories";
	private static String FIND_ALBUMS_BY_CATEGORY = "SELECT * FROM Music_Recordings WHERE category = ? ORDER BY artist_name";
	private static String FIND_ALBUMS_BY_PRICE = "SELECT * FROM Music_Recordings WHERE price >= ? AND price <= ? ORDER BY price, artist_name";
	private static String FIND_ALBUMS_BY_ARTIST = "SELECT * FROM Music_Recordings WHERE LOWER(artist_name) LIKE ?";
	private static String FIND_ALBUM_TRACKS = "SELECT * FROM Music_Tracks WHERE recording_id = ?";
	private static String FIND_ALBUM_BY_ID = "SELECT * FROM Music_Recordings WHERE recording_id = ?";
	
	
	public ArrayList<MusicRecordings> findAllRecordings() {
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALL_ALBUMS);
			resultSet = statement.executeQuery();
			albums = addToArray(resultSet);
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return albums;
	}
	
	public ArrayList<MusicCategories> findAllCategories() {
		ArrayList<MusicCategories> categories = new ArrayList<MusicCategories>();
		try {
			conn = new DBConnection().openConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return categories;
	}
	
	public ArrayList<MusicRecordings> findRecordingsByCategory(String selection) {
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALBUMS_BY_CATEGORY);
			statement.setString(1, selection);
			resultSet = statement.executeQuery();
			albums = addToArray(resultSet);
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return albums; 
	}
	
	public ArrayList<MusicRecordings> findRecordingsByPrice(int selectedPrice) {
		try {
			float lowerPrice = (float) selectedPrice - 2;
			float higherPrice = (float) selectedPrice;
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALBUMS_BY_PRICE);
			statement.setFloat(1, lowerPrice);
			statement.setFloat(2, higherPrice);
			resultSet = statement.executeQuery();
			albums = addToArray(resultSet);
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return albums;
	}
	
	public ArrayList<MusicRecordings> findRecordingsByArtist(String name) {
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALBUMS_BY_ARTIST);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			albums = addToArray(resultSet);
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return albums;
	}
	
	private ArrayList<MusicRecordings> addToArray(ResultSet resultSet) throws SQLException {
		albums = new ArrayList<MusicRecordings>();
		while(resultSet.next()) {
			int recordingId = resultSet.getInt("recording_id");
			String artistName = resultSet.getString("artist_name");
			String title = resultSet.getString("title");
			String category = resultSet.getString("category");
			String image = resultSet.getString("image_name");
			int num_tracks = resultSet.getInt("num_tracks");
			float price = resultSet.getFloat("price");
			int stockCount = resultSet.getInt("stock_count");
			MusicRecordings record = new MusicRecordings(recordingId, artistName, title, category, image, num_tracks, price, stockCount);
			albums.add(record);
		}
		return albums;
	}
	
	public ArrayList<MusicTracks> findAlbumTracks(int recordingId) {
		ArrayList<MusicTracks> tracks = new ArrayList<MusicTracks>();
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALBUM_TRACKS);
			statement.setInt(1, recordingId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String title = resultSet.getString("title");
				int duration = resultSet.getInt("duration");
				MusicTracks track = new MusicTracks(title, duration);
				tracks.add(track);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return tracks;
	}
	
	public MusicRecordings findAlbumById(int recordingId) {
		MusicRecordings album = null;
		try {
			conn = new DBConnection().openConnection();
			statement = conn.prepareStatement(FIND_ALBUM_BY_ID);
			statement.setInt(1, recordingId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String artistName = resultSet.getString("artist_name");
				String title = resultSet.getString("title");
				String category = resultSet.getString("category");
				String image = resultSet.getString("image_name");
				int numTracks = resultSet.getInt("num_tracks");
				float price = resultSet.getFloat("price");
				int stockCount = resultSet.getInt("stock_count");
				album = new MusicRecordings(recordingId, artistName, title, category, image, numTracks, price, stockCount);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return album;
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
