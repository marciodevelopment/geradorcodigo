package br.org.curitiba.ici.geradorcodigo.web.request;

import java.util.HashSet;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoPesquisaRequest implements ArquivoCodigo {
	
	private String nomePacote;
	private String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;

	private String getCodigoClasse() {
		String templateClasse = getTemplateClasse();
		return
				templateClasse
				.replace("nomePacote", getPacoteRequest())
				.replace("nomeClasse", getNomeClasse())
				.replace("atributos", this.getCodigoAtributos());
	}

	private String getNomeClasse() {
		return nomeEntidade + Constantes.NOME_FINAL_PESQUISA_REQUEST;
	}

	private String getPacoteRequest() {
		return nomePacote + "." + Constantes.NOME_PACOTE_REQUEST;
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

	private String getTemplateClasse() {
		return "package nomePacote;\n"
				+ "\n"
				+ "import lombok.Getter;\n"
				+ "import lombok.Setter;\n"
				+ "\n"
				+ "@Getter\n"
				+ "@Setter\n"
				+ "public class nomeClasse {\n"
				+ "	atributos\n"
				+ "}";
	}

	@Override
	public String getCodigoGerado() {
		return getCodigoClasse();
	}

	@Override
	public String getCaminhoPacoteClasse() {
		return getPacoteRequest() + "." + getNomeClasse() + ".java";
	}
}
