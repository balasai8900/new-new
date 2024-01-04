package com.pack.fabo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String storecode;
	private String email;
	private String pincode;
	private String storename;
	private String primaryNumber;
	private String secondaryNumber;
	private String state;
	private String city;
	private String fullAddress;
	private String gmbProfileLink;
	private String gstno;
	private String ownername;
	
	public Client() {
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getPrimaryNumber() {
		return primaryNumber;
	}

	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}

	public String getSecondaryNumber() {
		return secondaryNumber;
	}

	public void setSecondaryNumber(String secondaryNumber) {
		this.secondaryNumber = secondaryNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getGmbProfileLink() {
		return gmbProfileLink;
	}

	public void setGmbProfileLink(String gmbProfileLink) {
		this.gmbProfileLink = gmbProfileLink;
	}

	public String getGstno() {
		return gstno;
	}

	public void setGstno(String gstno) {
		this.gstno = gstno;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public Client(Long id, String storecode, String email, String pincode, String storename, String primaryNumber,
			String secondaryNumber, String state, String city, String fullAddress, String gmbProfileLink, String gstno,
			String ownername) {
		Id = id;
		this.storecode = storecode;
		this.email = email;
		this.pincode = pincode;
		this.storename = storename;
		this.primaryNumber = primaryNumber;
		this.secondaryNumber = secondaryNumber;
		this.state = state;
		this.city = city;
		this.fullAddress = fullAddress;
		this.gmbProfileLink = gmbProfileLink;
		this.gstno = gstno;
		this.ownername = ownername;
	}

	@Override
	public String toString() {
		return "Client [Id=" + Id + ", storecode=" + storecode + ", email=" + email + ", pincode=" + pincode
				+ ", storename=" + storename + ", primaryNumber=" + primaryNumber + ", secondaryNumber="
				+ secondaryNumber + ", state=" + state + ", city=" + city + ", fullAddress=" + fullAddress
				+ ", gmbProfileLink=" + gmbProfileLink + ", gstno=" + gstno + ", ownername=" + ownername + "]";
	}

	
}	