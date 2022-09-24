package br.org.curitiba.ici.geradorcodigo.validacao;

import java.util.stream.Stream;

import br.org.curitiba.ici.geradorcodigo.common.GeradorCodigoException;
import lombok.Getter;

public enum Validador {
	NOT_NULL("NotNull"),
	MIN("Min"),
	MAX("Max"),
	NOT_EMPTY("NotEmpty");

	@Getter
	private String validador;

	Validador(String validador) {
		this.validador = validador;
	}

	public String getImport() {
		return "javax.validation.constraints." + validador;
	}

	public String getAnotacaoValidador() {
		return "@" + this.validador;
	}
	
	static Validador toValidador(String nomeValidador) {
		return Stream.of(Validador.values()).filter(curValidador -> curValidador.getValidador().equalsIgnoreCase(nomeValidador))
				.findAny()
				.orElseThrow(() -> new GeradorCodigoException("Validação [" + nomeValidador + "] não configurada. Configurar validador na classe ImportValidadores"));
	}
	
}
