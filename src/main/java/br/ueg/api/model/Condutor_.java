package br.ueg.api.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Condutor.class)
public abstract class Condutor_ {

	public static volatile SingularAttribute<Condutor, String> categoriaCnh;
	public static volatile SingularAttribute<Condutor, Long> codigo;
	public static volatile SingularAttribute<Condutor, Campus> campus;
	public static volatile SingularAttribute<Condutor, String> cpf;
	public static volatile SingularAttribute<Condutor, String> nome;
	public static volatile SingularAttribute<Condutor, String> numeroCnh;
	public static volatile SingularAttribute<Condutor, LocalDate> dataVencimentoCnh;
	public static volatile SingularAttribute<Condutor, LocalDate> dataNascimento;
	public static volatile SingularAttribute<Condutor, LocalDate> dataCadastro;

}

