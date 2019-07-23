package br.ueg.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="03b_condutor")
public class Condutor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name="nome")
	private String nome;
	
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="numero_cnh", unique=true)
	private String numeroCnh;
	
	@Column(name="categoria_cnh")
	private String categoriaCnh;	
	
	@Column(name="data_cadastro")
	private LocalDate dataCadastro;
	
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name="data_vencimento_cnh")
	private LocalDate dataVencimentoCnh;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_campus")
	private Campus campus;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
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
	public String getNumeroCnh() {
		return numeroCnh;
	}
	public void setNumeroCnh(String numeroCnh) {
		this.numeroCnh = numeroCnh;
	}
	public String getCategoriaCnh() {
		return categoriaCnh;
	}
	public void setCategoriaCnh(String categoriaCnh) {
		this.categoriaCnh = categoriaCnh;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}	
	public LocalDate getDataVencimentoCnh() {
		return dataVencimentoCnh;
	}
	public void setDataVencimentoCnh(LocalDate dataVencimentoCnh) {
		this.dataVencimentoCnh = dataVencimentoCnh;
	}	
	public Campus getCampus() {
		return campus;
	}
	public void setCampus(Campus campus) {
		this.campus = campus;
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
		Condutor other = (Condutor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
