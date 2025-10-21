
package com.atividade9PI.LojaDeCarros.service;

import com.atividade9PI.LojaDeCarros.data.RegistroVeiculoEntity;
import com.atividade9PI.LojaDeCarros.data.RegistroVendaEntity;
import com.atividade9PI.LojaDeCarros.data.VeiculoEntity;
import com.atividade9PI.LojaDeCarros.repository.RegistroVeiculoRepository;
import com.atividade9PI.LojaDeCarros.repository.RegistroVendaRepository;
import com.atividade9PI.LojaDeCarros.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    
    @Autowired         
    private VeiculoRepository veiculoRepository;
    
    @Autowired
    private RegistroVendaRepository registroVendaRepository;
    
    @Autowired
    private RegistroVeiculoRepository registroVeiculoRepository;
    
    public VeiculoEntity criarVeiculo(VeiculoEntity veiculo){
        veiculo.setIdVeiculo(null);
        veiculoRepository.save(veiculo);
        return veiculo;
    }
    
    public VeiculoEntity getVeiculoId(Integer veiculoId){
        return veiculoRepository.findById(veiculoId).orElse(null);
    }
    
    public List<VeiculoEntity> listarVeiculos(){
        return veiculoRepository.findAll();
    }
    
    @Transactional
    public void deletarVeiculo(Integer id) {
        VeiculoEntity veiculo = veiculoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        

        List<RegistroVendaEntity> registrosVenda = registroVendaRepository.findAll();
        for (RegistroVendaEntity registro : registrosVenda) {
            if (registro.getVeiculosVendidos() != null && 
                registro.getVeiculosVendidos().contains(veiculo)) {
                registro.getVeiculosVendidos().remove(veiculo);
                registroVendaRepository.save(registro);
            }
        }
        
        List<RegistroVeiculoEntity> registrosVeiculo = registroVeiculoRepository.findAll();
        for (RegistroVeiculoEntity registro : registrosVeiculo) {
            if (registro.getVeiculos() != null && 
                registro.getVeiculos().contains(veiculo)) {
                registro.getVeiculos().remove(veiculo);
                registroVeiculoRepository.save(registro);
            }
        }
        
        veiculoRepository.deleteById(id);
    }
}
