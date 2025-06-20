/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.services;

import com.unincor.sistema_bancario.exceptions.CadastroException;
import com.unincor.sistema_bancario.model.dao.AgenciaDao;
import com.unincor.sistema_bancario.model.dao.GerenteDao;
import com.unincor.sistema_bancario.model.domain.Gerente;
import java.time.LocalDate;


/**
 *
 * @author Thomaz
 */
public class GerenteService {
     public GerenteDao gerenteDao = new GerenteDao();

    public void salvarGerente(Gerente gerente) throws CadastroException {
        if (gerente == null) {
            throw new CadastroException("Gerente informado inválido");
        }

        if (gerente.getNome() != null || gerente.getCpf().isBlank()) {
            throw new CadastroException("Nome não informado");
        }

        if (gerente.getCpf() == null || gerente.getCpf().isBlank()) {
            throw new CadastroException("Cpf não informado");
        }

        if (gerenteDao.buscarGerentePorCpf(gerente.getCpf())== null) {
            throw new CadastroException("Cpf já cadastrado");
        }
        
        if (gerenteDao.buscarGerentePorEmail(gerente.getEmail())== null) {
            throw new CadastroException("Email já cadastrado");
        }

        gerenteDao.inserirGerente(gerente);

    }

    public static void main(String[] args) {
        try {
            var gerente = new Gerente();
            gerente.setNome("Thomaz");
            gerente.setCpf("15918845655");
            gerente.setDataNascimento(LocalDate.now());
            gerente.setEmail("thomaz@gmail.com");
            gerente.setSenhaHash("senha123");
            gerente.setTelefone("35997412806");
            
            var agencia = new AgenciaDao().buscarAgenciaPorId(2l);
            gerente.setAgencia(agencia);
            
            GerenteService gerenteService = new GerenteService();
            gerenteService.salvarGerente(gerente);
            
        } catch (CadastroException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
