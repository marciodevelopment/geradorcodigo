package br.org.curitiba.ici.geradorcodigo.web.hateoas;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoModelAssember implements ArquivoCodigo {
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
				.replace("mapStructPacote", Constantes.NOME_PACOTE_MAP_STRUCT)
				.replace("pacoteResponse", Constantes.NOME_PACOTE_RESPONSE)
				.replace("pacoteController", Constantes.NOME_PACOTE_CONTROLLER)
				.replace("entidadePacote", Constantes.NOME_PACOTE_ENTIDADE);
	}

	private String getNomeClasse() {
		return this.nomeEntidade + Constantes.NOME_FINAL_MODEL_ASSEMBLER;
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

	private String getTemplate() {
		String template = "package nomePacote.hateoasPacote;\n"
				+ "\n"
				+ "import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;\n"
				+ "import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;\n"
				+ "\n"
				+ "import org.springframework.hateoas.Link;\n"
				+ "import org.springframework.hateoas.server.RepresentationModelAssembler;\n"
				+ "import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;\n"
				+ "import org.springframework.stereotype.Component;\n"
				+ "\n"
				+ "import nomePacote.entidadePacote.codigoEntidade;\n"
				+ "import nomePacote.pacoteController.nomeEntidadeController;\n"
				+ "import nomePacote.mapStructPacote.nomeEntidadeMapper;\n"
				+ "import nomePacote.pacoteResponse.nomeEntidadeResponse;\n"
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@RequiredArgsConstructor\n"
				+ "@Component\n"
				+ "public class nomeEntidadeModelAssembler implements RepresentationModelAssembler<codigoEntidade, nomeEntidadeResponse> {\n"
				+ "	private final nomeEntidadeMapper variavelEntidadeMapper;\n"
				+ "	\n"
				+ "	@Override\n"
				+ "	public nomeEntidadeResponse toModel(codigoEntidade entity) {\n"
				+ "		nomeEntidadeResponse variavelEntidadeResponse = variavelEntidadeMapper.toResponse(entity);\n"
				+ "		\n"
				+ "		variavelEntidadeResponse.add(\n"
				+ "				linkTo(methodOn(nomeEntidadeController.class)\n"
				+ "						.deletar(entity.getCodnomeEntidade())).withRel(\"deletar\"));\n"
				+ "		\n"
				+ "		variavelEntidadeResponse.add(\n"
				+ "				linkTo(methodOn(nomeEntidadeController.class)\n"
				+ "						.salvar(null)).withRel(\"salvar\"));\n"
				+ "		\n"
				+ "		variavelEntidadeResponse.add(\n"
				+ "				linkTo(methodOn(nomeEntidadeController.class)\n"
				+ "						.atualizar(null, entity.getCodnomeEntidade())).withRel(\"atualizar\"));\n"
				+ "		\n"
				+ "		variavelEntidadeResponse.add(\n"
				+ "				getSelfLink(entity.getCodnomeEntidade()));\n"
				+ "		\n"
				+ "		// TODO: verificar nome no plural.\n"
				+ "		variavelEntidadeResponse.add(getSelfLinkPesquisa().withRel(\"variavelEntidades\"));\n"
				+ "		return variavelEntidadeResponse;\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Link getSelfLink(Integer codnomeEntidade) {\n"
				+ "		return\n"
				+ "			linkTo(WebMvcLinkBuilder.methodOn(nomeEntidadeController.class)\n"
				+ "					.buscarPorId(codnomeEntidade)).withSelfRel();\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Link getSelfLinkPesquisa() {\n"
				+ "		return linkTo(WebMvcLinkBuilder.methodOn(nomeEntidadeController.class)\n"
				+ "				.pesquisar(null, null)).withSelfRel();\n"
				+ "	}\n"
				+ "}\n"
				+ "";
		return template;
	}


}
