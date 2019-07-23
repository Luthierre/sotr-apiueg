package br.ueg.api.repository.projection;

import br.ueg.api.model.TipoTelefone;

public class ResumoContatoTelefones {

	private Long codigo;
	private TipoTelefone tipo;
	private String area;
	private String telefone;
	
	

	public ResumoContatoTelefones(Long codigo, TipoTelefone tipo, String area, String telefone) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.area = area;
		this.telefone = telefone;
	
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
}
