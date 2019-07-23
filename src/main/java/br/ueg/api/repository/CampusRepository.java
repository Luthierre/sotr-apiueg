package br.ueg.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long>{
	
	public Page<Campus> findByNomeContaining(String nome, Pageable pageable);

	public Optional<Campus> findByNomeContaining(String campus);
	
}
