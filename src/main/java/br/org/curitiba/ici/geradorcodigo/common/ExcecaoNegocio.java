package br.org.curitiba.ici.geradorcodigo.common;

public class ExcecaoNegocio extends RuntimeException {
	private static final long serialVersionUID = -8551436680726232801L;
	
	public ExcecaoNegocio(String mensagem) {
		super(mensagem);
	}

	public ExcecaoNegocio(String mensagem, Exception e) {
		super(mensagem, e);
	}
	
}
