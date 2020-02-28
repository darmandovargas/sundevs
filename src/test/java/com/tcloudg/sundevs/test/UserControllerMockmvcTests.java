package com.tcloudg.sundevs.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tcloudg.sundevs.TCloudGSunDevsTestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TCloudGSunDevsTestApplication.class)
@AutoConfigureMockMvc
public class UserControllerMockmvcTests {
	@Autowired
    private MockMvc mockMvc;
	
	private String jsonBodyString = "{\"firstName\": \"Diego\",\"lastName\": \"Vargas\",\"address\": \"Cra x Calle y\",\"city\": \"Medellín\",\"state\": \"Antioquia\",\"zip\": \"0123456789\"}";
	
	// Simple user save test
	@Test
	public void createUserMockMVCTest() {
		
		try {
			this.mockMvc.perform(post("/api/v1/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonBodyString) 
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName").value("Diego")) 
			.andExpect(jsonPath("$.lastName").value("Vargas"))
			.andExpect(jsonPath("$.address").value("Cra x Calle y"))
			.andExpect(jsonPath("$.city").value("Medellín"))
			.andExpect(jsonPath("$.state").value("Antioquia"))
			.andExpect(jsonPath("$.zip").value("0123456789"))
			
			;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// Simple empty get all users endpoint
	@Test
    public void getAllUsersMockMVCTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/users")).andDo(print()).andExpect(status().isOk())
          .andExpect(content().string(containsString("[{\"id\":1,\"firstName\":\"Diego\",\"lastName\":\"Vargas\",\"address\":\"Cra x Calle y\",\"city\":\"MedellÃ­n\",\"state\":\"Antioquia\",\"zip\":\"0123456789\"}]")));
    }
}