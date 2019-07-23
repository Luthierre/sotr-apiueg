package br.ueg.api.resource;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.ueg.api.dto.OrdemTrafegoEstatisticaCampusDia;
import br.ueg.api.dto.OrdemTrafegoEstatisticaVeiculo;
import br.ueg.api.event.RecursoCriadoEvent;
import br.ueg.api.model.OrdemTrafego;
import br.ueg.api.repository.OrdemTrafegoRepository;
import br.ueg.api.repository.filter.OrdemTrafegoFilter;
import br.ueg.api.repository.projection.ResumoOrdemTrafego;
import br.ueg.api.service.OrdemTrafegoService;

@RestController
@RequestMapping("/ordem-trafego")
public class OrdemTrafegoResource {
	
	@Autowired
	private OrdemTrafegoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private OrdemTrafegoService service;
	
	@GetMapping("/relatorios/porOrdem")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ORDEM') and #oauth2.hasScope('write')")
	public ResponseEntity<byte[]> relatorioOrdemImpressao(@RequestParam Long codigo, @RequestParam String usuario) throws Exception {
		byte[] relatorio = service.relatorioOrdemTrafegoImpressao(codigo, usuario);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	@GetMapping("/estatisticas/por-veiculo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM') and #oauth2.hasScope('read')")
	public List<OrdemTrafegoEstatisticaVeiculo> porVeiculo(@RequestParam String campus) {
		return service.ordemTrafegoVeiculoPorMes(campus, LocalDate.now());
	}
	
	@GetMapping("/estatisticas/por-campus-dia")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM') and #oauth2.hasScope('read')")
	public List<OrdemTrafegoEstatisticaCampusDia> porCampusDia(@RequestParam String campus) {
		return service.ordemTrafegoCampusPorDia(campus, LocalDate.now());
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM') and #oauth2.hasScope('read')")
	public Page<OrdemTrafego> pesquisar(OrdemTrafegoFilter filtro, Pageable pageable) {
		return repository.filtrar(filtro, pageable);
	}
	
	@GetMapping(params="resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM') and #oauth2.hasScope('read')")
	public Page<ResumoOrdemTrafego> resumir(OrdemTrafegoFilter filtro, Pageable pageable) {
		return repository.resumir(filtro, pageable);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<OrdemTrafego> atualizarPropriedadeAtivo(@PathVariable Long codigo, @Valid @RequestBody Boolean ativo) {
		OrdemTrafego ordem = service.atualizarPropriedadeAtivo(codigo, ativo);
		return ResponseEntity.ok(ordem);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ORDEM') and #oauth2.hasScope('write')")
	public ResponseEntity<OrdemTrafego> criar(@Valid @RequestBody OrdemTrafego ordem, HttpServletResponse response) {
		OrdemTrafego ordemSalvo = service.salvar(ordem);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, ordemSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(ordemSalvo);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM') and #oauth2.hasScope('read')")
	public ResponseEntity<OrdemTrafego> buscarPeloCodigo(@PathVariable Long codigo) {
		OrdemTrafego ordem = repository.findByCodigo(codigo);
		 return ordem != null ? ResponseEntity.ok(ordem) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ORDEM') and #oauth2.hasScope('write')")
	public ResponseEntity<OrdemTrafego> atualizar(@PathVariable Long codigo, @Valid @RequestBody OrdemTrafego ordem) {
		OrdemTrafego ordemSalva = service.atualizar(codigo, ordem);
		return ResponseEntity.ok(ordemSalva);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_ORDEM') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {		
		repository.delete(codigo);		
	}

}
