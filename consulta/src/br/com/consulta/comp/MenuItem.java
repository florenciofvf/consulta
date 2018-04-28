package br.com.consulta.comp;

import javax.swing.Icon;
import javax.swing.JMenuItem;

import br.com.consulta.util.Mensagens;

public class MenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;

	public MenuItem(String chaveRotulo) {
		super(Mensagens.getString(chaveRotulo));
	}

	public MenuItem(String chaveRotulo, Icon icon) {
		super(Mensagens.getString(chaveRotulo), icon);
	}
}