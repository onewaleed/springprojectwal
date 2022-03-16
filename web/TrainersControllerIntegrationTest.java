package com.example.trainers.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.trainers.domain.Trainers;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:trainers-schema.sql", "classpath:trainers-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class TrainersControllerIntegrationTest {
	
	
	@Autowired
	private MockMvc mvc; 
	@Autowired
	private ObjectMapper mapper; 
	
	
	@Test
	void testCreate() throws Exception {
		Trainers testTrainers = new Trainers(null, "adidas", "yeezy 350", "bred");
		String testTrainersAsJSON = this.mapper.writeValueAsString(testTrainers);
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(testTrainersAsJSON);
		
		Trainers testCreatedTrainers = new Trainers (3, "adidas", "yeezy 350", "bred");
		String testCreatedTrainersAsJSON = this.mapper.writeValueAsString(testCreatedTrainers);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedTrainersAsJSON);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder request = get("/getAll");
		List<Trainers> testTrainers = List.of(new Trainers (1, "adidas", "yeezy 350", "bred"), new Trainers (2, "nike", "vapourmax", "black"));
		String json = this.mapper.writeValueAsString(testTrainers);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	@Test
	void getTest() throws Exception {
		RequestBuilder request = get("/get/1");
		String TrainersAsJSON = this.mapper.writeValueAsString(new Trainers(1, "adidas", "yeezy 350", "bred"));
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(TrainersAsJSON);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void testUpdate() throws Exception {
		Trainers testTrainers = new Trainers(null, "adidas", "yeezy 350", "bred");
		String testTrainersAsJSON = this.mapper.writeValueAsString(testTrainers);
		RequestBuilder request = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(testTrainersAsJSON);
		
		Trainers testCreatedTrainers = new Trainers(1, "adidas", "yeezy 350", "bred");
		String testCreatedTrainersAsJSON = this.mapper.writeValueAsString(testCreatedTrainers);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedTrainersAsJSON);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void testRemove() throws Exception {
	
		this.mvc.perform(delete("/delete/1")).andExpect(status().isNoContent());
	}

}
