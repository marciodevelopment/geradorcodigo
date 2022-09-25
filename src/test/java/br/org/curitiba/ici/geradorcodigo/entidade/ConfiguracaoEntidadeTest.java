package br.org.curitiba.ici.geradorcodigo.entidade;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.validacao.ConfiguracaoValidacaoAtributo;

class ConfiguracaoEntidadeTest {

	private String codigoFinalArquivo = "package br.com.ici.pessoa.entity; \n"
			+ "\n"
			+ "import javax.persistence.Entity;\n"
			+ "import javax.persistence.Column;\n"
			+ "import javax.persistence.Table;\n"
			+ "import lombok.Data;\n"
			+ "import javax.validation.constraints.Max;\n"
			+ "import javax.validation.constraints.NotNull;\n"
			+ "import javax.persistence.Id;\n"
			+ "\n"
			+ "@Data\n"
			+ "@Entity\n"
			+ "@Table(name = \"pessoa_tb\")\n"
			+ "public class PessoaEntity {\n"
			+ "\n"
			+ "	@NotNull(message=\"mensagem notnull\")\n"
			+ "	@Id\n"
			+ "	@Column(name = \"cod_pessoa\")\n"
			+ "	private Integer codPessoa;\n"
			+ "\n"
			+ "	@NotNull(message=\"mensagem notnull\")\n"
			+ "	@Max(message=\"mensagem max\", value=10)\n"
			+ "	@Column(name = \"vl_idade\")\n"
			+ "	private Integer vlIdade;\n"
			+ "\n"
			+ "}\n"
			+ "";
	
	//@Test
	void getArquivoTest() {
		HashSet<ConfiguracaoAtributo> configuracoesAtributo = new HashSet<>();
		
		ConfiguracaoValidacaoAtributo validacaoNotNull = new ConfiguracaoValidacaoAtributo();
		validacaoNotNull.setMensagem("mensagem notnull");
		validacaoNotNull.setNomeValidacao("notnull");
		
		ConfiguracaoValidacaoAtributo validacaoMax = new ConfiguracaoValidacaoAtributo();
		validacaoMax.setMensagem("mensagem max");
		validacaoMax.setNomeValidacao("max");
		validacaoMax.setComplemento("value=10");
		
		
		ConfiguracaoAtributo confCodPessoa = new ConfiguracaoAtributo();
		confCodPessoa.setId(true);
		confCodPessoa.setTipoAtributo("Integer");
		confCodPessoa.setNomeAtributo("codPessoa");
		confCodPessoa.setNomeColunaBanco("cod_pessoa");
		confCodPessoa.addValidacao(validacaoNotNull);
		configuracoesAtributo.add(confCodPessoa);
		
		ConfiguracaoAtributo confIdadePessoa = new ConfiguracaoAtributo();
		confIdadePessoa.setTipoAtributo("Integer");
		confIdadePessoa.setNomeAtributo("vlIdade");
		confIdadePessoa.setNomeColunaBanco("vl_idade");
		confIdadePessoa.addValidacao(validacaoNotNull);
		confIdadePessoa.addValidacao(validacaoMax);
		configuracoesAtributo.add(confIdadePessoa);
		
		ConfiguracaoEntidade configEntidade = new ConfiguracaoEntidade("pessoa_tb", "br.com.ici.pessoa", "Pessoa", configuracoesAtributo);
		System.out.println(configEntidade.getArquivo());
		assertEquals(codigoFinalArquivo, configEntidade.getArquivo());
	}
	
	@Test
	void getArquivoTest2() {
		HashSet<ConfiguracaoAtributo> configuracoesAtributo = new HashSet<>();
		
		
		ConfiguracaoAtributo confCodPessoa = new ConfiguracaoAtributo();
		confCodPessoa.setId(true);
		confCodPessoa.setTipoAtributo("Integer");
		confCodPessoa.setNomeAtributo("codPais");
		confCodPessoa.setNomeColunaBanco("Cod_Pais");
		configuracoesAtributo.add(confCodPessoa);
		
		
		ConfiguracaoValidacaoAtributo validacaoSize = new ConfiguracaoValidacaoAtributo();
		validacaoSize.setMensagem("Nome Pessoa");
		validacaoSize.setNomeValidacao("size");
		validacaoSize.setComplemento("max=10");
		
		ConfiguracaoValidacaoAtributo validacaoNotEmpty = new ConfiguracaoValidacaoAtributo();
		validacaoNotEmpty.setMensagem("Nome Pessoa");
		validacaoNotEmpty.setNomeValidacao("notempty");
		
		ConfiguracaoAtributo confIdadePessoa = new ConfiguracaoAtributo();
		confIdadePessoa.setTipoAtributo("String");
		confIdadePessoa.setNomeColunaBanco("Nme_Pais");
		confIdadePessoa.setNomeAtributo("nomePais");
		confIdadePessoa.addValidacao(validacaoNotEmpty);
		confIdadePessoa.addValidacao(validacaoSize);
		configuracoesAtributo.add(confIdadePessoa);
		
		ConfiguracaoEntidade configEntidade = new ConfiguracaoEntidade("GTMPAIPais", "br.org.curitiba.ici.gtm.pais", "Pais", configuracoesAtributo);
		
	}

}
