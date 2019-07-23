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
import br.ueg.api.model.Contato;
import br.ueg.api.model.Contato_;
import br.ueg.api.model.Departamento_;
import br.ueg.api.model.Telefones;
import br.ueg.api.model.Telefones_;
import br.ueg.api.repository.filter.ContatoFilter;
import br.ueg.api.repository.projection.ResumoContato;
import br.ueg.api.repository.projection.ResumoContatoTelefones;
import br.ueg.api.repository.query.ContatoRepositoryQuery;

public class ContatoRepositoryImpl implements ContatoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	

	public Page<Contato> filtrar(ContatoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Contato> criteria = builder.createQuery(Contato.class);
		Root<Contato> root = criteria.from(Contato.class);
		

		Predicate[] predicates = criarRestricoes(filtro, builder, root);

		criteria.where(predicates);
		
		criteria.orderBy(builder.asc(root.get("nome")));

		TypedQuery<Contato> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro));
	}

	private Predicate[] criarRestricoes(ContatoFilter filtro, CriteriaBuilder builder, Root<Contato> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(filtro.getCampus())) {			
			predicates.add(builder.like(builder.lower(root.get(Contato_.departamento).get(Departamento_.campus).get(Campus_.nome)),  "%"+ filtro.getCampus().toLowerCase() + "%"));				
		}
		if (!StringUtils.isEmpty(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Contato_.nome)), "%"+ filtro.getNome().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getEmail())) {
			predicates.add(builder.like(builder.lower(root.get(Contato_.email)), "%"+ filtro.getEmail().toLowerCase() + "%"));
		}		
		if (!StringUtils.isEmpty(filtro.getObservacao())) {
			predicates.add(builder.like(builder.lower(root.get(Contato_.observacao)), "%"+ filtro.getObservacao().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getDepartamento())) {			
			predicates.add(builder.like(builder.lower(root.get(Contato_.departamento).get(Departamento_.descricao)), "%"+ filtro.getDepartamento().toLowerCase() + "%"));			
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
	
	private Long total(ContatoFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Contato> root = criteria.from(Contato.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public Page<ResumoContato> resumir(ContatoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoContato> criteria = builder.createQuery(ResumoContato.class);
		Root<Contato> root = criteria.from(Contato.class);		
		
		
		
		criteria.select(builder.construct(ResumoContato.class
				, root.get(Contato_.codigo), root.get(Contato_.nome)
				, root.get(Contato_.email), root.get(Contato_.departamento).get(Departamento_.descricao)
				, root.get(Contato_.departamento).get(Departamento_.campus).get(Campus_.nome)));
				
		Predicate[] predicates = criarRestricoes(filtro, builder, root);

		criteria.where(predicates);
		criteria.orderBy(builder.asc(root.get("nome")));
		TypedQuery<ResumoContato> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro));
	}
	
	
	public List<ResumoContatoTelefones> telefonesPorContato(Long id) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoContatoTelefones> criteria = builder.createQuery(ResumoContatoTelefones.class);
		Root<Telefones> root = criteria.from(Telefones.class);
		
		criteria.select(builder.construct(ResumoContatoTelefones.class
				, root.get(Telefones_.codigo), root.get(Telefones_.tipo)
				, root.get(Telefones_.area), root.get(Telefones_.numero)));
		Predicate[] predicates = criarRestricoesTelefones(id, builder, root);

		criteria.where(predicates);

		TypedQuery<ResumoContatoTelefones> query = manager.createQuery(criteria);
		return query.getResultList(); 
	}

	private Predicate[] criarRestricoesTelefones(Long id, CriteriaBuilder builder, Root<Telefones> root) {
			List<Predicate> predicates = new ArrayList<>();
		
		if (id != null) {
			predicates.add(builder.equal(root.get(Telefones_.contato).get(Contato_.codigo), id));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}	
}
