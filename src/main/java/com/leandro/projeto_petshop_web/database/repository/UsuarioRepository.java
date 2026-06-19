package com.leandro.projeto_petshop_web.database.repository;

import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail (String email) ;
}
