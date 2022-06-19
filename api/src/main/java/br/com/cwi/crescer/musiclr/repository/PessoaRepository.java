package br.com.cwi.crescer.musiclr.repository;

import br.com.cwi.crescer.musiclr.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByUsuarioId(Long id);

    @Query("select p from Pessoa p where lower(p.nome) like lower(concat('%', ?1, '%')) " +
            "or lower(p.usuario.email) like lower(concat('%', ?1, '%'))")
    Page<Pessoa> filtrarPorNomeEmail(String textoBusca, Pageable pageable);
}
