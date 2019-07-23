package br.ueg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Telefones;

public interface TelefoneRepository extends JpaRepository<Telefones, Long>{

}
