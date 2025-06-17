/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.services;

import com.unincor.sistema_bancario.exceptions.CadastroException;
import com.unincor.sistema_bancario.model.dao.GerenteDao;
import com.unincor.sistema_bancario.model.domain.Gerente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */
public class GerenteService {
     private final GerenteDao gerenteDao = new GerenteDao();
    private Object gerenteBuscar;

    public void salvarGerente(Gerente gerente) throws CadastroException, SQLException {
        if (gerente.getCpf() == null || gerente.getCpf().isBlank()) {
            throw new CadastroException("Gerente não possui" + "um cpf cadastrado!");
        }

        // Criar uma validação se o id do gerente já está cadastrado
        gerenteDao.buscarGerentePorId(gerente.getIdGerente());
        if (gerenteBuscar != null) {
            throw new CadastroException("ID de Gerente já está cadastrado!");
        }

        // Validar se o gerente está com telefone preenchidos
        if (gerente.getTelefone() == null || gerente.getTelefone().isBlank()) {
            throw new CadastroException("O gerente não possui" + "um telefone informado!");
        }
        // is.Blank() - verificar se uma String está vazia ou contém apenas espaços em branco (ou outros caracteres considerados "brancos", como tabulações e quebras de linha).
        if (gerente.getAgencia()== null || gerente.getAgencia().getNumero().isBlank()) {
            throw new CadastroException("O gerente não possui" + "uma agência!");
        }

        gerenteDao.inserirGerente(gerente);

    }

    public List<Gerente> buscarGerentes() {

        return gerenteDao.buscarTodosGerentes();

    }

    public static void main(String[] args) throws SQLException {
        GerenteService gerenteService = new GerenteService();

        Gerente gerente = new Gerente();

        try {
            gerenteService.salvarGerente(gerente);
        } catch (CadastroException ex) {
            Logger.getLogger(GerenteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
