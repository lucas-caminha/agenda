package br.com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<AnotacaoDTO> getAll() {
		return anotacaoService.getAll();
	}
	
}
