package com.leandro.projeto_petshop_web.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ServicoDto {
    private String nomeServico;
    private Double preco;

}
