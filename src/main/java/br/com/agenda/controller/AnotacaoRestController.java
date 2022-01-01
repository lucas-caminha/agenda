package br.com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.dto.AnotacaoDTO;
import br.com.agenda.service.AnotacaoService;

@RestController
@RequestMapping(value = "/anotacoes")
public class AnotacaoRestController {

	@Autowired
	private AnotacaoService anotacaoService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	public ResponseEntity<List<AnotacaoDTO>> getAll() {	
		return ResponseEntity.ok(anotacaoService.getAll());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ResponseEntity<AnotacaoDTO> save(@RequestBody AnotacaoDTO dto) {	
		AnotacaoDTO saved = anotacaoService.save(dto);
		if (saved.getId() != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		}		
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualizar")
	public ResponseEntity<AnotacaoDTO> update(@RequestBody AnotacaoDTO dto) {
		AnotacaoDTO updated = anotacaoService.update(dto);
		if (updated != null) {
			return ResponseEntity.ok(updated);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deletar/{id}")
	public ResponseEntity<AnotacaoDTO> delete(@PathVariable(name = "id") Integer id) {			
		AnotacaoDTO deleted = anotacaoService.delete(id);
		if (deleted != null) {
			return ResponseEntity.ok(deleted);
		}	
		return ResponseEntity.noContent().build();
	}
}
