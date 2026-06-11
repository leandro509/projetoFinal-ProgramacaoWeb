
package com.leandro.projeto_petshop_web.controller;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import com.leandro.projeto_petshop_web.dto.PetDto;
import com.leandro.projeto_petshop_web.dto.UsuarioDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import com.leandro.projeto_petshop_web.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor //injeção de dependências
@RequestMapping("usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioEntity> findAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioEntity findUsuarioById(@PathVariable Long id) throws NotFoundException {
        return usuarioService.findUsuarioById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioEntity createPet(@RequestBody UsuarioDto usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioEntity updateUsuario(@PathVariable Long id,
                               @RequestBody UsuarioDto usuario) throws NotFoundException {

        return usuarioService.updateUsuario(usuario, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id) throws NotFoundException {
        usuarioService.deleteUsuario(id);
    }

}
