package br.ueg.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public class OrdemTrafegoFilter {
	
	private String solicitante;
	private String placa;
	private String modelo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSolicitacaoDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSolicitacaoAte;
	private String cpf;
	private String condutor;
	private String campus;
	private boolean ativo;
	
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}	
	public LocalDate getDataSolicitacaoDe() {
		return dataSolicitacaoDe;
	}
	public void setDataSolicitacaoDe(LocalDate dataSolicitacaoDe) {
		this.dataSolicitacaoDe = dataSolicitacaoDe;
	}
	public LocalDate getDataSolicitacaoAte() {
		return dataSolicitacaoAte;
	}
	public void setDataSolicitacaoAte(LocalDate dataSolicitacaoAte) {
		this.dataSolicitacaoAte = dataSolicitacaoAte;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCondutor() {
		return condutor;
	}
	public void setCondutor(String condutor) {
		this.condutor = condutor;
	}
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
}
