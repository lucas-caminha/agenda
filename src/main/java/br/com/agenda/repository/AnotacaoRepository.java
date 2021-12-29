package br.com.agenda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agenda.entity.AnotacaoEntity;

@Repository
public interface AnotacaoRepository extends JpaRepository<AnotacaoEntity, Integer>{

	Optional<AnotacaoEntity> findById(Integer id);
	
	List<AnotacaoEntity> findAllByOrderByIdAsc();
	
	Optional<AnotacaoEntity> findByTitulo(String titulo);

	Optional<AnotacaoEntity> findByIdAndTituloAndTexto(Integer id, String titulo, String texto);

	
}
