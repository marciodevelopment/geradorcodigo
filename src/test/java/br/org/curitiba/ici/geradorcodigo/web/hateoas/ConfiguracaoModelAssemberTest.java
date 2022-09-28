package br.org.curitiba.ici.geradorcodigo.web.hateoas;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ConfiguracaoModelAssemberTest {

	@Test
	void test() {
		ConfiguracaoModelAssember configuracao = new ConfiguracaoModelAssember("Pais", "br.org.curitiba.ici.gtm.pais");
		System.out.println(configuracao.getCodigoGerado());
	}

	@Test
	void getNomePastaTest() {
		ConfiguracaoModelAssember configuracao = new ConfiguracaoModelAssember("Pais", "br.org.curitiba.ici.gtm.pais");		
		assertEquals("br.org.curitiba.ici.gtm.pais.web.controller.hateoas.PaisModelAssembler.java", configuracao.getCaminhoPacoteClasse());
	}
	
}
