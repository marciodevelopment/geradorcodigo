package br.org.curitiba.ici.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.org.curitiba.ici.geradorcodigo.common.Configuracao;
import br.org.curitiba.ici.geradorcodigo.common.LinhaCodigoImplementada;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Data
public class ConfiguracaoAtributo implements Configuracao {
	private final boolean id;
	private final String nomeColunaBanco;
	private final String nomeAtributo;
	private final String tipoAtributo;
	private List<String> codigos  = new ArrayList<>();
	private HashSet<String> imports  = new HashSet<>();
	
	@Setter
	private List<Configuracao> validacoes = new ArrayList<>();
	
	public ConfiguracaoAtributo addValidacao(Configuracao validacao) {
		validacoes.add(validacao);
		return this;
	}
	
	private List<String> getCodigoGerado() {
		carregarCodigosValidacoes();
		if (isId()) {
			codigos.add("@Id");
			codigos.add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
		}
		codigos.add("@Column(name = \"" + nomeColunaBanco + "\")");
		codigos.add("private " + tipoAtributo + " " + nomeAtributo);
		return codigos;
	}
	
	private void carregarCodigosValidacoes() {
		if (validacoes == null)
			return;
		this.validacoes.forEach(validacao -> codigos.addAll(validacao.gerarConfiguacao().getLinhasCodigoGerado()));
	}
	
	private void carregarImportsValidacoes() {
		if (validacoes == null)
			return;
		this.validacoes.forEach(validacao -> imports.addAll(validacao.gerarConfiguacao().getImportsGerado()));
	}

	private HashSet<String> getImportGerado() {
		carregarImportsValidacoes();
		if (id) {
			imports.add("javax.persistence.Id");
			imports.add("javax.persistence.GeneratedValue");
			imports.add("javax.persistence.GenerationType");
		}
		imports.add("javax.persistence.Column");
		return imports;
	}
	
	@Override
	public LinhaCodigoImplementada gerarConfiguacao() {
		return new LinhaCodigoImplementada()
				.addLinhaCodigo(getCodigoGerado())
				.addImport(getImportGerado());
	}
	
}
