package com.leandro.projeto_petshop_web.dto;

import com.leandro.projeto_petshop_web.database.model.SexoPet;
import com.leandro.projeto_petshop_web.database.model.TipoPet;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDto {
    private String nome;
    private String raca;
    private TipoPet tipo;
    private SexoPet sexo;
}
