package br.org.curitiba.ici.gtm.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.org.curitiba.ici.gtm.pessoa.entity.PessoaEntity;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {

}
