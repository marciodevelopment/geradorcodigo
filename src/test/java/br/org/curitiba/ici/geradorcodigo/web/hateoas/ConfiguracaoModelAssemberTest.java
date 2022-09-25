package br.org.curitiba.ici.geradorcodigo.web.hateoas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfiguracaoModelAssemberTest {

	@Test
	void test() {
		ConfiguracaoModelAssember configuracao = new ConfiguracaoModelAssember("Pessoa", "br.org.curitiba.ici.gtm.pessoa");
		System.out.println(configuracao.getArquivo());
	}

}
