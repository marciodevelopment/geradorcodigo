package br.org.curitiba.ici.geradorcodigo.entidade;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.validacao.ConfiguracaoValidacaoAtributo;

class ConfiguracaoEntidadeTest {

	private String codigoTeste = "package br.org.curitiba.ici.gtm.usuario.entity;\n"
			+ "\n"
			+ "import javax.persistence.Entity;\n"
			+ "import javax.persistence.GenerationType;\n"
			+ "import javax.persistence.Column;\n"
			+ "import javax.persistence.Table;\n"
			+ "import javax.persistence.GeneratedValue;\n"
			+ "import lombok.Data;\n"
			+ "import javax.validation.constraints.Max;\n"
			+ "import javax.validation.constraints.NotNull;\n"
			+ "import javax.persistence.Id;\n"
			+ "\n"
			+ "@Data\n"
			+ "@Entity\n"
			+ "@Table(name = \"usuario_tb\")\n"
			+ "public class UsuarioEntity {\n"
			+ "\n"
			+ "	@NotNull(message=\"mensagem notnull\")\n"
			+ "	@Id\n"
			+ "	@GeneratedValue(strategy = GenerationType.IDENTITY)\n"
			+ "	@Column(name = \"Cod_Usuario\")\n"
			+ "	private Integer codUsuario;\n"
			+ "\n"
			+ "	@NotNull(message=\"mensagem notnull\")\n"
			+ "	@Max(message=\"mensagem max\", value=10)\n"
			+ "	@Column(name = \"nome\")\n"
			+ "	private String Nme_usuario;\n"
			+ "\n"
			+ "}";
	
	@Test
	void getArquivoTest() {
		HashSet<ConfiguracaoAtributo> configuracoesAtributo = new HashSet<>();
		
		ConfiguracaoValidacaoAtributo validacaoNotNull = new ConfiguracaoValidacaoAtributo("notnull", "mensagem notnull", null); 
		ConfiguracaoValidacaoAtributo validacaoMax = new ConfiguracaoValidacaoAtributo("max", "mensagem max", "value=10");
		
		ConfiguracaoAtributo confCodPessoa = new ConfiguracaoAtributo(true, "Cod_Usuario", "codUsuario", "Integer");
		confCodPessoa.addValidacao(validacaoNotNull);
		configuracoesAtributo.add(confCodPessoa);
		
		ConfiguracaoAtributo confIdadePessoa = new ConfiguracaoAtributo(false, "nome", "Nme_usuario", "String");
		
		confIdadePessoa.addValidacao(validacaoNotNull);
		confIdadePessoa.addValidacao(validacaoMax);
		configuracoesAtributo.add(confIdadePessoa);
		
		
		ConfiguracaoEntidade configEntidade = new ConfiguracaoEntidade("usuario_tb", "br.org.curitiba.ici.gtm.usuario", "Usuario", configuracoesAtributo);
		
		String codigoGerado = configEntidade.getCodigoGerado().replaceAll("[^a-zA-Z0-9]", "");
		String expected =  codigoTeste.replaceAll("[^a-zA-Z0-9]", "");
		assertEquals(expected, codigoGerado);
	}

	@Test
	void getCaminhoPacoteClasseTest() {
		ConfiguracaoEntidade configEntidade = new ConfiguracaoEntidade("GTMPAIPais", "br.org.curitiba.ici.gtm.pais", "Pais", null);
		System.out.println(configEntidade.getCaminhoPacoteClasse());
		assertEquals("br.org.curitiba.ici.gtm.pais.entity.PaisEntity.java" , configEntidade.getCaminhoPacoteClasse());
	}
	

}
