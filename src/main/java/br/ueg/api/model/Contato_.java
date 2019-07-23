package br.ueg.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contato.class)
public abstract class Contato_ {

	public static volatile SingularAttribute<Contato, Long> codigo;
	public static volatile SingularAttribute<Contato, String> observacao;
	public static volatile SingularAttribute<Contato, Departamento> departamento;
	public static volatile SingularAttribute<Contato, String> nome;
	public static volatile ListAttribute<Contato, Telefones> telefones;
	public static volatile SingularAttribute<Contato, String> email;

}

