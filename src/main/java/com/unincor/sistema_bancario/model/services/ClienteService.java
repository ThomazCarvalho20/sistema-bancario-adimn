/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.services;

import com.unincor.sistema_bancario.exceptions.CadastroException;
import com.unincor.sistema_bancario.model.dao.ClienteDao;
import com.unincor.sistema_bancario.model.domain.Cliente;
import java.time.LocalDate;


/**
 *
 * @author Thomaz
 */
public class ClienteService {

    private final ClienteDao clienteDao = new ClienteDao();

    public void salvarCliente(Cliente cliente) throws CadastroException {
        if (cliente == null) {
            throw new CadastroException("Cliente informado inválido");
        }

        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new CadastroException("O nome não foi infomrado");
        }

        if (cliente.getCpf()== null || cliente.getCpf().isBlank()) {
            throw new CadastroException("O cliente não possui um cpf infomrado");
        }
        
        if (clienteDao.buscarClientePorCpf(cliente.getCpf())!= null) {
            throw new CadastroException("Cpf já cadastrado");
        }
        
        if (clienteDao.buscarClientePorEmail(cliente.getEmail())!= null) {
            throw new CadastroException("E-mail já cadastrado");
        }

        clienteDao.inserirCliente(cliente);

    }

    public static void main(String[] args) {
        try{
            var cliente = new Cliente();
            cliente.setNome("Claudia");
            cliente.setCpf("15975345622");
            cliente.setDataNascimento(LocalDate.now());
            cliente.setEmail("caludia@gmail.com");
            cliente.setSenhaHash("naotemsenha");
            cliente.setTelefone("35998520223");
            
            ClienteService clienteService = new ClienteService();
            clienteService.salvarCliente(cliente);
        } catch (CadastroException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
