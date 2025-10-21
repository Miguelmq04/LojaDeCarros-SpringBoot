
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Data
@Embeddable
public class VeiculohasRegistroVeiculoId implements Serializable{
    private Integer veiculoID;
    private Integer registroVeiculoID;
}
