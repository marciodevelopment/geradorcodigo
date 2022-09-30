package br.org.curitiba.ici.gtm.pessoa.web.controller.mapstruct;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import br.org.curitiba.ici.gtm.common.web.response.PageResponse;
import br.org.curitiba.ici.gtm.pessoa.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.pessoa.service.PessoaService;
import br.org.curitiba.ici.gtm.pessoa.web.controller.request.PessoaAtualizacaoRequest;
import br.org.curitiba.ici.gtm.pessoa.web.controller.request.PessoaNovoRequest;
import br.org.curitiba.ici.gtm.pessoa.web.controller.request.PessoaPesquisaRequest;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.PessoaPesquisaResponse;
import br.org.curitiba.ici.gtm.pessoa.web.controller.response.PessoaResponse;

@Mapper(componentModel = "spring", 
implementationPackage = "Pessoa.web.controller.mapstruct")
public abstract class PessoaMapper {
	@Autowired
	private PessoaService pessoaService; 
	
	public abstract PessoaEntity toEntity(PessoaNovoRequest request);
	public abstract PessoaEntity toEntity(PessoaPesquisaRequest request);
	public abstract PessoaEntity toEntity(PessoaAtualizacaoRequest request);
	public abstract PessoaPesquisaResponse toPesquisaResponse(PessoaEntity entity);
	public abstract Collection<PessoaPesquisaResponse> toPesquisaResponse(Collection<PessoaEntity> entity);
	public abstract nomeEntidadeResponse toResponse(PessoaEntity entity);
	
	public PageResponse<PessoaPesquisaResponse> toPage(Page<PessoaEntity> pageEntity) {
		List<PessoaPesquisaResponse> responses = pageEntity
				.stream()
				.map(this::toPesquisaResponse)
				.collect(Collectors.toList());
		return new PageResponse<>(pageEntity, responses);
	}
	
	public PessoaEntity toEntity(Integer id, PessoaAtualizacaoRequest request) {
		PessoaEntity entityBanco = pessoaService.buscarPorId(id);
		BeanUtils.copyProperties(request, entityBanco);
		return entityBanco;
	}
}
