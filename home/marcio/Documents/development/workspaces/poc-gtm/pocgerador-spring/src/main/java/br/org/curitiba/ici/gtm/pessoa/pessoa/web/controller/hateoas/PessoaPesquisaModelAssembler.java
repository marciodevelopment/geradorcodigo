package br.org.curitiba.ici.gtm.pessoa.web.controller.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.pessoa.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.pessoa.web.controller.PessoaController;
import br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct.PessoaMapper;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.PessoaPesquisaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PessoaPesquisaModelAssembler implements RepresentationModelAssembler<PessoaEntity, PessoaPesquisaResponse> {
	private final PessoaMapper pessoaMapper;
	private final PagedResourcesAssembler<PessoaEntity> pageResourceAssembler;
	
	@Override
	public PessoaPesquisaResponse toModel(PessoaEntity entity) {
		return pessoaMapper
				.toPessoaPesquisaResponse(entity)
				.add(getSelfLinkItem(entity.getCodUsuario()));
	}
	
	private Link getSelfLinkItem(Integer codUsuario) {
		return
			linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
					.buscarPorId(codUsuario)).withSelfRel();
	}
	
	public PagedModel<PessoaPesquisaResponse> toCollectionPessoaPesquisaModelAssembler(
			Page<PessoaEntity> pagepessoas) {
		return pageResourceAssembler.toModel(pagepessoas, this);
	}
	
	public Link getSelfLink() {
		return linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
				.pesquisar(null, null)).withSelfRel();
	}
}
