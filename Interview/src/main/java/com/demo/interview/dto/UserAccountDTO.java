package com.demo.interview.dto;

import org.springframework.stereotype.Component;

@Component
public class UserAccountDTO {
	
	public UserAccountDTO() {
		
	}
	
	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public UserAccountDTO(String userID, String name, String address, Long AccountNum, double balance) {
		super();
		this.UserID = userID;
		this.Name = name;
		this.Address = address;
		this.AccountNum = AccountNum;
		this.balance = balance;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}

	public Long getAccountNum() {
		return AccountNum;
	}

	public void setAccountNum(Long AccountNum) {
		this.AccountNum = AccountNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	private String UserID;
	
	private String Name;
	
	private String Address;
	
	private Long AccountNum;
	
	private double balance;
}
