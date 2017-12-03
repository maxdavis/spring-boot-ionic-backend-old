package com.maxdavis.cursomc.domain.Enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setId(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (EstadoPagamento estPagamento : EstadoPagamento.values()) {
			if (cod.equals(estPagamento.getCod())) {
				return estPagamento;
			}
		}

		throw new IllegalArgumentException("Código inválido:" + cod);
	}

}
