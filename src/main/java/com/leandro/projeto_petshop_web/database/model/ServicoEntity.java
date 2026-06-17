package com.leandro.projeto_petshop_web.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "servicos", fetch = FetchType.LAZY)
    private Set<AgendamentoEntity> agendamentos = new HashSet<>();
}
