package br.com.cwi.crescer.musiclr.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Lob
    @Column(nullable = false)
    private byte[] imagem;

    @Lob
    private byte[] audio;

    @Column(nullable = false)
    private boolean isPublica;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
