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
		
		ConfiguracaoValidacaoAtributo validacaoNotNull = new ConfiguracaoValidacaoAtributo("notnull", "mensagem notnull", null); 
		
		ConfiguracaoValidacaoAtributo validacaoMax = new ConfiguracaoValidacaoAtributo("max", "mensagem max", "value=10");
		
		ConfiguracaoAtributo confCodPessoa = new ConfiguracaoAtributo(true, "cod_pessoa", "codPessoa", "Integer");
		confCodPessoa.addValidacao(validacaoNotNull);
		configuracoesAtributo.add(confCodPessoa);
		
		ConfiguracaoAtributo confIdadePessoa = new ConfiguracaoAtributo(false, "vl_idade", "vlIdade", "Integer");
		
		confIdadePessoa.addValidacao(validacaoNotNull);
		confIdadePessoa.addValidacao(validacaoMax);
		configuracoesAtributo.add(confIdadePessoa);
		
		ConfiguracaoEntidade configEntidade = new ConfiguracaoEntidade("pessoa_tb", "br.com.ici.pessoa", "Pessoa", configuracoesAtributo);
		System.out.println(configEntidade.getArquivo());
		assertEquals(codigoFinalArquivo, configEntidade.getArquivo());
	}
	
	//@Test
	void getArquivoTest2() {
		HashSet<ConfiguracaoAtributo> configuracoesAtributo = new HashSet<>();
		
		
		ConfiguracaoAtributo confCodPessoa = new ConfiguracaoAtributo(true, "Cod_Pais", "codPais", "Integer");
		configuracoesAtributo.add(confCodPessoa);
		
		
		ConfiguracaoValidacaoAtributo validacaoSize = new ConfiguracaoValidacaoAtributo("size", "Nome Pessoa", "max=10");
		
		ConfiguracaoValidacaoAtributo validacaoNotEmpty = new ConfiguracaoValidacaoAtributo("notempty", "Nome Pessoa", null);
		
		ConfiguracaoAtributo confIdadePessoa = new ConfiguracaoAtributo(false, "Nme_Pais", "nomePais", "String");
		confIdadePessoa.addValidacao(validacaoNotEmpty);
		confIdadePessoa.addValidacao(validacaoSize);
		configuracoesAtributo.add(confIdadePessoa);
		ConfiguracaoEntidade configEntidade = new ConfiguracaoEntidade("GTMPAIPais", "br.org.curitiba.ici.gtm.pais", "Pais", configuracoesAtributo);
		
	}

	@Test
	void getPastaTest() {
		ConfiguracaoEntidade configEntidade = new ConfiguracaoEntidade("GTMPAIPais", "br.org.curitiba.ici.gtm.pais", "Pais", null);
		assertEquals("br.org.curitiba.ici.gtm.pais.entity.PaisEntity.java" , configEntidade.getPasta());
	}
	

}
