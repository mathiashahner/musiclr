package br.com.cwi.crescer.musiclr.model;

import br.com.cwi.crescer.musiclr.security.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String apelido;

    @Lob
    private byte[] imagemPerfil;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
