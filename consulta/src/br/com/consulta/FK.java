package br.com.consulta;

import br.com.consulta.util.Util;

public class FK {
	private final String tabela;
	private final String nome;

	public FK(String tabela, String nome) {
		Util.checarVazio(tabela, "erro.nome_tabela_vazio");
		Util.checarVazio(nome, "erro.nome_fk_vazio");
		this.tabela = tabela;
		this.nome = nome;
	}

	public String getTabela() {
		return tabela;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FK) {
			return nome.equalsIgnoreCase(((FK) obj).nome);
		}

		return false;
	}

	@Override
	public String toString() {
		return tabela + "=" + nome;
	}
}