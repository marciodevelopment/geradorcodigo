package br.org.curitiba.ici.geradorcodigo.servico;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoFinal;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoServico implements ArquivoFinal {
	private String nomeEntidade;
	private String nomePacote;
	private String nomeAtributoId;
	private List<String> atributosPesquisaInit;

	private String getCodigoServico() {
		String nomeRepositorio = nomeEntidade + Constantes.NOME_FINAL_REPOSITORIO;
		String variavelRepositorio = nomeRepositorio.substring(0, 1).toLowerCase() +
				nomeRepositorio.substring(1, nomeRepositorio.length());

		return
				getTemplate()
				.replace("nomePacote", nomePacote)
				.replace("servicoPacote", Constantes.NOME_PACOTE_SERVICO)
				.replace("nome_Pacote_Entidade", Constantes.NOME_PACOTE_ENTIDADE)
				.replace("nome_Entidade_Mensagem", nomeEntidade)
				.replace("nomeEntidade", nomeEntidade + Constantes.NOME_FINAL_ENTIDADE)
				.replace("nome_Pacote_Repositorio", Constantes.NOME_PACOTE_REPOSTITORIO)
				.replace("nomeRepositorio", nomeRepositorio)
				.replace("nomeServico", nomeEntidade + Constantes.NOME_FINAL_SERVICO)
				.replace("variavelRepositorio", variavelRepositorio)
				.replace("nomeAtributoId", nomeAtributoId)
				.replace("mensagemNotFound", getMensagemNotFound())
				.replace("nomeVariavelEntidade", getNomeVariavelEntidade())
				.replace("getAtributoId", getAtributoId())
				.replace("matchers", getMatchers());

	}

	private String getMatchers() {
		String matcherStr = "\t\t\t\t.withMatcher(\"atributo\", ExampleMatcher.GenericPropertyMatchers.startsWith())\n";
		String matchersFinal =
				this.atributosPesquisaInit
				.stream()
				.map(atributo -> matcherStr.replace("atributo", atributo))
				.reduce("", (valorAtual, valorFinal) -> valorFinal + valorAtual);
		if (StringUtils.isEmpty(matchersFinal))
			return "";
		return matchersFinal.substring(0, matchersFinal.length() - 1);
	}

	private String getNomeVariavelEntidade() {
		return nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length());
	}

	private String getMensagemNotFound() {
		return nomeEntidade + " não encontrado para o código nomeVariavelEntidade enviado";
	}

	private String getAtributoId() {
		return "get" + nomeAtributoId.substring(0, 1).toUpperCase() + nomeAtributoId.substring(1, nomeAtributoId.length()) + "()";
	}


	@Override
	public String getArquivo() {
		return getCodigoServico();
	}
	@Override
	public String getPasta() {
		// TODO Auto-generated method stub
		return null;
	}


	private String getTemplate() {
		return "package nomePacote.servicoPacote;\n"
				+ "\n"
				+ "\n"
				+ "import java.util.Optional;\n"
				+ "\n"
				+ "import javax.persistence.EntityNotFoundException;\n"
				+ "\n"
				+ "import org.springframework.data.domain.Example;\n"
				+ "import org.springframework.data.domain.ExampleMatcher;\n"
				+ "import org.springframework.data.domain.Page;\n"
				+ "import org.springframework.data.domain.Pageable;\n"
				+ "import org.springframework.stereotype.Service;\n"
				+ "import org.springframework.transaction.annotation.Transactional;\n"
				+ "\n"
				+ "import br.org.curitiba.ici.gtm.common.exception.NotFoundException;\n"
				+ "\n"
				+ "import nomePacote.nome_Pacote_Entidade.nomeEntidade;\n"
				+ "import nomePacote.nome_Pacote_Repositorio.nomeRepositorio;\n"
				+ "\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@Transactional(readOnly = true)\n"
				+ "@RequiredArgsConstructor\n"
				+ "@Service\n"
				+ "public class nomeServico {\n"
				+ "	private final nomeRepositorio variavelRepositorio;\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public nomeEntidade salvar(nomeEntidade nomeVariavelEntidade) {\n"
				+ "		return variavelRepositorio.save(nomeVariavelEntidade);\n"
				+ "	}\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public nomeEntidade atualizar(nomeEntidade nomeVariavelEntidade) {\n"
				+ "		if (!variavelRepositorio.existsById(nomeVariavelEntidade.getAtributoId)) {\n"
				+ "			throw new NotFoundException(\"nome_Entidade_Mensagem não existente para o código \" + nomeVariavelEntidade.getAtributoId + \".\", \"nomeVariavelEntidade.nomeAtributoId\");\n"
				+ "		}\n"
				+ "		return variavelRepositorio.save(nomeVariavelEntidade);\n"
				+ "	}\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public void deletarPorId(Integer nomeAtributoId) {\n"
				+ "		if (!variavelRepositorio.existsById(nomeAtributoId)) {\n"
				+ "			throw new NotFoundException(\"nome_Entidade_Mensagem não existente para o código \" + nomeAtributoId + \".\", \"nomeVariavelEntidade.nomeAtributoId\");\n"
				+ "		}\n"
				+ "		variavelRepositorio.deleteById(nomeAtributoId);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Page<nomeEntidade> pesquisarPorExemplo(nomeEntidade nomeVariavelEntidade, Pageable pageable) {\n"
				+ "		ExampleMatcher customExampleMatcher = ExampleMatcher\n"
				+ "				.matching()\n"
				+ "				.withIgnoreNullValues()\n"
				+ "matchers;\n"
				+ "		Example<PaisEntity> example = Example.of(nomeVariavelEntidade, customExampleMatcher);\n"
				+ "		return variavelRepositorio.findAll(example, pageable);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Optional<nomeEntidade> buscarOpcionalPorId(Integer nomeAtributoId) {\n"
				+ "		return variavelRepositorio.findById(nomeAtributoId);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public nomeEntidade buscarPorId(Integer nomeAtributoId) {\n"
				+ "		return variavelRepositorio.findById(nomeAtributoId)\n"
				+ "				.orElseThrow(() -> new EntityNotFoundException(\"nome_Entidade_Mensagem não encontrado para o código nome_Entidade_Mensagem enviado\"));\n"
				+ "	}\n"
				+ "	\n"
				+ "}\n"
				+ "";

	}

}
