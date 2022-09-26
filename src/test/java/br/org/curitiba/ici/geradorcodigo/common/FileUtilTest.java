package br.org.curitiba.ici.geradorcodigo.common;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.leituraarquivo.ArquivoConfiguracao;

class FileUtilTest {
	

	@Test
	void lerArquivoYaml() throws Exception {
		ArquivoConfiguracao conf = FileUtil.lerArquivoYaml("/home/marcio/Documents/development/workspaces/poc-gtm/geradorcodigo/src/main/resources/configuracao.yaml", ArquivoConfiguracao.class);
		assertNotNull(conf.getPacote());
		assertNotNull(conf.getEntidades().iterator().next().getNomeClasse());
		assertNotNull(conf.getEntidades().iterator().next().getAtributos().get(0).getColuna());
		assertNotNull(conf.getEntidades().iterator().next().getAtributos().get(1).getValidadores().get(0).getNome());
	}
	
	
	@Test
	void escreverArquivoJavaSeNaoExistir() {
		FileUtil.escreverArquivoJavaSeNaoExistir("/home/marcio/Desktop/arquivos-gerador", "br.org.curitiba.ici.gtm.pais", "br.org.curitiba.ici.gtm.pais.service.PessoaService.java", "texto");
	}
	
	@Test
	void escreverArquivoJavaSeNaoExistirWeb() {
		FileUtil.escreverArquivoJavaSeNaoExistir("/home/marcio/Desktop/arquivos-gerador", "br.org.curitiba.ici.gtm.pais", "br.org.curitiba.ici.gtm.pais.web.controller.PessoaController.java", "texto");
	}
	
}

