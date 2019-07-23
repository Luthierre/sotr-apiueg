package br.ueg.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ueg.api.model.Campus;
import br.ueg.api.model.Condutor;
import br.ueg.api.repository.CampusRepository;
import br.ueg.api.repository.CondutorRepository;
import br.ueg.api.repository.filter.CondutorFilter;
import br.ueg.api.service.exception.CondutorCarteiraVencidaException;
import br.ueg.api.service.exception.CondutorCpfOuCnhExistenteException;
import br.ueg.api.service.exception.CondutorMenorIdadeException;

@Service
public class CondutorService {

	@Autowired
	private CondutorRepository repository;
	
	@Autowired
	private CampusRepository campusRepository;
	
	
	
	public Condutor atualizar(Long codigo, Condutor condutor) {
		Condutor condutorSalvo = repository.findOne(codigo);
		if(condutorSalvo == null) {
			System.out.println("EmptyResult");
			throw new EmptyResultDataAccessException(1);
		}
		List<Condutor> lista = repository.findByCampusOrCpfOrNumeroCnh(condutor.getCampus().getNome(), condutor.getCpf(), condutor.getNumeroCnh());
		if(procuraCpfOuCnhExistente(lista, condutor)) {
			throw new CondutorCpfOuCnhExistenteException();
		}
		
		BeanUtils.copyProperties(condutor, condutorSalvo, "codigo");		
		return repository.save(condutorSalvo);
	}
	

	public Condutor salvar(Condutor condutor) {
		LocalDate data = LocalDate.now();	
		LocalDate dataMenor18 = LocalDate.of(data.getYear() - 18, data.getMonth(), data.getDayOfMonth());
		List<Condutor> lista = repository.findByCampusOrCpfOrNumeroCnh(condutor.getCampus().getNome(), condutor.getCpf(), condutor.getNumeroCnh());
		if(condutor.getDataCadastro() == null) {
			condutor.setDataCadastro(LocalDate.now());
		}
		if(data.isAfter(condutor.getDataVencimentoCnh())) {
			throw new CondutorCarteiraVencidaException();
		}
		if(dataMenor18.isBefore(condutor.getDataNascimento())) {
			throw new CondutorMenorIdadeException();
		}
		if(procuraCpfOuCnhExistente(lista, condutor)) {
			throw new CondutorCpfOuCnhExistenteException();
		}
		return repository.save(condutor);
	}
	private boolean procuraCpfOuCnhExistente(List<Condutor> lista, Condutor condutor) {
		if (!lista.isEmpty() && lista.size() > 1) {			
			for (Condutor condutorBancoDados : lista) {				
				if(condutorBancoDados.getCampus().equals(condutor.getCampus()) && !condutorBancoDados.getCodigo().equals(condutor.getCodigo())) {					
					if(condutorBancoDados.getCpf().equals(condutor.getCpf()) || condutorBancoDados.getNumeroCnh().equals(condutor.getNumeroCnh())) {						
						return true;
					}
				}
			}			
		}
		return false;
	}


	public Page<Condutor> filtrar(CondutorFilter filtro, Pageable pageable) {
		Optional<Campus> optional = campusRepository.findByNomeContaining(filtro.getCampus());
		if(optional.isPresent()) {
			return repository.filtrar(filtro, pageable, optional.get());
		}
		return new PageImpl<>( new ArrayList<Condutor>(), pageable, new Long(null));
	}
}
