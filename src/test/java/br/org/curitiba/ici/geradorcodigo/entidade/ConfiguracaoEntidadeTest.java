package br.org.curitiba.ici.geradorcodigo.entidade;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.validacao.ConfiguracaoValidacaoAtributo;

class ConfiguracaoEntidadeTest {

	private String codigoFinalArquivo = "package br.com.ici.pessoa \n"
			+ "\n"
			+ "import javax.persistence.Entity;\n"
			+ "import javax.persistence.Column;\n"
			+ "import javax.persistence.Table;\n"
			+ "import lombok.Getter;\n"
			+ "import javax.validation.constraints.Max;\n"
			+ "import javax.validation.constraints.NotNull;\n"
			+ "import javax.persistence.Id;\n"
			+ "\n"
			+ "@Getter\n"
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
			+ "}";
	
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
		assertEquals(codigoFinalArquivo, configEntidade.getArquivo());
	}

}
