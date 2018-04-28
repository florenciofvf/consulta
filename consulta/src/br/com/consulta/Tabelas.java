package br.com.consulta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import br.com.consulta.util.Constantes;

public class Tabelas implements Arvore {
	private List<Tabela> tabelas;

	public Tabelas() {
		tabelas = new ArrayList<>();
	}

	public void add(Tabela t) {
		Objects.requireNonNull(t, getClass().getName());

		if (tabelas.contains(t)) {
			throw new IllegalArgumentException(Constantes.DUPLICADO + t.getNome());
		}

		t.parent = this;
		tabelas.add(t);
	}

	public Tabela getTabela(int index) {
		return tabelas.get(index);
	}

	@Override
	public String toString() {
		return "TABELAS (" + tabelas.size() + ")";
	}

	@Override
	public int getIndexOfChild(Object child) {
		return tabelas.indexOf(child);
	}

	@Override
	public Object getChild(int index) {
		return tabelas.get(index);
	}

	@Override
	public int getChildCount() {
		return tabelas.size();
	}

	@Override
	public boolean isLeaf() {
		return tabelas.isEmpty();
	}

	@Override
	public Arvore getParent() {
		return null;
	}

	public void ordenar() {
		Collections.sort(tabelas, new Comparador());
	}

	private class Comparador implements Comparator<Tabela> {
		@Override
		public int compare(Tabela o1, Tabela o2) {
			return o1.getNome().compareTo(o2.getNome());
		}
	}

	@Override
	public void setString(String s) {
		throw new IllegalStateException(s);
	}
}