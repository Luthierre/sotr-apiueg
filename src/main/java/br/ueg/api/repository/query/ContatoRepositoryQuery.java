package br.ueg.api.repository.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ueg.api.model.Contato;
import br.ueg.api.repository.filter.ContatoFilter;
import br.ueg.api.repository.projection.ResumoContato;
import br.ueg.api.repository.projection.ResumoContatoTelefones;

public interface ContatoRepositoryQuery {
	
	public Page<Contato> filtrar(ContatoFilter filtro, Pageable pageable);
	
	public Page<ResumoContato> resumir(ContatoFilter filtro, Pageable pageable);	
	public List<ResumoContatoTelefones> telefonesPorContato(Long id);
	/*public Page<ResumoContatoTelefones> resumirContatosTelefones(ContatoFilter filtro, Pageable pageable);*/
}
