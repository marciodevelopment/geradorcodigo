package br.org.curitiba.ici.geradorcodigo.common;

public enum NomeCodigoType {
	ENTIDADE("Entity", "entity"),
	SERVICO("Service", "service"),
	REPOSITORY("Repository", "repository");
	
	private String finalNome;
	private String pacoteClasse;

	NomeCodigoType(String nomeFinal, String nomePacote) {
		this.finalNome = nomeFinal;
		this.pacoteClasse = nomePacote;
	}

	public String classe(String nomeEntidade) {
		return nomeEntidade + finalNome;
	}
	
	public String pacote(String nomePacote) {
		return nomePacote + "." + pacoteClasse;
	}
	
	public String arquivo(String nomePacote, String nomeEntidade) {
		return pacoteImport(nomePacote, nomeEntidade) + ".java";
	}
	
	public String pacoteImport(String nomePacote, String nomeEntidade) {
		return nomePacote + "." + pacoteClasse + "." + this.classe(nomeEntidade);
	}

	public String variavel(String nomeEntidade) {
		return nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length()) + finalNome;
	}
	
	public static String getNomeAtributoId(String nomeAtributoId) {
		return "get" + nomeAtributoId.substring(0, 1).toUpperCase() + nomeAtributoId.substring(1, nomeAtributoId.length()) + "()";
	}
}
