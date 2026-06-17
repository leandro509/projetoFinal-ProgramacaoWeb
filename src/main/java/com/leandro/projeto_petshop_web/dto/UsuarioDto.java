package com.leandro.projeto_petshop_web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    @NotBlank
    private String nome;
    private List<Long> petIds;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String numeroTelefone;
}
