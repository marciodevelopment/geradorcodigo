package br.org.curitiba.ici.geradorcodigo.leituraarquivo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.FileUtil;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoEntidade;
import br.org.curitiba.ici.geradorcodigo.repositorio.ConfiguracaoRepositorio;
import br.org.curitiba.ici.geradorcodigo.servico.ConfiguracaoServico;
import br.org.curitiba.ici.geradorcodigo.validacao.ConfiguracaoValidacaoAtributo;
import br.org.curitiba.ici.geradorcodigo.web.controller.ConfiguracaoController;
import br.org.curitiba.ici.geradorcodigo.web.hateoas.ConfiguracaoModelAssember;
import br.org.curitiba.ici.geradorcodigo.web.hateoas.ConfiguracaoPesquisaModelAssember;
import br.org.curitiba.ici.geradorcodigo.web.mapper.ConfiguracaoMapper;
import br.org.curitiba.ici.geradorcodigo.web.request.ConfiguracaoAtualizacaoEntidadeRequest;
import br.org.curitiba.ici.geradorcodigo.web.request.ConfiguracaoNovaEntidadeRequest;
import br.org.curitiba.ici.geradorcodigo.web.request.ConfiguracaoPesquisaRequest;
import br.org.curitiba.ici.geradorcodigo.web.response.ConfiguracaoEntidadeResponse;
import br.org.curitiba.ici.geradorcodigo.web.response.ConfiguracaoPesquisaResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProcessadorConfiguracao {
	private ArquivoConfiguracao arquivoConfiguracao;


	public void processar() {
		List<ArquivoCodigo> arquivos = new ArrayList<>();
		arquivos.addAll(criarEntidades(arquivoConfiguracao));
		arquivos.add(criarRepositorio(arquivoConfiguracao));
		arquivos.add(criarServico(arquivoConfiguracao));
		arquivos.add(criarArquivoController(arquivoConfiguracao));
		arquivos.add(criarArquivoModelAssember(arquivoConfiguracao));
		arquivos.add(criarArquivoPesquisaModelAssember(arquivoConfiguracao));
		arquivos.add(criarArquivoMapper(arquivoConfiguracao));
		arquivos.add(criarArquivoAtualizacaoEntidadeRequest(arquivoConfiguracao));
		arquivos.add(criarArquivoNovaEntidadeRequest(arquivoConfiguracao));
		arquivos.add(criarArquivoPesquisaEntidadeRequest(arquivoConfiguracao));
		arquivos.add(criarArquivoEntidadeResponse(arquivoConfiguracao));
		arquivos.add(criarArquivoPesquisaResponse(arquivoConfiguracao));
		gravarArquivosEmDisco(arquivos, arquivoConfiguracao);
	}


	private ArquivoCodigo criarArquivoPesquisaResponse(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoPesquisaResponse(arquivoConfiguracao2.getPacote(), arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), criarConfiguracaoAtributo(arquivoConfiguracao.getEntidades().get(0)));
	}


	private ArquivoCodigo criarArquivoEntidadeResponse(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoEntidadeResponse(arquivoConfiguracao2.getPacote(), arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), criarConfiguracaoAtributo(arquivoConfiguracao.getEntidades().get(0)));
	}


	private ArquivoCodigo criarArquivoPesquisaEntidadeRequest(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoPesquisaRequest(arquivoConfiguracao2.getPacote(), arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), criarConfiguracaoAtributo(arquivoConfiguracao.getEntidades().get(0)));
	}

	private ArquivoCodigo criarArquivoNovaEntidadeRequest(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoNovaEntidadeRequest(arquivoConfiguracao2.getPacote(), arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), criarConfiguracaoAtributo(arquivoConfiguracao.getEntidades().get(0)));
	}


	private ArquivoCodigo criarArquivoAtualizacaoEntidadeRequest(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoAtualizacaoEntidadeRequest(arquivoConfiguracao2.getPacote(), arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), criarConfiguracaoAtributo(arquivoConfiguracao.getEntidades().get(0)));
	}


	private ArquivoCodigo criarArquivoMapper(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoMapper(arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), arquivoConfiguracao.getPacote());
	}


	private ArquivoCodigo criarArquivoPesquisaModelAssember(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoPesquisaModelAssember(arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), arquivoConfiguracao.getPacote(), arquivoConfiguracao2.getNomeAtributoId());
	}

	private ArquivoCodigo criarArquivoModelAssember(ArquivoConfiguracao arquivoConfiguracao) {
		return new ConfiguracaoModelAssember(arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), arquivoConfiguracao.getPacote(), arquivoConfiguracao.getNomeAtributoId());
	}


	private ArquivoCodigo criarArquivoController(ArquivoConfiguracao arquivoConfiguracao2) {
		return new ConfiguracaoController(arquivoConfiguracao2.getEntidades().get(0).getNomeClasse(), arquivoConfiguracao2.getPacote(), arquivoConfiguracao2.getNomeAtributoId());
	}

	private void gravarArquivosEmDisco(List<ArquivoCodigo> arquivos, ArquivoConfiguracao arquivoConfiguracao) {
		arquivos.forEach(arquivo -> 
			FileUtil.escreverArquivoJavaSeNaoExistir(
					arquivoConfiguracao.getPastaArquivos(), 
					arquivoConfiguracao.getPacote(), 
					arquivo.getCaminhoPacoteClasse(), 
					arquivo.getCodigoGerado()));
	}

	
	
	private ArquivoCodigo criarServico(ArquivoConfiguracao arquivoConfiguracao) {
		return new ConfiguracaoServico(arquivoConfiguracao.getEntidades().get(0).getNomeClasse(), arquivoConfiguracao.getPacote(), arquivoConfiguracao.getNomeAtributoId(), arquivoConfiguracao.getAtributosPesquisaIniciaEm());
	}


	private ConfiguracaoRepositorio criarRepositorio(ArquivoConfiguracao arquivoConfiguracao) {
		return new ConfiguracaoRepositorio(arquivoConfiguracao.getPacote(), arquivoConfiguracao.getEntidades().get(0).getNomeClasse());
	}


	private List<ConfiguracaoEntidade> criarEntidades(ArquivoConfiguracao arquivoConfiguracao) {
		return
				arquivoConfiguracao
				.getEntidades()
				.stream()
				.map(entidade -> new ConfiguracaoEntidade(entidade.getTabela(), arquivoConfiguracao.getPacote(), entidade.getNomeClasse(), criarConfiguracaoAtributo(entidade)))
				.collect(Collectors.toList());

	}


	private HashSet<ConfiguracaoAtributo> criarConfiguracaoAtributo(Entidade entidade) {
		return
				entidade
				.getAtributos()
				.stream()
				.map(atributo -> {
					ConfiguracaoAtributo configuracao = new ConfiguracaoAtributo(atributo.isId(), atributo.getColuna(), atributo.getNome(), atributo.getTipo());
					atributo.getValidadores()
					.forEach(validador -> configuracao.addValidacao(new ConfiguracaoValidacaoAtributo(validador.getNome(), validador.existeMensagem() ? validador.getMensagem(): atributo.getNomeExibicao(), validador.getComplemento())));
					return configuracao;
				})
				.collect(Collectors.toCollection(HashSet::new));
	}

}
