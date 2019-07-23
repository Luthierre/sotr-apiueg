package br.ueg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Departamento;
import br.ueg.api.repository.query.DepartamentoRepositoryQuery;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>, DepartamentoRepositoryQuery{

	List<Departamento> findByDescricaoContainingAndCampus(String descricao, String campus);

	
}
