package br.ueg.api.repository.projection;

public class ResumoContato {

	private Long codigo;
	private String nome;
	private String email;
	private String departamento;
	private String campus;
	

	public ResumoContato(Long codigo, String nome, String email, String departamento, String campus) {
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.departamento = departamento;
		this.campus = campus;
	
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}


}
