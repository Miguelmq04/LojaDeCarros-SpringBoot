package com.atividade9PI.LojaDeCarros.controller;

import com.atividade9PI.LojaDeCarros.data.LoginFuncionarioEntity;
import com.atividade9PI.LojaDeCarros.service.LoginFuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
    @Autowired
    private LoginFuncionarioService loginService;
    
    @GetMapping("/login")
    public String exibirLogin(Model model) {
        model.addAttribute("loginFuncionario", new LoginFuncionarioEntity());
        return "Login";
    }
    
    @PostMapping("/login")
    public String processarLogin(
            @Valid @ModelAttribute("loginFuncionario") LoginFuncionarioEntity loginFuncionario, 
            BindingResult result, 
            Model model) {
        
        if (!result.hasFieldErrors("Login") && !result.hasFieldErrors("Senha")) {
            boolean autenticado = loginService.autenticar(
                loginFuncionario.getLogin(), 
                loginFuncionario.getSenha()
            );
            
            if (!autenticado) {
                result.rejectValue("Login", "error.loginFuncionario", "Login ou senha inválidos!");
            }
        }
        
        if (result.hasErrors()) {
            return "Login";
        }
        
        return "redirect:/home";
    }
}