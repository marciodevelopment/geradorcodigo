package br.org.curitiba.ici.geradorcodigo.web.hateoas;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoPesquisaModelAssember implements ArquivoCodigo {
	private String nomeEntidade;
	private String nomePacote;
	private String nomeAtributoId;

	private String getCodigoModelAssembler() {
		
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("nomePacote", NomeCodigoType.PESQUISA_HATEOAS.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.PESQUISA_HATEOAS.classe(nomeEntidade));
		valuesMap.put("importEntidade", NomeCodigoType.ENTIDADE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importController", NomeCodigoType.CONTROLE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importMapper", NomeCodigoType.MAPPER.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importResponsePesquisa", NomeCodigoType.RESPONSE_PESQUISA.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("variavelMapper", NomeCodigoType.MAPPER.variavel(nomeEntidade));
		valuesMap.put("nomeClasseMapper", NomeCodigoType.MAPPER.classe(nomeEntidade));
		valuesMap.put("nomeClasseEntidade", NomeCodigoType.ENTIDADE.classe(nomeEntidade));
		valuesMap.put("nomeClassePesquisaResponse", NomeCodigoType.RESPONSE_PESQUISA.classe(nomeEntidade));
		valuesMap.put("nomeClasseController", NomeCodigoType.CONTROLE.classe(nomeEntidade));
		valuesMap.put("nomeAtributoId", nomeAtributoId);
		valuesMap.put("nomeClassePesquisaModelAssembler", NomeCodigoType.PESQUISA_HATEOAS.classe(nomeEntidade));
		valuesMap.put("getAtributoId", NomeCodigoType.getNomeAtributoId(nomeAtributoId));
		valuesMap.put("variavelEntidade", NomeCodigoType.ENTIDADE.variavelEntidade(nomeEntidade));
		
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}

	private String getHateosPacote() {
		return Constantes.NOME_PACOTE_HATEOAS;
	}

	@Override
	public String getCodigoGerado() {
		return this.getCodigoModelAssembler();
	}


	@Override
	public String getCaminhoPacoteClasse() {
		return this.nomePacote + "." + getHateosPacote() + "." + getNomeClasse() + ".java";
	}

	private String getNomeClasse() {
		return this.nomeEntidade + Constantes.NOME_FINAL_PESQUISA_MODEL_ASSEMBLER;
	}

	private String getTemplate() {
		return "package ${nomePacote};\n"
				+ "\n"
				+ "import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;\n"
				+ "\n"
				+ "import org.springframework.data.domain.Page;\n"
				+ "import org.springframework.data.web.PagedResourcesAssembler;\n"
				+ "import org.springframework.hateoas.Link;\n"
				+ "import org.springframework.hateoas.PagedModel;\n"
				+ "import org.springframework.hateoas.server.RepresentationModelAssembler;\n"
				+ "import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;\n"
				+ "import org.springframework.stereotype.Component;\n"
				+ "\n"
				+ "import ${importEntidade};\n"
				+ "import ${importController};\n"
				+ "import ${importMapper};\n"
				+ "import ${importResponsePesquisa};\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@RequiredArgsConstructor\n"
				+ "@Component\n"
				+ "public class ${nomeClasse} implements RepresentationModelAssembler<${nomeClasseEntidade}, ${nomeClassePesquisaResponse}> {\n"
				+ "	private final ${nomeClasseMapper} ${variavelMapper};\n"
				+ "	private final PagedResourcesAssembler<${nomeClasseEntidade}> pageResourceAssembler;\n"
				+ "	\n"
				+ "	@Override\n"
				+ "	public ${nomeClassePesquisaResponse} toModel(${nomeClasseEntidade} entity) {\n"
				+ "		return ${variavelMapper}\n"
				+ "				.to${nomeClassePesquisaResponse}(entity)\n"
				+ "				.add(getSelfLinkItem(entity.${getAtributoId}));\n"
				+ "	}\n"
				+ "	\n"
				+ "	private Link getSelfLinkItem(Integer ${nomeAtributoId}) {\n"
				+ "		return\n"
				+ "			linkTo(WebMvcLinkBuilder.methodOn(${nomeClasseController}.class)\n"
				+ "					.buscarPorId(${nomeAtributoId})).withSelfRel();\n"
				+ "	}\n"
				+ "	\n"
				+ "	public PagedModel<${nomeClassePesquisaResponse}> toCollection${nomeClassePesquisaModelAssembler}(\n"
				+ "			Page<${nomeClasseEntidade}> page${variavelEntidade}s) {\n"
				+ "		return pageResourceAssembler.toModel(page${variavelEntidade}s, this);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Link getSelfLink() {\n"
				+ "		return linkTo(WebMvcLinkBuilder.methodOn(${nomeClasseController}.class)\n"
				+ "				.pesquisar(null, null)).withSelfRel();\n"
				+ "	}\n"
				+ "}\n"
				+ "";
		
	}


}


