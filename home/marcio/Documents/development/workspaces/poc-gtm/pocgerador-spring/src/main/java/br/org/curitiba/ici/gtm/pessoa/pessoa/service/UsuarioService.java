package br.org.curitiba.ici.gtm.pessoa.service;


import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.curitiba.ici.gtm.common.exception.NotFoundException;

import br.org.curitiba.ici.gtm.pessoa.entity.UsuarioEntity;
import br.org.curitiba.ici.gtm.pessoa.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioEntity salvar(UsuarioEntity usuarioEntity) {
		return usuarioRepository.save(usuarioEntity);
	}
	
	@Transactional
	public UsuarioEntity atualizar(UsuarioEntity usuarioEntity) {
		if (!usuarioRepository.existsById(usuarioEntity.getCodUsuario())) {
			throw new NotFoundException("Usuario não encontrado para o código usuario enviado", "usuario.codUsuario");
		}
		return usuarioRepository.save(usuarioEntity);
	}
	
	@Transactional
	public void deletarPorId(Integer codUsuario) {
		if (!usuarioRepository.existsById(codUsuario)) {
			throw new NotFoundException("Usuario não encontrado para o código usuario enviado", "usuario.codUsuario");
		}
		usuarioRepository.deleteById(codUsuario);
	}
	
	public Page<UsuarioEntity> pesquisarPorExemplo(UsuarioEntity usuarioEntity, Pageable pageable) {
		ExampleMatcher customExampleMatcher = ExampleMatcher
				.matching()
				.withIgnoreNullValues()
				.withMatcher("cpf", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withMatcher("pai", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withMatcher("mae", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.startsWith());
		Example<UsuarioEntity> example = Example.of(usuarioEntity, customExampleMatcher);
		return usuarioRepository.findAll(example, pageable);
	}
	
	public Optional<UsuarioEntity> buscarOpcionalPorId(Integer codUsuario) {
		return usuarioRepository.findById(codUsuario);
	}
	
	public UsuarioEntity buscarPorId(Integer codUsuario) {
		return usuarioRepository.findById(codUsuario)
				.orElseThrow(() -> new NotFoundException("Usuario não encontrado para o código usuario enviado", "usuario.codUsuario"));
	}
	
}
