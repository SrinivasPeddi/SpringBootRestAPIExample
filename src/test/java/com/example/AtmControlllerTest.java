package com.example;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.example.domain.response.GetAtmDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmControlllerTest {
	
	@Autowired
    private WebApplicationContext context;

	 private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
	
	@MockBean
	RestTemplate restTemplate;
	
	@Test
	public void getAtmListTest() throws Exception 
	{
		 ObjectMapper mapper = new ObjectMapper();
		 
		 List<GetAtmDetails> mockRes = mapper.readValue(
			        new ClassPathResource("Success.json").getFile(), 
			        new TypeReference<List<GetAtmDetails>>(){});

		String jsonString = mapper.writeValueAsString(mockRes);
		jsonString=")]}',"+jsonString;
		Mockito.when(restTemplate.getForObject(Mockito.any(String.class), Mockito.eq(String.class)))
		.thenReturn(jsonString);

		mockMvc.perform( MockMvcRequestBuilders
	      .get("/getAtmList")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}

}
