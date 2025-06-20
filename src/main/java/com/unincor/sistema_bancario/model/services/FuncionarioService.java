/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.services;

import com.unincor.sistema_bancario.exceptions.CadastroException;
import com.unincor.sistema_bancario.model.dao.FuncionarioDao;
import com.unincor.sistema_bancario.model.domain.Funcionario;
import java.time.LocalDate;


/**
 *
 * @author Thomaz
 */
public class FuncionarioService {
    private final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public void salvarFuncionario(Funcionario funcionario) throws CadastroException {
        if (funcionario == null) {
            throw new CadastroException("Funcionario informado inválido");
        }

        // is.Blank() - verificar se uma String está vazia ou contém apenas espaços em branco (ou outros caracteres considerados "brancos", como tabulações e quebras de linha).
        if (funcionario.getNome() != null || funcionario.getNome().isBlank()) {
            throw new CadastroException("Nome não foi informado");
        }

        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new CadastroException("Cpf não foi informado");
        }

        if (funcionarioDao.buscarFuncionarioPorCpf(funcionario.getCpf()) != null ) {
            throw new CadastroException("Cpf já cadastrado");
        }
        
        if (funcionarioDao.buscarFuncionarioPorEmail(funcionario.getEmail()) != null ) {
            throw new CadastroException("Email já cadastrado");
        }
        
        funcionarioDao.inserirFuncionario(funcionario);

    }

    public static void main(String[] args) {
        try {
            var funcionario = new Funcionario();
            funcionario.setNome("Thomaz");
            funcionario.setCpf("15018845622");
            funcionario.setDataNascimento(LocalDate.now());
            funcionario.setEmail("thomaz@gmail.com");
            funcionario.setSenhaHash("senhasenha");
            funcionario.setTelefone("35997412805");
            funcionario.setTurno("Noturno");
            
            FuncionarioService funcionarioService = new FuncionarioService();
            funcionarioService.salvarFuncionario(funcionario);
        } catch (CadastroException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
