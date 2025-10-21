
package com.atividade9PI.LojaDeCarros.service;

import com.atividade9PI.LojaDeCarros.data.TipoCargoEntity;
import com.atividade9PI.LojaDeCarros.repository.TipoCargoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCargoService {
    @Autowired
    
    TipoCargoRepository tipoCargoRepository;
    
    public List<TipoCargoEntity> listarTipoCargos(){
         return tipoCargoRepository.findAll();
    }
}
