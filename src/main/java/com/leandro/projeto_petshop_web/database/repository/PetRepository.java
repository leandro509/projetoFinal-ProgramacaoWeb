package com.leandro.projeto_petshop_web.database.repository;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, Long> {

}
