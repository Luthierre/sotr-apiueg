package br.ueg.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.api.event.RecursoCriadoEvent;
import br.ueg.api.model.Campus;
import br.ueg.api.repository.CampusRepository;
import br.ueg.api.service.CampusService;

@RestController
@RequestMapping("/campus")
public class CampusResource {
	
	@Autowired
	private CampusRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CampusService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CAMPUS') and #oauth2.hasScope('read')")
	public Page<Campus> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return repository.findByNomeContaining(nome, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CAMPUS') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Campus> criar(@Valid @RequestBody Campus campus, HttpServletResponse response) {
		Campus campusSalvo = repository.save(campus);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, campusSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(campusSalvo);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CAMPUS') and #oauth2.hasScope('read')")
	public ResponseEntity<Campus> buscarPeloCodigo(@PathVariable Long codigo) {
		Campus campus = repository.findOne(codigo);
		 return campus != null ? ResponseEntity.ok(campus) : ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CAMPUS') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Campus> atualizar(@PathVariable Long codigo, @Valid @RequestBody Campus campus) {
		Campus campusSalvo = service.atualizar(codigo, campus);
		return ResponseEntity.ok(campusSalvo);
	}	

}
 