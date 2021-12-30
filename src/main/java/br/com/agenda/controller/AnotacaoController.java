package br.com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.dto.AnotacaoDTO;
import br.com.agenda.service.AnotacaoService;

@RestController
@RequestMapping(value = "/anotacoes")
public class AnotacaoController {

	@Autowired
	private AnotacaoService anotacaoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	public List<AnotacaoDTO> getAll() {
		return anotacaoService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public AnotacaoDTO save(AnotacaoDTO dto) {	
		return anotacaoService.save(dto);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualizar")
	public AnotacaoDTO update(AnotacaoDTO dto) {
		return anotacaoService.update(dto);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deletar/{id}")
	public ResponseEntity<AnotacaoDTO> delete(@PathVariable(name = "id") Integer id) {	
		return ResponseEntity.ok(anotacaoService.delete(id));
	}
}
