package br.com.consulta;

public interface Arvore {
	public int getIndexOfChild(Object child);

	public Object getChild(int index);

	public void setString(String s);

	public int getChildCount();

	public Arvore getParent();

	public boolean isLeaf();
}