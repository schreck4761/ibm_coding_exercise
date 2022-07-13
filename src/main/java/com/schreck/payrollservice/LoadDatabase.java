package com.schreck.payrollservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {

    return args -> {
      List<Employee> testEmployees = getTestEmployees();
      for (Employee employee : testEmployees) {
        log.info("Preloading " + repository.save(employee));
      }
    };
  }

  private List<Employee> getTestEmployees() {
    List<Employee> testEmployees = new ArrayList<Employee>();
    testEmployees.add(new Employee("Joe", "Tester", "Administrator", 300000, "USD", 10));
    testEmployees.add(new Employee("Hello", "World", "Intern", 1337, "USD", 365));
    testEmployees.add(new Employee("Laura", "Bailey", "Marketer", 130000, "USD", 0));
    testEmployees.add(new Employee("Alexandra", "Li", "President", 163112, "NZD", 16));
    return testEmployees;
  }
}
