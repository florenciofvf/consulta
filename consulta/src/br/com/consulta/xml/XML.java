package br.com.consulta.xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.consulta.Arvore;
import br.com.consulta.Consulta;
import br.com.consulta.Tabela;
import br.com.consulta.Tabelas;
import br.com.consulta.util.Constantes;
import br.com.consulta.util.Util;

public class XML {

	public static Tabelas processar(File file) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		Tabelas tabelas = null;

		try {
			XMLHandler handler = new XMLHandler();
			parser.parse(file, handler);
			tabelas = (Tabelas) handler.getAnterior();
			tabelas.ordenar();
		} catch (Exception ex) {
			String msg = Util.getStackTrace("XML.processar()", ex);
			Util.mensagem(null, msg);
			System.exit(0);
		}

		return tabelas;
	}

}

class XMLHandler extends DefaultHandler {
	private final StringBuilder sb = new StringBuilder();
	private Arvore selecionado;
	private Arvore anterior;

	public Arvore getAnterior() {
		return anterior;
	}

	private void limpar() {
		if (sb.length() > 0) {
			sb.delete(0, sb.length());
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (Constantes.TABELAS.equals(qName)) {
			selecionado = new Tabelas();
			limpar();

		} else if (Constantes.TABELA.equals(qName)) {
			String destaque = attributes.getValue(Constantes.DESTAQUE);
			String icone = attributes.getValue(Constantes.ICONE);
			String nome = attributes.getValue(Constantes.NOME);

			Tabela tabela = new Tabela(nome);
			tabela.setDestaque(Boolean.parseBoolean(destaque));
			tabela.setIcone(icone);

			if (!(selecionado instanceof Tabelas)) {
				throw new IllegalStateException("O ELEMENTO " + Constantes.TABELA + " DEVE ESTAR DENTRO DO ELEMENTO "
						+ Constantes.TABELAS + ".");
			}

			((Tabelas) selecionado).add(tabela);
			selecionado = tabela;
			limpar();

		} else if (Constantes.CONSULTA.equals(qName)) {
			String resumo = attributes.getValue(Constantes.RESUMO);
			String icone = attributes.getValue(Constantes.ICONE);
			Consulta consulta = new Consulta(resumo);
			consulta.setIcone(icone);

			if (selecionado instanceof Tabela) {
				((Tabela) selecionado).add(consulta);
				selecionado = consulta;

			} else if (selecionado instanceof Consulta) {
				((Consulta) selecionado).add(consulta);
				selecionado = consulta;

			} else {
				throw new IllegalStateException("O ELEMENTO " + Constantes.CONSULTA + " DEVE ESTAR DENTRO DO ELEMENTO "
						+ Constantes.TABELA + " OU O ELEMENTO " + Constantes.CONSULTA + ".");
			}

			limpar();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (selecionado == null) {
			throw new IllegalStateException("ELEMENTO FORA DA HIERARQUIA: " + qName);
		}

		anterior = selecionado;
		selecionado = selecionado.getParent();

		if (selecionado != null) {
			String string = sb.toString().trim();

			if (string.length() > 0) {
				selecionado.setString(string);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		sb.append(new String(ch, start, length));
	}
}
