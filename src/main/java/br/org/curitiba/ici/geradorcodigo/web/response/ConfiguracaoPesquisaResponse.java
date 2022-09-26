package br.org.curitiba.ici.geradorcodigo.web.response;

import java.util.HashSet;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoFinal;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoPesquisaResponse implements ArquivoFinal {
	private String nomePacote;
	private String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;

	private String getCodigoClasse() {
		String templateClasse = getTemplateClasse();
		String nomeRelation = nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length());

		return
				templateClasse
				.replace("nomePacote", getPacoteResponse())
				.replace("nomeClasse", getNomeClasse())
				.replace("nomeRelation", nomeRelation)
				.replace("atributos", this.getCodigoAtributos());
	}

	private String getNomeClasse() {
		return nomeEntidade + Constantes.NOME_FINAL_PESQUISA_RESPONSE;
	}

	private String getPacoteResponse() {
		return nomePacote + "." + Constantes.NOME_PACOTE_RESPONSE;
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
	public String getArquivo() {
		return getCodigoClasse();
	}

	@Override
	public String getPasta() {
		return getPacoteResponse() + "." + getNomeClasse() + ".java";
	}
	
	private String getTemplateClasse() {
		return "package nomePacote;\n"
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
				+ "@Relation(itemRelation = \"nomeRelation\", collectionRelation = \"nomeRelations\")\n"
				+ "public class nomeClasse extends RepresentationModel<nomeClasse> {\n"
				+ "	atributos\n"
				+ "}";
	}
}
