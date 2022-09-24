package br.org.curitiba.ici.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.org.curitiba.ici.geradorcodigo.common.Configuracao;
import br.org.curitiba.ici.geradorcodigo.common.LinhaCodigoImplementada;
import lombok.Data;
import lombok.Setter;

@Data
public class ConfiguracaoAtributo implements Configuracao {
	private boolean id;
	private String nomeColunaBanco;
	private String nomeAtributo;
	private String tipoAtributo;
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
		if (id) {
			codigos.add("@Id");
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
