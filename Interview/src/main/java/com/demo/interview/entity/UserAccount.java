package com.demo.interview.entity;

import jakarta.persistence.*;

@Entity
public class UserAccount {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(unique = true)
	private String UserID;
	
	private String Name;
	
	private String Address;
	
	@Column(unique = true,name = "AccountNum")
	private Long accountNum;
	
	private double balance;
	
	public UserAccount(String userID, String name, String address, Long AccountNum, double balance) {
		super();
		this.UserID = userID;
		this.Name = name;
		this.Address = address;
		this.accountNum = AccountNum;
		this.balance = balance;
	}

	public UserAccount() {
		// TODO Auto-generated constructor stub
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Long getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(Long AccountNum) {
		this.accountNum = AccountNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	
}
