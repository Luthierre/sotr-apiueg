package br.ueg.api.repository.query.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.ueg.api.dto.OrdemTrafegoEstatisticaCampusDia;
import br.ueg.api.dto.OrdemTrafegoEstatisticaVeiculo;
import br.ueg.api.model.Campus;
import br.ueg.api.model.Campus_;
import br.ueg.api.model.Condutor_;
import br.ueg.api.model.OrdemTrafego;
import br.ueg.api.model.OrdemTrafego_;
import br.ueg.api.model.Rota;
import br.ueg.api.model.Rota_;
import br.ueg.api.model.Usuario_;
import br.ueg.api.model.Veiculo;
import br.ueg.api.model.Veiculo_;
import br.ueg.api.repository.filter.OrdemTrafegoFilter;
import br.ueg.api.repository.projection.ResumoOrdemTrafego;
import br.ueg.api.repository.query.OrdemTrafegoRepositoryQuery;

public class OrdemTrafegoRepositoryImpl implements OrdemTrafegoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	public Page<OrdemTrafego> filtrar(OrdemTrafegoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<OrdemTrafego> criteria = builder.createQuery(OrdemTrafego.class);
		Root<OrdemTrafego> root = criteria.from(OrdemTrafego.class);		

		Predicate[] predicates = criarRestricoes(filtro, builder, root);

		criteria.where(predicates);
		

		TypedQuery<OrdemTrafego> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro));
	}

	private Predicate[] criarRestricoes(OrdemTrafegoFilter filtro, CriteriaBuilder builder, Root<OrdemTrafego> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filtro.getCampus())) {
			predicates.add(builder.like(builder.lower(root.get(OrdemTrafego_.campus).get(Campus_.nome)), "%"+  filtro.getCampus().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getSolicitante())) {
			predicates.add(builder.like(builder.lower(root.get(OrdemTrafego_.solicitante)), "%"+ filtro.getSolicitante().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getCondutor())) {
			predicates.add(builder.like(builder.lower(root.get(OrdemTrafego_.condutor).get(Condutor_.nome)), "%"+ filtro.getCondutor().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getCpf())) {
			predicates.add(builder.like(builder.lower(root.get(OrdemTrafego_.condutor).get(Condutor_.cpf)), "%"+ filtro.getCpf().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getPlaca())) {
			predicates.add(builder.like(builder.lower(root.get(OrdemTrafego_.veiculo).get(Veiculo_.placa)), "%"+ filtro.getPlaca().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filtro.getModelo())) {
			predicates.add(builder.like(builder.lower(root.get(OrdemTrafego_.veiculo).get(Veiculo_.modelo)), "%"+ filtro.getModelo().toLowerCase() + "%"));
		}
		if (filtro.getDataSolicitacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(OrdemTrafego_.dataSolicitacao), filtro.getDataSolicitacaoDe()));
		}
		
		if (filtro.getDataSolicitacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(OrdemTrafego_.dataSolicitacao), filtro.getDataSolicitacaoAte()));
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
	
	private Long total(OrdemTrafegoFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<OrdemTrafego> root = criteria.from(OrdemTrafego.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public Page<ResumoOrdemTrafego> resumir(OrdemTrafegoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoOrdemTrafego> criteria = builder.createQuery(ResumoOrdemTrafego.class);
		Root<OrdemTrafego> root = criteria.from(OrdemTrafego.class);
		
		criteria.select(builder.construct(ResumoOrdemTrafego.class
				, root.get(OrdemTrafego_.codigo)
				,root.get(OrdemTrafego_.ativo)				
				, root.get(OrdemTrafego_.solicitante)
				, root.get(OrdemTrafego_.veiculo).get(Veiculo_.modelo)
				, root.get(OrdemTrafego_.veiculo).get(Veiculo_.placa)
				, root.get(OrdemTrafego_.condutor).get(Condutor_.nome)				
				, root.get(OrdemTrafego_.dataSolicitacao)
				, root.get(OrdemTrafego_.usuario).get(Usuario_.nome)
				, root.get(OrdemTrafego_.campus).get(Campus_.nome)));
		Predicate[] predicates = criarRestricoes(filtro, builder, root);

		criteria.where(predicates);
		criteria.orderBy(builder.desc(root.get("dataSolicitacao")));

		TypedQuery<ResumoOrdemTrafego> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>( query.getResultList(), pageable, total(filtro));
	}

	@Override
	public OrdemTrafego porOrdem(Long id) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder(); 		
		CriteriaQuery<OrdemTrafego> criteriaQuery = criteriaBuilder.createQuery(OrdemTrafego.class);
		Root<OrdemTrafego> root = criteriaQuery.from(OrdemTrafego.class);		
		criteriaQuery.where(criteriaBuilder.equal(root.get(OrdemTrafego_.codigo), id));		
		TypedQuery<OrdemTrafego> typedQuery = manager.createQuery(criteriaQuery);		
		return typedQuery.getSingleResult();
		
	}

	@Override
	public List<OrdemTrafegoEstatisticaVeiculo> ordemTrafegoVeiculoPorMes(Campus campus, LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder(); 
		CriteriaQuery<OrdemTrafegoEstatisticaVeiculo> criteriaQuery = criteriaBuilder.createQuery(OrdemTrafegoEstatisticaVeiculo.class);
		
		Root<OrdemTrafego> root = criteriaQuery.from(OrdemTrafego.class);
		@SuppressWarnings("unused")
		Join<OrdemTrafego, Veiculo> joiVeiculo = root.join(OrdemTrafego_.veiculo);
		Join<OrdemTrafego, Rota> joinRota = root.join(OrdemTrafego_.rotas);
		criteriaQuery.select(criteriaBuilder.construct(OrdemTrafegoEstatisticaVeiculo.class, root.get(OrdemTrafego_.veiculo), criteriaBuilder.sum(joinRota.get(Rota_.kmPercorrido))));
		
		
		criteriaQuery.where(criarRestricoesCampus(campus, mesReferencia, criteriaBuilder, root));
		/*criteriaQuery.where(				
				criteriaBuilder.like(criteriaBuilder.lower(root.get(OrdemTrafego_.campus).get(Campus_.nome)), "%"+ campus.toLowerCase() + "%"),
				criteriaBuilder.greaterThanOrEqualTo(root.get(OrdemTrafego_.dataSolicitacao), primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(OrdemTrafego_.dataSolicitacao), ultimoDia));*/
		
		criteriaQuery.groupBy(root.get(OrdemTrafego_.veiculo).get(Veiculo_.modelo));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get(OrdemTrafego_.veiculo).get(Veiculo_.modelo)));
		
		
		TypedQuery<OrdemTrafegoEstatisticaVeiculo> typedQuery = manager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}	
	public Predicate[] criarRestricoesCampus(Campus campus, LocalDate mesReferencia, CriteriaBuilder builder, Root<OrdemTrafego> root) {
		List<Predicate> predicados = new ArrayList<>();
		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());
		if(!campus.isPrincipal()) {
			predicados.add(builder.like(builder.lower(root.get(OrdemTrafego_.campus).get(Campus_.nome)), "%"+ campus.getNome().toLowerCase() + "%"));
		}
		predicados.add(builder.greaterThanOrEqualTo(root.get(OrdemTrafego_.dataSolicitacao), primeiroDia));
		predicados.add(builder.lessThanOrEqualTo(root.get(OrdemTrafego_.dataSolicitacao), ultimoDia));
		
		
		
		return  predicados.toArray(new Predicate[predicados.size()] );
	}

	@SuppressWarnings("unused")
	@Override
	public List<OrdemTrafegoEstatisticaCampusDia> ordemTrafegoCampusPorDia(Campus campus, LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder(); 
		CriteriaQuery<OrdemTrafegoEstatisticaCampusDia> criteriaQuery = criteriaBuilder.createQuery(OrdemTrafegoEstatisticaCampusDia.class);
		
		Root<OrdemTrafego> root = criteriaQuery.from(OrdemTrafego.class);
		Join<OrdemTrafego, Veiculo> joiVeiculo = root.join(OrdemTrafego_.veiculo);
		Join<OrdemTrafego, Rota> joinRota = root.join(OrdemTrafego_.rotas);
		criteriaQuery.select(criteriaBuilder.construct(OrdemTrafegoEstatisticaCampusDia.class, root.get(OrdemTrafego_.campus).get(Campus_.nome), root.get(OrdemTrafego_.dataSolicitacao), criteriaBuilder.sum(joinRota.get(Rota_.kmPercorrido))));
		
		
		criteriaQuery.where(criarRestricoesCampus(campus, mesReferencia, criteriaBuilder, root));
		
		
		criteriaQuery.groupBy(root.get(OrdemTrafego_.campus).get(Campus_.nome), root.get(OrdemTrafego_.dataSolicitacao));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get(OrdemTrafego_.campus).get(Campus_.nome)));
		
		
		TypedQuery<OrdemTrafegoEstatisticaCampusDia> typedQuery = manager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
}
