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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */
public class ClenteDao {
    
    public void inserirCliente(Cliente cliente){
        String sql = "INSERT INTO clientes(nome, cpf, data_nascimento, email, telefone, senha_hash) VALUES (?,?,?,?,?,?)";
        try(Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            ps.setString(4, cliente.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(ClenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
