package br.com.consulta;

import br.com.consulta.util.Util;

public class PK {
	private final String nome;

	public PK(String nome) {
		Util.checarVazio(nome, "erro.nome_pk_vazio");
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PK) {
			return nome.equalsIgnoreCase(((PK) obj).nome);
		}

		return false;
	}

	@Override
	public String toString() {
		return nome;
	}
}