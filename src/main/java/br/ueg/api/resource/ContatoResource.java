package br.ueg.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.api.event.RecursoCriadoEvent;
import br.ueg.api.model.Contato;
import br.ueg.api.repository.ContatoRepository;
import br.ueg.api.repository.filter.ContatoFilter;
import br.ueg.api.repository.projection.ResumoContato;
import br.ueg.api.repository.projection.ResumoContatoTelefones;
import br.ueg.api.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {
	
	@Autowired
	private ContatoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ContatoService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
	public Page<Contato> pesquisar(ContatoFilter filtro, Pageable pageable) {
		return repository.filtrar(filtro, pageable);
	}
	
	@GetMapping(params="resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
	public Page<ResumoContato> resumir(ContatoFilter filtro, Pageable pageable) {
		return repository.resumir(filtro, pageable);
	}
	
	
	
	@GetMapping("/telefones/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
	public List<ResumoContatoTelefones> buscarTelefones(@PathVariable Long codigo) {
		return repository.telefonesPorContato(codigo);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
	public ResponseEntity<Contato> criar(@Valid @RequestBody Contato contato, HttpServletResponse response) {
		Contato contatoSalvo = service.salvar(contato);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, contatoSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoSalvo);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
	public ResponseEntity<Contato> buscarPeloCodigo(@PathVariable Long codigo) {
		Contato contato = repository.findOne(codigo);
		 return contato != null ? ResponseEntity.ok(contato) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
	public ResponseEntity<Contato> atualizar(@PathVariable Long codigo, @Valid @RequestBody Contato contato) {
		Contato contatoSalvo = service.atualizar(codigo, contato);
		return ResponseEntity.ok(contatoSalvo);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CONTATO') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {		
		repository.delete(codigo);		
	}

}
