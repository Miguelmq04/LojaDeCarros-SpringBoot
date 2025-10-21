
package com.atividade9PI.LojaDeCarros.service;

import com.atividade9PI.LojaDeCarros.data.FuncionariosEntity;
import com.atividade9PI.LojaDeCarros.repository.FuncionarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    @Autowired
    
    FuncionarioRepository funcionarioRepository;
    
    public FuncionariosEntity criarFuncionario(FuncionariosEntity funcionario){
        funcionario.setIdFuncionario(null);
        funcionarioRepository.save(funcionario);
        return funcionario;
    }
    
    public List<FuncionariosEntity> listarFuncionarios(){
         return funcionarioRepository.findAll();
    }
}
