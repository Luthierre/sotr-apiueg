package br.ueg.api.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class CondutorFilter {
	
	private String nome;
	private String cpf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimentoDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimentoAte;
	
	private String campus;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimentoDe() {
		return dataNascimentoDe;
	}
	public void setDataNascimentoDe(LocalDate dataNascimentoDe) {
		this.dataNascimentoDe = dataNascimentoDe;
	}
	public LocalDate getDataNascimentoAte() {
		return dataNascimentoAte;
	}
	public void setDataNascimentoAte(LocalDate dataNascimentoAte) {
		this.dataNascimentoAte = dataNascimentoAte;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	

	
}
