
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="LoginFuncionario")
public class LoginFuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoginFuncionario")
    private Integer idLoginFuncionario;
    
    @NotBlank(message = "O login é obrigatório.")
    private String Login;
    
    @NotBlank(message = "A senha é obrigatória.")
    private String Senha;
    
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "login")
    private FuncionariosEntity funcionario;
}


