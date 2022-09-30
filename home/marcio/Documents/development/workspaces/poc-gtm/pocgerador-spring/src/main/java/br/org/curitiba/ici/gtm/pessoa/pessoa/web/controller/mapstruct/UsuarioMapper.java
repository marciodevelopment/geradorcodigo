package br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import br.org.curitiba.ici.gtm.common.web.response.PageResponse;
import br.org.curitiba.ici.gtm.pessoa.entity.UsuarioEntity;
import br.org.curitiba.ici.gtm.pessoa.service.UsuarioService;
import br.org.curitiba.ici.gtm.pessoa.web.controller.request.UsuarioAtualizacaoRequest;
import br.org.curitiba.ici.gtm.pessoa.web.controller.request.UsuarioNovoRequest;
import br.org.curitiba.ici.gtm.pessoa.web.controller.request.UsuarioPesquisaRequest;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.UsuarioPesquisaResponse;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.UsuarioResponse;

@Mapper(componentModel = "spring", 
implementationPackage = "Usuario.web.controller.mapstruct")
public abstract class UsuarioMapper {
	@Autowired
	private UsuarioService usuarioService; 
	
	public abstract UsuarioEntity toEntity(UsuarioNovoRequest request);
	public abstract UsuarioEntity toEntity(UsuarioPesquisaRequest request);
	public abstract UsuarioEntity toEntity(UsuarioAtualizacaoRequest request);
	public abstract UsuarioPesquisaResponse toPesquisaResponse(UsuarioEntity entity);
	public abstract Collection<UsuarioPesquisaResponse> toPesquisaResponse(Collection<UsuarioEntity> entity);
	public abstract nomeEntidadeResponse toResponse(UsuarioEntity entity);
	
	public PageResponse<UsuarioPesquisaResponse> toPage(Page<UsuarioEntity> pageEntity) {
		List<UsuarioPesquisaResponse> responses = pageEntity
				.stream()
				.map(this::toPesquisaResponse)
				.collect(Collectors.toList());
		return new PageResponse<>(pageEntity, responses);
	}
	
	public UsuarioEntity toEntity(Integer id, UsuarioAtualizacaoRequest request) {
		UsuarioEntity entityBanco = usuarioService.buscarPorId(id);
		BeanUtils.copyProperties(request, entityBanco);
		return entityBanco;
	}
}
