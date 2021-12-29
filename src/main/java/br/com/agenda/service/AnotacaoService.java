package br.com.agenda.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	public AnotacaoDTO save(AnotacaoDTO dto) {
		Optional<AnotacaoEntity> find = anotacaoRepository.findByTitulo(dto.getTitulo());
		if (find.isPresent()) {
			throw new RuntimeException();
		}
		dto.setData(LocalDate.now());
		AnotacaoEntity salvo = anotacaoRepository.save(dto.toEntity());	
		return salvo.toDTO();
	}

	public AnotacaoDTO update(AnotacaoDTO dto) {
		Optional<AnotacaoEntity> find = 
				anotacaoRepository.findByIdAndTituloAndTexto(dto.getId(), dto.getTitulo(), dto.getTexto());
		
		if (find.isPresent()) {
			throw new RuntimeException();
		}
		dto.setData(LocalDate.now());
		AnotacaoEntity updated = anotacaoRepository.save(dto.toEntity());
		return updated.toDTO();
	}


	
	
}
