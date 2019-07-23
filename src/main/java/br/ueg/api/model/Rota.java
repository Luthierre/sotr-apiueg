package br.ueg.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="03d_rotas")
public class Rota {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name="local_saida")
	private String localSaida;
	
	
	@Column(name="data_saida")	
	private LocalDate dataSaida;
	
	@Column(name="hora_saida")
	private String horaSaida;
	
	@Column(name="km_saida", precision=2, scale=10)
	private BigDecimal kmSaida;
	
	@Column(name="local_chegada")
	private String localChegada;
	
	
	@Column(name="data_chegada")	
	private LocalDate dataChegada;
	
	@Column(name="hora_chegada")
	private String horaChegada;
	
	@Column(name="km_chegada", precision=2, scale=10)
	private BigDecimal kmChegada;
	
	@Column(name="km_percorrido", precision=2, scale=10)
	private BigDecimal kmPercorrido;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="servico")
	private String servico;
	
	@Column(name="observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_ordem")
	private OrdemTrafego ordem;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getLocalSaida() {
		return localSaida;
	}
	public void setLocalSaida(String localSaida) {
		this.localSaida = localSaida;
	}
	public LocalDate getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public BigDecimal getKmSaida() {
		return kmSaida;
	}
	public void setKmSaida(BigDecimal kmSaida) {
		this.kmSaida = kmSaida;
	}
	public String getLocalChegada() {
		return localChegada;
	}
	public void setLocalChegada(String localChegada) {
		this.localChegada = localChegada;
	}
	public LocalDate getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(LocalDate dataChegada) {
		this.dataChegada = dataChegada;
	}
	public String getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}
	public BigDecimal getKmChegada() {
		return kmChegada;
	}
	public void setKmChegada(BigDecimal kmChegada) {
		this.kmChegada = kmChegada;
	}
	public BigDecimal getKmPercorrido() {
		return kmPercorrido;
	}
	public void setKmPercorrido(BigDecimal kmPercorrido) {
		this.kmPercorrido = kmPercorrido;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public OrdemTrafego getOrdem() {
		return ordem;
	}
	public void setOrdem(OrdemTrafego ordem) {
		this.ordem = ordem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rota other = (Rota) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	

}
