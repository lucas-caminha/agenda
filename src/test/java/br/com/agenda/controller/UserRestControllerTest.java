package br.com.agenda.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import br.com.agenda.dto.UserDTO;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;	
	@Autowired
	private Gson gson;

	@Test
	@Rollback(value = true)
	void deveRetornar201createdAoRegistrarUsuario() throws JsonProcessingException, Exception {		
		UserDTO userDTO = new UserDTO("userRegister", "userRegister");
		String json = gson.toJson(userDTO);
		
		mockMvc.perform(post("/user/registrar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());			
	} 

	@Test
	@Rollback(value = true)
	void deveRetornar422unprocessableEntityAoTentarRegistrarUsuarioJaExistente() throws JsonProcessingException, Exception {		
		UserDTO userDTO = new UserDTO("admin", "admin");
		String json = gson.toJson(userDTO);
		
		mockMvc.perform(post("/user/registrar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());			
	} 
}
