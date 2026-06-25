package com.leandro.projeto_petshop_web.dto;

import com.leandro.projeto_petshop_web.database.model.SexoPet;
import com.leandro.projeto_petshop_web.database.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDto {

    @NotNull
    private Long usuarioId;
    @NotBlank
    private String nome;
    @NotBlank
    private String raca;
    @NotNull
    private TipoPet tipo;
    @NotNull
    private SexoPet sexo;

    private List<Long> agendamentosIds;
}
