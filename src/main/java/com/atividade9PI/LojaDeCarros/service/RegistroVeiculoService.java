
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

@Service
public class RegistroVeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RegistroVeiculoRepository registroVeiculoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    public VeiculoEntity criarRegistroVeiculo(VeiculoEntity veiculo, Integer funcionarioId){
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
    
    public List<RegistroVeiculoEntity> listarRegistrosVeiculo(){
         return registroVeiculoRepository.findAll();
    }
}
