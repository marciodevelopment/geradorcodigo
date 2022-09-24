package br.org.curitiba.ici.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoFinal;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
public class ConfiguracaoEntidade implements ArquivoFinal {
	private String nomeTabela;
	private String nomePacote;
	private String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;
	

	private String getCodigoClasse() {
		String templateClasse = getTemplateClasse();
		return
				templateClasse
				.replace("nomePacote", nomePacote)
				.replace("imports", this.getCodigoImports())
				.replace("nomeTabela", nomeTabela)
				.replace("nomeEntidade", nomeEntidade + Constantes.NOME_FINAL_ENTIDADE)
				.replace("atributos", this.getCodigoAtributos());
	}

	private String getTemplateClasse() {
		return
				"package nomePacote \n" +
				"imports\n\n" + 
				"@Getter\n" +
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
		imports.add("lombok.Getter");
		imports.add("javax.persistence.Table");
		imports.add("lombok.Getter");
		imports.add("javax.persistence.Entity");
		return imports;
	}

	@Override
	public String getArquivo() {
		return getCodigoClasse();
	}

	@Override
	public String getPasta() {
		// TODO Auto-generated method stub
		return null;
	}

	public ConfiguracaoEntidade(String nomeTabela, String nomePacote, String nomeEntidade,
			HashSet<ConfiguracaoAtributo> configuracoesAtributo) {
		super();
		this.nomeTabela = nomeTabela;
		this.nomePacote = nomePacote;
		this.nomeEntidade = nomeEntidade;
		this.configuracoesAtributo = configuracoesAtributo;
	}

}

