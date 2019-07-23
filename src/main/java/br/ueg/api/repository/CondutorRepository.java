package br.ueg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Condutor;
import br.ueg.api.repository.query.CondutorRepositoryQuery;

public interface CondutorRepository extends JpaRepository<Condutor, Long>, CondutorRepositoryQuery{

	List<Condutor> findByCampusOrCpfOrNumeroCnh(String nome, String cpf, String numeroCnh);
	
}
