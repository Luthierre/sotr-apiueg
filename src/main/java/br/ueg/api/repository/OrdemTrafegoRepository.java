package br.ueg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.OrdemTrafego;
import br.ueg.api.repository.query.OrdemTrafegoRepositoryQuery;


public interface OrdemTrafegoRepository extends JpaRepository<OrdemTrafego, Long>, OrdemTrafegoRepositoryQuery{

	public OrdemTrafego findByCodigo(Long id);
}
