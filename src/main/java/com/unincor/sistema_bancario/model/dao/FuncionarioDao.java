/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.dao;

import com.unincor.sistema_bancario.configurations.MySQL;
import com.unincor.sistema_bancario.model.domain.Funcionario;
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
public class FuncionarioDao {

    public Funcionario inserirFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios(nome, cpf, data_nascimento, email, "
                + "telefone, senha_hash, turno) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            ps.setString(4, funcionario.getEmail());
            ps.setString(5, funcionario.getTelefone());
            ps.setString(6, funcionario.getSenhaHash());
            ps.setString(7, funcionario.getTurno());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var funcionario = construirFuncionarioSql(rs);
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionarios;
    }

    public Funcionario buscarFuncionarioPorId(Long idFuncionario) {
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            /*setLong - Não da certo se for null / setObject - executa com o valor null*/
            ps.setObject(1, idFuncionario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirFuncionarioSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Funcionario construirFuncionarioSql(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(rs.getLong("id_funcionario"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        funcionario.setEmail(rs.getString("email"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setSenhaHash(rs.getString("senha_Hash"));
        funcionario.setTurno(rs.getString("turno"));
        return funcionario;
    }
   
    public static void main(String[] args) {
        /*
        Funcionario funcionario = new Funcionario(null, "Thomaz", "21324654", LocalDate.now(),
                "thomaz.carvalho@aluno.unincor.edu.br", "4564654897", "389102312749128903");
         FuncionarioDao.inserirFuncionario(funcionario);

        var funcionarios = funcionarioDao.buscarTodosFuncionarios();
        System.out.println(funcionarios);
        funcionarios.forEach(c -> System.out.println("Id: " + c.getIdFuncionario() + "/ Nome: " + c.getNome( ) + "/ Cpf: " + c.getCpf() + "/ Data de Nascimento: " + c.getDataNascimento() +
              "/ Data de Nascimento: " + c.getDataNascimento()+ "/ Email: " + c.getEmail() + "/ Telefone: " + c.getTelefone() + "/ Senha: " + c.getSenhaHash() ));
        
        var f = FuncionarioDao.buscarFuncionarioPorId(1l);
        if (f != null) {
            System.out.println("Id: " + f.getIdFuncionario() + " - Nome: " + f.getNome());
        } else {
            System.out.println("Não tem funcionário");
        }
         */
    }
    
}
