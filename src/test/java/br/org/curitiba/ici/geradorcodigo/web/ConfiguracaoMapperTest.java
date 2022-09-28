package br.org.curitiba.ici.geradorcodigo.web;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.web.mapper.ConfiguracaoMapper;

class ConfiguracaoMapperTest {

	@Test
	void test() {
		ConfiguracaoMapper configuracao = new ConfiguracaoMapper("Pais", "br.org.curitiba.ici.gtm.pais");
		System.out.println(configuracao.getCodigoGerado());
	}
	
	@Test
	void getNomePastaTest() {
		ConfiguracaoMapper configuracao = new ConfiguracaoMapper("Pais", "br.org.curitiba.ici.gtm.pais");		
		assertEquals("br.org.curitiba.ici.gtm.pais.web.controller.mapstruct.PaisMapper.java", configuracao.getCaminhoPacoteClasse());
	}

}
