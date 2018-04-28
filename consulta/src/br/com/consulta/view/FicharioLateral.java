package br.com.consulta.view;

import br.com.consulta.Tabelas;
import br.com.consulta.banco.MetaDados;
import br.com.consulta.comp.Arvore;
import br.com.consulta.comp.PanelBorderLayout;
import br.com.consulta.comp.ScrollPane;
import br.com.consulta.comp.TabbedPane;
import br.com.consulta.modelo.ModeloArvore;
import br.com.consulta.util.Util;

public class FicharioLateral extends TabbedPane {
	private static final long serialVersionUID = 1L;
	private final ProgressoDialog progresso;
	private final Tabelas tabelas;

	public FicharioLateral(Tabelas tabelas) {
		progresso = new ProgressoDialog();
		this.tabelas = tabelas;

		// fichario.addTab("label.destaques", painelDestaques);
		// fichario.addTab("label.tab_com_registros", painelComRegistros);
		// fichario.addTab("label.tab_sem_registros", painelSemRegistros);
		addTab("label.tabelas", new PainelArvore(tabelas));
		// fichario.addTab("label.referencias", painelConsultas);
		// fichario.addTab("label.config", new ScrollPane(tableConfig));
		// fichario.addTab("label.mensagens", new ScrollPane(tableMsg));
	}

	public void atualizarMetaDados() {
		try {
			MetaDados.atualizarMetaDados(tabelas, progresso);
		} catch (Exception ex) {
			String msg = Util.getStackTrace(getClass().getName() + ".atualizarTotalRegistros()", ex);
			Util.mensagem(this, msg);
		}
	}

	class PainelArvore extends PanelBorderLayout {
		private static final long serialVersionUID = 1L;

		PainelArvore(Tabelas tabelas) {
			ModeloArvore modelo = new ModeloArvore(tabelas);
			Arvore arvore = new Arvore(modelo, null);
			add(new ScrollPane(arvore));
		}
	}
}