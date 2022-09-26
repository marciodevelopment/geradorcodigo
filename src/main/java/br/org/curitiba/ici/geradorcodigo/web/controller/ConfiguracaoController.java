package br.org.curitiba.ici.geradorcodigo.web.controller;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoFinal;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoController implements ArquivoFinal {

	private String nomeEntidade;
	private String nomePacote;
	private String nomeAtributoId;

	private String getCodigoModelAssembler() {
		String variavelEntidade = nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length());
		return
				getTemplate()
				.replace("nomePacote", nomePacote)
				.replace("pacoteController", getPacoteController())
				.replace("hateoasPacote", Constantes.NOME_PACOTE_HATEOAS)
				.replace("mapStructPacote", Constantes.NOME_PACOTE_MAP_STRUCT)
				.replace("pacoteResponse", Constantes.NOME_PACOTE_RESPONSE)
				.replace("pacoteRequest", Constantes.NOME_PACOTE_REQUEST)
				.replace("pacoteServico", Constantes.NOME_PACOTE_SERVICO)
				.replace("pacoteEntidade", Constantes.NOME_PACOTE_ENTIDADE)
				.replace("variavelEntidade", variavelEntidade)
				.replace("nomeClasseEntidade", nomeEntidade + Constantes.NOME_FINAL_ENTIDADE)
				.replace("nomeEntidade", nomeEntidade)
				.replace("getAtributoId", getAtributoId())
				.replace("nomeService", getNomeService());
	}


	private String getNomeController() {
		return this.nomeEntidade + Constantes.NOME_FINAL_CONTROLLER;
	}
	
	private String getNomeService() {
		return nomeEntidade + Constantes.NOME_FINAL_SERVICO;
	}

	private String getPacoteController() {
		return Constantes.NOME_PACOTE_CONTROLLER;
	}
	
	private String getAtributoId() {
		return "get" + nomeAtributoId.substring(0, 1).toUpperCase() + nomeAtributoId.substring(1, nomeAtributoId.length()) + "()";
	}
	
	@Override
	public String getArquivo() {
		return getCodigoModelAssembler();
	}

	@Override
	public String getPasta() {
		return this.nomePacote + "." + this.getPacoteController() + "." + getNomeController() + ".java";
	}
	
	public String getTemplate() {
		return "package nomePacote.pacoteController;\n"
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
				+ "import nomePacote.pacoteEntidade.nomeClasseEntidade;\n"
				+ "import nomePacote.pacoteServico.nomeService;\n"
				+ "import nomePacote.hateoasPacote.nomeEntidadeModelAssembler;\n"
				+ "import nomePacote.hateoasPacote.nomeEntidadePesquisaModelAssembler;\n"
				+ "import nomePacote.mapStructPacote.nomeEntidadeMapper;\n"
				+ "import nomePacote.pacoteRequest.AtualizacaonomeEntidadeRequest;\n"
				+ "import nomePacote.pacoteRequest.NovonomeEntidadeRequest;\n"
				+ "import nomePacote.pacoteRequest.nomeEntidadePesquisaRequest;\n"
				+ "import nomePacote.pacoteResponse.nomeEntidadePesquisaResponse;\n"
				+ "import nomePacote.pacoteResponse.nomeEntidadeResponse;\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@RequiredArgsConstructor\n"
				+ "@RestController\n"
				+ "// TODO: verificar nome do controller gerado\n"
				+ "@RequestMapping(\"/variavelEntidades\")\n"
				+ "public class nomeEntidadeController {\n"
				+ "	private final nomeService variavelEntidadeService;\n"
				+ "	private final nomeEntidadeMapper variavelEntidadeMapper;\n"
				+ "	private final nomeEntidadeModelAssembler variavelEntidadeModelAssembler;\n"
				+ "	private final nomeEntidadePesquisaModelAssembler variavelEntidadePesquisaModelAssembler;\n"
				+ "	\n"
				+ "	@PostMapping\n"
				+ "	public ResponseEntity<Void> salvar(@RequestBody @Valid NovonomeEntidadeRequest request) {\n"
				+ "		nomeClasseEntidade novonomeEntidade = variavelEntidadeService.salvar(variavelEntidadeMapper.toEntity(request));\n"
				+ "		// TODO: verificar nome do controller gerado\n"
				+ "		return ResponseEntity.created(URI.create(\"/variavelEntidade/\" + novonomeEntidade.getAtributoId)).build();\n"
				+ "	}\n"
				+ "	\n"
				+ "	@PutMapping(path = \"/{id}\")\n"
				+ "	public ResponseEntity<Void> atualizar(@RequestBody @Valid AtualizacaonomeEntidadeRequest request, @PathVariable(\"id\") Integer id) {\n"
				+ "		variavelEntidadeService.salvar(variavelEntidadeMapper.toEntity(id, request));\n"
				+ "		return ResponseEntity.ok().build();\n"
				+ "	}\n"
				+ "	\n"
				+ "	@GetMapping\n"
				+ "	public PagedModel<nomeEntidadePesquisaResponse> pesquisar(@Valid nomeEntidadePesquisaRequest request, @Valid\n"
				+ "			PaginationRequest paginationRequest) {\n"
				+ "		Page<nomeClasseEntidade> pagenomeEntidadees = variavelEntidadeService.pesquisarPorExemplo(variavelEntidadeMapper.toEntity(request), paginationRequest.toPageable());\n"
				+ "		return variavelEntidadePesquisaModelAssembler.toCollectionPesquisanomeEntidadeModel(pagenomeEntidadees);\n"
				+ "	}\n"
				+ "	\n"
				+ "	@GetMapping(path = \"/{id}\")\n"
				+ "	public nomeEntidadeResponse buscarPorId(@NotNull @PathVariable(\"id\") Integer id) {\n"
				+ "		return variavelEntidadeModelAssembler.toModel(variavelEntidadeService.buscarPorId(id));\n"
				+ "	}\n"
				+ "	\n"
				+ "	@DeleteMapping(path = \"/{id}\")\n"
				+ "	public ResponseEntity<Void> deletar(@NotNull @PathVariable(\"id\") Integer id) {\n"
				+ "		variavelEntidadeService.deletarPorId(id);\n"
				+ "		return ResponseEntity.ok().build();\n"
				+ "	}\n"
				+ "\n"
				+ "}\n"
				+ "";
	}

}
