package br.org.curitiba.ici.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoFinal;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Setter
public class ConfiguracaoEntidade implements ArquivoFinal {
	private final String nomeTabela;
	private final String nomePacote;
	private final String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;
	

	private String getCodigoClasse() {
		String templateClasse = getTemplateClasse();
		return
				templateClasse
				.replace("nomePacote", getPacote())
				.replace("imports", this.getCodigoImports())
				.replace("nomeTabela", nomeTabela)
				.replace("nomeEntidade", getNomeEntidade())
				.replace("atributos", this.getCodigoAtributos());
	}

	private String getNomeEntidade() {
		return nomeEntidade + Constantes.NOME_FINAL_ENTIDADE;
	}

	private String getPacote() {
		return nomePacote + "." + Constantes.NOME_PACOTE_ENTIDADE;
	}

	private String getTemplateClasse() {
		return
				"package nomePacote; \n" +
				"imports\n\n" + 
				"@Data\n" +
				"@Entity\n" +
				"@Table(name = \"nomeTabela\")\n" +
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
			.forEach(configuracao -> 
				configuracao.getCodigos().forEach(codigos::add));
		
		return				
				codigos
				.stream()
				.reduce("", (codigoParcial, codigoCorrente) -> codigoParcial + "\n\t" + codigoCorrente + (codigoCorrente.contains("@") ? "" :";\n"));
	}

	private String getCodigoImports() {
		return
				getImports()
				.stream()
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
		imports.add("javax.persistence.Table");
		imports.add("javax.persistence.Entity");
		return imports;
	}

	@Override
	public String getArquivo() {
		return getCodigoClasse();
	}

	@Override
	public String getPasta() {
		return this.getPacote() + "." + getNomeEntidade() + ".java";
	}
}

