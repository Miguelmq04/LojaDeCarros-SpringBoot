
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="Funcionarios")
public class FuncionariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFuncionario")
    private Integer idFuncionario;
    
    @NotBlank(message = "O nome do funcionário é obrigatório.")
    private String NomeFuncionario;
    
    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}", message = "Formato de telefone inválido. Ex: (11) 98765-4321")
    private String Telefone;
    
    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "O CPF deve conter apenas 11 números.")
    private String CPF;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FuncionarioidLogin", referencedColumnName = "idLoginFuncionario")
    @Valid
    private LoginFuncionarioEntity login;

    @OneToMany(mappedBy = "funcionario")
    private List<RegistroVeiculoEntity> registrosVeiculos;

    @OneToMany(mappedBy = "funcionario")
    private List<RegistroVendaEntity> registrosVenda;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @NotNull(message = "Funcionário é obrigatório.")
    private List<CargoEntity> cargos = new ArrayList<>();
}
