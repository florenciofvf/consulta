package br.com.consulta.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.consulta.Tabela;
import br.com.consulta.Tabelas;
import br.com.consulta.view.ProgressoDialog;

public class MetaDados {

	public static void atualizarMetaDados(Tabelas tabelas, ProgressoDialog progresso) throws Exception {
		Connection conn = Conexao.getConnection();
		progresso.exibir(tabelas.getChildCount());

		for (int i = 0; i < tabelas.getChildCount(); i++) {
			Tabela tabela = tabelas.getTabela(i);

			PreparedStatement psmt = conn.prepareStatement(tabela.getConsultaCount());
			ResultSet rs = psmt.executeQuery();
			rs.next();

			tabela.setRegistros(rs.getInt(1));
			progresso.atualizar(++i);
			rs.close();
			psmt.close();
		}

		progresso.esconder();
	}

}