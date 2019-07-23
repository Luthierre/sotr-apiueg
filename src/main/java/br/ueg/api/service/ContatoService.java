package br.ueg.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.api.model.Contato;
import br.ueg.api.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repository;	
	
	public Contato atualizar(Long codigo, Contato contato) {
		Contato contatoSalvo = repository.findOne(codigo);
		
		contatoSalvo.getTelefones().clear();
		contatoSalvo.getTelefones().addAll(contato.getTelefones());
		contatoSalvo.getTelefones().forEach(t -> t.setContato(contatoSalvo));
		
		BeanUtils.copyProperties(contato, contatoSalvo, "codigo", "telefones");		
		return repository.save(contatoSalvo);
	}

	public Contato salvar(Contato contato) {
		if(contato.getTelefones() !=null ) {
			contato.getTelefones().forEach(c -> c.setContato(contato));
		}
		return repository.save(contato);
	}
	
	
}
