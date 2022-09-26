package br.org.curitiba.ici.geradorcodigo.leituraarquivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.org.curitiba.ici.geradorcodigo.common.ExcecaoNegocio;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArquivoConfiguracao {
	private String pacote;
	private String pastaArquivos;
	private List<Entidade> entidades;


	public List<Entidade> getEntidades() {
		if (this.entidades == null)
			return new ArrayList<>();
		return entidades;
	}


	public String getNomeAtributoId() {
		Optional<Atributo> possivelId = this.getEntidades().get(0).getAtributos().stream().filter(atributo -> atributo.isId()).findFirst();
		return
				possivelId
				.orElseThrow(() -> new ExcecaoNegocio("É necessário inserir um Id na entidade principal"))
				.getNome();
	}


	public List<String> getAtributosPesquisaIniciaEm() {
		return
			this.getEntidades().get(0).getAtributos()
			.stream()
			.filter(Atributo::isString)
			.map(Atributo::getNome)
			.collect(Collectors.toList());
		
	}
}
