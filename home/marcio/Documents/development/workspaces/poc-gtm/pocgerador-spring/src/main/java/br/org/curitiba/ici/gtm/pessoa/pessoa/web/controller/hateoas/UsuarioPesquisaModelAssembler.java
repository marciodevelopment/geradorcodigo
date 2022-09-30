package br.org.curitiba.ici.gtm.pessoa.web.controller.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.pessoa.entity.UsuarioEntity;
import br.org.curitiba.ici.gtm.pessoa.web.controller.UsuarioController;
import br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct.UsuarioMapper;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.UsuarioPesquisaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UsuarioPesquisaModelAssembler implements RepresentationModelAssembler<UsuarioEntity, UsuarioPesquisaResponse> {
	private final UsuarioMapper usuarioMapper;
	private final PagedResourcesAssembler<UsuarioEntity> pageResourceAssembler;
	
	@Override
	public UsuarioPesquisaResponse toModel(UsuarioEntity entity) {
		return usuarioMapper
				.toUsuarioPesquisaResponse(entity)
				.add(getSelfLinkItem(entity.getCodUsuario()));
	}
	
	private Link getSelfLinkItem(Integer codUsuario) {
		return
			linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
					.buscarPorId(codUsuario)).withSelfRel();
	}
	
	public PagedModel<UsuarioPesquisaResponse> toCollectionUsuarioPesquisaModelAssembler(
			Page<UsuarioEntity> pageusuarios) {
		return pageResourceAssembler.toModel(pageusuarios, this);
	}
	
	public Link getSelfLink() {
		return linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
				.pesquisar(null, null)).withSelfRel();
	}
}
