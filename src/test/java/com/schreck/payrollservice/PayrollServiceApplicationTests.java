package com.schreck.payrollservice;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
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
		testEmployees.add(new Employee("Hello", "World", "Intern", 1337, "USD", 365));
		testEmployees.add(new Employee("Laura", "Bailey", "Marketer", 130000, "USD", 0));
		testEmployees.add(new Employee("Alexandra", "Li", "President", 163112, "NZD", 16));
		return testEmployees;
	}

	@Test
	public void givenEmployees_whenGetAllEmployees_thenReturnHalJson() throws Exception {
		List<Employee> testEmployees = this.getTestEmployees();
		given(employeeService.getAllEmployees()).willReturn(testEmployees);

		this.mockMvc.perform(get("/api/employees").accept(MediaTypes.HAL_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andExpect(jsonPath("$._embedded.employeeList.length()", is(testEmployees.size())));
	}

}
