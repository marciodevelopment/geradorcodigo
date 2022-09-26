package br.org.curitiba.ici.geradorcodigo.leituraarquivo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entidade {
	private String nomeClasse;
	private String tabela;
	private List<Atributo> atributos;
	
	
	public List<Atributo> getAtributos() {
		if (this.atributos == null)
			return new ArrayList<>();
		return this.atributos;
	}
}
