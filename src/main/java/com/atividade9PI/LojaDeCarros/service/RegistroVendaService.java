
package com.atividade9PI.LojaDeCarros.service;


import com.atividade9PI.LojaDeCarros.data.RegistroVendaEntity;
import com.atividade9PI.LojaDeCarros.repository.RegistroVendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroVendaService {
    @Autowired
    
    RegistroVendaRepository registroVendaRepository;
    
    public RegistroVendaEntity criarVenda(RegistroVendaEntity registroVenda){
        registroVenda.setIdRegistroVenda(null);
        registroVendaRepository.save(registroVenda);
        return registroVenda;
    }
    
    public List<RegistroVendaEntity> listarVendas(){
         return registroVendaRepository.findAll();
    }
}
