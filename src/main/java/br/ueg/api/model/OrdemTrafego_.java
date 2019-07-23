package br.ueg.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrdemTrafego.class)
public abstract class OrdemTrafego_ {

	public static volatile SingularAttribute<OrdemTrafego, LocalDate> dataSolicitacao;
	public static volatile SingularAttribute<OrdemTrafego, Veiculo> veiculo;
	public static volatile SingularAttribute<OrdemTrafego, Condutor> condutor;
	public static volatile SingularAttribute<OrdemTrafego, Long> codigo;
	public static volatile SingularAttribute<OrdemTrafego, String> observacao;
	public static volatile SingularAttribute<OrdemTrafego, Boolean> ativo;
	public static volatile ListAttribute<OrdemTrafego, Rota> rotas;
	public static volatile SingularAttribute<OrdemTrafego, Campus> campus;
	public static volatile SingularAttribute<OrdemTrafego, String> solicitante;
	public static volatile SingularAttribute<OrdemTrafego, Usuario> usuario;
	public static volatile SingularAttribute<OrdemTrafego, BigDecimal> totalKm;
	public static volatile SingularAttribute<OrdemTrafego, LocalDate> dataCadastro;

}

