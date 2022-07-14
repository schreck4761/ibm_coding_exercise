package com.schreck.payrollservice;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

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

	@Test
	public void givenEmployees_whenGetAllEmployees_theReturnHalJson() throws Exception {
		given(employeeService.getAllEmployees()).willReturn(
			Arrays.asList(
				new Employee(),
				new Employee(),
				new Employee()
			)
		);

		this.mockMvc.perform(get("/api/employees").accept(MediaTypes.HAL_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andExpect(jsonPath("$._embedded.employeeList.length()", is(3)));
	}

}
