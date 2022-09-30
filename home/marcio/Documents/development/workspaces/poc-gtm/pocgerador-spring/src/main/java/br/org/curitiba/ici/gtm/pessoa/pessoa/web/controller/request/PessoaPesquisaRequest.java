package br.org.curitiba.ici.gtm.pessoa.web.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaPesquisaRequest {
	
	private String nome;
	private String mae;
	private String cpf;
	private Integer codUsuario;
	private String pai;
}