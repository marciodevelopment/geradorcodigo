package br.org.curitiba.ici.gtm.pessoa.web.controller.request; 

import javax.validation.constraints.Size;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class UsuarioNovoRequest {

	@NotEmpty(message="Nome Usuario")
	@Size(message="Nome Usuario", max = 255)
	private String nome;

	@NotEmpty(message="Nome Mãe")
	@Size(message="Nome Mãe", max = 255)
	private String mae;

	@NotEmpty(message="Número CPF")
	@Size(message="Número CPF", max = 20)
	private String cpf;

	@NotEmpty(message="Nome Pai")
	@Size(message="Nome Pai", max = 255)
	private String pai;

}