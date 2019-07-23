package br.ueg.api.model;

public enum StatusReserva {
	
	CADASTRADO("Cadastrado"),
	EXCLUIDO("Excluido"),
	ENVIADO("Enviado"),
	ANALISANDO("Analisando"),
	AUTORIZADO("Autorizado"),
	CANCELADO("Cancelado"),
	REJEITADO("Rejeitado");
	
	private final String descricao;
	
	StatusReserva(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
