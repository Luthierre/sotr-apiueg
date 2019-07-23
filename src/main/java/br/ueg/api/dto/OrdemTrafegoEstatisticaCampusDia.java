package br.ueg.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemTrafegoEstatisticaCampusDia {
	
	private String campus;
	private LocalDate dia;
	private BigDecimal totalKmDia;
	
	
	public OrdemTrafegoEstatisticaCampusDia(String campus, LocalDate data, BigDecimal totalKmDia) {	
		this.campus = campus;
		this.dia = data;
		this.totalKmDia = totalKmDia;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public LocalDate getDia() {
		return dia;
	}
	public void setDia(LocalDate dia) {
		this.dia = dia;
	}
	public BigDecimal getTotalKmDia() {
		return totalKmDia;
	}
	public void setTotalKmDia(BigDecimal totalKmDia) {
		this.totalKmDia = totalKmDia;
	}
	
	

}
