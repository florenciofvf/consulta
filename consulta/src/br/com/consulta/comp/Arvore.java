package br.com.consulta.comp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.com.consulta.Tabelas;

public class Arvore extends JTree {
	private static final long serialVersionUID = 1L;
	private final ArvoreListener arvoreListener;

	public Arvore(TreeModel newModel, ArvoreListener arvoreListener) {
		super(newModel);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		putClientProperty("JTree.lineStyle", "Horizontal");
		Objects.requireNonNull(arvoreListener, "Arvore");
		this.arvoreListener = arvoreListener;
		addMouseListener(new OuvinteArvore());
	}

	private class OuvinteArvore extends MouseAdapter {
		br.com.consulta.Arvore ultimoSelecionado;

		@Override
		public void mouseClicked(MouseEvent e) {
			TreePath path = getSelectionPath();

			if (path == null) {
				return;
			}

			if (path.getLastPathComponent() instanceof br.com.consulta.Arvore) {
				br.com.consulta.Arvore selecionado = (br.com.consulta.Arvore) path.getLastPathComponent();

				if (selecionado instanceof Tabelas) {
					return;
				}

				if (ultimoSelecionado != selecionado) {
					ultimoSelecionado = selecionado;
					arvoreListener.selecionado(selecionado);
				}
			}
		}
	}
}