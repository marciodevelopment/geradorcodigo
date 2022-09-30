package br.org.curitiba.ici.geradorcodigo.web.hateoas;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ConfiguracaoPesquisaModelAssemberTest {

	@Test
	void test() {
		ConfiguracaoPesquisaModelAssember configuracao = new ConfiguracaoPesquisaModelAssember("Pais", "br.org.curitiba.ici.gtm.pais", "codPais");
		System.out.println(configuracao.getCodigoGerado());
	}
	
	@Test
	void getNomePastaTest() {
		ConfiguracaoPesquisaModelAssember configuracao = new ConfiguracaoPesquisaModelAssember("Pais", "br.org.curitiba.ici.gtm.pais", "codPais");		
		assertEquals("br.org.curitiba.ici.gtm.pais.web.controller.hateoas.PaisPesquisaModelAssembler.java", configuracao.getCaminhoPacoteClasse());
	}

}
