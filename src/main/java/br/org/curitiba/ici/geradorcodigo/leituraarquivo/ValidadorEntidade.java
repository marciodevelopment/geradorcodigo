package br.org.curitiba.ici.geradorcodigo.leituraarquivo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidadorEntidade {
	private String nome;
	private String complemento;
	private String mensagem;
	
	public boolean existeMensagem() {
		return StringUtils.isNotBlank(mensagem);
	}
}
