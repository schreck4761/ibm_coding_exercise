package com.schreck.payrollservice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository repository;

    EmployeeServiceImp(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee newEmployeeDetails) {
        return repository.findById(id)
            .map(employee -> {
                employee.updateFromEmployee(newEmployeeDetails);
                return repository.save(employee);
            })
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
