package br.com.consulta.comp;

import java.awt.Color;

import javax.swing.JLabel;

import br.com.consulta.util.Mensagens;

public class Label extends JLabel {
	private static final long serialVersionUID = 1L;

	public Label() {
	}

	public Label(Color corFonte) {
		super();
		setForeground(corFonte);
	}

	public Label(String chaveRotulo) {
		super(Mensagens.getString(chaveRotulo));
	}

	public Label(String chaveRotulo, Color corFonte) {
		super(Mensagens.getString(chaveRotulo));
		setForeground(corFonte);
	}
}