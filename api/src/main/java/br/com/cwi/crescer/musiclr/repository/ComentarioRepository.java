package br.com.cwi.crescer.musiclr.repository;

import br.com.cwi.crescer.musiclr.model.Comentario;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Long countByPublicacao(Publicacao publicacao);

    List<Comentario> findAllByPublicacao(Publicacao publicacao);
}
