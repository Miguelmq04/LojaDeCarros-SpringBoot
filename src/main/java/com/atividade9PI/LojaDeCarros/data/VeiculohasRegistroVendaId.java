/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade9PI.LojaDeCarros.data;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import lombok.Data;

@Data
@Embeddable
public class VeiculohasRegistroVendaId implements Serializable {
    private Integer veiculoID;
    private Integer registroVendaID;
}
