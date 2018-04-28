package br.com.consulta.util;

import java.util.ArrayList;
import java.util.List;

import br.com.consulta.Tabela;
import br.com.consulta.Tabelas;

public class ArvoreUtil {
	private ArvoreUtil() {
	}

	public static List<Tabela> tabelasDestaques(Tabelas tabelas) {
		List<Tabela> listagem = new ArrayList<>();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.isDestaque()) {
				listagem.add(tabela);
			}
		}

		return listagem;
	}

	public static List<Tabela> tabelasComRegistros(Tabelas tabelas) {
		List<Tabela> listagem = new ArrayList<>();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getRegistros() > 0) {
				listagem.add(tabela);
			}
		}

		return listagem;
	}

	public static List<Tabela> tabelasSemRegistros(Tabelas tabelas) {
		List<Tabela> listagem = new ArrayList<>();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getRegistros() == 0) {
				listagem.add(tabela);
			}
		}

		return listagem;
	}

	public static List<Tabela> tabelasDuasFK(Tabelas tabelas) {
		List<Tabela> listagem = new ArrayList<>();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getCountFK() == 2) {
				listagem.add(tabela);
			}
		}

		return listagem;
	}

	public static List<Tabela> tabelasMultiplasFK(Tabelas tabelas) {
		List<Tabela> listagem = new ArrayList<>();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getCountFK() > 2) {
				listagem.add(tabela);
			}
		}

		return listagem;
	}

	public static List<Tabela> tabelasChavesCompostas(Tabelas tabelas) {
		List<Tabela> listagem = new ArrayList<>();

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);
			if (tabela.getCountPK() > 1) {
				listagem.add(tabela);
			}
		}

		return listagem;
	}
}