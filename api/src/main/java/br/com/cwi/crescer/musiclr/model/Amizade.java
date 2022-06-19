package br.com.cwi.crescer.musiclr.model;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Amizade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Pessoa solicitante;

    @ManyToOne
    @JoinColumn(name = "solicitado_id", nullable = false)
    private Pessoa solicitado;

    @Column(nullable = false)
    private LocalDateTime dataSolicitacao;

    @Enumerated(STRING)
    @Column(nullable = false)
    private SituacaoAmizade situacao;
}
