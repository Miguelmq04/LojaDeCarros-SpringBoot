
package com.atividade9PI.LojaDeCarros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class telaInicialController{
    @GetMapping
    public String home() {
        return "tela-inicial";
    }
}
