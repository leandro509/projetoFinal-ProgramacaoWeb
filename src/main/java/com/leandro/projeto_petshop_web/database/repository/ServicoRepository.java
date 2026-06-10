package com.leandro.projeto_petshop_web.database.repository;

import com.leandro.projeto_petshop_web.database.model.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
}
