
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="VeiculoHasRegistroVeiculo")
public class VeiculohasRegistroVeiculo {
    
    @EmbeddedId
    private VeiculohasRegistroVeiculoId id;
    
    @ManyToOne
    @MapsId("veiculoID")
    @JoinColumn(name = "VeiculoID")
    private VeiculoEntity veiculo;

    @ManyToOne
    @MapsId("registroVeiculoID")
    @JoinColumn(name = "RegistroVeiculoID")
    private RegistroVeiculoEntity registroVeiculo;
}
