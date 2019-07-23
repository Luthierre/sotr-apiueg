package br.ueg.api.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ueg.api.model.Departamento;
import br.ueg.api.repository.filter.DepartamentoFilter;

public interface DepartamentoRepositoryQuery {
	
	public Page<Departamento> filtrar(DepartamentoFilter filtro, Pageable pageable);
	
}
