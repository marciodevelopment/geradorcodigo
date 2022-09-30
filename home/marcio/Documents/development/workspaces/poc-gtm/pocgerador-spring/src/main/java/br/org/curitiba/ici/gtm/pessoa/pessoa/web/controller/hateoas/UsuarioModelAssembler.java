package br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.pessoa.entity.UsuarioEntity;
import br.org.curitiba.ici.gtm.pessoa.web.controller.UsuarioController ;
import br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct.UsuarioMapper;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UsuarioMapper implements RepresentationModelAssembler<UsuarioEntity, UsuarioResponse> {
	private final UsuarioMapper usuarioMapper;
	
	@Override
	public UsuarioResponse toModel(UsuarioEntity entity) {
		UsuarioResponse usuarioMapper = usuarioMapper.toResponse(entity);
		
		usuarioMapper.add(
				linkTo(methodOn(UsuarioController.class)
						.deletar(entity.{getAtributoId}())).withRel("deletar"));
		
		usuarioMapper.add(
				linkTo(methodOn(UsuarioController.class)
						.salvar(null)).withRel("salvar"));
		
		usuarioMapper.add(
				linkTo(methodOn(UsuarioController.class)
						.atualizar(null, entity.getCodUsuario()())).withRel("atualizar"));
		
		usuarioMapper.add(
				getSelfLink(entity.getCodUsuario()()));
		
		// TODO: verificar nome no plural.
		usuarioMapper.add(getSelfLinkPesquisa().withRel("usuarios"));
		return usuarioMapper;
	}
	
	public Link getSelfLink(Integer codUsuario) {
		return
			linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
					.buscarPorId(codUsuario)).withSelfRel();
	}
	
	public Link getSelfLinkPesquisa() {
		return linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
				.pesquisar(null, null)).withSelfRel();
	}
}
