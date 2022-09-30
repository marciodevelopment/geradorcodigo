package br.org.curitiba.ici.geradorcodigo.web.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ConfiguracaoControllerTest {
	
	private String codigoController = "package br.org.curitiba.ici.gtm.pais.web.controller;\n"
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
			+ "import br.org.curitiba.ici.gtm.pais.entity.PaisEntity;\n"
			+ "import br.org.curitiba.ici.gtm.pais.service.PaisService;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.hateoas.PaisModelAssembler;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.hateoas.PaisPesquisaModelAssembler;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.mapstruct.PaisMapper;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.request.AtualizacaoPaisRequest;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.request.NovoPaisRequest;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.request.PaisPesquisaRequest;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.response.PaisPesquisaResponse;\n"
			+ "import br.org.curitiba.ici.gtm.pais.web.controller.response.PaisResponse;\n"
			+ "import lombok.RequiredArgsConstructor;\n"
			+ "\n"
			+ "@RequiredArgsConstructor\n"
			+ "@RestController\n"
			+ "// TODO: verificar nome do controller gerado\n"
			+ "@RequestMapping(\"/paiss\")\n"
			+ "public class PaisController {\n"
			+ "	private final PaisService paisService;\n"
			+ "	private final PaisMapper paisMapper;\n"
			+ "	private final PaisModelAssembler paisModelAssembler;\n"
			+ "	private final PaisPesquisaModelAssembler paisPesquisaModelAssembler;\n"
			+ "	\n"
			+ "	@PostMapping\n"
			+ "	public ResponseEntity<Void> salvar(@RequestBody @Valid NovoPaisRequest request) {\n"
			+ "		PaisEntity novoPais = paisService.salvar(paisMapper.toEntity(request));\n"
			+ "		// TODO: verificar nome do controller gerado\n"
			+ "		return ResponseEntity.created(URI.create(\"/pais/\" + novoPais.getCodPais())).build();\n"
			+ "	}\n"
			+ "	\n"
			+ "	@PutMapping(path = \"/{id}\")\n"
			+ "	public ResponseEntity<Void> atualizar(@RequestBody @Valid AtualizacaoPaisRequest request, @PathVariable(\"id\") Integer id) {\n"
			+ "		paisService.salvar(paisMapper.toEntity(id, request));\n"
			+ "		return ResponseEntity.ok().build();\n"
			+ "	}\n"
			+ "	\n"
			+ "	@GetMapping\n"
			+ "	public PagedModel<PaisPesquisaResponse> pesquisar(@Valid PaisPesquisaRequest request, @Valid\n"
			+ "			PaginationRequest paginationRequest) {\n"
			+ "		Page<PaisEntity> pagePaises = paisService.pesquisarPorExemplo(paisMapper.toEntity(request), paginationRequest.toPageable());\n"
			+ "		return paisPesquisaModelAssembler.toCollectionPesquisaPaisModel(pagePaises);\n"
			+ "	}\n"
			+ "	\n"
			+ "	@GetMapping(path = \"/{id}\")\n"
			+ "	public PaisResponse buscarPorId(@NotNull @PathVariable(\"id\") Integer id) {\n"
			+ "		return paisModelAssembler.toModel(paisService.buscarPorId(id));\n"
			+ "	}\n"
			+ "	\n"
			+ "	@DeleteMapping(path = \"/{id}\")\n"
			+ "	public ResponseEntity<Void> deletar(@NotNull @PathVariable(\"id\") Integer id) {\n"
			+ "		paisService.deletarPorId(id);\n"
			+ "		return ResponseEntity.ok().build();\n"
			+ "	}\n"
			+ "\n"
			+ "}";

	@Test
	void test() {
		ConfiguracaoController configuracao = new ConfiguracaoController("Pais", "br.org.curitiba.ici.gtm.pais", "codPais");
		String codigoGerado = configuracao.getCodigoGerado().replaceAll("[^a-zA-Z0-9]", "");
		String codigoServicoExpected = codigoController.replaceAll("[^a-zA-Z0-9]", "");
		System.out.println(configuracao.getCodigoGerado());
		assertEquals(codigoServicoExpected, codigoGerado);
	}

	@Test
	void getPastaTest() {
		ConfiguracaoController configuracao = new ConfiguracaoController("Pais", "br.org.curitiba.ici.gtm.pais", "codPais");
		assertEquals("br.org.curitiba.ici.gtm.pais.web.controller.PaisController.java", configuracao.getCaminhoPacoteClasse());
	}
	
}
