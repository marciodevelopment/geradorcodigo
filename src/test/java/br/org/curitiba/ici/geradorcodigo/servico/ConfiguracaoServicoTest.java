package br.org.curitiba.ici.geradorcodigo.servico;

import org.junit.jupiter.api.Test;

class ConfiguracaoServicoTest {

	@Test
	void test() {
		ConfiguracaoServico configuracao = new ConfiguracaoServico("Pessoa", "br.com.ici.pessoa", "codPessoa");
		System.out.println(configuracao.getArquivo());
	}

}
