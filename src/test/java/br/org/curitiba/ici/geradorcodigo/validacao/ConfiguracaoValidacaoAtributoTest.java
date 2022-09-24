package br.org.curitiba.ici.geradorcodigo.validacao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;



class ConfiguracaoValidacaoAtributoTest {

	@Test
	void testGerarConfiguacaoGerarApenasAnotacaoTest() {
		ConfiguracaoValidacaoAtributo configuracao = new ConfiguracaoValidacaoAtributo();
		configuracao.setNomeValidacao("notnull");
		String expected = "@NotNull()";
		assertEquals(expected, configuracao.gerarConfiguacao().getLinhasCodigoGerado().iterator().next());
	}
	
	@Test
	void testGerarConfiguacaoGerarAnotacaoComAnotacaoEMensagemTest() {
		ConfiguracaoValidacaoAtributo configuracao = new ConfiguracaoValidacaoAtributo();
		configuracao.setNomeValidacao("notnull");
		configuracao.setMensagem("mensagem");
		String expected = "@NotNull(message=\"mensagem\")";
		assertEquals(expected, configuracao.gerarConfiguacao().getLinhasCodigoGerado().iterator().next());
	}
	
	@Test
	void testGerarConfiguacaoGerarAnotacaoComAnotacaoMensagemEComplementoTest() {
		ConfiguracaoValidacaoAtributo configuracao = new ConfiguracaoValidacaoAtributo();
		configuracao.setNomeValidacao("max");
		configuracao.setMensagem("mensagem");
		configuracao.setComplemento("value=9999");
		String expected = "@Max(message=\"mensagem\", value=9999)";
		assertEquals(expected, configuracao.gerarConfiguacao().getLinhasCodigoGerado().iterator().next());
	}
	
	@Test
	void testGerarConfiguacaoGerarAnotacaoApenasComComplementoTest() {
		ConfiguracaoValidacaoAtributo configuracao = new ConfiguracaoValidacaoAtributo();
		configuracao.setNomeValidacao("max");
		configuracao.setComplemento("value=9999");
		String expected = "@Max(value=9999)";
		assertEquals(expected, configuracao.gerarConfiguacao().getLinhasCodigoGerado().iterator().next());
	}
	
	@Test
	void testGerarConfiguacaoGerarAnotacaoComImportTest() {
		ConfiguracaoValidacaoAtributo configuracao = new ConfiguracaoValidacaoAtributo();
		configuracao.setNomeValidacao("max");
		configuracao.setComplemento("value=9999");
		String expected = "import javax.validation.constraints.Max";
		assertEquals(expected, configuracao.gerarConfiguacao().getImportsGerado().iterator().next());
	}
	
	

}
