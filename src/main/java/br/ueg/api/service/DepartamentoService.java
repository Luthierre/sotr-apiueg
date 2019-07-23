package br.ueg.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.ueg.api.model.Departamento;
import br.ueg.api.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;
	
	
	
	public Departamento atualizar(Long codigo, Departamento deparmento) {
		Departamento departamentoSalvo = repository.findOne(codigo);
		if(departamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(deparmento, departamentoSalvo, "codigo");		
		return repository.save(departamentoSalvo);
	}

}
