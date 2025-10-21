
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
@Data
@Entity
@Table(name="Cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;
    
    @NotBlank(message = "O nome do cliente é obrigatório.")
    private String NomeCliente;
    
    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}", message = "Formato de telefone inválido. Ex: (11) 98765-4321")
    private String Telefone;
    
    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp =  "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "O CPF deve conter apenas 11 números.")
    private String CPF;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<RegistroVendaEntity> registrosVenda = new ArrayList<>();
}
