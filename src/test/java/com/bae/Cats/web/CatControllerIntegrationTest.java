package com.bae.Cats.web;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.Cats.domain.Cat;
import com.fasterxml.jackson.databind.ObjectMapper;

// boots the entire context - random port to avoid collisions
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMvc object
@Sql(scripts = { "classpath:cat-schema.sql",
"classpath:cat-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class CatControllerIntegrationTest {

	@Autowired // pulls the MockMvc object from the context
	private MockMvc mvc; // class that performs the request
	
	@Autowired 
	private ObjectMapper mapper; // java to JSON converter that Spring uses
	
	@Test
	void testCreate() throws Exception {
		Cat testCat = new Cat("larry", 4, "brown", true);
		String testCatAsJSON = this.mapper.writeValueAsString(testCat);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testCatAsJSON);
		
		Cat testCreatedCat = new Cat("larry", 4, "brown", true, 2);
		String testCreatedCatAsJSON = this.mapper.writeValueAsString(testCreatedCat);
		ResultMatcher checkStatus = status().isCreated(); // is status 201 created
		ResultMatcher checkBody = content().json(testCreatedCatAsJSON); // does the body match my testCreateCatAsJSON
		
		// sends request - checks the status- checks the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testCreate2() throws Exception {
		Cat testCat = new Cat("larry", 4, "brown", true);
		String testCatAsJSON = this.mapper.writeValueAsString(testCat);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testCatAsJSON);
		
		Cat testCreatedCat = new Cat("larry", 4, "brown", true, 2);
		String testCreatedCatAsJSON = this.mapper.writeValueAsString(testCreatedCat);
		ResultMatcher checkStatus = status().isCreated(); // is status 201 created
		ResultMatcher checkBody = content().json(testCreatedCatAsJSON); // does the body match my testCreateCatAsJSON
		
		// sends request - checks the status- checks the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	
	@Test
	void getAllTest() throws Exception {
		List<Cat> testCats = List.of(new Cat("barry", 7, "pink", false, 1));
		String json = this.mapper.writeValueAsString(testCats);
		
		
		RequestBuilder req = get("/getAll");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getCatTest() throws Exception {
		Cat testCat = new Cat("barry", 7, "pink", false, 1);
		String json = this.mapper.writeValueAsString(testCat);
		
		RequestBuilder req = get("/get/1");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		}
	
	@Test
	void getCatByNameTest() throws Exception {
		List<Cat> testCat = List.of(new Cat("barry", 7, "pink", false, 1));
		String json = this.mapper.writeValueAsString(testCat);
		
		RequestBuilder req = get("/getByName/barry");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		}
	
	@Test
	void replaceTest() throws Exception {
		Cat testCat = new Cat("larry", 4, "brown", true);
		String testCatAsJSON = this.mapper.writeValueAsString(testCat);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testCatAsJSON);
		
		Cat testCreatedCat = new Cat("larry", 4, "brown", true, 1);
		String testCreatedCatAsJSON = this.mapper.writeValueAsString(testCreatedCat);
		ResultMatcher checkStatus = status().isAccepted(); // is status 201 created
		ResultMatcher checkBody = content().json(testCreatedCatAsJSON); // does the body match my testCreateCatAsJSON
		
		// sends request - checks the status- checks the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	
	@Test
	void deleteTest() throws Exception {
		RequestBuilder req = delete("/remove/1");
		
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
}
