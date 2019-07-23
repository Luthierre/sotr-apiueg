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
import br.ueg.api.model.Veiculo;
import br.ueg.api.repository.VeiculoRepository;
import br.ueg.api.repository.filter.VeiculoFilter;
import br.ueg.api.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {
	
	@Autowired
	private VeiculoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private VeiculoService service;
	
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_VEICULOS_CONDUTOR') and #oauth2.hasScope('read')")
	public Page<Veiculo> pesquisar(VeiculoFilter filtro, Pageable pageable) {
		return service.filtrar(filtro, pageable);
	}
	@RequestMapping("/pesquisar")
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_VEICULOS_CONDUTOR') and #oauth2.hasScope('read')")
	public List<Veiculo> pesquisarModeloOuPlaca(VeiculoFilter filtro) {
		return repository.procurarPorPlacaOuModelo(filtro);
	}
		
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_DEPARTAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Veiculo> criar(@Valid @RequestBody Veiculo veiculo, HttpServletResponse response) {
		Veiculo veiculoSalvo = service.salvar(veiculo);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, veiculoSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_DEPARTAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Veiculo> buscarPeloCodigo(@PathVariable Long codigo) {
		Veiculo veiculo = repository.findOne(codigo);
		 return veiculo != null ? ResponseEntity.ok(veiculo) : ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_DEPARTAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Veiculo> atualizar(@PathVariable Long codigo, @Valid @RequestBody Veiculo veiculo) {
		Veiculo veiculoSalvo = service.atualizar(codigo, veiculo);
		return ResponseEntity.ok(veiculoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_DEPARTAMENTO') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {		
		repository.delete(codigo);		
	}

}
 