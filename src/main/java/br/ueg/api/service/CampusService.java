package br.ueg.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.ueg.api.model.Campus;
import br.ueg.api.repository.CampusRepository;

@Service
public class CampusService {

	@Autowired
	private CampusRepository repository;
	
	
	
	public Campus atualizar(Long codigo, Campus campus) {
		Campus campusSalvo = repository.findOne(codigo);
		if(campusSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(campus, campusSalvo, "codigo");		
		return repository.save(campusSalvo);
	}

}
