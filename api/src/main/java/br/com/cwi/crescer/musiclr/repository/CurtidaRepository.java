package br.com.cwi.crescer.musiclr.repository;

import br.com.cwi.crescer.musiclr.model.Curtida;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    Long countByPublicacao(Publicacao publicacao);

    boolean existsByPublicacaoAndPessoa(Publicacao publicacao, Pessoa pessoa);

    Optional<Curtida> findByPublicacaoIdAndPessoaId(Long id, Long usuarioId);

}
