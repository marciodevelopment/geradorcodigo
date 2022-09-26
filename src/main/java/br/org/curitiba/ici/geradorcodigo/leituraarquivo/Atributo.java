package br.org.curitiba.ici.geradorcodigo.leituraarquivo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Atributo {
	private String nome;
	private String coluna;
	private String nomeExibicao;
	private String tipo;
	private boolean id;
	private List<ValidadorEntidade> validadores;
	
	public List<ValidadorEntidade> getValidadores() {
		if (this.validadores == null)
			return new ArrayList<>();
		return this.validadores;
	}

	public boolean isString() {
		return "string".equalsIgnoreCase(tipo);
	}
	
}
