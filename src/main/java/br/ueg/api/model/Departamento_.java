package br.ueg.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Departamento.class)
public abstract class Departamento_ {

	public static volatile SingularAttribute<Departamento, Long> codigo;
	public static volatile SingularAttribute<Departamento, Campus> campus;
	public static volatile SingularAttribute<Departamento, String> descricao;

}

