package br.ueg.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rota.class)
public abstract class Rota_ {

	public static volatile SingularAttribute<Rota, Long> codigo;
	public static volatile SingularAttribute<Rota, String> observacao;
	public static volatile SingularAttribute<Rota, String> localChegada;
	public static volatile SingularAttribute<Rota, String> horaChegada;
	public static volatile SingularAttribute<Rota, BigDecimal> kmPercorrido;
	public static volatile SingularAttribute<Rota, BigDecimal> kmChegada;
	public static volatile SingularAttribute<Rota, String> nome;
	public static volatile SingularAttribute<Rota, LocalDate> dataSaida;
	public static volatile SingularAttribute<Rota, BigDecimal> kmSaida;
	public static volatile SingularAttribute<Rota, String> localSaida;
	public static volatile SingularAttribute<Rota, OrdemTrafego> ordem;
	public static volatile SingularAttribute<Rota, String> horaSaida;
	public static volatile SingularAttribute<Rota, LocalDate> dataChegada;
	public static volatile SingularAttribute<Rota, String> servico;

}

