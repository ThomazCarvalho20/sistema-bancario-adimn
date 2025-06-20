/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.dao;

import com.unincor.sistema_bancario.configurations.MySQL;
import com.unincor.sistema_bancario.model.domain.Agencia;
import com.unincor.sistema_bancario.model.domain.Gerente;
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
public class GerenteDao {

    public Gerente inserirGerente(Gerente gerente){
        String sql = "INSERT INTO gerentes(nome, cpf, data_nascimento, email, "
                + "telefone, senha_hash, id_agencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gerente.getNome());
            ps.setString(2, gerente.getCpf());
            ps.setDate(3, Date.valueOf(gerente.getDataNascimento()));
            ps.setString(4, gerente.getEmail());
            ps.setString(5, gerente.getTelefone());
            ps.setString(6, gerente.getSenhaHash());
            /*o if ser ve para que o código não quebre na agência, não permitindo que o usuário cadastre um gerente sem agencia*/
            if (gerente.getAgencia() != null) {
                ps.setLong(7, gerente.getAgencia().getIdAgencia());
            } else {
                ps.setObject(7, null);
            }
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Gerente> buscarTodosGerentes() {
        List<Gerente> gerentes = new ArrayList<>();
        String sql = "SELECT * FROM gerentes";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var gerente = construirGerenteSql(rs);
                gerentes.add(gerente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gerentes;
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

    public Gerente buscarGerentePorCpf(String cpf) {
        String sql = "SELECT * FROM gerentes WHERE cpf = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirGerenteSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Gerente buscarGerentePorEmail(String email) {
        String sql = "SELECT * FROM gerentes WHERE email = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
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
        /*buscar agência no banco de dados*/
        Agencia agencia = new AgenciaDao().buscarAgenciaPorId(rs.getLong("id_agencia"));
        gerente.setAgencia(agencia);
        return gerente;
    }

    public static void main(String[] args) {
        // Crinado um gerente para inserir
        var gerente = new Gerente();
        gerente.setNome("João");
        gerente.setCpf("14725836955");
        gerente.setDataNascimento(LocalDate.now());
        gerente.setEmail("joao@gmail.com");
        gerente.setSenhaHash("essetemsenha");
        gerente.setTelefone("35974122589");
        var agencia = new AgenciaDao().buscarAgenciaPorId(2l);
        gerente.setAgencia(agencia);

        //Verificar se o gerente foi inserido
        var gerenteDao = new GerenteDao();
        gerenteDao.inserirGerente(gerente);
        System.out.println("==============================");

        //Listou todos gerente
        var gerentes = gerenteDao.buscarTodosGerentes();
        gerentes.forEach(System.out::println);
        System.out.println("================================");

        //Buscar todos os gerentes
        var gerenteBusca = gerenteDao.buscarGerentePorId(2l);
        System.out.println(gerenteBusca);

    }

    /* Erro de que não tem o metodo, não criar, tem algo errado no services e não puxa o metodo domain
    public void buscarGerentePorCpfGerente(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
