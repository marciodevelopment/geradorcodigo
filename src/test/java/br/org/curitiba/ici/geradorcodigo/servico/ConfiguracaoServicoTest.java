package br.org.curitiba.ici.geradorcodigo.servico;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ConfiguracaoServicoTest {

	@Test
	void test() {
		List<String> atributosPesquisaInit = new ArrayList<String>();
		atributosPesquisaInit.add("nomePais");
		atributosPesquisaInit.add("siglaPais");
		
		ConfiguracaoServico configuracao = new ConfiguracaoServico("Pais", "br.org.curitiba.ici.gtm.pais", "codPais", atributosPesquisaInit);
		System.out.println(configuracao.getArquivo());
	}

}
