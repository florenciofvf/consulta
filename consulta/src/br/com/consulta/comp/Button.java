package br.com.consulta.comp;

import javax.swing.Icon;
import javax.swing.JButton;

import br.com.consulta.util.Mensagens;

public class Button extends JButton {
	private static final long serialVersionUID = 1L;

	public Button(String chaveRotulo, Icon icon) {
		super(icon);
		setToolTipText(Mensagens.getString(chaveRotulo));
	}
}