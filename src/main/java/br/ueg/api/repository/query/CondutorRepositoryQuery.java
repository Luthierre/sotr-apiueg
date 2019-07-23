package br.ueg.api.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ueg.api.model.Campus;
import br.ueg.api.model.Condutor;
import br.ueg.api.repository.filter.CondutorFilter;

public interface CondutorRepositoryQuery {
	
	public Page<Condutor> filtrar(CondutorFilter filtro, Pageable pageable, Campus campus);
	
}
