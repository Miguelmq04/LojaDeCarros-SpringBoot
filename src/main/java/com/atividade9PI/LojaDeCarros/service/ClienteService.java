
package com.atividade9PI.LojaDeCarros.service;

import com.atividade9PI.LojaDeCarros.data.ClienteEntity;
import com.atividade9PI.LojaDeCarros.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    
    ClienteRepository clienteRepository;
    
    public ClienteEntity criarCliente(ClienteEntity cliente){
        cliente.setIdCliente(null);
        clienteRepository.save(cliente);
        return cliente;
    }
    
    public List<ClienteEntity> listarClientes(){
         return clienteRepository.findAll();
    }
}
