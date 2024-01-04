package com.pack.fabo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String storeName;
	private String storeCode;
	private String ownerName;
	private String primaryNumber;
	private LocalDate invoiceDate;
	private String invoiceAmount;
	private String invoiceType;
	private String invoiceStatus;
	
	
	public Invoice() {
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getStoreCode() {
		return storeCode;
	}


	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getPrimaryNumber() {
		return primaryNumber;
	}


	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}


	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public String getInvoiceAmount() {
		return invoiceAmount;
	}


	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}


	public String getInvoiceType() {
		return invoiceType;
	}


	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}


	public String getInvoiceStatus() {
		return invoiceStatus;
	}


	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}


	public Invoice( String storeName, String storeCode, String ownerName, String primaryNumber,
			LocalDate invoiceDate, String invoiceAmount, String invoiceType, String invoiceStatus) {
		this.storeName = storeName;
		this.storeCode = storeCode;
		this.ownerName = ownerName;
		this.primaryNumber = primaryNumber;
		this.invoiceDate = invoiceDate;
		this.invoiceAmount = invoiceAmount;
		this.invoiceType = invoiceType;
		this.invoiceStatus = invoiceStatus;
	}


	@Override
	public String toString() {
		return "Invoice [id=" + id + ", storeName=" + storeName + ", storeCode=" + storeCode + ", ownerName="
				+ ownerName + ", primaryNumber=" + primaryNumber + ", invoiceDate=" + invoiceDate + ", invoiceAmount="
				+ invoiceAmount + ", invoiceType=" + invoiceType + ", invoiceStatus=" + invoiceStatus + "]";
	}

	
	
	
}
