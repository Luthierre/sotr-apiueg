package br.ueg.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Telefones.class)
public abstract class Telefones_ {

	public static volatile SingularAttribute<Telefones, String> area;
	public static volatile SingularAttribute<Telefones, Long> codigo;
	public static volatile SingularAttribute<Telefones, TipoTelefone> tipo;
	public static volatile SingularAttribute<Telefones, String> numero;
	public static volatile SingularAttribute<Telefones, Contato> contato;

}

