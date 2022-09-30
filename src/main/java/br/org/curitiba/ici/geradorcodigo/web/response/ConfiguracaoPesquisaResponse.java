package br.org.curitiba.ici.geradorcodigo.web.response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoPesquisaResponse implements ArquivoCodigo {
	private String nomePacote;
	private String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;

	private String getCodigoClasse() {
		
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("nomePacote", NomeCodigoType.RESPONSE_PESQUISA.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.RESPONSE_PESQUISA.classe(nomeEntidade));
		valuesMap.put("nomeRelation", NomeCodigoType.ENTIDADE.relation(nomeEntidade));
		valuesMap.put("nomeRelations", NomeCodigoType.ENTIDADE.relations(nomeEntidade));
		valuesMap.put("atributos", this.getCodigoAtributos());
		
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}

	

	private String getCodigoAtributos() {
		if (configuracoesAtributo == null)
			return "";
		return
				this.configuracoesAtributo
				.stream()
				.map(configuracao -> "private " + configuracao.getTipoAtributo() + " " + configuracao.getNomeAtributo() + ";")
				.reduce("", (codigoParcial, codigoCorrente) -> codigoParcial + "\n\t" + codigoCorrente);

	}

	@Override
	public String getCodigoGerado() {
		return getCodigoClasse();
	}

	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.RESPONSE_PESQUISA.arquivo(nomePacote, nomeEntidade);
	}
	
	private String getTemplate() {
		return "package ${nomePacote};\n"
				+ "\n"
				+ "import org.springframework.hateoas.RepresentationModel;\n"
				+ "import org.springframework.hateoas.server.core.Relation;\n"
				+ "\n"
				+ "import lombok.Getter;\n"
				+ "import lombok.Setter;\n"
				+ "\n"
				+ "@Getter\n"
				+ "@Setter\n"
				+ "// TODO: realizar correção nos nomes dos relations\n"
				+ "@Relation(itemRelation = \"${nomeRelation}\", collectionRelation = \"${nomeRelations}\")\n"
				+ "public class ${nomeClasse} extends RepresentationModel<${nomeClasse}> {\n"
				+ "	${atributos}\n"
				+ "}";
	}
}
