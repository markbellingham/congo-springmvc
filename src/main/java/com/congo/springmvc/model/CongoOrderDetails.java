package com.congo.springmvc.model;

public class CongoOrderDetails {

	private int orderId;
	private int recordingId;
	private int orderQuantity;
	
	public CongoOrderDetails(int orderId, int recordingId, int orderQuantity) {
		super();
		this.orderId = orderId;
		this.recordingId = recordingId;
		this.orderQuantity = orderQuantity;
	}

	public CongoOrderDetails() {
		super();
	}

	@Override
	public String toString() {
		return "CongoOrderDetails [orderId=" + orderId + ", recordingId=" + recordingId + ", orderQuantity="
				+ orderQuantity + "]";
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getRecordingId() {
		return recordingId;
	}
	public void setRecordingId(int recordingId) {
		this.recordingId = recordingId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	
}
