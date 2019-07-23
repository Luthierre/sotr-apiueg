package br.ueg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
	
	
}
