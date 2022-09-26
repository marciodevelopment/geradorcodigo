package br.org.curitiba.ici.geradorcodigo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class FileUtil {

	public static <T> T lerArquivoYaml(String arquivo, Class<T> clazz) {
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		try {
			return om.readValue(new File(arquivo), clazz);
		} catch (IOException e) {
			throw new ExcecaoNegocio("Erro ao ler o arquivo yaml", e);
		}
	}
	
	
	public static void escreverArquivoJavaSeNaoExistir(
			String pastaArquivos, 
			String pacote, 
			String pacoteComArquivo,
			String conteudoArquivo) {
		
		String ultimoPacote = pacote.substring(pacote.lastIndexOf(".") + 1, pacote.length());
		String nomeArquivo = pacoteComArquivo.substring(pacoteComArquivo.replace(".java", "#java").lastIndexOf(".") + 1, pacoteComArquivo.length());
		String pastaArquivo = pacoteComArquivo.replace(pacote + ".", "").replace("." + nomeArquivo, "").replace(".", "/");
		String pastaGravacaoArquivo = pastaArquivos + "/" + ultimoPacote + "/" + pastaArquivo;		
		String enderecoEscritaArquivo = pastaGravacaoArquivo + "/" + nomeArquivo;
		
		try {
			if (Files.notExists(Paths.get(pastaGravacaoArquivo))) {
				Files.createDirectories(Paths.get(pastaGravacaoArquivo));
			}
			if (Files.notExists(Paths.get(enderecoEscritaArquivo))) {
				Files.createFile(Paths.get(enderecoEscritaArquivo));
				Files.writeString(Paths.get(enderecoEscritaArquivo), conteudoArquivo);
			}
		} catch (IOException e) {
			throw new ExcecaoNegocio("Erro ao gravar o arquivo no endereco " + pastaGravacaoArquivo, e);
		}

	}
}

