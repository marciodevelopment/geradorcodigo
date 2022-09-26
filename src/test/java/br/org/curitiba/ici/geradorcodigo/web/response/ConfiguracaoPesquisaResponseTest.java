package br.org.curitiba.ici.geradorcodigo.web.response;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ConfiguracaoPesquisaResponseTest {

	/*
	@Test
	void getArquivoTest() {
		HashSet<ConfiguracaoAtributo> configuracoesAtributo = new HashSet<>();
		
		ConfiguracaoValidacaoAtributo validacaoNotNull = new ConfiguracaoValidacaoAtributo();
		validacaoNotNull.setMensagem("mensagem notnull");
		validacaoNotNull.setNomeValidacao("notnull");
		
		ConfiguracaoValidacaoAtributo validacaoMax = new ConfiguracaoValidacaoAtributo();
		validacaoMax.setMensagem("mensagem max");
		validacaoMax.setNomeValidacao("max");
		validacaoMax.setComplemento("value=10");
		
		
		ConfiguracaoAtributo coonfCodPais = new ConfiguracaoAtributo();
		coonfCodPais.setId(true);
		coonfCodPais.setTipoAtributo("Integer");
		coonfCodPais.setNomeAtributo("codPais");
		coonfCodPais.setNomeColunaBanco("cod_pais");
		coonfCodPais.addValidacao(validacaoNotNull);
		configuracoesAtributo.add(coonfCodPais);
		
		ConfiguracaoAtributo confNomePais = new ConfiguracaoAtributo();
		confNomePais.setTipoAtributo("String");
		confNomePais.setNomeAtributo("nomePais");
		confNomePais.setNomeColunaBanco("nm_pais");
		confNomePais.addValidacao(validacaoNotNull);
		confNomePais.addValidacao(validacaoMax);
		configuracoesAtributo.add(confNomePais);
		
		ConfiguracaoAtributo confSiglaPais = new ConfiguracaoAtributo();
		confSiglaPais.setTipoAtributo("String");
		confSiglaPais.setNomeAtributo("siglaPais");
		confSiglaPais.setNomeColunaBanco("siglaPais");
		confSiglaPais.addValidacao(validacaoNotNull);
		confSiglaPais.addValidacao(validacaoMax);
		configuracoesAtributo.add(confSiglaPais);
		
		
		ConfiguracaoPesquisaResponse configEntidadeResponse = new ConfiguracaoPesquisaResponse("br.org.curitiba.ici.gtm.pais", "Pais", configuracoesAtributo);
		System.out.println(configEntidadeResponse.getArquivo());
		
	}
	*/
	
	@Test
	void getNomePastaTest() {
		ConfiguracaoPesquisaResponse configEntidadeRequest = new ConfiguracaoPesquisaResponse("br.org.curitiba.ici.gtm.pais", "Pais", null);		
		assertEquals("br.org.curitiba.ici.gtm.pais.web.controller.response.PaisPesquisaResponse.java", configEntidadeRequest.getPasta());
	}
	
}
