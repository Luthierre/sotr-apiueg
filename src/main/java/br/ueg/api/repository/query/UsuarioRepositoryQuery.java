package br.ueg.api.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ueg.api.model.Usuario;
import br.ueg.api.repository.filter.UsuarioFilter;

public interface UsuarioRepositoryQuery {
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	
}
