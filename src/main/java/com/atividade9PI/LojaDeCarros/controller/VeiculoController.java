package com.atividade9PI.LojaDeCarros.controller;

import com.atividade9PI.LojaDeCarros.data.FuncionariosEntity;
import com.atividade9PI.LojaDeCarros.data.RegistroVeiculoEntity;
import com.atividade9PI.LojaDeCarros.data.VeiculoEntity;
import com.atividade9PI.LojaDeCarros.service.FuncionarioService;
import com.atividade9PI.LojaDeCarros.service.RegistroVeiculoService;
import com.atividade9PI.LojaDeCarros.service.VeiculoService;
import com.atividade9PI.LojaDeCarros.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/veiculos")
@SessionAttributes("veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private FuncionarioService funcionariosService;

    @Autowired
    private RegistroVeiculoService registroVeiculoService;

    @GetMapping("/registro")
    public String mostrarFormularioPagina1(Model model) {
        model.addAttribute("veiculo", new VeiculoEntity());
        return "veiculos/veiculo-cadastroPg1";
    }

    @PostMapping("/registro/pagina1")
    public String processarPagina1(
            @Validated(ValidationGroups.Pagina1.class) @ModelAttribute("veiculo") VeiculoEntity veiculo,
            BindingResult result,
            Model model) {
        
        if (veiculo.getAnoVeiculo() != null) {
            int anoAtual = Year.now().getValue();
            
            if (veiculo.getAnoVeiculo() < 1886 || veiculo.getAnoVeiculo() > anoAtual + 1) {
                result.rejectValue("AnoVeiculo", "error.veiculo", 
                    "O ano deve estar entre 1886 e " + (anoAtual + 1));
            }
        }
        
        if (result.hasErrors()) {
            return "veiculos/veiculo-cadastroPg1";
        }

        model.addAttribute("veiculo", veiculo);
        
        return "redirect:/veiculos/registro/pagina2";
    }

    @GetMapping("/registro/pagina2")
    public String mostrarFormularioPagina2(@ModelAttribute("veiculo") VeiculoEntity veiculo,
            Model model) {
        
        if (veiculo == null || veiculo.getMarcaVeiculo() == null) {
            return "redirect:/veiculos/registro";
        }

        List<FuncionariosEntity> funcionarios = funcionariosService.listarFuncionarios();
        model.addAttribute("funcionarios", funcionarios);
        return "veiculos/veiculo-cadastroPg2";
    }

    @PostMapping("/registro/salvar")
    public String salvarVeiculo(
            @Validated(ValidationGroups.Pagina2.class) @ModelAttribute("veiculo") VeiculoEntity veiculo,
            BindingResult result,
            @RequestParam(required = false) Integer funcionarioId,
            Model model,
            SessionStatus sessionStatus) { 

        if (funcionarioId == null || funcionarioId == 0) {
            model.addAttribute("erroFuncionario", "Selecione o funcionário que está fazendo o registro");
            List<FuncionariosEntity> funcionarios = funcionariosService.listarFuncionarios();
            model.addAttribute("funcionarios", funcionarios);
            return "veiculos/veiculo-cadastroPg2";
        }
        
        if (result.hasErrors()) {
            List<FuncionariosEntity> funcionarios = funcionariosService.listarFuncionarios();
            model.addAttribute("funcionarios", funcionarios);
            return "veiculos/veiculo-cadastroPg2";
        }

        try {
            VeiculoEntity veiculoSalvo = registroVeiculoService.criarRegistroVeiculo(veiculo, funcionarioId);

            sessionStatus.setComplete();
            
            model.addAttribute("veiculo", new VeiculoEntity());
            model.addAttribute("mensagemSucesso", "Veículo registrado com sucesso!");
            
            List<FuncionariosEntity> funcionarios = funcionariosService.listarFuncionarios();
            model.addAttribute("funcionarios", funcionarios);
            
            return "veiculos/veiculo-cadastroPg2";
            
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro ao registrar veículo: " + e.getMessage());
            List<FuncionariosEntity> funcionarios = funcionariosService.listarFuncionarios();
            model.addAttribute("funcionarios", funcionarios);
            return "veiculos/veiculo-cadastroPg2";
        }
    }
    
    @GetMapping("/lista")
    public String mostrarTabela(Model model) {
        List<RegistroVeiculoEntity> registros = registroVeiculoService.listarRegistrosVeiculo();
        model.addAttribute("registros", registros);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("dateFormatter", formatter);

        return "veiculos/tabela-veiculo";
    }
    
    @PostMapping("/excluir/{id}")
    public String excluirVeiculo(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            veiculoService.deletarVeiculo(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Veículo excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir veículo.");
        }
        return "redirect:/veiculos/lista";
    }
}