package com.leandro.projeto_petshop_web.dto;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentoDto {
    private Date data;
    private PetEntity pet;
    private UsuarioEntity usuarioEntity;
}
