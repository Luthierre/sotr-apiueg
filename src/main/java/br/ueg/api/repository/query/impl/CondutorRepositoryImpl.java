package br.ueg.api.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.ueg.api.model.Campus;
import br.ueg.api.model.Campus_;
import br.ueg.api.model.Condutor;
import br.ueg.api.model.Condutor_;
import br.ueg.api.repository.filter.CondutorFilter;
import br.ueg.api.repository.query.CondutorRepositoryQuery;

public class CondutorRepositoryImpl implements CondutorRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	

	public Page<Condutor> filtrar(CondutorFilter filtro, Pageable pageable, Campus campus) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Condutor> criteria = builder.createQuery(Condutor.class);
		Root<Condutor> root = criteria.from(Condutor.class);		

		Predicate[] predicates = criarRestricoes(filtro, builder, root, campus);

		criteria.where(predicates);
		
		if(campus.isPrincipal()) {			
			criteria.orderBy(builder.asc(root.get(Condutor_.campus).get(Campus_.nome)), builder.asc(root.get(Condutor_.nome)));
		}else {
			criteria.orderBy(builder.asc(root.get(Condutor_.nome)));
		}

		TypedQuery<Condutor> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro, campus));
	}

	private Predicate[] criarRestricoes(CondutorFilter filtro, CriteriaBuilder builder, Root<Condutor> root, Campus campus) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(filtro.getCampus())) {
			if(!campus.isPrincipal()) {
				predicates.add(builder.like(builder.lower(root.get(Condutor_.campus).get(Campus_.nome)), "%"+ filtro.getCampus().toLowerCase() + "%"));
			}
		}
		if (!StringUtils.isEmpty(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Condutor_.nome)), "%"+ filtro.getNome().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getCpf())) {
			predicates.add(builder.like(builder.lower(root.get(Condutor_.cpf)), "%"+ filtro.getCpf().toLowerCase() + "%"));
		}		
		if (filtro.getDataNascimentoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Condutor_.dataNascimento), filtro.getDataNascimentoDe()));
		}
		
		if (filtro.getDataNascimentoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Condutor_.dataNascimento), filtro.getDataNascimentoAte()));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(CondutorFilter filtro, Campus campus) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Condutor> root = criteria.from(Condutor.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root, campus);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	
}
