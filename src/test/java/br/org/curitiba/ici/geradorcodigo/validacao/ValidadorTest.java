package br.org.curitiba.ici.geradorcodigo.validacao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.org.curitiba.ici.geradorcodigo.common.GeradorCodigoException;

class ValidadorTest {

	@Test
	void toValidadorDeveEncontrarValidadorTest() {
		assertNotNull(Validador.toValidador("notnull"));
	}
	
	@Test
	void toValidadorDeveLancarExcecaoQuandoValidadorNaoExistirTest() {
		assertThrows(GeradorCodigoException.class, () -> Validador.toValidador("anotacaonaoexistente"));
	}
	
	

}
