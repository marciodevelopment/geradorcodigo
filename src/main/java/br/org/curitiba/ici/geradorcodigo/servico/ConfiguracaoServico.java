package br.org.curitiba.ici.geradorcodigo.servico;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoServico implements ArquivoCodigo {
	private String nomeEntidade;
	private String nomePacote;
	private String nomeAtributoId;
	private List<String> atributosPesquisaIniciaEm;

	private String getCodigoServico() {
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("nomePacote", NomeCodigoType.SERVICO.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.SERVICO.classe(nomeEntidade));
		valuesMap.put("pacoteEntidade", NomeCodigoType.ENTIDADE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("pacoteRepository", NomeCodigoType.REPOSITORY.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("nomeRepositorio", NomeCodigoType.REPOSITORY.classe(nomeEntidade));
		valuesMap.put("variavelRepositorio", NomeCodigoType.REPOSITORY.variavel(nomeEntidade));
		valuesMap.put("variavelEntidade", NomeCodigoType.ENTIDADE.variavel(nomeEntidade));
		valuesMap.put("nomeEntidade", NomeCodigoType.ENTIDADE.classe(nomeEntidade));
		valuesMap.put("getAtributoId", NomeCodigoType.getNomeAtributoId(nomeAtributoId));
		valuesMap.put("nomeAtributoId", nomeAtributoId);
		valuesMap.put("mensagemNotFound", getMensagemNotFound());
		valuesMap.put("matchers", getMatchers());
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}



	private String getMatchers() {
		String matcherStr = "\t\t\t\t.withMatcher(\"atributo\", ExampleMatcher.GenericPropertyMatchers.startsWith())\n";
		String matchersFinal =
				this.atributosPesquisaIniciaEm
				.stream()
				.map(atributo -> matcherStr.replace("atributo", atributo))
				.reduce("", (valorAtual, valorFinal) -> valorFinal + valorAtual);
		if (StringUtils.isEmpty(matchersFinal))
			return "";
		return matchersFinal.substring(0, matchersFinal.length() - 1);
	}

	private String getMensagemNotFound() {
		return "\"" + nomeEntidade + " não encontrado para o código " + NomeCodigoType.ENTIDADE.variavel(nomeEntidade).replace("Entity", "") + " enviado\", " + "\"" +
				NomeCodigoType.ENTIDADE.variavel(nomeEntidade).replace("Entity", "") + "." + nomeAtributoId + "\"";
	}


	@Override
	public String getCodigoGerado() {
		return getCodigoServico();
	}

	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.SERVICO.arquivo(nomePacote, nomeEntidade);
	}


	private String getTemplate() {
		return "package ${nomePacote};\n"
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
				+ "import ${pacoteEntidade};\n"
				+ "import ${pacoteRepository};\n"
				+ "\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@Transactional(readOnly = true)\n"
				+ "@RequiredArgsConstructor\n"
				+ "@Service\n"
				+ "public class ${nomeClasse} {\n"
				+ "	private final ${nomeRepositorio} ${variavelRepositorio};\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public ${nomeEntidade} salvar(${nomeEntidade} ${variavelEntidade}) {\n"
				+ "		return ${variavelRepositorio}.save(${variavelEntidade});\n"
				+ "	}\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public ${nomeEntidade} atualizar(${nomeEntidade} ${variavelEntidade}) {\n"
				+ "		if (!${variavelRepositorio}.existsById(${variavelEntidade}.${getAtributoId})) {\n"
				+ "			throw new NotFoundException(${mensagemNotFound});\n"
				+ "		}\n"
				+ "		return ${variavelRepositorio}.save(${variavelEntidade});\n"
				+ "	}\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public void deletarPorId(Integer ${nomeAtributoId}) {\n"
				+ "		if (!${variavelRepositorio}.existsById(${nomeAtributoId})) {\n"
				+ "			throw new NotFoundException(${mensagemNotFound});\n"
				+ "		}\n"
				+ "		${variavelRepositorio}.deleteById(${nomeAtributoId});\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Page<${nomeEntidade}> pesquisarPorExemplo(${nomeEntidade} ${variavelEntidade}, Pageable pageable) {\n"
				+ "		ExampleMatcher customExampleMatcher = ExampleMatcher\n"
				+ "				.matching()\n"
				+ "				.withIgnoreNullValues()\n"
				+ "${matchers};\n"
				+ "		Example<${nomeEntidade}> example = Example.of(${variavelEntidade}, customExampleMatcher);\n"
				+ "		return ${variavelRepositorio}.findAll(example, pageable);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Optional<${nomeEntidade}> buscarOpcionalPorId(Integer ${nomeAtributoId}) {\n"
				+ "		return ${variavelRepositorio}.findById(${nomeAtributoId});\n"
				+ "	}\n"
				+ "	\n"
				+ "	public ${nomeEntidade} buscarPorId(Integer ${nomeAtributoId}) {\n"
				+ "		return ${variavelRepositorio}.findById(${nomeAtributoId})\n"
				+ "				.orElseThrow(() -> new NotFoundException(${mensagemNotFound}));\n"
				+ "	}\n"
				+ "	\n"
				+ "}\n"
				+ "";

	}

}
