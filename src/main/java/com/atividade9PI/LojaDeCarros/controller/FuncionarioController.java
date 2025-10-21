package com.atividade9PI.LojaDeCarros.controller;

import com.atividade9PI.LojaDeCarros.data.CargoEntity;
import com.atividade9PI.LojaDeCarros.data.FuncionariosEntity;
import com.atividade9PI.LojaDeCarros.data.TipoCargoEntity;
import com.atividade9PI.LojaDeCarros.repository.TipoCargoRepository;
import com.atividade9PI.LojaDeCarros.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TipoCargoRepository tipoCargoRepository;

    @GetMapping("/lista")
    public String listarFuncionarios(Model model) {
        model.addAttribute("listaFuncionarios", funcionarioService.listarFuncionarios());
        return "tabela-funcionario";
    }

    @GetMapping("/registro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("funcionario", new FuncionariosEntity());
        model.addAttribute("listaTiposCargo", tipoCargoRepository.findAll());
        return "funcionario-registro";
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@Valid @ModelAttribute("funcionario") FuncionariosEntity funcionario ,BindingResult result, 
            @RequestParam(value = "cargoSelecionado", required = false) Integer idTipoCargo, Model model, RedirectAttributes redirectAttributes) {
        
        if (idTipoCargo == null) {
            model.addAttribute("listaTiposCargo", tipoCargoRepository.findAll());
            model.addAttribute("erroCargo", "Por favor, selecione o cargo do funcionário.");
            return "funcionario-registro";
        }  
        
        if (result.hasErrors()) {
            model.addAttribute("listaTiposCargo", tipoCargoRepository.findAll());
            return "funcionario-registro";
        }
        
         TipoCargoEntity tipoCargo = tipoCargoRepository.findById(idTipoCargo)
            .orElseThrow(() -> new IllegalArgumentException("Tipo de cargo inválido: " + idTipoCargo));
        
        CargoEntity cargo = new CargoEntity();
        cargo.setTipoCargo(tipoCargo);
        cargo.setFuncionario(funcionario);
        
        funcionario.getCargos().add(cargo);
        funcionarioService.criarFuncionario(funcionario);
        
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Funcionário registrado com sucesso!");
        return "redirect:/funcionarios/cadastro";
    }
}