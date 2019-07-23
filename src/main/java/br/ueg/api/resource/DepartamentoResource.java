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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.api.event.RecursoCriadoEvent;
import br.ueg.api.model.Departamento;
import br.ueg.api.repository.DepartamentoRepository;
import br.ueg.api.repository.filter.DepartamentoFilter;
import br.ueg.api.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoResource {
	
	@Autowired
	private DepartamentoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_DEPARTAMENTO') or hasAuthority('ROLE_CADASTRAR_CONTATO')  and #oauth2.hasScope('read')")
	public Page<Departamento> pesquisar(DepartamentoFilter filtro, Pageable pageable) {
		return repository.filtrar(filtro, pageable);
	}
	@GetMapping("/procurar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_DEPARTAMENTO') and #oauth2.hasScope('read')")
	public List<Departamento> procurar(@RequestParam(required = false, defaultValue = "%") String descricao, String campus) {
		return repository.findByDescricaoContainingAndCampus(descricao, campus);
	}
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_DEPARTAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Departamento> criar(@Valid @RequestBody Departamento departamento, HttpServletResponse response) {
		Departamento departamentoSalvo = repository.save(departamento);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, departamentoSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoSalvo);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_DEPARTAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Departamento> buscarPeloCodigo(@PathVariable Long codigo) {
		Departamento departamento = repository.findOne(codigo);
		 return departamento != null ? ResponseEntity.ok(departamento) : ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_DEPARTAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Departamento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Departamento deparmento) {
		Departamento departamentoSalvo = service.atualizar(codigo, deparmento);
		return ResponseEntity.ok(departamentoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_DEPARTAMENTO') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {		
		repository.delete(codigo);		
	}

}
 