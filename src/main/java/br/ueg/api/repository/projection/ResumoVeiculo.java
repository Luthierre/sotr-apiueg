package br.ueg.api.repository.projection;

public class ResumoVeiculo {
	
	private Long codigo;	
	private String modelo;	
	private String placa;		
	private Integer odometro;
	
	public ResumoVeiculo(Long codigo, String modelo, String placa, Integer odometro) {
	
		this.codigo = codigo;
		this.modelo = modelo;
		this.placa = placa;
		this.odometro = odometro;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getOdometro() {
		return odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}
}
