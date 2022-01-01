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
import br.com.agenda.exceptions.BusinessException;
import br.com.agenda.exceptions.NotFoundException;
import br.com.agenda.repository.AnotacaoRepository;
import br.com.agenda.util.MessageUtil;

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
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.TITULO_EXISTENTE);
		}
		dto.setData(LocalDate.now());
		AnotacaoEntity salvo = anotacaoRepository.save(dto.toEntity());	
		return salvo.toDTO();
	}

	public AnotacaoDTO update(AnotacaoDTO dto) {
		
		Optional<AnotacaoEntity> findById = anotacaoRepository.findById(dto.getId());
		
		if(findById.isPresent()) {
			if (dto.getTitulo().equals(findById.get().getTitulo()) && 
				dto.getTexto().equals(findById.get().getTexto())) {
				throw new BusinessException(MessageUtil.FAIL_UPDATE + MessageUtil.TITULO_TEXTO_NAO_ALTERADOS);
			}
			
			findById.get().setTitulo(dto.getTitulo());
			findById.get().setTexto(dto.getTexto());
			findById.get().setData(LocalDate.now());
			AnotacaoEntity updated = anotacaoRepository.save(findById.get());
			return updated.toDTO();
		}
		
		throw new NotFoundException(MessageUtil.ANOTACAO_NAO_ENCONTRADA);
	}

	public AnotacaoDTO delete(Integer id) {
		
		Optional<AnotacaoEntity> find = anotacaoRepository.findById(id);
		
		if (find.isPresent()) {
			anotacaoRepository.delete(find.get());
			return find.get().toDTO();
		}
		
		throw new NotFoundException(MessageUtil.ANOTACAO_NAO_ENCONTRADA);
	}
	
	


	
	
}
