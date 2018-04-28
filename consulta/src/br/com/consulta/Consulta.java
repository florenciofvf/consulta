package br.com.consulta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.Icon;

import br.com.consulta.util.Constantes;
import br.com.consulta.util.Icones;
import br.com.consulta.util.Util;

public class Consulta implements Arvore {
	private final List<Consulta> consultas;
	private final String resumo;
	private String consulta;
	protected Arvore parent;
	private Icon icone;

	public Consulta(String resumo) {
		Util.checarVazio(resumo, "erro.resumo_consulta_vazio");
		consultas = new ArrayList<>();
		this.resumo = resumo;
	}

	public void add(Consulta c) {
		Objects.requireNonNull(c, resumo);

		if (consultas.contains(c)) {
			throw new IllegalArgumentException(Constantes.DUPLICADO + c.getResumo());
		}

		consultas.add(c);
		c.parent = this;
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

	public String getResumo() {
		return resumo;
	}

	public String getConsulta() {
		return consulta;
	}

	@Override
	public void setString(String s) {
		setConsulta(s);
	}

	public void setConsulta(String consulta) {
		if (Util.isVazio(consulta)) {
			throw new IllegalStateException(Constantes.SEM_CONSULTA + resumo);
		}

		this.consulta = consulta;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Consulta) {
			return resumo.equalsIgnoreCase(((Consulta) obj).resumo);
		}

		return false;
	}

	@Override
	public String toString() {
		return resumo;
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

	@Override
	public boolean isLeaf() {
		return consultas.isEmpty();
	}

	@Override
	public Arvore getParent() {
		return parent;
	}
}