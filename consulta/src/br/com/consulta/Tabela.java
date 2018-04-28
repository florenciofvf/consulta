package br.com.consulta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.Icon;

import br.com.consulta.util.Constantes;
import br.com.consulta.util.Icones;
import br.com.consulta.util.Util;

public class Tabela implements Arvore {
	private final List<Consulta> consultas;
	private final List<Campo> campos;
	private final List<PK> pks;
	private final List<FK> fks;
	private final String nome;
	private boolean destaque;
	private String consulta;
	protected Arvore parent;
	private int registros;
	private Icon icone;

	public Tabela(String nome) {
		Util.checarVazio(nome, "erro.nome_tabela_vazio");
		consultas = new ArrayList<>();
		campos = new ArrayList<>();
		pks = new ArrayList<>();
		fks = new ArrayList<>();
		this.nome = nome;
	}

	public void add(Consulta c) {
		Objects.requireNonNull(c, nome);

		if (consultas.contains(c)) {
			throw new IllegalArgumentException(Constantes.DUPLICADO + c.getResumo());
		}

		consultas.add(c);
		c.parent = this;
	}

	public void add(Campo c) {
		Objects.requireNonNull(c, nome);

		if (campos.contains(c)) {
			throw new IllegalArgumentException(Constantes.DUPLICADO + c.getNome());
		}

		campos.add(c);
	}

	public void add(PK pk) {
		Objects.requireNonNull(pk, nome);

		if (pks.contains(pk)) {
			throw new IllegalArgumentException(Constantes.DUPLICADO + pk.getNome());
		}

		pks.add(pk);
	}

	public void add(FK fk) {
		Objects.requireNonNull(fk, nome);

		if (fks.contains(fk)) {
			throw new IllegalArgumentException(Constantes.DUPLICADO + fk.getNome());
		}

		fks.add(fk);
	}

	public String getConsulta() {
		if (Util.isVazio(consulta)) {
			consulta = "SELECT * FROM " + nome + " WHERE 1=1";
		}

		return consulta;
	}

	public String getConsultaCount() {
		return "SELECT COUNT(*) FROM " + nome;
	}

	@Override
	public void setString(String s) {
		setConsulta(s);
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public boolean isDestaque() {
		return destaque;
	}

	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}

	public Icon getIcone() {
		return icone;
	}

	public void setIcone(Icon icone) {
		if (icone != null) {
			this.icone = icone;
		}
	}

	public void setIcone(String icone) {
		if (Util.isVazio(icone)) {
			this.icone = Icones.getIcon("um_pixel");
		} else {
			this.icone = Icones.getIcon(icone);
		}
	}

	public String getNome() {
		return nome;
	}

	public int getRegistros() {
		return registros;
	}

	public void setRegistros(int registros) {
		this.registros = registros;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tabela) {
			return nome.equalsIgnoreCase(((Tabela) obj).nome);
		}

		return false;
	}

	@Override
	public String toString() {
		return nome + " (" + registros + ")";
	}

	@Override
	public int getIndexOfChild(Object child) {
		return consultas.indexOf(child);
	}

	@Override
	public Object getChild(int index) {
		return consultas.get(index);
	}

	@Override
	public int getChildCount() {
		return consultas.size();
	}

	public int getCountPK() {
		return pks.size();
	}

	public int getCountFK() {
		return fks.size();
	}

	@Override
	public boolean isLeaf() {
		return consultas.isEmpty();
	}

	@Override
	public Arvore getParent() {
		return parent;
	}
}