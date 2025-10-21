package com.atividade9PI.LojaDeCarros.data;

import com.atividade9PI.LojaDeCarros.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="Veiculo")
public class VeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVeiculo")
    private Integer idVeiculo;
    
    @NotBlank(message = "A marca do veículo é obrigatória", groups = ValidationGroups.Pagina1.class)
    private String MarcaVeiculo;
    
    @NotBlank(message = "O modelo do veículo é obrigatório", groups = ValidationGroups.Pagina1.class)
    private String ModeloVeiculo;
    
    @NotBlank(message = "A cor do veículo é obrigatória", groups = ValidationGroups.Pagina1.class)
    private String CorVeiculo;
    
    @NotBlank(message = "O câmbio do veículo é obrigatório", groups = ValidationGroups.Pagina1.class)
    private String CambioVeiculo;
    
    @NotNull(message = "O ano de fabricação é obrigatório", groups = ValidationGroups.Pagina1.class)
    private Integer AnoVeiculo;
    
    @NotBlank(message = "Os acessórios são obrigatórios", groups = ValidationGroups.Pagina1.class)
    private String Acessorios;
    
    @NotNull(message = "A quilometragem é obrigatória", groups = ValidationGroups.Pagina1.class)
    @Positive(message = "A quilometragem deve ser um valor positivo", groups = ValidationGroups.Pagina1.class)
    private Double Quilometragem;
    
    @NotBlank(message = "A condição do veículo é obrigatória", groups = ValidationGroups.Pagina2.class)
    private String CondicaoVeiculo;
    
    @NotBlank(message = "O status de manutenção é obrigatório", groups = ValidationGroups.Pagina2.class)
    private String StatusManutencao;
    
    @NotNull(message = "O preço do veículo é obrigatório", groups = ValidationGroups.Pagina2.class)
    @Positive(message = "O preço deve ser um valor positivo", groups = ValidationGroups.Pagina2.class)
    private Double PrecoVeiculo;
    
    @NotBlank(message = "A garantia é obrigatória", groups = ValidationGroups.Pagina2.class)
    private String Garantia;
    
    @ManyToMany(mappedBy = "veiculos")
    private List<RegistroVeiculoEntity> registroVeiculos;
    
    @ManyToMany(mappedBy = "veiculosVendidos")
    private List<RegistroVendaEntity> registrosVenda;
}