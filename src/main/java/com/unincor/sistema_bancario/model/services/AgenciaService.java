/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.services;

import com.unincor.sistema_bancario.exceptions.CadastroException;
import com.unincor.sistema_bancario.model.dao.AgenciaDao;
import com.unincor.sistema_bancario.model.domain.Agencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */

public class AgenciaService {
    
    private final AgenciaDao agenciaDao = new AgenciaDao();
    
    public void salvarAgencia(Agencia agencia) throws CadastroException {
        if (agencia.getCodigoAgencia() == null || agencia.getCodigoAgencia() .isBlank()) {
            throw new CadastroException ("A agência não possui" + "um código de agência!");
        }
        
        
        // Criar uma validação se o código da agência já está cadastrado
        Agencia agenciaBusca = agenciaDao.buscarAgenciaPorCodigoAgencia(agencia.getCodigoAgencia());
        if (agenciaBusca != null) {
            throw new CadastroException("Código de Agência já está cadastrado!");
        }
        
        // Validar se a agência está com Cidade e UF preenchidos
        if (agencia.getCidade() == null || agencia.getCidade() .isBlank()) {
            throw new CadastroException ("A agência não possui uma cidade informada!");
        }
        if (agencia.getUf()== null || agencia.getUf().isBlank()) {
            throw new CadastroException("A agência não possui uma UF informada");
        }

        agenciaDao.inserirAgencia(agencia);

    }
    
    public List<Agencia> buscarAgencias(){
        
        return agenciaDao.listarTodasAgencias();
        
    }
    
    public static void main(String[] args)  {
        AgenciaService agenciaService = new AgenciaService();
        
        Agencia agencia = new Agencia(null, "1547", "Itajuba", "MG", "Rua de itajuba", "587", "37456235");
        
        try {
            agenciaService.salvarAgencia(agencia);
        } catch (CadastroException ex) {
            Logger.getLogger(AgenciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

