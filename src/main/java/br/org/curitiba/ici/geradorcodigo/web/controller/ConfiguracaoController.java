package br.org.curitiba.ici.geradorcodigo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoController implements ArquivoCodigo {

	private String nomeEntidade;
	private String nomePacote;
	private String nomeAtributoId;

	private String getCodigoModelAssembler() {
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("nomePacote", NomeCodigoType.CONTROLE.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.CONTROLE.classe(nomeEntidade));
		valuesMap.put("nomePacoteHateoas", NomeCodigoType.HATEOAS.pacote(nomePacote));
		valuesMap.put("importEntidade", NomeCodigoType.ENTIDADE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importService", NomeCodigoType.SERVICO.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importModelHateoas", NomeCodigoType.HATEOAS.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importModelPesquisaHateoas", NomeCodigoType.PESQUISA_HATEOAS.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importMapper", NomeCodigoType.MAPPER.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importRequestAtualizacao", NomeCodigoType.REQUEST_ATUALIZACAO.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importRequestNovo", NomeCodigoType.REQUEST_NOVO.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importRequestPesquisa", NomeCodigoType.REQUEST_PESQUISA.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importResponsePesquisa", NomeCodigoType.RESPONSE_PESQUISA.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importResponse", NomeCodigoType.RESPONSE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("nomeClasseEntidade", NomeCodigoType.ENTIDADE.classe(nomeEntidade));
		valuesMap.put("variavelEntidade", NomeCodigoType.ENTIDADE.variavelEntidade(nomeEntidade));
		valuesMap.put("nomeClasseService", NomeCodigoType.SERVICO.classe(nomeEntidade));
		valuesMap.put("variavelService", NomeCodigoType.SERVICO.variavel(nomeEntidade));
		valuesMap.put("nomeClasseMapper", NomeCodigoType.MAPPER.classe(nomeEntidade));
		valuesMap.put("variavelMapper", NomeCodigoType.MAPPER.variavel(nomeEntidade));
		valuesMap.put("nomeClasseModelAssembler", NomeCodigoType.HATEOAS.classe(nomeEntidade));
		valuesMap.put("variavelModelAssembler", NomeCodigoType.HATEOAS.variavel(nomeEntidade));
		valuesMap.put("nomeClassePesquisaModelAssembler", NomeCodigoType.PESQUISA_HATEOAS.classe(nomeEntidade));
		valuesMap.put("variavelPesquisaModelAssembler", NomeCodigoType.PESQUISA_HATEOAS.variavel(nomeEntidade));
		valuesMap.put("nomeClasseRequestNovo", NomeCodigoType.REQUEST_NOVO.classe(nomeEntidade));
		valuesMap.put("variavelRequestNovo", NomeCodigoType.REQUEST_NOVO.variavel(nomeEntidade));
		valuesMap.put("getAtributoId", NomeCodigoType.getNomeAtributoId(nomeAtributoId));
		valuesMap.put("classeResponse", NomeCodigoType.RESPONSE.classe(nomeEntidade));
		valuesMap.put("classePesquisaResponse", NomeCodigoType.RESPONSE_PESQUISA.classe(nomeEntidade));
		valuesMap.put("classePesquisaRequest", NomeCodigoType.REQUEST_PESQUISA.classe(nomeEntidade));
		valuesMap.put("classeAtualizacaoRequest", NomeCodigoType.REQUEST_ATUALIZACAO.classe(nomeEntidade));
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}

	
	
	@Override
	public String getCodigoGerado() {
		return getCodigoModelAssembler();
	}

	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.CONTROLE.arquivo(nomePacote, nomeEntidade);
	}
	
	public String getTemplate() {
		return "package ${nomePacote};\n"
				+ "\n"
				+ "import java.net.URI;\n"
				+ "\n"
				+ "import javax.validation.Valid;\n"
				+ "import javax.validation.constraints.NotNull;\n"
				+ "\n"
				+ "import org.springframework.data.domain.Page;\n"
				+ "import org.springframework.hateoas.PagedModel;\n"
				+ "import org.springframework.http.ResponseEntity;\n"
				+ "import org.springframework.web.bind.annotation.DeleteMapping;\n"
				+ "import org.springframework.web.bind.annotation.GetMapping;\n"
				+ "import org.springframework.web.bind.annotation.PathVariable;\n"
				+ "import org.springframework.web.bind.annotation.PostMapping;\n"
				+ "import org.springframework.web.bind.annotation.PutMapping;\n"
				+ "import org.springframework.web.bind.annotation.RequestBody;\n"
				+ "import org.springframework.web.bind.annotation.RequestMapping;\n"
				+ "import org.springframework.web.bind.annotation.RestController;\n"
				+ "\n"
				+ "import br.org.curitiba.ici.gtm.common.web.request.PaginationRequest;\n"
				+ "import ${importEntidade};\n"
				+ "import ${importService};\n"
				+ "import ${importModelHateoas};\n"
				+ "import ${importModelPesquisaHateoas};\n"
				+ "import ${importMapper};\n"
				+ "import ${importRequestAtualizacao};\n"
				+ "import ${importRequestNovo};\n"
				+ "import ${importRequestPesquisa};\n"
				+ "import ${importResponsePesquisa};\n"
				+ "import ${importResponse};\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@RequiredArgsConstructor\n"
				+ "@RestController\n"
				+ "// TODO: verificar nome do controller gerado\n"
				+ "@RequestMapping(\"/${variavelEntidade}s\")\n"
				+ "public class ${nomeClasse} {\n"
				+ "	private final ${nomeClasseService} ${variavelService};\n"
				+ "	private final ${nomeClasseMapper} ${variavelMapper};\n"
				+ "	private final ${nomeClasseModelAssembler} ${variavelModelAssembler};\n"
				+ "	private final ${nomeClassePesquisaModelAssembler} ${variavelPesquisaModelAssembler};\n"
				+ "	\n"
				+ "	@PostMapping\n"
				+ "	public ResponseEntity<Void> salvar(@RequestBody @Valid ${nomeClasseRequestNovo} ${variavelRequestNovo}) {\n"
				+ "		${nomeClasseEntidade} ${variavelEntidade} = ${variavelService}.salvar(${variavelMapper}.toEntity(${variavelRequestNovo}));\n"
				+ "		// TODO: verificar nome do controller gerado\n"
				+ "		return ResponseEntity.created(URI.create(\"/${variavelEntidade}/\" + ${variavelEntidade}.${getAtributoId})).build();\n"
				+ "	}\n"
				+ "	\n"
				+ "	@PutMapping(path = \"/{id}\")\n"
				+ "	public ResponseEntity<Void> atualizar(@RequestBody @Valid ${classeAtualizacaoRequest} request, @PathVariable(\"id\") Integer id) {\n"
				+ "		${variavelService}.salvar(${variavelMapper}.toEntity(id, request));\n"
				+ "		return ResponseEntity.ok().build();\n"
				+ "	}\n"
				+ "	\n"
				+ "	@GetMapping\n"
				+ "	public PagedModel<${classePesquisaResponse}> pesquisar(@Valid ${classePesquisaRequest} request, @Valid\n"
				+ "			PaginationRequest paginationRequest) {\n"
				+ "		Page<${nomeClasseEntidade}> page${nomeClasseEntidade} = ${variavelService}.pesquisarPorExemplo(${variavelMapper}.toEntity(request), paginationRequest.toPageable());\n"
				+ "		return ${variavelPesquisaModelAssembler}.toCollection${nomeClassePesquisaModelAssembler}(page${nomeClasseEntidade});\n"
				+ "	}\n"
				+ "	\n"
				+ "	@GetMapping(path = \"/{id}\")\n"
				+ "	public ${classeResponse} buscarPorId(@NotNull @PathVariable(\"id\") Integer id) {\n"
				+ "		return ${variavelModelAssembler}.toModel(${variavelService}.buscarPorId(id));\n"
				+ "	}\n"
				+ "	\n"
				+ "	@DeleteMapping(path = \"/{id}\")\n"
				+ "	public ResponseEntity<Void> deletar(@NotNull @PathVariable(\"id\") Integer id) {\n"
				+ "		${variavelService}.deletarPorId(id);\n"
				+ "		return ResponseEntity.ok().build();\n"
				+ "	}\n"
				+ "\n"
				+ "}\n"
				+ "";
	}

}
