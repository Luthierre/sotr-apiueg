package br.ueg.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.api.event.RecursoCriadoEvent;
import br.ueg.api.exceptionhandler.ApiExceptionHandler.Erro;
import br.ueg.api.model.Condutor;
import br.ueg.api.repository.CondutorRepository;
import br.ueg.api.repository.filter.CondutorFilter;
import br.ueg.api.service.CondutorService;
import br.ueg.api.service.exception.CondutorCarteiraVencidaException;
import br.ueg.api.service.exception.CondutorCpfOuCnhExistenteException;
import br.ueg.api.service.exception.CondutorMenorIdadeException;

@RestController
@RequestMapping("/motoristas")
public class CondutorResource {
	
	@Autowired
	private CondutorRepository repository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CondutorService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_VEICULOS_CONDUTOR') and #oauth2.hasScope('read')")
	public Page<Condutor> pesquisar(CondutorFilter filtro, Pageable pageable) {
		return service.filtrar(filtro, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_VEICULOS_CONDUTOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Condutor> criar(@Valid @RequestBody Condutor condutor, HttpServletResponse response) {
		Condutor condutorSalvo = service.salvar(condutor);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, condutorSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(condutorSalvo);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_VEICULOS_CONDUTOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Condutor> buscarPeloCodigo(@PathVariable Long codigo) {
		Condutor condutor = repository.findOne(codigo);
		 return condutor != null ? ResponseEntity.ok(condutor) : ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_VEICULOS_CONDUTOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Condutor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Condutor condutor) {
		Condutor condutorSalvo = service.atualizar(codigo, condutor);
		return ResponseEntity.ok(condutorSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_VEICULOS_CONDUTOR') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {		
		repository.delete(codigo);		
	}
	
	@ExceptionHandler({CondutorCarteiraVencidaException.class})
	public ResponseEntity<Object> handleCondutorCarteiraVencidaException(CondutorCarteiraVencidaException ex) {
		String mensagemUsuario = messageSource.getMessage("condutor.carteira-vencida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	@ExceptionHandler({CondutorMenorIdadeException.class})
	public ResponseEntity<Object> handleCondutorMenorIdadeException(CondutorMenorIdadeException ex) {
		String mensagemUsuario = messageSource.getMessage("condutor.menor-idade", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({CondutorCpfOuCnhExistenteException.class})
	public ResponseEntity<Object> handleCondutorCpfOuCnhExistenteException(CondutorCpfOuCnhExistenteException ex) {
		String mensagemUsuario = messageSource.getMessage("condutor.existente-campus", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
 