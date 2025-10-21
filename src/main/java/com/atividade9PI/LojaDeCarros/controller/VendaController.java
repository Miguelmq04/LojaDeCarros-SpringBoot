package com.atividade9PI.LojaDeCarros.controller;

import com.atividade9PI.LojaDeCarros.data.ClienteEntity;
import com.atividade9PI.LojaDeCarros.data.FuncionariosEntity;
import com.atividade9PI.LojaDeCarros.data.RegistroVendaEntity;
import com.atividade9PI.LojaDeCarros.data.VeiculoEntity;
import com.atividade9PI.LojaDeCarros.repository.ClienteRepository;
import com.atividade9PI.LojaDeCarros.repository.FuncionarioRepository;
import com.atividade9PI.LojaDeCarros.repository.RegistroVendaRepository;
import com.atividade9PI.LojaDeCarros.repository.VeiculoRepository;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
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
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private RegistroVendaRepository registroVendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionariosRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/registro")
    public String mostrarFormularioVenda(Model model) {
        model.addAttribute("vendas", new RegistroVendaEntity());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("funcionarios", funcionariosRepository.findAll());
        model.addAttribute("veiculos", veiculoRepository.findAll());
        return "venda-registro";
    }

    @PostMapping("/salvar")
    public String salvarVenda(
            @Valid @ModelAttribute("vendas") RegistroVendaEntity venda,
            BindingResult result,
            @RequestParam(value = "clienteSelecionado", required = false) Integer idCliente,
            @RequestParam(value = "funcionarioSelecionado", required = false) Integer idFuncionario,
            @RequestParam(value = "veiculoSelecionado", required = false) Integer idVeiculo,
            Model model,
            RedirectAttributes redirectAttributes) {

        boolean temErro = false;

        if (idCliente == null) {
            model.addAttribute("erroCliente", "Selecione um cliente.");
            temErro = true;
        }

        if (idFuncionario == null) {
            model.addAttribute("erroFuncionario", "Selecione um funcionário.");
            temErro = true;
        }

        if (idVeiculo == null) {
            model.addAttribute("erroVeiculo", "Selecione um veículo.");
            temErro = true;
        }
        
        
        if (temErro) {
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("funcionarios", funcionariosRepository.findAll());
            model.addAttribute("veiculos", veiculoRepository.findAll());
            return "venda-registro";
        }

        ClienteEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + idCliente));
        FuncionariosEntity funcionario = funcionariosRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + idFuncionario));
        VeiculoEntity veiculo = veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new IllegalArgumentException("Veículo inválido: " + idVeiculo));

        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setVeiculosVendidos(List.of(veiculo));
        venda.setDataRegistro(LocalDate.now());

        registroVendaRepository.save(venda);

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Venda registrada com sucesso!");
        return "redirect:/vendas/cadastro";
    }

    @GetMapping("/lista")
    public String listarVendas(Model model) {
        List<RegistroVendaEntity> vendas = registroVendaRepository.findAll();
        model.addAttribute("vendas", vendas);
        return "tabela-vendas";
    }
}
