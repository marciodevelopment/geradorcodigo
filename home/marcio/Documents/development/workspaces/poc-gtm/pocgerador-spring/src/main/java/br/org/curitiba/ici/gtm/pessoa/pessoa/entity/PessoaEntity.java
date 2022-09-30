package br.org.curitiba.ici.gtm.pessoa.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Id;

@Data
@Entity
@Table(name = "GTM_USUSUARIO")
public class PessoaEntity {

	@NotEmpty(message="Nome Usuario")
	@Size(message="Nome Usuario", max = 255)
	@Column(name = "Nme_Iusuario")
	private String nome;

	@NotEmpty(message="Nome Mãe")
	@Size(message="Nome Mãe", max = 255)
	@Column(name = "NMe_mae")
	private String mae;

	@NotEmpty(message="Número CPF")
	@Size(message="Número CPF", max = 20)
	@Column(name = "nr_cpf")
	private String cpf;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Cod_Usuario")
	private Integer codUsuario;

	@NotEmpty(message="Nome Pai")
	@Size(message="Nome Pai", max = 255)
	@Column(name = "NMe_pai")
	private String pai;

}