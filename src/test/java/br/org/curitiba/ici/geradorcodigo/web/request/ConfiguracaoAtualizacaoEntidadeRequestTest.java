package br.org.curitiba.ici.geradorcodigo.web.request;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import br.org.curitiba.ici.geradorcodigo.validacao.ConfiguracaoValidacaoAtributo;

class ConfiguracaoAtualizacaoEntidadeRequestTest {

	@Test
	void getArquivoTest() {
HashSet<ConfiguracaoAtributo> configuracoesAtributo = new HashSet<>();
		
		ConfiguracaoValidacaoAtributo validacaoNotNull = new ConfiguracaoValidacaoAtributo("notnull", "mensagem notnull", null); 
		ConfiguracaoValidacaoAtributo validacaoMax = new ConfiguracaoValidacaoAtributo("max", "mensagem max", "value=10");
		
		ConfiguracaoAtributo confCodPessoa = new ConfiguracaoAtributo(true, "Cod_Usuario", "codUsuario", "Integer");
		confCodPessoa.addValidacao(validacaoNotNull);
		configuracoesAtributo.add(confCodPessoa);
		
		ConfiguracaoAtributo confIdadePessoa = new ConfiguracaoAtributo(false, "Nme_usuario", "nome", "String");
		
		confIdadePessoa.addValidacao(validacaoNotNull);
		confIdadePessoa.addValidacao(validacaoMax);
		configuracoesAtributo.add(confIdadePessoa);
		
		
		ConfiguracaoAtualizacaoEntidadeRequest configEntidadeRequest = new ConfiguracaoAtualizacaoEntidadeRequest("br.org.curitiba.ici.gtm.pais", "Pais", configuracoesAtributo);
		System.out.println(configEntidadeRequest.getCodigoGerado());
		
	}
	
	
	
	@Test
	void getNomePastaTest() {
		ConfiguracaoAtualizacaoEntidadeRequest configuracao = new ConfiguracaoAtualizacaoEntidadeRequest("br.org.curitiba.ici.gtm.pais", "Pais", null);		
		assertEquals("br.org.curitiba.ici.gtm.pais.web.controller.request.AtualizacaoPaisRequest.java", configuracao.getCaminhoPacoteClasse());
	}
}
