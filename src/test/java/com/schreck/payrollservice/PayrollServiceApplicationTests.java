package com.schreck.payrollservice;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PayrollServiceApplicationTests {

	@MockBean
	private EmployeeServiceImp employeeService;

	@Autowired
	private MockMvc mockMvc;

	private List<Employee> getTestEmployees() {
		List<Employee> testEmployees = new ArrayList<Employee>();
		testEmployees.add(new Employee("Joe", "Tester", "Administrator", 300000, "USD", 10));
		testEmployees.get(0).setId((long) 1);
		testEmployees.add(new Employee("Hello", "World", "Intern", 1337, "USD", 365));
		testEmployees.get(1).setId((long)2);
		testEmployees.add(new Employee("Laura", "Bailey", "Marketer", 130000, "USD", 0));
		testEmployees.get(2).setId((long)3);
		testEmployees.add(new Employee("Alexandra", "Li", "President", 163112, "NZD", 16));
		testEmployees.get(3).setId((long)4);
		return testEmployees;
	}

	@Test
	public void givenEmployees_whenGetAllEmployees_thenReturnAllEmployeesAsHalJson() throws Exception {
		List<Employee> testEmployees = this.getTestEmployees();
		given(employeeService.getAllEmployees()).willReturn(testEmployees);

		this.mockMvc.perform(get("/api/employees").accept(MediaTypes.HAL_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andExpect(jsonPath("$._embedded.employeeList.length()", is(testEmployees.size())))
			.andExpect(jsonPath("$._embedded.employeeList[0].id", is(testEmployees.get(0).getId()), Long.class))
			.andExpect(jsonPath("$._embedded.employeeList[0].firstName", is(testEmployees.get(0).getFirstName())))
			.andExpect(jsonPath("$._embedded.employeeList[0].lastName", is(testEmployees.get(0).getLastName())))
			.andExpect(jsonPath("$._embedded.employeeList[0].role", is(testEmployees.get(0).getRole())))
			.andExpect(jsonPath("$._embedded.employeeList[0].salary", is(testEmployees.get(0).getSalary())))
			.andExpect(jsonPath("$._embedded.employeeList[0].salaryCurrency", is(testEmployees.get(0).getSalaryCurrency())))
			.andExpect(jsonPath("$._embedded.employeeList[0].remainingVacationDays", is(testEmployees.get(0).getRemainingVacationDays())))
			.andExpect(jsonPath("$._embedded.employeeList[0]._links.self.href", is(String.format("http://localhost/api/employees/%o", testEmployees.get(0).getId()))))
			.andExpect(jsonPath("$._embedded.employeeList[0]._links.employees.href", is("http://localhost/api/employees")))
			.andExpect(jsonPath("$._embedded.employeeList[1].id", is(testEmployees.get(1).getId()), Long.class))
			.andExpect(jsonPath("$._embedded.employeeList[1].firstName", is(testEmployees.get(1).getFirstName())))
			.andExpect(jsonPath("$._embedded.employeeList[1].lastName", is(testEmployees.get(1).getLastName())))
			.andExpect(jsonPath("$._embedded.employeeList[1].role", is(testEmployees.get(1).getRole())))
			.andExpect(jsonPath("$._embedded.employeeList[1].salary", is(testEmployees.get(1).getSalary())))
			.andExpect(jsonPath("$._embedded.employeeList[1].salaryCurrency", is(testEmployees.get(1).getSalaryCurrency())))
			.andExpect(jsonPath("$._embedded.employeeList[1].remainingVacationDays", is(testEmployees.get(1).getRemainingVacationDays())))
			.andExpect(jsonPath("$._embedded.employeeList[1]._links.self.href", is(String.format("http://localhost/api/employees/%o", testEmployees.get(1).getId()))))
			.andExpect(jsonPath("$._embedded.employeeList[1]._links.employees.href", is("http://localhost/api/employees")))
			.andExpect(jsonPath("$._embedded.employeeList[2].id", is(testEmployees.get(2).getId()), Long.class))
			.andExpect(jsonPath("$._embedded.employeeList[2].firstName", is(testEmployees.get(2).getFirstName())))
			.andExpect(jsonPath("$._embedded.employeeList[2].lastName", is(testEmployees.get(2).getLastName())))
			.andExpect(jsonPath("$._embedded.employeeList[2].role", is(testEmployees.get(2).getRole())))
			.andExpect(jsonPath("$._embedded.employeeList[2].salary", is(testEmployees.get(2).getSalary())))
			.andExpect(jsonPath("$._embedded.employeeList[2].salaryCurrency", is(testEmployees.get(2).getSalaryCurrency())))
			.andExpect(jsonPath("$._embedded.employeeList[2].remainingVacationDays", is(testEmployees.get(2).getRemainingVacationDays())))
			.andExpect(jsonPath("$._embedded.employeeList[2]._links.self.href", is(String.format("http://localhost/api/employees/%o", testEmployees.get(2).getId()))))
			.andExpect(jsonPath("$._embedded.employeeList[2]._links.employees.href", is("http://localhost/api/employees")))
			.andExpect(jsonPath("$._embedded.employeeList[3].id", is(testEmployees.get(3).getId()), Long.class))
			.andExpect(jsonPath("$._embedded.employeeList[3].firstName", is(testEmployees.get(3).getFirstName())))
			.andExpect(jsonPath("$._embedded.employeeList[3].lastName", is(testEmployees.get(3).getLastName())))
			.andExpect(jsonPath("$._embedded.employeeList[3].role", is(testEmployees.get(3).getRole())))
			.andExpect(jsonPath("$._embedded.employeeList[3].salary", is(testEmployees.get(3).getSalary())))
			.andExpect(jsonPath("$._embedded.employeeList[3].salaryCurrency", is(testEmployees.get(3).getSalaryCurrency())))
			.andExpect(jsonPath("$._embedded.employeeList[3].remainingVacationDays", is(testEmployees.get(3).getRemainingVacationDays())))
			.andExpect(jsonPath("$._embedded.employeeList[3]._links.self.href", is(String.format("http://localhost/api/employees/%o", testEmployees.get(3).getId()))))
			.andExpect(jsonPath("$._embedded.employeeList[3]._links.employees.href", is("http://localhost/api/employees")));
	}

	@Test
	public void givenEmployees_whenGetEmployeeById_thenReturnOneEmployeeAsHalJson() throws Exception {
		List<Employee> testEmployees = this.getTestEmployees();
		given(employeeService.getEmployeeById((long)1)).willReturn(testEmployees.get(0));

		this.mockMvc.perform(get("/api/employees/1").accept(MediaTypes.HAL_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(testEmployees.get(0).getId()), Long.class))
			.andExpect(jsonPath("$.firstName", is(testEmployees.get(0).getFirstName())))
			.andExpect(jsonPath("$.lastName", is(testEmployees.get(0).getLastName())))
			.andExpect(jsonPath("$.role", is(testEmployees.get(0).getRole())))
			.andExpect(jsonPath("$.salary", is(testEmployees.get(0).getSalary())))
			.andExpect(jsonPath("$.salaryCurrency", is(testEmployees.get(0).getSalaryCurrency())))
			.andExpect(jsonPath("$.remainingVacationDays", is(testEmployees.get(0).getRemainingVacationDays())))
			.andExpect(jsonPath("$._links.self.href", is("http://localhost/api/employees/1")))
			.andExpect(jsonPath("$._links.employees.href", is("http://localhost/api/employees")));
	}
}
