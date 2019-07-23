package br.ueg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Contato;
import br.ueg.api.repository.query.ContatoRepositoryQuery;

public interface ContatoRepository extends JpaRepository<Contato, Long>, ContatoRepositoryQuery{

	
}
