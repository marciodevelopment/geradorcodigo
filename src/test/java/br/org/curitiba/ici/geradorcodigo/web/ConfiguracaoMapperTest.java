package br.org.curitiba.ici.geradorcodigo.web;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.web.mapper.ConfiguracaoMapper;

class ConfiguracaoMapperTest {

	@Test
	void test() {
		ConfiguracaoMapper configuracao = new ConfiguracaoMapper("Pais", "br.org.curitiba.ici.gtm.pais");
		System.out.println(configuracao.getArquivo());
	}

}
