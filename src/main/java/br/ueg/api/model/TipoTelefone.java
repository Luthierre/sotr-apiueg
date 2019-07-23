package br.ueg.api.model;

public enum TipoTelefone {
	
	COMERCIAL("Comercial"),
	CORPORATIVO("Corporativo"),
	DEPARTAMENTO("Departamento"),
	PESSOAL("Pessoal"),
	RESIDENCIAL("Residencial"),
	TRABALHO("Trabalho");
	
	private final String descricao;
	
	TipoTelefone(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
