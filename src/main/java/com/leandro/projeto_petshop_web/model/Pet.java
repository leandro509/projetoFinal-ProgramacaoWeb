package com.leandro.projeto_petshop_web.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private Long pet_id;
    private String nome;
    private String raca;
    private TipoPet tipo;
    private SexoPet sexo;
}
