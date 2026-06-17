package com.leandro.projeto_petshop_web.database.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petId;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String raca;
    @Column(name = "tipo_pet",nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPet tipo;
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo_pet",nullable = false)
    private SexoPet sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private Set<AgendamentoEntity> agendamentos = new HashSet<>();
}
