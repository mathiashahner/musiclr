package br.com.cwi.crescer.musiclr.security.repository;

import br.com.cwi.crescer.musiclr.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
