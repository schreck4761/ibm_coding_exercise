package com.schreck.payrollservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String role;
    private int salary;
    private String salaryCurrency;
    private int remainingVacationDays;
    
    Employee () {}

    Employee(String firstName, String lastName, String role, int salary, String salaryCurrency, int remainingVacationDays) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
        this.salaryCurrency = salaryCurrency;
        this.remainingVacationDays = remainingVacationDays;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRole() {
        return this.role;
    }

    public int getSalary() {
        return this.salary;
    }

    public String getSalaryCurrency() {
        return this.salaryCurrency;
    }

    public int getRemainingVacationDays() {
        return this.remainingVacationDays;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSalaryCurrency(String currency) {
        this.salaryCurrency = currency;
    }

    public void setRemainingVacationDays(int days) {
        this.remainingVacationDays = days;
    }
}
