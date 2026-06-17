package com.leandro.projeto_petshop_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ServicoDto {
    @NotBlank
    private String nomeServico;
    @NotNull
    private Double preco;
    private List<Long> agendamentosIds;
}
