package br.org.curitiba.ici.gtm.pessoa.service;


import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.curitiba.ici.gtm.common.exception.NotFoundException;

import br.org.curitiba.ici.gtm.pessoa.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.pessoa.repository.PessoaRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PessoaService {
	private final PessoaRepository pessoaRepository;
	
	@Transactional
	public PessoaEntity salvar(PessoaEntity pessoaEntity) {
		return pessoaRepository.save(pessoaEntity);
	}
	
	@Transactional
	public PessoaEntity atualizar(PessoaEntity pessoaEntity) {
		if (!pessoaRepository.existsById(pessoaEntity.getCodUsuario())) {
			throw new NotFoundException("Pessoa não encontrado para o código pessoa enviado", "pessoa.codUsuario");
		}
		return pessoaRepository.save(pessoaEntity);
	}
	
	@Transactional
	public void deletarPorId(Integer codUsuario) {
		if (!pessoaRepository.existsById(codUsuario)) {
			throw new NotFoundException("Pessoa não encontrado para o código pessoa enviado", "pessoa.codUsuario");
		}
		pessoaRepository.deleteById(codUsuario);
	}
	
	public Page<PessoaEntity> pesquisarPorExemplo(PessoaEntity pessoaEntity, Pageable pageable) {
		ExampleMatcher customExampleMatcher = ExampleMatcher
				.matching()
				.withIgnoreNullValues()
				.withMatcher("cpf", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withMatcher("pai", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withMatcher("mae", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.startsWith());
		Example<PessoaEntity> example = Example.of(pessoaEntity, customExampleMatcher);
		return pessoaRepository.findAll(example, pageable);
	}
	
	public Optional<PessoaEntity> buscarOpcionalPorId(Integer codUsuario) {
		return pessoaRepository.findById(codUsuario);
	}
	
	public PessoaEntity buscarPorId(Integer codUsuario) {
		return pessoaRepository.findById(codUsuario)
				.orElseThrow(() -> new NotFoundException("Pessoa não encontrado para o código pessoa enviado", "pessoa.codUsuario"));
	}
	
}
