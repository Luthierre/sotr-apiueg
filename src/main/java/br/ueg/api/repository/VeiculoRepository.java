package br.ueg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Veiculo;
import br.ueg.api.repository.query.VeiculoRepositoryQuery;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoRepositoryQuery{

	public List<Veiculo> findByPlacaContaining(String placa);
		
	
}
