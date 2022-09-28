package br.org.curitiba.ici.geradorcodigo.web.request;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoNovaEntidadeRequest implements ArquivoCodigo {
	private String nomePacote;
	private String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;
	

	private String getCodigoClasse() {
		String templateClasse = getTemplateClasse();
		return
				templateClasse
				.replace("nomePacote", getPacoteRequest())
				.replace("imports", this.getCodigoImports())
				.replace("nomeEntidade", getNomeClasse())
				.replace("atributos", this.getCodigoAtributos());
	}

	private String getPacoteRequest() {
		return nomePacote + "." + Constantes.NOME_PACOTE_REQUEST;
	}

	private String getNomeClasse() {
		return Constantes.NOME_INICIO_NOVA_ENTIDADE + nomeEntidade + Constantes.NOME_FINAL_REQUEST;
	}

	private String getTemplateClasse() {
		return
				"package nomePacote; \n" +
				"imports\n\n" + 
				"@Data\n" +
				"public class nomeEntidade {\n" +
				"atributos\n" +
				"}";

	}
	
	private String getCodigoAtributos() {
		if (configuracoesAtributo == null)
			return "";

		List<String> codigos = new ArrayList<>();
		this.configuracoesAtributo
			.stream()
			.filter(configuracao -> !configuracao.isId())
			.forEach(configuracao -> 
				configuracao
				.getCodigos()
				.stream()
				.forEach(codigos::add));
		
		return				
				codigos
				.stream()
				.filter(codigo -> codigoDeveSerInserido(codigo))
				.reduce("", (codigoParcial, codigoCorrente) -> codigoParcial + "\n\t" + codigoCorrente + (codigoCorrente.contains("@") ? "" :";\n"));
	}

	private boolean codigoDeveSerInserido(String codigo) {
		boolean codigoExclusivoEntidade = codigo.contains("@Column") ||
				codigo.contains("@Id") ||
				codigo.contains("@GeneratedValue");
		return !codigoExclusivoEntidade;
	}

	private String getCodigoImports() {
		return
				getImports()
				.stream()
				.filter(imports -> !imports.contains("persistence"))
				.reduce("", (codigoParcial, codigoCorrente) -> codigoParcial + "\n" + "import " + codigoCorrente + ";");
	}

	private HashSet<String> getImports() {
		HashSet<String> imports = getImportsEntidade();
		if (configuracoesAtributo != null)
			this.configuracoesAtributo.forEach(validacao -> imports.addAll(validacao.gerarConfiguacao().getImportsGerado()));
		return imports;
	}

	private HashSet<String> getImportsEntidade() {
		HashSet<String> imports = new HashSet<>();
		imports.add("lombok.Data");
		return imports;
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
