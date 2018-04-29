package br.com.consulta.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;

import br.com.consulta.Tabelas;
import br.com.consulta.banco.Conexao;
import br.com.consulta.banco.MetaDados;
import br.com.consulta.comp.SplitPane;
import br.com.consulta.util.Constantes;
import br.com.consulta.util.Util;
import br.com.consulta.xml.XML;

public class Formulario extends JFrame {
	private static final long serialVersionUID = 1L;
	private final FicharioRegistros ficharioRegistros;
	private final FicharioLateral ficharioLateral;

	public Formulario(File file) throws Exception {
		super(Constantes.CONSULTA);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Tabelas tabelas = XML.processar(file);
		ficharioLateral = new FicharioLateral();
		ficharioRegistros = new FicharioRegistros();
		setSize(500, 500);
		montarLayout();
		eventos();
	}

	private void eventos() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					Conexao.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			};
		});
	}

	private void montarLayout() {
		setLayout(new BorderLayout());

		SplitPane splitPane = new SplitPane();
		splitPane.setLeftComponent(ficharioLateral);
		splitPane.setRightComponent(ficharioRegistros);

		add(BorderLayout.CENTER, splitPane);
	}

	public void atualizarMetaDados() {
//		try {
//			MetaDados.atualizarMetaDados(tabelas, progresso);
//		} catch (Exception ex) {
//			String msg = Util.getStackTrace(getClass().getName() + ".atualizarTotalRegistros()", ex);
//			Util.mensagem(this, msg);
//		}
	}
}