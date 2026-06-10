package com.leandro.projeto_petshop_web.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "servicos")
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id",  nullable = false)
    private Long servicoId;
    @Column(name = "nome_servico",  nullable = false)
    private String nomeServico;
    @Column(name = "preco_servico",   nullable = false)
    private Double preco;

    @ManyToMany(mappedBy = "servicos")
    private List<AgendamentoEntity> agendamentos = new ArrayList<>();
}
