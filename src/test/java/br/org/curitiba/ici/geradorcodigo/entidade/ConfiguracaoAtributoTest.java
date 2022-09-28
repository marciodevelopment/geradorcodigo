package br.org.curitiba.ici.geradorcodigo.entidade;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.validacao.ConfiguracaoValidacaoAtributo;

class ConfiguracaoAtributoTest {

	@Test
	void gerarConfiguacaoParaIdTrueTest() {
		String expectedCodigo0 = "@Id";
		String expectedCodigo1 = "@GeneratedValue(strategy = GenerationType.IDENTITY)";
		String expectedCodigo2 = "@Column(name = \"cod_banco\")";
		String expectedCodigo3 = "private Integer codBanco";
		
		String expectedImport0 = "javax.persistence.Id";
		String expectedImport1 = "javax.persistence.Column";
		String expectedImport2 = "javax.persistence.GenerationType";
		
		ConfiguracaoAtributo configuracaoAtributo = new ConfiguracaoAtributo(true, "cod_banco", "codBanco", "Integer");
		
		assertEquals(expectedCodigo0, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(0));
		assertEquals(expectedCodigo1, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(1));
		assertEquals(expectedCodigo2, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(2));
		assertEquals(expectedCodigo3, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(3));
		
		assertEquals(1, configuracaoAtributo.gerarConfiguacao().getImportsGerado().stream().filter(codigo -> codigo.equals(expectedImport0)).count());
		assertEquals(1, configuracaoAtributo.gerarConfiguacao().getImportsGerado().stream().filter(codigo -> codigo.equals(expectedImport1)).count());
		assertEquals(1, configuracaoAtributo.gerarConfiguacao().getImportsGerado().stream().filter(codigo -> codigo.equals(expectedImport2)).count());
		
	}
	
	@Test
	void gerarConfiguacaoParaIdFalseTest() {
		String expectedImport0 = "javax.persistence.Column";
		
		String expectedCodigo0 = "@Column(name = \"nome_banco\")";
		String expectedCodigo1 = "private String nomeBanco";
		
		
		ConfiguracaoAtributo configuracaoAtributo = new ConfiguracaoAtributo(false, "nome_banco", "nomeBanco", "String");
		assertEquals(expectedCodigo0, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(0));
		assertEquals(expectedCodigo1, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(1));
		
		assertEquals(1, configuracaoAtributo.gerarConfiguacao().getImportsGerado().stream().filter(codigo -> codigo.equals(expectedImport0)).count());
	}
	
	
	@Test
	void gerarConfiguacaoParaIdFalseComValidacoesTest() {
		
		ConfiguracaoAtributo configuracaoAtributo = new ConfiguracaoAtributo(false, "cod_banco", "codBanco", "Integer");
		ConfiguracaoValidacaoAtributo validacaoNotNull = new ConfiguracaoValidacaoAtributo("notnull", "naonulo", null);
		
		
		ConfiguracaoValidacaoAtributo validacaoMax = new ConfiguracaoValidacaoAtributo("max", "maxvalue", "value=10");
		
		configuracaoAtributo
		.addValidacao(validacaoNotNull)
		.addValidacao(validacaoMax);
		
		String expectedValidacaoNotNull = "@NotNull(message=\"naonulo\")";
		String expectedValidacaoMax = "@Max(message=\"maxvalue\", value=10)";
		String expectedCodigoColum = "@Column(name = \"cod_banco\")";
		String expectedCodigoAtributo = "private Integer codBanco";
		
		assertEquals(expectedValidacaoNotNull, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(0));
		assertEquals(expectedValidacaoMax, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(1));
		assertEquals(expectedCodigoColum, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(2));
		assertEquals(expectedCodigoAtributo, configuracaoAtributo.gerarConfiguacao().getLinhasCodigoGerado().get(3));
		
		
		String expectedImportColumn = "javax.persistence.Column";
		String expectedImportNotNull = "javax.validation.constraints.NotNull";
		String expectedImportMax = "javax.validation.constraints.Max";
		
		assertEquals(1, configuracaoAtributo.gerarConfiguacao().getImportsGerado().stream().filter(codigo -> codigo.equals(expectedImportColumn)).count());
		assertEquals(1, configuracaoAtributo.gerarConfiguacao().getImportsGerado().stream().filter(codigo -> codigo.equals(expectedImportNotNull)).count());
		assertEquals(1, configuracaoAtributo.gerarConfiguacao().getImportsGerado().stream().filter(codigo -> codigo.equals(expectedImportMax)).count());
		
		
	}
	

}


