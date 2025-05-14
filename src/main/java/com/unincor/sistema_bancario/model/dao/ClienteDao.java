/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.dao;

import com.unincor.sistema_bancario.configurations.MySQL;
import com.unincor.sistema_bancario.model.domain.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */
public class ClienteDao {
    
    public void inserirCliente(Cliente cliente){
        String sql = "INSERT INTO clientes(nome, cpf, data_nascimento, email, "
                + "telefone, senha_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = MySQL.connect();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getSenhaHash());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public List<Cliente> buscarTodosClientes(){
          List<Cliente> clientes = new ArrayList<>();
          String sql = "SELECT * FROM clientes";
          try(Connection con = MySQL.connect();
                PreparedStatement ps = con.prepareStatement(sql)){
              ResultSet rs = ps.executeQuery();
              while(rs.next()) {
                  Cliente cliente = new Cliente();
                  cliente.setIdCliente(rs.getLong("id_cliente"));
                  cliente.setNome(rs.getString("nome"));
                  cliente.setCpf(rs.getString("cpf"));
                  cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                  cliente.setEmail(rs.getString("email"));
                  cliente.setTelefone(rs.getString("telefone"));
                  cliente.setSenhaHash(rs.getString("senha_Hash"));
                  clientes.add(cliente);
              }
          } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
      }
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente(null, "Thomaz", "21324654", LocalDate.now(),
                "thomaz.carvalho@aluno.unincor.edu.br", "4564654897", "389102312749128903");
        ClienteDao clienteDao = new ClienteDao();
        var clientes = clienteDao.buscarTodosClientes();
        System.out.println(clientes);
        clientes.forEach(c -> System.out.println("Id: " + c.getIdCliente() + "/ Nome: " + c.getNome( ) + "/ Cpf: " + c.getCpf() + "/ Data de Nascimento: " + c.getDataNascimento() +
              "/ Data de Nascimento: " + c.getDataNascimento()+ "/ Email: " + c.getEmail() + "/ Telefone: " + c.getTelefone() + "/ Senha: " + c.getSenhaHash() ));
        
    }
}
