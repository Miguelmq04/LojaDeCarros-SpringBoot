
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="TipoCargo")
public class TipoCargoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoCargo")
    private Integer idTipoCargo;
    
    private String Descricao;
    private Float Salario;
    private Float Comissao;
    
    @OneToMany(mappedBy = "tipoCargo")
    private List<CargoEntity> cargos;
}
