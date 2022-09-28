package br.org.curitiba.ici.geradorcodigo.repositorio;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.Constantes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoRepositorio implements ArquivoCodigo {
	private String nomePacote;
	private String nomeEntidade;
	
	
	private String getCodigoRepositorio() {
		return
				getTemplate()
				.replace("nomePacote_Entidade", nomePacote + "." + Constantes.NOME_PACOTE_ENTIDADE + ".nomeEntidade")
				.replace("nomePacote", getNomePacote())
				.replace("nomeEntidade", nomeEntidade + Constantes.NOME_FINAL_ENTIDADE)
				.replace("nomeRepositorio", getNomeRepositorio());
				
	}

	private String getNomeRepositorio() {
		return nomeEntidade + Constantes.NOME_FINAL_REPOSITORIO;
	}

	private String getNomePacote() {
		return nomePacote + "." + Constantes.NOME_PACOTE_REPOSTITORIO;
	}
	
	private String getTemplate() {
		return "package nomePacote;\n"
				+ "\n"
				+ "import org.springframework.data.jpa.repository.JpaRepository;\n"
				+ "import nomePacote_Entidade;\n"
				+ "\n"
				+ "public interface nomeRepositorio extends JpaRepository<nomeEntidade, Integer> {\n"
				+ "\n"
				+ "}\n";
	}
	
	@Override
	public String getCodigoGerado() {
		return getCodigoRepositorio();
	}
	
	@Override
	public String getCaminhoPacoteClasse() {
		return this.getNomePacote() + "." + getNomeRepositorio() + ".java";
	}
	
	
}
