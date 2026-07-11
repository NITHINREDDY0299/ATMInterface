package com.bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeId;
    private String name;
    private String position;
    private String password;
    private LocalDateTime joinedAt;

    public Employee() {
        this.joinedAt = LocalDateTime.now();
    }

    public Employee(String employeeId, String name, String position, String password) {
        this();
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.password = password;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
