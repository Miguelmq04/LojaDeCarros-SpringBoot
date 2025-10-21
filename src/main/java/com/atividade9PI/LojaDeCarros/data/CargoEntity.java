
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Cargo")
public class CargoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCargo")
    private Integer idCargo;
    
    @ManyToOne
    @JoinColumn(name = "FuncionariosID", referencedColumnName = "idFuncionario")
    private FuncionariosEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "TipoCargoID", referencedColumnName = "idTipoCargo")
    private TipoCargoEntity tipoCargo;
}
