package br.org.curitiba.ici.geradorcodigo.web.hateoas;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoPesquisaModelAssember implements ArquivoCodigo {
	private String nomeEntidade;
	private String nomePacote;

	private String getCodigoModelAssembler() {
		String variavelEntidade = nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length()); 
		return
				getTemplate()
				.replace("nomePacote", nomePacote)
				.replace("hateoasPacote", getHateosPacote())				
				.replace("codigoEntidade", nomeEntidade + Constantes.NOME_FINAL_ENTIDADE)
				.replace("nomeEntidade", nomeEntidade)
				.replace("variavelEntidade", variavelEntidade)
				.replace("pacoteResponse", Constantes.NOME_PACOTE_RESPONSE)
				.replace("mapStructPacote", Constantes.NOME_PACOTE_MAP_STRUCT)
				.replace("pacoteController", Constantes.NOME_PACOTE_CONTROLLER);
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
		return "package nomePacote.hateoasPacote;\n"
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
				+ "import nomePacote.entity.codigoEntidade;\n"
				+ "import nomePacote.pacoteController.nomeEntidadeController;\n"
				+ "import nomePacote.mapStructPacote.nomeEntidadeMapper;\n"
				+ "import nomePacote.pacoteResponse.nomeEntidadePesquisaResponse;\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@RequiredArgsConstructor\n"
				+ "@Component\n"
				+ "public class nomeEntidadePesquisaModelAssembler implements RepresentationModelAssembler<codigoEntidade, nomeEntidadePesquisaResponse> {\n"
				+ "	private final nomeEntidadeMapper variavelEntidadeMapper;\n"
				+ "	private final PagedResourcesAssembler<codigoEntidade> pageResourceAssembler;\n"
				+ "	\n"
				+ "	@Override\n"
				+ "	public nomeEntidadePesquisaResponse toModel(codigoEntidade entity) {\n"
				+ "		return variavelEntidadeMapper\n"
				+ "				.toPesquisaResponse(entity)\n"
				+ "				.add(getSelfLinkItem(entity.getCodnomeEntidade()));\n"
				+ "	}\n"
				+ "	\n"
				+ "	private Link getSelfLinkItem(Integer codnomeEntidade) {\n"
				+ "		return\n"
				+ "			linkTo(WebMvcLinkBuilder.methodOn(nomeEntidadeController.class)\n"
				+ "					.buscarPorId(codnomeEntidade)).withSelfRel();\n"
				+ "	}\n"
				+ "	\n"
				+ "	public PagedModel<nomeEntidadePesquisaResponse> toCollectionPesquisanomeEntidadeModel(\n"
				+ "			Page<codigoEntidade> pagenomeEntidadees) {\n"
				+ "		return pageResourceAssembler.toModel(pagenomeEntidadees, this);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Link getSelfLink() {\n"
				+ "		return linkTo(WebMvcLinkBuilder.methodOn(nomeEntidadeController.class)\n"
				+ "				.pesquisar(null, null)).withSelfRel();\n"
				+ "	}\n"
				+ "}\n"
				+ "";
		
	}


}
