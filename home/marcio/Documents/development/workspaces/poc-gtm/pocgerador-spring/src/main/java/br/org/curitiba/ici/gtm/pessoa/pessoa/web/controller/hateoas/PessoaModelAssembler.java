package br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.pessoa.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.pessoa.web.controller.PessoaController ;
import br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct.PessoaMapper;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.PessoaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PessoaMapper implements RepresentationModelAssembler<PessoaEntity, PessoaResponse> {
	private final PessoaMapper pessoaMapper;
	
	@Override
	public PessoaResponse toModel(PessoaEntity entity) {
		PessoaResponse pessoaMapper = pessoaMapper.toResponse(entity);
		
		pessoaMapper.add(
				linkTo(methodOn(PessoaController.class)
						.deletar(entity.{getAtributoId}())).withRel("deletar"));
		
		pessoaMapper.add(
				linkTo(methodOn(PessoaController.class)
						.salvar(null)).withRel("salvar"));
		
		pessoaMapper.add(
				linkTo(methodOn(PessoaController.class)
						.atualizar(null, entity.getCodUsuario()())).withRel("atualizar"));
		
		pessoaMapper.add(
				getSelfLink(entity.getCodUsuario()()));
		
		// TODO: verificar nome no plural.
		pessoaMapper.add(getSelfLinkPesquisa().withRel("pessoas"));
		return pessoaMapper;
	}
	
	public Link getSelfLink(Integer codUsuario) {
		return
			linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
					.buscarPorId(codUsuario)).withSelfRel();
	}
	
	public Link getSelfLinkPesquisa() {
		return linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
				.pesquisar(null, null)).withSelfRel();
	}
}
