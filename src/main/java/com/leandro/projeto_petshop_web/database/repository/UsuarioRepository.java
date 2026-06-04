package com.leandro.projeto_petshop_web.database.repository;

import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
