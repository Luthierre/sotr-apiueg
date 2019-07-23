package br.ueg.api.repository.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ueg.api.model.Campus;
import br.ueg.api.model.Veiculo;
import br.ueg.api.repository.filter.VeiculoFilter;

public interface VeiculoRepositoryQuery {
	
	public Page<Veiculo> filtrar(VeiculoFilter filtro, Pageable pageable, Campus campus);
	public List<Veiculo> procurarPorPlacaOuModelo(VeiculoFilter filtro);
	
	
	
	
}
