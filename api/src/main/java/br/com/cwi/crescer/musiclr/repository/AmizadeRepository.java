package br.com.cwi.crescer.musiclr.repository;

import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.SituacaoAmizade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    List<Amizade> findAllBySolicitadoIdAndSituacao(Long id, SituacaoAmizade situacao);

    @Query("select count(a) > 0 from Amizade a where " +
            "a.solicitante = ?1 and a.solicitado = ?2 and a.situacao = ?3 or " +
            "a.solicitado = ?1 and a.solicitante = ?2 and a.situacao = ?3")
    boolean verificarAmizadePorSituacao(Pessoa solicitante, Pessoa solicitado, SituacaoAmizade situacao);

    @Query("select a from Amizade a where " +
            "a.solicitante = ?1 and a.situacao = ?2 or " +
            "a.solicitado = ?1 and a.situacao = ?2")
    Page<Amizade> buscarAmizadesPorSituacao(Pessoa pessoa, SituacaoAmizade situacao, Pageable pageable);

    @Query("select a from Amizade a where " +
            "(a.solicitante = ?1 and a.solicitado = ?2) or " +
            "(a.solicitado = ?1 and a.solicitante = ?2)")
    Amizade buscarAmizade(Pessoa solicitante, Pessoa solicitado);
}
