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
import br.ueg.api.model.Permissao;
import br.ueg.api.model.Usuario;
import br.ueg.api.repository.PermissaoRepository;
import br.ueg.api.repository.UsuarioRepository;
import br.ueg.api.repository.filter.UsuarioFilter;
import br.ueg.api.service.UsuarioService;
import br.ueg.api.service.exception.UsuarioEmailExistenteException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PermissaoRepository repositoryPermissao;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') or hasAuthority('USUARIO')  and #oauth2.hasScope('read')")
	public Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable) {
		return repository.filtrar(filtro, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')  and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuario = repository.findOne(codigo);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/permissoes")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public List<Permissao> listarPermissoes() {		
		return repositoryPermissao.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = service.salvar(usuario);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@ExceptionHandler({UsuarioEmailExistenteException.class})
	public ResponseEntity<Object> handleUsuarioEmailExistenteException(UsuarioEmailExistenteException ex) {
		String mensagemUsuario = messageSource.getMessage("usuario.email-existente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = service.atualizar(codigo, usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {		
		repository.delete(codigo);		
	}

}
 