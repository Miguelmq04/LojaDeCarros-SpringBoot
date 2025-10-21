
package com.atividade9PI.LojaDeCarros.controller;

import com.atividade9PI.LojaDeCarros.data.ClienteEntity;
import com.atividade9PI.LojaDeCarros.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/lista")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "tabela-cliente"; // 
    }

    @GetMapping("/registro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("cliente", new ClienteEntity());
        return "cliente-registro";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") ClienteEntity cliente, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cliente-registro"; 
        }
        clienteService.criarCliente(cliente);
        
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente registrado com sucesso!");
        return "redirect:/clientes/cadastro"; 
    }
}
