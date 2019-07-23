package br.ueg.api.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrdemTrafegoRelatorio {
	
	
	private Long codigo;	
	private String localSaida;		
	private Date dataSaida;
	private String horaSaida;
	private BigDecimal kmSaida;	
	private String localChegada;	
	private Date dataChegada;
	private String horaChegada;
	private BigDecimal kmChegada;
	private BigDecimal kmPercorrido;	
	private String nome;	
	private String servico;	
	private String observacao;
	public OrdemTrafegoRelatorio(Long codigo, String localSaida, Date dataSaida, String horaSaida, BigDecimal kmSaida,
			String localChegada, Date dataChegada, String horaChegada, BigDecimal kmChegada, BigDecimal kmPercorrido,
			String nome, String servico, String observacao) {
		super();
		this.codigo = codigo;
		this.localSaida = localSaida;
		this.dataSaida = dataSaida;
		this.horaSaida = horaSaida;
		this.kmSaida = kmSaida;
		this.localChegada = localChegada;
		this.dataChegada = dataChegada;
		this.horaChegada = horaChegada;
		this.kmChegada = kmChegada;
		this.kmPercorrido = kmPercorrido;
		this.nome = nome;
		this.servico = servico;
		this.observacao = observacao;
	}
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
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
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
	public Date getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(Date dataChegada) {
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
	
			
}
