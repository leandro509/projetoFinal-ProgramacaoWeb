package com.leandro.projeto_petshop_web.database.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetEntity {
    private Long pet_id;
    private String nome;
    private String raca;
    private TipoPet tipo;
    private SexoPet sexo;
}
