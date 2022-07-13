package com.schreck.payrollservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    Employee getEmployeeById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping("/employees")
    Employee createEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @PostMapping("/employees/{id}")
    Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return repository.findById(id)
            .map(employee -> {
                employee.updateFromEmployee(updatedEmployee);
                return repository.save(employee);
            })
            .orElseThrow(() -> new EmployeeNotFoundException(id));
        
    }
}
