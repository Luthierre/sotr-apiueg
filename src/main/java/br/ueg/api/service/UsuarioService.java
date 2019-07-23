package br.ueg.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ueg.api.model.Usuario;
import br.ueg.api.repository.UsuarioRepository;
import br.ueg.api.service.exception.UsuarioEmailExistenteException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;	
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalvo = repository.findOne(codigo);
		if(!usuarioSalvo.getSenha().equals(usuario.getSenha())){
			usuario.setSenha(encoder.encode(usuario.getSenha()));
		}
		usuarioSalvo.getPermissoes().clear();
		usuarioSalvo.getPermissoes().addAll(usuario.getPermissoes());
		
		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo", "permissoes");	
		
		return repository.save(usuarioSalvo);
	}
	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> optional = repository.findByEmail(usuario.getEmail());
		if(optional.isPresent()) {
			if(optional.get().getEmail().equals(usuario.getEmail())) {
				throw new UsuarioEmailExistenteException();
			}
			
		}
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return repository.save(usuario);
		
		
	}
	
	
}
