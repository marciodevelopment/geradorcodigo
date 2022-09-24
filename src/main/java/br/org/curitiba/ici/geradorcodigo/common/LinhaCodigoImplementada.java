package br.org.curitiba.ici.geradorcodigo.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.Getter;

@Getter
public class LinhaCodigoImplementada {
	private List<String> linhasCodigoGerado = new ArrayList<>();
	private HashSet<String> importsGerado = new HashSet<>();
	
	public LinhaCodigoImplementada addImport(String importGerado) {
		this.importsGerado.add(importGerado);
		return this;
	}
	
	public LinhaCodigoImplementada addLinhaCodigo(String linhaCodigo) {
		this.linhasCodigoGerado.add(linhaCodigo);
		return this;
	}
	
	public LinhaCodigoImplementada addLinhaCodigo(List<String> linhaCodigo) {
		this.linhasCodigoGerado.addAll(linhaCodigo);
		return this;
	}

	public LinhaCodigoImplementada addImport(HashSet<String> imporstGerado) {
		this.importsGerado.addAll(imporstGerado);
		return this;
	}
}
