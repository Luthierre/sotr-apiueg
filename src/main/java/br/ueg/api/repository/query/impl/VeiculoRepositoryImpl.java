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
import br.ueg.api.model.Veiculo;
import br.ueg.api.model.Veiculo_;
import br.ueg.api.repository.filter.VeiculoFilter;
import br.ueg.api.repository.query.VeiculoRepositoryQuery;

public class VeiculoRepositoryImpl implements VeiculoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	public Page<Veiculo> filtrar(VeiculoFilter filtro, Pageable pageable, Campus campus) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteria = builder.createQuery(Veiculo.class);
		Root<Veiculo> root = criteria.from(Veiculo.class);		

		Predicate[] predicates = criarRestricoes(filtro, builder, root, campus);

		criteria.where(predicates);
		if(campus.isPrincipal()) {			
			criteria.orderBy(builder.asc(root.get(Veiculo_.campus).get(Campus_.nome)), builder.desc(root.get(Veiculo_.placa)));
		}else {
			criteria.orderBy(builder.desc(root.get(Veiculo_.placa)));
		}
		

		TypedQuery<Veiculo> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro, campus));
	}	
	private Predicate[] criarRestricoes(VeiculoFilter filtro, CriteriaBuilder builder, Root<Veiculo> root, Campus campus) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filtro.getModelo())) {
			predicates.add(builder.like(builder.lower(root.get(Veiculo_.modelo)), "%"+ filtro.getModelo().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getFabricante())) {
			predicates.add(builder.like(builder.lower(root.get(Veiculo_.fabricante)), "%"+ filtro.getFabricante().toLowerCase() + "%"));
		}		
		if (!StringUtils.isEmpty(filtro.getPlaca())) {
			predicates.add(builder.like(builder.lower(root.get(Veiculo_.placa)), "%"+ filtro.getPlaca().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getAno())) {
			predicates.add(builder.like(builder.lower(root.get(Veiculo_.ano)), "%"+ filtro.getAno().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getObservacao())) {			
			predicates.add(builder.like(builder.lower(root.get(Veiculo_.observacao)), "%"+ filtro.getObservacao().toLowerCase() + "%"));			
		}
		if (!StringUtils.isEmpty(filtro.getCampus())) {
			if(!campus.isPrincipal()) {
				predicates.add(builder.like(builder.lower(root.get(Veiculo_.campus).get(Campus_.nome)), "%"+ filtro.getCampus().toLowerCase() + "%"));
			}			
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
	private Long total(VeiculoFilter filtro, Campus campus) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Veiculo> root = criteria.from(Veiculo.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root, campus);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	@Override
	public List<Veiculo> procurarPorPlacaOuModelo(VeiculoFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteria = builder.createQuery(Veiculo.class);
		Root<Veiculo> root = criteria.from(Veiculo.class);		
		Predicate[] predicates = criarRestricoesPesquisa(filtro, builder, root);
		
		criteria.where(predicates);
		TypedQuery<Veiculo> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}	
	
	private Predicate[] criarRestricoesPesquisa(VeiculoFilter filtro, CriteriaBuilder builder, Root<Veiculo> root) {
		List<Predicate> predicates = new ArrayList<>();		
		if (!StringUtils.isEmpty(filtro.getModelo())) {
			predicates.add(builder.or(builder.like(builder.lower(root.get(Veiculo_.modelo)), "%"+ filtro.getModelo().toLowerCase() + "%"),builder.or(builder.like(builder.lower(root.get(Veiculo_.placa)), "%"+ filtro.getModelo().toLowerCase() + "%"))));
		}		
		
		if (!StringUtils.isEmpty(filtro.getCampus())) {			
			predicates.add(builder.like(builder.lower(root.get(Veiculo_.campus).get(Campus_.nome)), "%"+ filtro.getCampus().toLowerCase() + "%"));
						
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
