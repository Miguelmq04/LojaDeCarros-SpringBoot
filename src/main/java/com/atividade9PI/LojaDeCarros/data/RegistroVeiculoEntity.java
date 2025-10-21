
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="RegistroVeiculo")
public class RegistroVeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegistroVeiculo")
    private Integer idRegistroVeiculo;
    
    @ManyToOne
    @JoinColumn(name = "FuncionariosID", referencedColumnName = "idFuncionario")
    private FuncionariosEntity funcionario;
    
    private LocalDate DataRegistro;
    
    @ManyToMany
    @JoinTable(
        name = "VeiculoHasRegistroVeiculo",
        joinColumns = @JoinColumn(name = "RegistroVeiculoID"),
        inverseJoinColumns = @JoinColumn(name = "VeiculoID")
    )
    private List<VeiculoEntity> veiculos;
}
