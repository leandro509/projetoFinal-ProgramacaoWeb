package com.leandro.projeto_petshop_web.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String get() {
        return "Hello World";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String get2() {
        return "Hello World";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String ge3() {
        return "Hello World as";
    }

}
