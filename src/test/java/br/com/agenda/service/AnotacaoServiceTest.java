package br.com.agenda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import br.com.agenda.dto.AnotacaoDTO;
import br.com.agenda.exceptions.BusinessException;
import br.com.agenda.exceptions.NotFoundException;

/**
 * Renomear o arquivo na pasta src/main/resources/ para data.sql e 
 * alterar o application.proprerties para configuração do banco H2.
 * @author lucas
 */
@SpringBootTest
class AnotacaoServiceTest {

	@Autowired
	private AnotacaoService anotacaoService;
		
	@Test
	void deveRetornarBusinessExceptionAoSalvarAnotacaoComTituloExistente() {	
		AnotacaoDTO anotacaoDTO = new AnotacaoDTO("Teste", "", null);
		assertThrows(BusinessException.class, () -> anotacaoService.save(anotacaoDTO));	
	}
	
	@Test
	void deveRetornarBusinessExceptionAoAtualizarAnotacaoJaExistenteSemAlteracao() {
		AnotacaoDTO anotacaoDTO = new AnotacaoDTO(2, "Teste2", "Testando 2", LocalDate.of(2022, 1, 1));
		assertThrows(BusinessException.class, () -> anotacaoService.update(anotacaoDTO));	
	}
	
	@Test
	void deveRetornarNotFoundExceptionAoAtualizarAnotacaoQueNaoExiste() {
		AnotacaoDTO anotacaoDTO = new AnotacaoDTO(5, "Teste5", "Testando 6", LocalDate.of(2022, 1, 1));
		assertThrows(NotFoundException.class, () -> anotacaoService.update(anotacaoDTO));	
	}
	
	@Test
	void deveRetornarNotFoundExceptionAoDeletarAnotacaoNaoExistente () {
		assertThrows(NotFoundException.class, () -> anotacaoService.delete(10));	
	}
	
	@Test
	@Rollback(value = true)
	void deveRetornarAnotacaoDTOAoSalvarAnotacaoNaoExistente() {	
		AnotacaoDTO anotacaoDTO = new AnotacaoDTO("Teste6", "Testando Salvamento",  LocalDate.of(2022, 1, 1));
		assertEquals(AnotacaoDTO.class, anotacaoService.save(anotacaoDTO).getClass());	
	}
	
	@Test
	@Rollback(value = true)
	void deveRetornarAnotacaoDTOAoAtualizarAnotacaoExistente() {	
		AnotacaoDTO anotacaoDTO = new AnotacaoDTO("Teste1 updated", "Testando atualizacao",  LocalDate.of(2022, 1, 1));
		anotacaoDTO.setId(3);
		assertEquals(AnotacaoDTO.class, anotacaoService.update(anotacaoDTO).getClass());	
	}
	
	@Test
	@Rollback(value = true)
	void deveRetornarAnotacaoDTOAoDeletarAnotacaoExistente() {	
		//AnotacaoDTO anotacaoDTO = new AnotacaoDTO("Teste2", "Testando 2",  LocalDate.of(2022, 1, 1));
		//anotacaoDTO.setId(2);
		assertEquals(AnotacaoDTO.class, anotacaoService.delete(3).getClass());	
	}

}
