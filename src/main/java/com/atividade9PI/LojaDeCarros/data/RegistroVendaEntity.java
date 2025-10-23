/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="RegistroVenda")
public class RegistroVendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegistroVenda")
    private Integer idRegistroVenda;
    
    @ManyToOne
    @JoinColumn(name = "ClienteID", referencedColumnName = "idCliente")
    @NotNull(message = "Cliente é obrigatório.")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "FuncionariosID", referencedColumnName = "idFuncionario")
    @NotNull(message = "Funcionário é obrigatório.")
    private FuncionariosEntity funcionario;
    
    private LocalDate DataRegistro;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "VeiculoHasRegistroVenda",
        joinColumns = @JoinColumn(name = "RegistroVendaID"),
        inverseJoinColumns = @JoinColumn(name = "veiculoID")
    )
    @NotEmpty(message = "Selecione um veículo.")
    private List<VeiculoEntity> veiculosVendidos;
}
