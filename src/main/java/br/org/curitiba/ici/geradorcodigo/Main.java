package br.org.curitiba.ici.geradorcodigo;

import br.org.curitiba.ici.geradorcodigo.common.FileUtil;
import br.org.curitiba.ici.geradorcodigo.leituraarquivo.ArquivoConfiguracao;
import br.org.curitiba.ici.geradorcodigo.leituraarquivo.ProcessadorConfiguracao;

public class Main {
	public static void main(String[] args) throws Exception {
		ArquivoConfiguracao conf = FileUtil.lerArquivoYaml("/home/marcio/Documents/development/workspaces/poc-gtm/geradorcodigo/src/main/resources/configuracao.yaml", ArquivoConfiguracao.class);
		new ProcessadorConfiguracao(conf).processar();
		System.out.println("finalizado");
	}
}
