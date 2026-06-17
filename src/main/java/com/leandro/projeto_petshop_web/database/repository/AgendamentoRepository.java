package com.leandro.projeto_petshop_web.database.repository;

import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    List<AgendamentoEntity> findByDataAndPetPetId(LocalDateTime data, Long petId);
}