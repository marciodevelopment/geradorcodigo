package br.org.curitiba.ici.gtm.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.org.curitiba.ici.gtm.pessoa.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

}
