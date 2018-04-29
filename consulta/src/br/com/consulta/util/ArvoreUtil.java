package br.com.consulta.util;

import br.com.consulta.Tabela;
import br.com.consulta.Tabelas;

public class ArvoreUtil {
	private ArvoreUtil() {
	}

	public static Tabelas tabelasDestaques(Tabelas tabelas) {
		Tabelas resposta = new Tabelas();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.isDestaque()) {
				resposta.add(tabela);
			}
		}

		return resposta;
	}

	public static Tabelas tabelasComRegistros(Tabelas tabelas) {
		Tabelas resposta = new Tabelas();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getRegistros() > 0) {
				resposta.add(tabela);
			}
		}

		return resposta;
	}

	public static Tabelas tabelasSemRegistros(Tabelas tabelas) {
		Tabelas resposta = new Tabelas();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getRegistros() == 0) {
				resposta.add(tabela);
			}
		}

		return resposta;
	}

	public static Tabelas tabelasDuasFK(Tabelas tabelas) {
		Tabelas resposta = new Tabelas();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getCountFK() == 2) {
				resposta.add(tabela);
			}
		}

		return resposta;
	}

	public static Tabelas tabelasMultiplasFK(Tabelas tabelas) {
		Tabelas resposta = new Tabelas();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getCountFK() > 2) {
				resposta.add(tabela);
			}
		}

		return resposta;
	}

	public static Tabelas tabelasChavesCompostas(Tabelas tabelas) {
		Tabelas resposta = new Tabelas();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getCountPK() > 1) {
				resposta.add(tabela);
			}
		}

		return resposta;
	}
}