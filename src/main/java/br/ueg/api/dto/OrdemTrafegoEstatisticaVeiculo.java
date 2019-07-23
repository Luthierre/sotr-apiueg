package br.ueg.api.dto;

import java.math.BigDecimal;

import br.ueg.api.model.Veiculo;

public class OrdemTrafegoEstatisticaVeiculo {

	private Veiculo veiculo;
	private BigDecimal totalViagem;
	
	
	public OrdemTrafegoEstatisticaVeiculo(Veiculo veiculo, BigDecimal totalViagem) {
		this.veiculo = veiculo;
		this.totalViagem = totalViagem;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public BigDecimal getTotalViagem() {
		return totalViagem;
	}
	public void setTotalViagem(BigDecimal totalViagem) {
		this.totalViagem = totalViagem;
	}
	
	
}
