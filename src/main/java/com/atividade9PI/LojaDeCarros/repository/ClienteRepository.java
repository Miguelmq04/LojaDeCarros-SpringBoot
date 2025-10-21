
package com.atividade9PI.LojaDeCarros.repository;

import com.atividade9PI.LojaDeCarros.data.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>{
    
}
