package com.leandro.projeto_petshop_web.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "agendamento")
@Table(name = "agendamento")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    private PetEntity pet;
    private UsuarioEntity usuarioEntity;
}
