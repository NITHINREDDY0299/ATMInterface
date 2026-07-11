package com.bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String accountNumber;
    private double balance;
    private LocalDateTime registeredAt;
    private LocalDateTime lastInterestApplied;

    public Customer() {
        this.registeredAt = LocalDateTime.now();
        this.lastInterestApplied = LocalDateTime.now();
    }

    public Customer(String customerId, String fullName, String email, String phone,
                    String address, String password, String accountNumber) {
        this();
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getLastInterestApplied() {
        return lastInterestApplied;
    }

    public void setLastInterestApplied(LocalDateTime lastInterestApplied) {
        this.lastInterestApplied = lastInterestApplied;
    }
}
