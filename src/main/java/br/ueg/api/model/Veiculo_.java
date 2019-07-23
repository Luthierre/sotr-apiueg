package br.ueg.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Veiculo.class)
public abstract class Veiculo_ {

	public static volatile SingularAttribute<Veiculo, Long> codigo;
	public static volatile SingularAttribute<Veiculo, String> observacao;
	public static volatile SingularAttribute<Veiculo, String> ano;
	public static volatile SingularAttribute<Veiculo, Campus> campus;
	public static volatile SingularAttribute<Veiculo, String> fabricante;
	public static volatile SingularAttribute<Veiculo, String> modelo;
	public static volatile SingularAttribute<Veiculo, String> placa;

}

