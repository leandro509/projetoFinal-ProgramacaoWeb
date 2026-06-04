package com.leandro.projeto_petshop_web.database.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "agendamentos")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuarioEntity;
}
