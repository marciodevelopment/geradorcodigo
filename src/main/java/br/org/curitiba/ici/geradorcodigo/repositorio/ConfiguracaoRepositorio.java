package br.org.curitiba.ici.geradorcodigo.repositorio;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import br.org.curitiba.ici.geradorcodigo.common.ArquivoCodigo;
import br.org.curitiba.ici.geradorcodigo.common.NomeCodigoType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfiguracaoRepositorio implements ArquivoCodigo {
	private String nomePacote;
	private String nomeEntidade;
	
	
	private String getCodigoRepositorio() {
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("nomePacote", NomeCodigoType.REPOSITORY.pacote(nomePacote));
		valuesMap.put("nomeClasse", NomeCodigoType.REPOSITORY.classe(nomeEntidade));
		valuesMap.put("pacoteEntidade", NomeCodigoType.ENTIDADE.pacoteImport(nomePacote, nomeEntidade));
		valuesMap.put("nomeEntidade", NomeCodigoType.ENTIDADE.classe(nomeEntidade));
		
		StringSubstitutor stringSubstitutor = new StringSubstitutor(valuesMap);
		return stringSubstitutor.replace(getTemplate());
	}
	
	private String getTemplate() {
		return "package ${nomePacote};\n"
				+ "\n"
				+ "import org.springframework.data.jpa.repository.JpaRepository;\n"
				+ "import ${pacoteEntidade};\n"
				+ "\n"
				+ "public interface ${nomeClasse} extends JpaRepository<${nomeEntidade}, Integer> {\n"
				+ "\n"
				+ "}\n";
	}
	
	@Override
	public String getCodigoGerado() {
		return getCodigoRepositorio();
	}
	
	@Override
	public String getCaminhoPacoteClasse() {
		return NomeCodigoType.REPOSITORY.arquivo(nomePacote, nomeEntidade);
	}
	
	
}
