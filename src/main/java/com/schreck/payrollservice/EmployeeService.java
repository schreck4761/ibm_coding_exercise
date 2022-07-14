package com.schreck.payrollservice;

import java.util.List;

public interface EmployeeService {
    public abstract List<Employee> getAllEmployees();
    public abstract Employee getEmployeeById(Long id);
    public abstract Employee createEmployee(Employee employee);
    public abstract Employee updateEmployeeById(Long id, Employee newEmployeeDetails);
}
