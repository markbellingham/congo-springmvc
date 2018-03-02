package com.congo.springmvc.model;

import java.sql.Timestamp;

public class CongoOrders {
	
	private int orderId;
	private int custId;
	private Timestamp orderDate;
	private String comments;
	
	public CongoOrders(int orderId, int custId, Timestamp orderDate, String comments) {
		super();
		this.orderId = orderId;
		this.custId = custId;
		this.orderDate = orderDate;
		this.comments = comments;
	}

	public CongoOrders() {
		super();
	}

	@Override
	public String toString() {
		return "CongoOrders [orderId=" + orderId + ", custId=" + custId + ", orderDate=" + orderDate + ", comments="
				+ comments + "]";
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}
