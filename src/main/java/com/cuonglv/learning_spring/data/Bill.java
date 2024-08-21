package com.cuonglv.learning_spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "bills")
public class Bill {

	@Id
	private Integer billId;
	private String creatorId;
	private Double amount;
	private String status;
	private String description;
	private String method;
	private String transactionId;
	private Date createdAt;
	private Date updatedAt;

	public Bill() {
		// Default constructor
	}

	public Bill(Integer billId, String creatorId, Double amount, String status, String description, String method,
			String transactionId, Date createdAt, Date updatedAt) {
		this.billId = billId;
		this.creatorId = creatorId;
		this.amount = amount;
		this.status = status;
		this.description = description;
		this.method = method;
		this.transactionId = transactionId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Getters and Setters
	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Bill{" + "billId=" + billId + ", creatorId='" + creatorId + '\'' + ", amount=" + amount + ", status='"
				+ status + '\'' + ", description='" + description + '\'' + ", method='" + method + '\''
				+ ", transactionId='" + transactionId + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ '}';
	}
}