
package com.atividade9PI.LojaDeCarros.repository;

import com.atividade9PI.LojaDeCarros.data.FuncionariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FuncionarioRepository extends JpaRepository<FuncionariosEntity, Integer>{
    
}
