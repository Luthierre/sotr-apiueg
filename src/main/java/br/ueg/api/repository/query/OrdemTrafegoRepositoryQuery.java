package br.ueg.api.repository.query;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ueg.api.dto.OrdemTrafegoEstatisticaCampusDia;
import br.ueg.api.dto.OrdemTrafegoEstatisticaVeiculo;
import br.ueg.api.model.Campus;
import br.ueg.api.model.OrdemTrafego;
import br.ueg.api.repository.filter.OrdemTrafegoFilter;
import br.ueg.api.repository.projection.ResumoOrdemTrafego;

public interface OrdemTrafegoRepositoryQuery {
	
	public Page<OrdemTrafego> filtrar(OrdemTrafegoFilter filtro, Pageable pageable);
	
	public Page<ResumoOrdemTrafego> resumir(OrdemTrafegoFilter filtro, Pageable pageable);
	
	public OrdemTrafego porOrdem(Long id);
	
	public List<OrdemTrafegoEstatisticaVeiculo> ordemTrafegoVeiculoPorMes(Campus campus, LocalDate mesReferencia);
	public List<OrdemTrafegoEstatisticaCampusDia> ordemTrafegoCampusPorDia(Campus campus, LocalDate mesReferencia);
	
	
	
}
