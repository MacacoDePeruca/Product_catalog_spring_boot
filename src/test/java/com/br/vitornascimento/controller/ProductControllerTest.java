package com.br.vitornascimento.controller;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	URI uri;
	
	@Before
	public void setUp() throws Exception {
		uri = new URI("/api/v1/products");
	}

	@Test
	public void shouldReturnHttpOKWhenSearchProductById() throws Exception {
		
		long id = 1;
		
		
		mockMvc.perform(
				MockMvcRequestBuilders
				.get(uri)
				.pathInfo("/"+id)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers
						.status()
						.is(HttpStatus.OK.value())
						);
		
	}
	@Test
	public void shouldReturnHttpOKWhenSearchAllProducts() throws Exception {
			
		mockMvc.perform(
				MockMvcRequestBuilders
				.get(uri)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers
						.status()
						.isOk()
						);
		
	}
	@Test
	public void shouldReturnHttpOKWhenSaveAProduct() throws Exception {
		
		String JSONContent = "{\"name\": \"LARANJA PERA\",\r\n"
				+ "        \"description\": \"BOA PRA SUCO\",\r\n"
				+ "        \"price\": 1.99}";
		
		mockMvc.perform(
				MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONContent))
					.andExpect(MockMvcResultMatchers
							.status()
							.isOk()
							)
		
		;
		
	}
	@Test
	public void shouldReturnBadRequestWhenSaveAInvalidFormatProduct() throws Exception {
		
		String JSONInvalidContent = "{\r\n"
				+ "        \"name\": \"LARANJA PERA\",\r\n"
				+ "        \"description\": \"BOA PRA SUCO\"\r\n"
				+ "}";
		
		mockMvc.perform(
				MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONInvalidContent))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest()
				)
		
		;
		
	}
	
	@Test
	public void shouldReturnHttpOKWhenDeleteAProduct() throws Exception {
		long id = 2;	
		
		mockMvc.perform(
				MockMvcRequestBuilders
				.delete(uri+"/"+id)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
						).andExpect(
								MockMvcResultMatchers
								.status().isOk());
		
	}

}
