package br.org.curitiba.ici.geradorcodigo.web.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoMapper implements ArquivoCodigo {
	private String nomeEntidade;
	private String nomePacote;
	
	private String getCodigoMapper() {
		
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("nomePacote", NomeCodigoType.MAPPER.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.MAPPER.classe(nomeEntidade));
		valuesMap.put("importEntidade", NomeCodigoType.ENTIDADE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importService", NomeCodigoType.SERVICO.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importRequestAtualizacao", NomeCodigoType.REQUEST_ATUALIZACAO.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importRequestNovo", NomeCodigoType.REQUEST_NOVO.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importPesquisaRequest", NomeCodigoType.REQUEST_PESQUISA.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importRequestPesquisa", NomeCodigoType.REQUEST_PESQUISA.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importResponsePesquisa", NomeCodigoType.RESPONSE_PESQUISA.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("importResponse", NomeCodigoType.RESPONSE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("nomeClasseResponse", NomeCodigoType.RESPONSE.classe(nomeEntidade));
		valuesMap.put("nomePacoteMapper", NomeCodigoType.MAPPER.pacote(nomeEntidade));
		valuesMap.put("nomeClasseService", NomeCodigoType.SERVICO.classe(nomeEntidade));
		valuesMap.put("variavelService", NomeCodigoType.SERVICO.variavel(nomeEntidade));
		valuesMap.put("nomeClasseEntidade", NomeCodigoType.ENTIDADE.classe(nomeEntidade));
		valuesMap.put("classeAtualizacaoRequest", NomeCodigoType.REQUEST_ATUALIZACAO.classe(nomeEntidade));
		valuesMap.put("classePesquisaResponse", NomeCodigoType.RESPONSE_PESQUISA.classe(nomeEntidade));
		valuesMap.put("nomeClasseRequestNovo", NomeCodigoType.REQUEST_NOVO.classe(nomeEntidade));
		valuesMap.put("classePesquisaRequest", NomeCodigoType.REQUEST_PESQUISA.classe(nomeEntidade));
		valuesMap.put("nomeClassePesquisaResponse", NomeCodigoType.RESPONSE_PESQUISA.classe(nomeEntidade));
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}

	
	
	@Override
	public String getCodigoGerado() {
		return getCodigoMapper();
	}

	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.MAPPER.arquivo(nomePacote, nomeEntidade);
	}
	

	private String getTemplate() {
		return "package ${nomePacote};\n"
				+ "\n"
				+ "import java.util.Collection;\n"
				+ "import java.util.List;\n"
				+ "import java.util.stream.Collectors;\n"
				+ "\n"
				+ "import org.mapstruct.Mapper;\n"
				+ "import org.springframework.beans.BeanUtils;\n"
				+ "import org.springframework.beans.factory.annotation.Autowired;\n"
				+ "import org.springframework.data.domain.Page;\n"
				+ "\n"
				+ "import br.org.curitiba.ici.gtm.common.web.response.PageResponse;\n"
				+ "import ${importEntidade};\n"
				+ "import ${importService};\n"
				+ "import ${importRequestAtualizacao};\n"
				+ "import ${importRequestNovo};\n"
				+ "import ${importRequestPesquisa};\n"
				+ "import ${importResponsePesquisa};\n"
				+ "import ${importResponse};\n"
				+ "\n"
				+ "@Mapper(componentModel = \"spring\", \n"
				+ "implementationPackage = \"${nomePacote}.impl\")\n"
				+ "public abstract class ${nomeClasse} {\n"
				+ "	@Autowired\n"
				+ "	private ${nomeClasseService} ${variavelService}; \n"
				+ "	\n"
				+ "	public abstract ${nomeClasseEntidade} toEntity(${nomeClasseRequestNovo} request);\n"
				+ "	public abstract ${nomeClasseEntidade} toEntity(${classePesquisaRequest} request);\n"
				+ "	public abstract ${nomeClasseEntidade} toEntity(${classeAtualizacaoRequest} request);\n"
				+ "	public abstract ${classePesquisaResponse} to${nomeClassePesquisaResponse}(${nomeClasseEntidade} entity);\n"
				+ "	public abstract Collection<${classePesquisaResponse}> to${nomeClassePesquisaResponse}(Collection<${nomeClasseEntidade}> entity);\n"
				+ "	public abstract ${nomeClasseResponse} toResponse(${nomeClasseEntidade} entity);\n"
				+ "	\n"
				+ "	public PageResponse<${classePesquisaResponse}> toPage(Page<${nomeClasseEntidade}> pageEntity) {\n"
				+ "		List<${classePesquisaResponse}> responses = pageEntity\n"
				+ "				.stream()\n"
				+ "				.map(this::to${nomeClassePesquisaResponse})\n"
				+ "				.collect(Collectors.toList());\n"
				+ "		return new PageResponse<>(pageEntity, responses);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public ${nomeClasseEntidade} toEntity(Integer id, ${classeAtualizacaoRequest} request) {\n"
				+ "		${nomeClasseEntidade} entityBanco = ${variavelService}.buscarPorId(id);\n"
				+ "		BeanUtils.copyProperties(request, entityBanco);\n"
				+ "		return entityBanco;\n"
				+ "	}\n"
				+ "}\n"
				+ "";
	}

}

