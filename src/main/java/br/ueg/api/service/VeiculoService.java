package br.ueg.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ueg.api.model.Campus;
import br.ueg.api.model.Veiculo;
import br.ueg.api.repository.CampusRepository;
import br.ueg.api.repository.VeiculoRepository;
import br.ueg.api.repository.filter.VeiculoFilter;
import br.ueg.api.service.exception.CondutorCarteiraVencidaException;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;
	
	@Autowired
	private CampusRepository campusRepository;
	
	public Veiculo atualizar(Long codigo, Veiculo veiculo) {
		Veiculo veiculoSalvo = repository.findOne(codigo);
		if(veiculoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(veiculo, veiculoSalvo, "codigo");		
		return repository.save(veiculoSalvo);
	}
	
	public Veiculo salvar(Veiculo veiculo) {
		List<Veiculo> veiculos = repository.findByPlacaContaining(veiculo.getPlaca());
		boolean existe = false;
		if(!veiculos.isEmpty()) {
			for (Veiculo v : veiculos) {
				if(v.getPlaca().equals(veiculo.getPlaca())) {
					existe = true;
					System.out.println(existe);
					break;
					
				}
			}
			
			
		
		}
		if(existe) {
			throw new CondutorCarteiraVencidaException();
		}
		return repository.save(veiculo);
	}

	public Page<Veiculo> filtrar(VeiculoFilter filtro, Pageable pageable) {
		Optional<Campus> optional = campusRepository.findByNomeContaining(filtro.getCampus());
		if(optional.isPresent()) {
			return repository.filtrar(filtro, pageable, optional.get());
		}
		return null;
	}
	
	

}
