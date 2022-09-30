package br.org.curitiba.ici.gtm.pessoa.web.controller.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO: realizar correção nos nomes dos relations
@Relation(itemRelation = "usuario", collectionRelation = "usuarios")
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {
	
	private String nome;
	private String mae;
	private String cpf;
	private Integer codUsuario;
	private String pai;
}