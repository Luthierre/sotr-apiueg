package br.ueg.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.api.model.Usuario;
import br.ueg.api.repository.query.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{
	
	public Optional<Usuario> findByEmail(String email);
}
