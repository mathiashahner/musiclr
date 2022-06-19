package br.com.cwi.crescer.musiclr.repository;

import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    boolean existsByIdAndPessoaId(Long id, Long pessoaId);

    Page<Publicacao> findAllByPessoaOrderByDataCriacaoDesc(Pessoa pessoa, Pageable pageable);

    Page<Publicacao> findAllByPessoaAndIsPublicaTrueOrderByDataCriacaoDesc(Pessoa pessoa, Pageable pageable);

    @Query("select p from Publicacao p where p.pessoa.id = ?1 " +
            "or p.pessoa.id in (select a.solicitado.id from Amizade a where a.solicitante.id = ?1 and a.situacao = 'APROVADA') " +
            "or p.pessoa.id in (select a.solicitante.id from Amizade a where a.solicitado.id = ?1 and a.situacao = 'APROVADA') " +
            "order by p.dataCriacao desc")
    Page<Publicacao> buscarPublicacoes(Long pessoaId, Pageable pageable);

}
