package br.org.curitiba.ici.geradorcodigo.web.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import br.org.curitiba.ici.geradorcodigo.entidade.ConfiguracaoAtributo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoNovaEntidadeRequest implements ArquivoCodigo {
	private String nomePacote;
	private String nomeEntidade;
	private HashSet<ConfiguracaoAtributo> configuracoesAtributo;
	

	private String getCodigoClasse() {
		
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("nomePacote", NomeCodigoType.REQUEST_NOVO.pacote(nomePacote));
		valuesMap.put("imports", this.getCodigoImports());
		valuesMap.put("nomeClasse", NomeCodigoType.REQUEST_NOVO.classe(nomeEntidade));
		valuesMap.put("atributos", this.getCodigoAtributos());
		
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}

	private String getTemplate() {
		return
				"package ${nomePacote}; \n" +
				"${imports}\n\n" + 
				"@Data\n" +
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
		return NomeCodigoType.REQUEST_NOVO.arquivo(nomePacote, nomeEntidade);
	}
}
