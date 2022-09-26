package br.org.curitiba.ici.geradorcodigo.web.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ConfiguracaoControllerTest {

	@Test
	void test() {
		ConfiguracaoController configuracao = new ConfiguracaoController("Pais", "br.org.curitiba.ici.gtm.pais", "codPais");
		System.out.println(configuracao.getArquivo());
	}

	@Test
	void getPastaTest() {
		ConfiguracaoController configuracao = new ConfiguracaoController("Pais", "br.org.curitiba.ici.gtm.pais", "codPais");
		assertEquals("br.org.curitiba.ici.gtm.pais.web.controller.PaisController.java", configuracao.getPasta());
	}
	
}
