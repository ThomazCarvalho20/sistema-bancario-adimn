/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.dao;

import com.unincor.sistema_bancario.configurations.MySQL;
import com.unincor.sistema_bancario.model.domain.Gerente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Thomaz
 */
public class GerenteDao {
   
    public Gerente inserirGerente(Gerente gerente) throws SQLException {
        String sql = "INSERT INTO gerentes(nome, cpf, data_nascimento, email, "
                + "telefone, senha_hash, id_agencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gerente.getNome());
            ps.setString(2, gerente.getCpf());
            ps.setDate(3, Date.valueOf(gerente.getDataNascimento()));
            ps.setString(4, gerente.getEmail());
            ps.setString(5, gerente.getTelefone());
            ps.setString(6, gerente.getSenhaHash());
            ps.setString(7, gerente.getIdAgencia());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gerente;
    }

    public Gerente buscarGerentePorId(Long idGerente) {
        String sql = "SELECT * FROM gerentes WHERE id_gerente = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idGerente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirGerenteSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Gerente construirGerenteSql(ResultSet rs) throws SQLException {
        Gerente gerente = new Gerente();
        gerente.setIdGerente(rs.getLong("id_gerente"));
        gerente.setNome(rs.getString("nome"));
        gerente.setCpf(rs.getString("cpf"));
        gerente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        gerente.setEmail(rs.getString("email"));
        gerente.setTelefone(rs.getString("telefone"));
        gerente.setSenhaHash(rs.getString("senha_Hash"));
        gerente.setGerente(rs.getString("id_agencia"));
        return gerente;
    }

    public static void main(String[] args) {
//        Cliente cliente = new Cliente(null, "Thomaz", "21324654", LocalDate.now(),
//                "thomaz.carvalho@aluno.unincor.edu.br", "4564654897", "389102312749128903");
        GerenteDao gerenteDao = new GerenteDao();
//        var clientes = clienteDao.buscarTodosClientes();
//        System.out.println(clientes);
//        clientes.forEach(c -> System.out.println("Id: " + c.getIdCliente() + "/ Nome: " + c.getNome( ) + "/ Cpf: " + c.getCpf() + "/ Data de Nascimento: " + c.getDataNascimento() +
//              "/ Data de Nascimento: " + c.getDataNascimento()+ "/ Email: " + c.getEmail() + "/ Telefone: " + c.getTelefone() + "/ Senha: " + c.getSenhaHash() ));
        var g = gerenteDao.buscarGerentePorId(1l);
        if (g != null) {
            System.out.println("Id: " + g.getIdGerente() + " - Nome: " + g.getNome());
        } else {
            System.out.println("Não tem gerente");
        }
    }
}
