package br.com.consulta.main;

import java.awt.Window;
import java.io.File;
import java.lang.reflect.Method;

import javax.swing.UIManager;

import br.com.consulta.util.Util;
import br.com.consulta.view.Formulario;

public class Main {
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		File file = new File("projeto_atual.xml");

		if (file.exists() && file.canRead()) {
			Formulario formulario = new Formulario(file);

			if (System.getProperty("os.name").startsWith("Mac OS")) {
				try {
					Class<?> classe = Class.forName("com.apple.eawt.FullScreenUtilities");
					Method method = classe.getMethod("setWindowCanFullScreen", Window.class, Boolean.TYPE);
					method.invoke(classe, formulario, true);
				} catch (Exception e) {
					String msg = Util.getStackTrace("setWindowCanFullScreen()", e);
					Util.mensagem(null, msg);
				}
			}

			formulario.setVisible(true);

		} else if (!file.exists()) {
			Util.mensagem(null, "Arquivo inexistente!\r\n\r\n" + file.getAbsolutePath());

		} else if (!file.canRead()) {
			Util.mensagem(null, "O arquivo nao pode ser lido!\r\n\r\n" + file.getAbsolutePath());

		} else {
			Util.mensagem(null, "Erro!");
		}
	}
}