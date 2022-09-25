package br.org.curitiba.ici.geradorcodigo.repositorio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfiguracaoRepositorioTest {

	private String codigoRepositorio = "package br.com.ici.pessoa.repository;\n"
			+ "\n"
			+ "import org.springframework.data.jpa.repository.JpaRepository;\n"
			+ "import br.com.ici.pessoa.entity.PessoaEntity;\n"
			+ "\n"
			+ "public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {\n"
			+ "\n"
			+ "}\n"
			+ "";

	@Test
	void getArquivoTest() {
		ConfiguracaoRepositorio configuracao = new ConfiguracaoRepositorio("br.com.ici.pessoa", "Pessoa");
		System.out.println(configuracao.getArquivo());
		
		assertEquals(codigoRepositorio, configuracao.getArquivo());
	}
	
	
	@Test
	void getArquivoTest2() {
		ConfiguracaoRepositorio configuracao = new ConfiguracaoRepositorio("br.org.curitiba.ici.gtm.pais", "Pais");
		System.out.println(configuracao.getArquivo());
		
		assertEquals(codigoRepositorio, configuracao.getArquivo());
	}

}
