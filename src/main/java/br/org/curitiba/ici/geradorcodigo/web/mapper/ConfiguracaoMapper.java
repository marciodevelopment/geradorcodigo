package br.org.curitiba.ici.geradorcodigo.web.mapper;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoFinal;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoMapper implements ArquivoFinal {
	private String nomeEntidade;
	private String nomePacote;
	
	private String getCodigoMapper() {
		String nomeServico = nomeEntidade + Constantes.NOME_FINAL_SERVICO;
		String variavelServico = nomeServico.substring(0, 1).toLowerCase() +
				nomeServico.substring(1, nomeServico.length());
		
		return
				getTemplate()
				.replace("nomePacote", nomePacote)
				.replace("nomeEntidade", nomeEntidade)
				.replace("codigoEntidade", nomeEntidade + Constantes.NOME_FINAL_ENTIDADE)
				.replace("mapStructPacote", Constantes.NOME_PACOTE_MAP_STRUCT)
				.replace("entidadePacote", Constantes.NOME_PACOTE_ENTIDADE)
				.replace("pacoteResponse", Constantes.NOME_PACOTE_RESPONSE)
				.replace("pacoteRequest", Constantes.NOME_PACOTE_REQUEST)
				.replace("nome_Pacote_Entidade", Constantes.NOME_PACOTE_ENTIDADE)
				.replace("nomeServico", nomeServico)
				.replace("variavelServico", variavelServico)
				.replace("servicoPacote", Constantes.NOME_PACOTE_SERVICO);
	}
	
	@Override
	public String getArquivo() {
		return getCodigoMapper();
	}

	@Override
	public String getPasta() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private String getTemplate() {
		return "package nomePacote.mapStructPacote;\n"
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
				+ "import nomePacote.nome_Pacote_Entidade.codigoEntidade;\n"
				+ "import nomePacote.servicoPacote.nomeServico;\n"
				+ "import nomePacote.pacoteRequest.AtualizacaonomeEntidadeRequest;\n"
				+ "import nomePacote.pacoteRequest.NovonomeEntidadeRequest;\n"
				+ "import nomePacote.pacoteRequest.PesquisanomeEntidadeRequest;\n"
				+ "import nomePacote.pacoteResponse.nomeEntidadePesquisaResponse;\n"
				+ "import nomePacote.pacoteResponse.nomeEntidadeResponse;\n"
				+ "\n"
				+ "@Mapper(componentModel = \"spring\", \n"
				+ "implementationPackage = \"nomePacote.mapStructPacote\")\n"
				+ "public abstract class nomeEntidadeMapper {\n"
				+ "	@Autowired\n"
				+ "	private nomeServico variavelServico; \n"
				+ "	\n"
				+ "	public abstract codigoEntidade toEntity(NovonomeEntidadeRequest request);\n"
				+ "	public abstract codigoEntidade toEntity(PesquisanomeEntidadeRequest request);\n"
				+ "	public abstract codigoEntidade toEntity(AtualizacaonomeEntidadeRequest request);\n"
				+ "	public abstract nomeEntidadePesquisaResponse toPesquisaResponse(codigoEntidade entity);\n"
				+ "	public abstract Collection<nomeEntidadePesquisaResponse> toPesquisaResponse(Collection<codigoEntidade> entity);\n"
				+ "	public abstract nomeEntidadeResponse toResponse(codigoEntidade entity);\n"
				+ "	\n"
				+ "	public PageResponse<nomeEntidadePesquisaResponse> toPage(Page<codigoEntidade> pageEntity) {\n"
				+ "		List<nomeEntidadePesquisaResponse> responses = pageEntity\n"
				+ "				.stream()\n"
				+ "				.map(this::toPesquisaResponse)\n"
				+ "				.collect(Collectors.toList());\n"
				+ "		return new PageResponse<>(pageEntity, responses);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public codigoEntidade toEntity(Integer id, AtualizacaonomeEntidadeRequest request) {\n"
				+ "		codigoEntidade entityBanco = variavelServico.buscarPorId(id);\n"
				+ "		BeanUtils.copyProperties(request, entityBanco);\n"
				+ "		return entityBanco;\n"
				+ "	}\n"
				+ "}\n"
				+ "";
	}

}

