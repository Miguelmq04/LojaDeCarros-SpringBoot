
package com.atividade9PI.LojaDeCarros.service;

import com.atividade9PI.LojaDeCarros.data.LoginFuncionarioEntity;
import com.atividade9PI.LojaDeCarros.repository.LoginFuncionarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginFuncionarioService {
    @Autowired
    
    LoginFuncionarioRepository loginRepository;
            
    public LoginFuncionarioEntity criaLogin(LoginFuncionarioEntity login){
        login.setIdLoginFuncionario(null);
        loginRepository.save(login);
        return login;
    }
    
    public List<LoginFuncionarioEntity> listarLogins(){
         return loginRepository.findAll();
    }
    
    public boolean autenticar(String login, String senha) {
        if (login == null || senha == null) {
            return false;
        }
        
        List<LoginFuncionarioEntity> logins = loginRepository.findAll();
        
        return logins.stream()
                .anyMatch(l -> l.getLogin().equals(login) && l.getSenha().equals(senha));
    }
}
