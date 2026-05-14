package com.leandro.projeto_petshop_web.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String nome;
    private String raca;
    private TipoPet tipo;
    private SexoPet sexo;
}
