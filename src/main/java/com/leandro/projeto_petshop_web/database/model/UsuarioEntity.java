package com.leandro.projeto_petshop_web.database.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    private Long usuario_id;
    private String nome;
    private String email;
    private String senha;
    private String numeroTelefone;
}
