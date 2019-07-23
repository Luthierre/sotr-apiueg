package br.ueg.api.repository.projection;

import java.time.LocalDate;

public class ResumoOrdemTrafego {

	private Long codigo;
	private String solicitante;
	private String veiculo;
	private String placa;
	private String condutor;
	private LocalDate dataSolicitacao;
	private String usuario;
	private String campus;
	private boolean ativo;
	
	

	public ResumoOrdemTrafego(Long codigo,boolean ativo, String solicitante, String veiculo, String placa, String condutor,
			LocalDate dataSolicitacao, String usuario, String campus) {		
		this.codigo = codigo;
		this.ativo = ativo;
		this.solicitante = solicitante;
		this.veiculo = veiculo;
		this.placa = placa;
		this.condutor = condutor;
		this.dataSolicitacao = dataSolicitacao;
		this.usuario = usuario;		
		this.campus = campus;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCondutor() {
		return condutor;
	}

	public void setCondutor(String condutor) {
		this.condutor = condutor;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
