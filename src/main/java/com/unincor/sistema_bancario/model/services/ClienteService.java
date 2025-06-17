/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.services;

import com.unincor.sistema_bancario.exceptions.CadastroException;
import com.unincor.sistema_bancario.model.dao.ClienteDao;
import com.unincor.sistema_bancario.model.domain.Cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */
public class ClienteService {

    private final ClienteDao clienteDao = new ClienteDao();
    private Object clienteBusca;

    public void salvarCliente(Cliente cliente) throws CadastroException {
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new CadastroException("Cliente não possui" + "um cpf de cliente!");
        }

        // Criar uma validação se o cpf do cliente já está cadastrado
        clienteDao.buscarClientePorId(cliente.getIdCliente());
        if (clienteBusca != null) {
            throw new CadastroException("Cliente já está cadastrado!");
        }

        // Validar se o cliente está com telefone preenchidos
        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
            throw new CadastroException("O cliente não possui" + "um telefone informado!");
        }
        // is.Blank() - verificar se uma String está vazia ou contém apenas espaços em branco (ou outros caracteres considerados "brancos", como tabulações e quebras de linha).
        if (cliente.getSenhaHash() == null || cliente.getSenhaHash().isBlank()) {
            throw new CadastroException("O cliente não possui" + "uma senha!");
        }

        clienteDao.inserirCliente(cliente);

    }

    public List<Cliente> buscarClientes() {

        return clienteDao.buscarTodosClientes();

    }

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();

        Cliente cliente = new Cliente(null, "Thomaz", "15018899999", LocalDate.MIN, "thomaz@gmail.ocm", "3599741000", "senha");

        try {
            clienteService.salvarCliente(cliente);
        } catch (CadastroException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
