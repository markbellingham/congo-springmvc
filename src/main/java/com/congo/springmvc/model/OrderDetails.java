package com.congo.springmvc.model;

public class OrderDetails {
	
	private String artistName;
	private int recordingId;
	private String title;
	private float price;
	private int orderQuantity;
	private String orderDate;
	
	
	public OrderDetails(String artistName, int recordingId, String title, float price, int orderQuantity,
			String orderDate) {
		super();
		this.artistName = artistName;
		this.recordingId = recordingId;
		this.title = title;
		this.price = price;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
	}

	public OrderDetails() {
		super();
	}

	@Override
	public String toString() {
		return "OrderDetails [artistName=" + artistName + ", recordingId=" + recordingId + ", title=" + title
				+ ", price=" + price + ", orderQuantity=" + orderQuantity + ", orderDate=" + orderDate + "]";
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public int getRecordingId() {
		return recordingId;
	}

	public void setRecordingId(int recordingId) {
		this.recordingId = recordingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}
