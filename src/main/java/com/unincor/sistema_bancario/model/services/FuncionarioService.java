/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.services;

import com.unincor.sistema_bancario.exceptions.CadastroException;
import com.unincor.sistema_bancario.model.dao.FuncionarioDao;
import com.unincor.sistema_bancario.model.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */
public class FuncionarioService {
    private final FuncionarioDao funcionarioDao = new FuncionarioDao();
    private Object funcionarioBusca;

    public void salvarFuncionario(Funcionario funcionario) throws CadastroException, SQLException {
        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new CadastroException("Funcionario não possui" + "um cpf cadastrado!");
        }

        // Criar uma validação se o cpf do funcionario já está cadastrado
        funcionarioDao.buscarFuncionarioPorId(Long.MIN_VALUE);
        if (funcionarioBusca != null) {
            throw new CadastroException("o fucionário já tem um id");
        }

        // Validar se o funcionario está com telefone preenchidos
        if (funcionario.getTelefone() == null || funcionario.getTelefone().isBlank()) {
            throw new CadastroException("O funcionario não possui" + "um telefone informado!");
        }
        // is.Blank() - verificar se uma String está vazia ou contém apenas espaços em branco (ou outros caracteres considerados "brancos", como tabulações e quebras de linha).
        if (funcionario.getTurno() == null || funcionario.getTurno().isBlank()) {
            throw new CadastroException("O funcionario não possui" + "um turno!");
        }

        funcionarioDao.inserirFuncionario(funcionario);

    }

    public List<Funcionario> buscarTodosFuncionarios() {

        return funcionarioDao.buscarTodosFuncionarios();

    }

    public static void main(String[] args) throws SQLException {
        FuncionarioService funcionarioService = new FuncionarioService();

        Funcionario funcionario = new Funcionario();

        try {
            funcionarioService.salvarFuncionario(funcionario);
        } catch (CadastroException ex) {
            Logger.getLogger(FuncionarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
