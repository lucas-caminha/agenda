package br.com.agenda.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.dto.AnotacaoDTO;
import br.com.agenda.entity.AnotacaoEntity;
import br.com.agenda.repository.AnotacaoRepository;

@Service
public class AnotacaoService {
	
	@Autowired
	private AnotacaoRepository anotacaoRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<AnotacaoDTO> getAll(){
		List<AnotacaoEntity> anotacoes = anotacaoRepository.findAll();
		return Arrays.asList(modelMapper.map(anotacoes, AnotacaoDTO[].class));		
	}
	
}
