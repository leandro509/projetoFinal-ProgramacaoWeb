package com.leandro.projeto_petshop_web.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonoPet extends Pessoa{
    private String email;
    private String numeroTelefone;
}
