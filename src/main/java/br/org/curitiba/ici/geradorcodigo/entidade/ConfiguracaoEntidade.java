package br.org.curitiba.ici.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import lombok.AllArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Setter
public class ConfiguracaoEntidade implements ArquivoCodigo {
	private final String nomeTabela;
	private final String nomePacote;
	private final String nomeClasse;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;
	

	private String getCodigoClasse() {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("nomePacote", NomeCodigoType.ENTIDADE.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.ENTIDADE.classe(nomeClasse));
		valuesMap.put("imports", getCodigoImports());
		valuesMap.put("nomeTabela", nomeTabela);
		valuesMap.put("atributos", this.getCodigoAtributos());
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplateClasse());
	}

	private String getTemplateClasse() {
		return
				"package ${nomePacote};\n" +
				"${imports}\n\n" + 
				"@Data\n" +
				"@Entity\n" +
				"@Table(name = \"${nomeTabela}\")\n" +
				"public class ${nomeClasse} {\n" +
				"${atributos}\n" +
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
	public String getCodigoGerado() {
		return getCodigoClasse();
	}

	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.ENTIDADE.arquivo(nomePacote, nomeClasse);
	}
}

