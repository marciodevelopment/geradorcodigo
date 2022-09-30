package br.org.curitiba.ici.geradorcodigo.common;

public enum NomeCodigoType {
	ENTIDADE("Entity", "entity"),
	SERVICO("Service", "service"),
	REPOSITORY("Repository", "repository"),
	CONTROLE("Controller", "web.controller"),
	HATEOAS("ModelAssembler", "web.controller.hateoas"),
	PESQUISA_HATEOAS("PesquisaModelAssembler", "web.controller.hateoas"),
	MAPPER("Mapper", "web.controller.mapstruct"),
	REQUEST_ATUALIZACAO("AtualizacaoRequest", "web.controller.request"),
	REQUEST_NOVO("NovoRequest", "web.controller.request"),
	REQUEST_PESQUISA("PesquisaRequest", "web.controller.request"),
	RESPONSE("Response", "web.controller.response"),
	RESPONSE_PESQUISA("PesquisaResponse", "web.controller.response");
	
	
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
	
	public String variavelEntidade(String nomeEntidade) {
		return nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length());
	}
	
	public String relations(String nomeEntidade) {
		return nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length()) + "s";
	}
	
	public String relation(String nomeEntidade) {
		return nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length());
	}
	
	public static String getNomeAtributoId(String nomeAtributoId) {
		return "get" + nomeAtributoId.substring(0, 1).toUpperCase() + nomeAtributoId.substring(1, nomeAtributoId.length()) + "()";
	}
}
