package com.atividade9PI.LojaDeCarros;

import com.atividade9PI.LojaDeCarros.data.VeiculoEntity;
import com.atividade9PI.LojaDeCarros.data.FuncionariosEntity;
import com.atividade9PI.LojaDeCarros.data.RegistroVeiculoEntity;
import com.atividade9PI.LojaDeCarros.repository.FuncionarioRepository;
import com.atividade9PI.LojaDeCarros.repository.VeiculoRepository;
import com.atividade9PI.LojaDeCarros.repository.RegistroVeiculoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RegistroVeiculoTest {
    
    @Autowired
    private VeiculoRepository veiculoRepository;
    
    @Autowired
    private FuncionarioRepository funcionariosRepository;
    
    @Autowired
    private RegistroVeiculoRepository registroVeiculoRepository;
    
    private VeiculoEntity veiculo;
    
    @BeforeEach
    public void setUp() {
        veiculo = new VeiculoEntity();
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testRealizarRegistro() {
        veiculo.setMarcaVeiculo("TesteMarca");
        veiculo.setModeloVeiculo("TesteModelo");
        veiculo.setCorVeiculo("TesteCor");
        veiculo.setCambioVeiculo("TesteCambio");
        veiculo.setAnoVeiculo(2016);
        veiculo.setAcessorios("TesteAcessorio");
        veiculo.setQuilometragem(50.00);
        veiculo.setStatusManutencao("Em dia");
        veiculo.setCondicaoVeiculo("Semi Novo");
        veiculo.setPrecoVeiculo(50000.00);
        veiculo.setGarantia("Fabricante");
        
        FuncionariosEntity funcionarioTeste = funcionariosRepository.findById(2)
                .orElseGet(() -> {
                    FuncionariosEntity novoFunc = new FuncionariosEntity();
                    novoFunc.setIdFuncionario(2);
                    return funcionariosRepository.save(novoFunc);
                });
        
        RegistroVeiculoEntity registro = new RegistroVeiculoEntity();
        registro.setFuncionario(funcionarioTeste);
        registro.setDataRegistro(LocalDate.now());
        
        VeiculoEntity veiculoSalvo = veiculoRepository.save(veiculo);
        
        registro.setVeiculos(Arrays.asList(veiculoSalvo));
        registroVeiculoRepository.save(registro);
        
        assertNotNull(veiculoSalvo.getIdVeiculo(), "O ID do veículo deve ser gerado");
        assertEquals("TesteMarca", veiculoSalvo.getMarcaVeiculo());
        assertEquals("TesteModelo", veiculoSalvo.getModeloVeiculo());
        assertEquals(2016, veiculoSalvo.getAnoVeiculo());
        assertEquals(50000.00, veiculoSalvo.getPrecoVeiculo(), 0.01);
    }
    
    @Test
    public void testVeiculoComDadosInvalidos() {
        VeiculoEntity veiculoInvalido = new VeiculoEntity();
        
        assertThrows(Exception.class, () -> {
            veiculoRepository.save(veiculoInvalido);
            veiculoRepository.flush(); 
        });
    }
}