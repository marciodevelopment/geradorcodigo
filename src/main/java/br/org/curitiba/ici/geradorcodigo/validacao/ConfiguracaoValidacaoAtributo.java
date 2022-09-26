package br.org.curitiba.ici.geradorcodigo.validacao;

import org.apache.commons.lang3.StringUtils;

import br.org.curitiba.ici.geradorcodigo.common.Configuracao;
import br.org.curitiba.ici.geradorcodigo.common.LinhaCodigoImplementada;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ConfiguracaoValidacaoAtributo implements Configuracao {
	private String nomeValidacao;
	private String mensagem;
	private String complemento;

	private String getLinhaCodigo() {
		StringBuilder configuracao = new StringBuilder(Validador.toValidador(nomeValidacao).getAnotacaoValidador()).append("("); 
		if (!StringUtils.isAllBlank(mensagem))
			configuracao.append("message=\"").append(mensagem).append("\"");
		if (!StringUtils.isAllBlank(mensagem) && !StringUtils.isAllBlank(complemento))
			configuracao.append(", ");
		if (!StringUtils.isAllBlank(complemento))
			configuracao.append(complemento);
		configuracao.append(")");
		return configuracao.toString();
	}
	

	@Override
	public LinhaCodigoImplementada gerarConfiguacao() {
		return new LinhaCodigoImplementada()
				.addLinhaCodigo(this.getLinhaCodigo())
				.addImport(Validador.toValidador(nomeValidacao).getImport());
	}

}
