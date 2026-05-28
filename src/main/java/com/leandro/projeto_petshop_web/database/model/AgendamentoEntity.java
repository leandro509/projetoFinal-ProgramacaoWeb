package com.leandro.projeto_petshop_web.database.model;


import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AgendamentoEntity {

    private Long id;
    private Date data;
    private PetEntity pet;
    private UsuarioEntity usuarioEntity;
}
