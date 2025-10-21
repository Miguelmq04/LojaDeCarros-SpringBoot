
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
@Table(name="VeiculoHasRegistroVenda")
public class VeiculoHasRegistroVenda {
    @EmbeddedId
    private VeiculohasRegistroVendaId id;
    
    @ManyToOne
    @MapsId("veiculoID") 
    @JoinColumn(name = "veiculoID")
    private VeiculoEntity veiculo;

    @ManyToOne
    @MapsId("registroVendaID")
    @JoinColumn(name = "RegistroVendaID")
    private RegistroVendaEntity registroVenda;
}
