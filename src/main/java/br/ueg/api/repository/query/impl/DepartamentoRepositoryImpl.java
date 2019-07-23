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

import br.ueg.api.model.Campus_;
import br.ueg.api.model.Departamento;
import br.ueg.api.model.Departamento_;
import br.ueg.api.repository.filter.DepartamentoFilter;
import br.ueg.api.repository.query.DepartamentoRepositoryQuery;

public class DepartamentoRepositoryImpl implements DepartamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	public Page<Departamento> filtrar(DepartamentoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
		Root<Departamento> root = criteria.from(Departamento.class);		

		Predicate[] predicates = criarRestricoes(filtro, builder, root);

		criteria.where(predicates);
		criteria.orderBy(builder.asc(root.get("descricao")));

		TypedQuery<Departamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro));
	}

	private Predicate[] criarRestricoes(DepartamentoFilter filtro, CriteriaBuilder builder, Root<Departamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filtro.getCampus())) {
			predicates.add(builder.like(builder.lower(root.get(Departamento_.campus).get(Campus_.nome)), "%"+  filtro.getCampus().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filtro.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Departamento_.descricao)), "%"+ filtro.getDescricao().toLowerCase() + "%"));
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
	
	private Long total(DepartamentoFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Departamento> root = criteria.from(Departamento.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	
}
