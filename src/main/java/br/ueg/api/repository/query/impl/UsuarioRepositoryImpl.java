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

import br.ueg.api.model.Usuario;
import br.ueg.api.model.Usuario_;
import br.ueg.api.repository.filter.UsuarioFilter;
import br.ueg.api.repository.query.UsuarioRepositoryQuery;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);		

		Predicate[] predicates = criarRestricoes(filtro, builder, root);

		criteria.where(predicates);
		criteria.orderBy(builder.asc(root.get("nome")));

		TypedQuery<Usuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro));
	}

	private Predicate[] criarRestricoes(UsuarioFilter filtro, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.nome)), "%"+ filtro.getNome().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getEmail())) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.email)), "%"+ filtro.getEmail().toLowerCase() + "%"));
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
	
	private Long total(UsuarioFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	

}
