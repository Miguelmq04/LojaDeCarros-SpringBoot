package com.atividade9PI.LojaDeCarros.service;

import com.atividade9PI.LojaDeCarros.data.RegistroVeiculoEntity;
import com.atividade9PI.LojaDeCarros.data.VeiculoEntity;
import com.atividade9PI.LojaDeCarros.repository.FuncionarioRepository;
import com.atividade9PI.LojaDeCarros.repository.RegistroVeiculoRepository;
import com.atividade9PI.LojaDeCarros.repository.VeiculoRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroVeiculoService {
    
    @Autowired
    private VeiculoRepository veiculoRepository;
    
    @Autowired
    private RegistroVeiculoRepository registroVeiculoRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Transactional
    public VeiculoEntity criarRegistroVeiculo(VeiculoEntity veiculo, Integer funcionarioId) {
        veiculo.setIdVeiculo(null);
        VeiculoEntity veiculoSalvo = veiculoRepository.save(veiculo);
        
        var funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        RegistroVeiculoEntity registro = new RegistroVeiculoEntity();
        registro.setFuncionario(funcionario);
        registro.setDataRegistro(LocalDate.now());
        registro.setVeiculos(List.of(veiculoSalvo));
        
        registroVeiculoRepository.save(registro);
        
        return veiculoSalvo;
    }
    
    @Transactional(readOnly = true)
    public List<RegistroVeiculoEntity> listarRegistrosVeiculo() {
        List<RegistroVeiculoEntity> registros = registroVeiculoRepository.findAll();
      
        registros.forEach(registro -> {
            registro.getVeiculos().size();
            registro.getFuncionario().getNomeFuncionario(); 
        });
        return registros;
    }
}