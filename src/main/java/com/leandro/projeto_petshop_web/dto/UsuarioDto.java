package com.leandro.projeto_petshop_web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private String nome;
    private String email;
    private String senha;
    private String numeroTelefone;
}
