package br.org.curitiba.ici.geradorcodigo.servico;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoFinal;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoServico implements ArquivoFinal {
	private String nomeEntidade;
	private String nomePacote;
	private String nomeAtributoId;


	private String getCodigoServico() {
		String nomeRepositorio = nomeEntidade + Constantes.NOME_FINAL_REPOSITORIO;
		String variavelRepositorio = nomeRepositorio.substring(0, 1).toLowerCase() +
				nomeRepositorio.substring(1, nomeRepositorio.length());
		
		return
				getTemplate()
				.replace("nomePacote", nomePacote)
				.replace("servicoPacote", Constantes.NOME_PACOTE_SERVICO)
				.replace("nome_Pacote_Entidade", Constantes.NOME_PACOTE_ENTIDADE)
				.replace("nomeEntidade", nomeEntidade + Constantes.NOME_FINAL_ENTIDADE)
				.replace("nome_Pacote_Repositorio", Constantes.NOME_PACOTE_REPOSTITORIO)
				.replace("nomeRepositorio", nomeRepositorio)
				.replace("nomeServico", nomeEntidade + Constantes.NOME_PACOTE_SERVICO)
				.replace("variavelRepositorio", variavelRepositorio)
				.replace("nomeAtributoId", nomeAtributoId)
				.replace("mensagemNotFound", getMensagemNotFound())
				.replace("nomeVariavelEntidade", getNomeVariavelEntidade())
				.replace("getAtributoId", getAtributoId())
				.replace("mensagemConstraintViolationPersist", getMensagemConstraintViolationPersist())
				.replace("mensagemConstraintViolationUpdate", getMensagemConstraintViolationUpdate())
				;
	
	}
	
	private String getNomeVariavelEntidade() {
		return nomeEntidade.substring(0, 1).toLowerCase() + nomeEntidade.substring(1, nomeEntidade.length());
	}
	
	private String getMensagemNotFound() {
		return nomeEntidade + " não encontrado para o código nomeVariavelEntidade enviado";
	}
	
	private String getMensagemConstraintViolationPersist() {
		return nomeEntidade + " já existente para o código de " + getNomeVariavelEntidade()  + ".\", \"" + getNomeVariavelEntidade() + "." + nomeAtributoId;
	}
	
	private String getAtributoId() {
		return "get" + nomeAtributoId.substring(0, 1).toUpperCase() + nomeAtributoId.substring(1, nomeAtributoId.length());
	}
	
	private String getMensagemConstraintViolationUpdate() {
		return nomeEntidade + " não existente para o código de " + getNomeVariavelEntidade()  + ".\", \"" + getNomeVariavelEntidade() + "." + nomeAtributoId;
	}
	
	
	
	private String getTemplate() {
		return "package nomePacote.servicoPacote;\n"
				+ "\n"
				+ "import java.util.Collection;\n"
				+ "import java.util.Optional;\n"
				+ "\n"
				+ "import javax.persistence.EntityNotFoundException;\n"
				+ "\n"
				+ "import org.springframework.data.domain.Sort;\n"
				+ "import org.springframework.data.domain.Sort.Direction;\n"
				+ "import org.springframework.stereotype.Service;\n"
				+ "\n"
				
				+ "import nomePacote.nome_Pacote_Entidade.nomeEntidade;\n"
				+ "import nomePacote.nome_Pacote_Repositorio.nomeRepositorio;\n"
				
				+ "import lombok.RequiredArgsConstructor;\n"
				+ "\n"
				+ "@RequiredArgsConstructor\n"
				+ "@Service\n"
				+ "public class nomeServico {\n"
				+ "	private final nomeRepositorio variavelRepositorio;\n"
				+ "	\n"
				+ "	public nomeEntidade getReference(Integer nomeAtributoId) {\n"
				+ "		return variavelRepositorio.getReferenceById(nomeAtributoId);\n"
				+ "	}\n"
				+ "\n"
				+ "	// TODO: inserir o nome-atributo de ordenação\n"
				+ "	public Collection<nomeEntidade> listAll() {\n"
				+ "		return this.variavelRepositorio.findAll(Sort.by(Direction.ASC, \"nome-atributo\"));\n"
				+ "	}\n"
				+ "\n"
				+ "	public Optional<nomeEntidade> findByOptionalId(Integer nomeAtributoId) {\n"
				+ "		return variavelRepositorio.findById(nomeAtributoId);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public nomeEntidade findById(Integer nomeAtributoId) {\n"
				+ "		return variavelRepositorio.findById(nomeAtributoId).orElseThrow(() -> new EntityNotFoundException(\"mensagemNotFound\"));\n"
				+ "	}\n"
				+ "	\n"
				+ "	public Collection<nomeEntidade> pesquisar(Optional<String> nomePessoa, Pageable pageable) {\n"
				+ "		// TODO: Criar implementação de pesquisa\n"
				+ "		return null;\n"
				+ "	}\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public nomeEntidade persist(nomeEntidade nomeVariavelEntidade) {\n"
				+ "		if (variavelRepositorio.existsById(nomeVariavelEntidade.getAtributoId())) {\n"
				+ "			throw new ConstraintViolationException(\"mensagemConstraintViolationPersist\");\n"
				+ "		}\n"
				+ "		return variavelRepositorio.save(nomeVariavelEntidade);\n"
				+ "	}\n"
				+ "\n"
				+ "	@Transactional\n"
				+ "	public nomeEntidade update(nomeEntidade request) {\n"
				+ "		if (!variavelRepositorio.existsById(nomeVariavelEntidade.getAtributoId())) {\n"
				+ "			throw new ConstraintViolationException(\"mensagemConstraintViolationUpdate\");\n"
				+ "		}\n"
				+ "		return variavelRepositorio.save(request);\n"
				+ "	}\n"
				+ "	\n"
				+ "	@Transactional\n"
				+ "	public void deleteById(Integer codPessoa) {\n"
				+ "		if (!variavelRepositorio.existsById(codPessoa)) {\n"
				+ "			throw new NotFoundException(\"mensagemConstraintViolationUpdate\");\n"
				+ "		}\n"
				+ "		variavelRepositorio.deleteById(codPessoa);\n"
				+ "	}\n"
				+ "\n"
				+ "}";

	}


	@Override
	public String getArquivo() {
		return getCodigoServico();
	}
	@Override
	public String getPasta() {
		// TODO Auto-generated method stub
		return null;
	}



}
