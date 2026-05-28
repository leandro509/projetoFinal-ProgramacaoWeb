package com.leandro.projeto_petshop_web.controller;

import com.leandro.projeto_petshop_web.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor //injeção de dependências
@RequestMapping
public class UsuarioController {

    private final UsuarioService usuarioService;

}
