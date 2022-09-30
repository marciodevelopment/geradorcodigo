package br.org.curitiba.ici.geradorcodigo.web.request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoPesquisaRequest implements ArquivoCodigo {
	
	private String nomePacote;
	private String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;

	private String getCodigoClasse() {
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("nomePacote", NomeCodigoType.REQUEST_PESQUISA.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.REQUEST_PESQUISA.classe(nomeEntidade));
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

	private String getTemplate() {
		return "package ${nomePacote};\n"
				+ "\n"
				+ "import lombok.Getter;\n"
				+ "import lombok.Setter;\n"
				+ "\n"
				+ "@Getter\n"
				+ "@Setter\n"
				+ "public class ${nomeClasse} {\n"
				+ "	${atributos}\n"
				+ "}";
	}

	@Override
	public String getCodigoGerado() {
		return getCodigoClasse();
	}

	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.REQUEST_PESQUISA.arquivo(nomePacote, nomeEntidade);
	}
}
