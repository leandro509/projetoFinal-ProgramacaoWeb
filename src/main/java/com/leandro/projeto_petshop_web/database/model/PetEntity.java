package com.leandro.projeto_petshop_web.database.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private Long pet_id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String raca;
    @Column(name = "tipo_pet",nullable = false)
    private TipoPet tipo;
    @Column(name = "sexo_pet",nullable = false)
    private SexoPet sexo;

    @OneToMany(mappedBy = "pet")
    private List<AgendamentoEntity> agendamento;
    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
