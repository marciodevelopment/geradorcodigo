package br.org.curitiba.ici.geradorcodigo.web.hateoas;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoModelAssember implements ArquivoCodigo {
	private String nomeEntidade;
	private String nomePacote;
	private String nomeAtributoId;

	private String getCodigoModelAssembler() {
		
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("nomePacote", NomeCodigoType.HATEOAS.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.HATEOAS.classe(nomeEntidade));
		valuesMap.put("importEntidade", NomeCodigoType.ENTIDADE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importControle", NomeCodigoType.CONTROLE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importMapper", NomeCodigoType.MAPPER.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importResponse", NomeCodigoType.RESPONSE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("nomeClasseEntidade", NomeCodigoType.ENTIDADE.classe(nomeEntidade));
		valuesMap.put("classeResponse", NomeCodigoType.RESPONSE.classe(nomeEntidade));
		valuesMap.put("nomeClasseMapper", NomeCodigoType.MAPPER.classe(nomeEntidade));
		valuesMap.put("variavelMapper", NomeCodigoType.MAPPER.variavel(nomeEntidade));
		valuesMap.put("getAtributoId", NomeCodigoType.getNomeAtributoId(nomeAtributoId));
		valuesMap.put("variavelEntidade", NomeCodigoType.ENTIDADE.variavelEntidade(nomeEntidade));
		valuesMap.put("nomeAtributoId", nomeAtributoId);
		valuesMap.put("nomeClasseController", NomeCodigoType.CONTROLE.classe(nomeEntidade));
		valuesMap.put("variavelResponse", NomeCodigoType.RESPONSE.variavel(nomeEntidade));
		
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}

	@Override
	public String getCodigoGerado() {
		return this.getCodigoModelAssembler();
	}


	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.HATEOAS.arquivo(nomePacote, nomeEntidade);
	}

	private String getTemplate() {
		String template = "package ${nomePacote};\n"
				+ "\n"
				+ "import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;\n"
				+ "import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;\n"
				+ "\n"
				+ "import org.springframework.hateoas.Link;\n"
				+ "import org.springframework.hateoas.server.RepresentationModelAssembler;\n"
				+ "import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;\n"
				+ "import org.springframework.stereotype.Component;\n"
				+ "\n"
				+ "import ${importEntidade};\n"
				+ "import ${importControle} ;\n"
				+ "import ${importMapper};\n"
				+ "import ${importResponse};\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@RequiredArgsConstructor\n"
				+ "@Component\n"
				+ "public class ${nomeClasse} implements RepresentationModelAssembler<${nomeClasseEntidade}, ${classeResponse}> {\n"
				+ "	private final ${nomeClasseMapper} ${variavelMapper};\n"
				+ "	\n"
				+ "	@Override\n"
				+ "	public ${classeResponse} toModel(${nomeClasseEntidade} entity) {\n"
				+ "		${classeResponse} ${variavelResponse} = ${variavelMapper}.toResponse(entity);\n"
				+ "		\n"
				+ "		${variavelResponse}.add(\n"
				+ "				linkTo(methodOn(${nomeClasseController}.class)\n"
				+ "						.deletar(entity.${getAtributoId})).withRel(\"deletar\"));\n"
				+ "		\n"
				+ "		${variavelResponse}.add(\n"
				+ "				linkTo(methodOn(${nomeClasseController}.class)\n"
				+ "						.salvar(null)).withRel(\"salvar\"));\n"
				+ "		\n"
				+ "		${variavelResponse}.add(\n"
				+ "				linkTo(methodOn(${nomeClasseController}.class)\n"
				+ "						.atualizar(null, entity.${getAtributoId})).withRel(\"atualizar\"));\n"
				+ "		\n"
				+ "		${variavelResponse}.add(\n"
				+ "				getSelfLink(entity.${getAtributoId}));\n"
				+ "		\n"
				+ "		// TODO: verificar nome no plural.\n"
				+ "		${variavelResponse}.add(getSelfLinkPesquisa().withRel(\"${variavelEntidade}s\"));\n"
				+ "		return ${variavelResponse};\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Link getSelfLink(Integer ${nomeAtributoId}) {\n"
				+ "		return\n"
				+ "			linkTo(WebMvcLinkBuilder.methodOn(${nomeClasseController}.class)\n"
				+ "					.buscarPorId(${nomeAtributoId})).withSelfRel();\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Link getSelfLinkPesquisa() {\n"
				+ "		return linkTo(WebMvcLinkBuilder.methodOn(${nomeClasseController}.class)\n"
				+ "				.pesquisar(null, null)).withSelfRel();\n"
				+ "	}\n"
				+ "}\n"
				+ "";
		return template;
	}


}
