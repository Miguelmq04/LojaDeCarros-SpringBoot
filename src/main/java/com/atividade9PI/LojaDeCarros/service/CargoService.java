
package com.atividade9PI.LojaDeCarros.service;

import com.atividade9PI.LojaDeCarros.data.CargoEntity;
import com.atividade9PI.LojaDeCarros.repository.CargoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    @Autowired
    
    CargoRepository cargoRepository;
    
    public List<CargoEntity> listarCargos(){
         return cargoRepository.findAll();
    }
}
