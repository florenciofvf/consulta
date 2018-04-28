package br.com.consulta;

import br.com.consulta.util.Util;

public class Campo {
	private final String nome;
	private final String tipo;

	public Campo(String nome, String tipo) {
		Util.checarVazio(nome, "erro.nome_fk_vazio");
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Campo) {
			return nome.equalsIgnoreCase(((Campo) obj).nome);
		}

		return false;
	}

	@Override
	public String toString() {
		return nome + "=" + tipo;
	}
}