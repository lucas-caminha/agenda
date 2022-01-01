package br.com.agenda.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import br.com.agenda.dto.AnotacaoDTO;

/**
 * Renomear o arquivo na pasta src/main/resources/ para data.sql e 
 * alterar o application.proprerties para configuração do banco H2.
 * @author lucas
 */
@SpringBootTest
@AutoConfigureMockMvc
class AnotacaoRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;	
	@Autowired
	private Gson gson;
		
	@Test
	@Rollback(value = true)
	void deveRetornar201createdAoSalvarAnotacao() throws JsonProcessingException, Exception {		
		AnotacaoDTO dto = new AnotacaoDTO("Rest", "Testando Rest 1");
		String json = gson.toJson(dto);
		
		mockMvc.perform(post("/anotacoes/salvar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());			
	} 

	@Test
	@Rollback(value = true)
	void deveRetornar422unprocessableEntityAoTentarSalvarAnotacaoExistente() throws JsonProcessingException, Exception {		
		AnotacaoDTO dto = new AnotacaoDTO("Teste2", "Testando 2");	
		String json = gson.toJson(dto);
	
		mockMvc.perform(post("/anotacoes/salvar")
				.contentType(MediaType.APPLICATION_JSON)
				.content((json)))
				.andExpect(status().isUnprocessableEntity());			
	} 
	
	@Test
	@Rollback(value = true)
	void deveRetornar200sucessAoAtualizarAnotacao() throws JsonProcessingException, Exception {		
		AnotacaoDTO dto = new AnotacaoDTO("Teste", "Textando API");
		dto.setId(1);
		String json = gson.toJson(dto);
		
		mockMvc.perform(put("/anotacoes/atualizar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());			
	} 
	
	@Test
	@Rollback(value = true)
	void deveRetornar422unprocessableEntityAoAtualizarAnotacaoSemAlteracao() throws JsonProcessingException, Exception {		
		AnotacaoDTO dto = new AnotacaoDTO("Teste2", "Testando 2");
		dto.setId(2);
		String json = gson.toJson(dto);
		
		mockMvc.perform(put("/anotacoes/atualizar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());			
	} 
	
	@Test
	@Rollback(value = true)
	void deveRetornar404notFoundAoTentarAtualizarAnotacaoNaoExistente() throws JsonProcessingException, Exception {		
		AnotacaoDTO dto = new AnotacaoDTO("Teste25", "Testando 25");
		dto.setId(25);
		String json = gson.toJson(dto);
		
		mockMvc.perform(put("/anotacoes/atualizar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());			
	} 
	
	@Test
	void deveRetornar404notFoundAoTentarDeletarAnotacaoNaoExistente() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/anotacoes/deletar/6")
				.contentType("application/json"))
				.andExpect(status().isNotFound());			
	}
		
	@Test
	@Rollback(value = true)
	void deveRetornar200sucessAoTentarDeletarAnotacaoExistente() throws JsonProcessingException, Exception {
		mockMvc.perform(delete("/anotacoes/deletar/3")
				.contentType("application/json"))
				.andExpect(status().isOk());			
	}

}
