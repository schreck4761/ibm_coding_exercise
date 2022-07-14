package com.schreck.payrollservice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeModelAssembler assembler;

    @Autowired
    EmployeeService employeeService;

    EmployeeController(EmployeeModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        List<EntityModel<Employee>> allEmployeeModels = allEmployees.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(allEmployeeModels,
            linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
    }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return assembler.toModel(employee);
    }

    @PostMapping("/employees")
    ResponseEntity<EntityModel<Employee>> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        EntityModel<Employee> entityModel = assembler.toModel(createdEmployee);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PostMapping("/employees/{id}")
    ResponseEntity<EntityModel<Employee>> updateEmployeeById(@PathVariable Long id, @RequestBody Employee newEmployeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployeeById(id, newEmployeeDetails);
        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }
}
