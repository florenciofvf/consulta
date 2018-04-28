package br.com.consulta.modelo;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import br.com.consulta.Arvore;
import br.com.consulta.Tabelas;

public class ModeloArvore implements TreeModel {
	private final EventListenerList listenerList = new EventListenerList();
	private final Tabelas tabelas;

	public ModeloArvore(Tabelas tabelas) {
		this.tabelas = tabelas;
	}

	@Override
	public Object getRoot() {
		return tabelas;
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof Arvore) {
			return ((Arvore) parent).getChild(index);
		}

		throw new IllegalStateException();
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof Arvore) {
			return ((Arvore) parent).getChildCount();
		}

		throw new IllegalStateException();
	}

	@Override
	public boolean isLeaf(Object parent) {
		if (parent instanceof Arvore) {
			return ((Arvore) parent).isLeaf();
		}

		throw new IllegalStateException();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent instanceof Arvore) {
			return ((Arvore) parent).getIndexOfChild(child);
		}

		throw new IllegalStateException();
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(TreeModelListener.class, l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

	public void treeNodesChanged(TreeModelEvent event) {
		Object[] listeners = listenerList.getListenerList();

		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				((TreeModelListener) listeners[i + 1]).treeNodesChanged(event);
			}
		}
	}
}