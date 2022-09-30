package br.org.curitiba.ici.geradorcodigo.servico;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ConfiguracaoServicoTest {

	private String codigoServico = "package br.org.curitiba.ici.gtm.pais.service;\n"
			+ "\n"
			+ "\n"
			+ "import java.util.Optional;\n"
			+ "\n"
			+ "import javax.persistence.EntityNotFoundException;\n"
			+ "\n"
			+ "import org.springframework.data.domain.Example;\n"
			+ "import org.springframework.data.domain.ExampleMatcher;\n"
			+ "import org.springframework.data.domain.Page;\n"
			+ "import org.springframework.data.domain.Pageable;\n"
			+ "import org.springframework.stereotype.Service;\n"
			+ "import org.springframework.transaction.annotation.Transactional;\n"
			+ "\n"
			+ "import br.org.curitiba.ici.gtm.common.exception.NotFoundException;\n"
			+ "\n"
			+ "import br.org.curitiba.ici.gtm.pais.entity.PaisEntity;\n"
			+ "import br.org.curitiba.ici.gtm.pais.repository.PaisRepository;\n"
			+ "\n"
			+ "import lombok.RequiredArgsConstructor;\n"
			+ "\n"
			+ "@Transactional(readOnly = true)\n"
			+ "@RequiredArgsConstructor\n"
			+ "@Service\n"
			+ "public class PaisService {\n"
			+ "	private final PaisRepository paisRepository;\n"
			+ "	\n"
			+ "	@Transactional\n"
			+ "	public PaisEntity salvar(PaisEntity paisEntity) {\n"
			+ "		return paisRepository.save(paisEntity);\n"
			+ "	}\n"
			+ "	\n"
			+ "	@Transactional\n"
			+ "	public PaisEntity atualizar(PaisEntity paisEntity) {\n"
			+ "		if (!paisRepository.existsById(paisEntity.getCodPais())) {\n"
			+ "			throw new NotFoundException(\"Pais não encontrado para o código pais enviado\", \"pais.codPais\");\n"
			+ "		}\n"
			+ "		return paisRepository.save(paisEntity);\n"
			+ "	}\n"
			+ "	\n"
			+ "	@Transactional\n"
			+ "	public void deletarPorId(Integer codPais) {\n"
			+ "		if (!paisRepository.existsById(codPais)) {\n"
			+ "			throw new NotFoundException(\"Pais não encontrado para o código pais enviado\", \"pais.codPais\");\n"
			+ "		}\n"
			+ "		paisRepository.deleteById(codPais);\n"
			+ "	}\n"
			+ "	\n"
			+ "	public Page<PaisEntity> pesquisarPorExemplo(PaisEntity paisEntity, Pageable pageable) {\n"
			+ "		ExampleMatcher customExampleMatcher = ExampleMatcher\n"
			+ "				.matching()\n"
			+ "				.withIgnoreNullValues()\n"
			+ "				.withMatcher(\"siglaPais\", ExampleMatcher.GenericPropertyMatchers.startsWith())\n"
			+ "				.withMatcher(\"nomePais\", ExampleMatcher.GenericPropertyMatchers.startsWith());\n"
			+ "		Example<PaisEntity> example = Example.of(paisEntity, customExampleMatcher);\n"
			+ "		return paisRepository.findAll(example, pageable);\n"
			+ "	}\n"
			+ "	\n"
			+ "	public Optional<PaisEntity> buscarOpcionalPorId(Integer codPais) {\n"
			+ "		return paisRepository.findById(codPais);\n"
			+ "	}\n"
			+ "	\n"
			+ "	public PaisEntity buscarPorId(Integer codPais) {\n"
			+ "		return paisRepository.findById(codPais)\n"
			+ "				.orElseThrow(() -> new NotFoundException(\"Pais não encontrado para o código pais enviado\", \"pais.codPais\"));\n"
			+ "	}\n"
			+ "	\n"
			+ "}";
	
	@Test
	void test() {
		List<String> atributosPesquisaInit = new ArrayList<String>();
		atributosPesquisaInit.add("nomePais");
		atributosPesquisaInit.add("siglaPais");
		
		ConfiguracaoServico configuracao = new ConfiguracaoServico("Pais", "br.org.curitiba.ici.gtm.pais", "codPais", atributosPesquisaInit);
		
		String codigoGerado = configuracao.getCodigoGerado().replaceAll("[^a-zA-Z0-9]", "");
		String codigoServicoExpected = codigoServico.replaceAll("[^a-zA-Z0-9]", "");
		
		assertEquals(codigoServicoExpected, codigoGerado);
	}
	
	@Test
	void getCaminhoPacoteClasseTest() {
		ConfiguracaoServico configuracao = new ConfiguracaoServico("Pais", "br.org.curitiba.ici.gtm.pais", "codPais", null);
		assertEquals("br.org.curitiba.ici.gtm.pais.service.PaisService.java", configuracao.getCaminhoPacoteClasse());
		
	}

}
