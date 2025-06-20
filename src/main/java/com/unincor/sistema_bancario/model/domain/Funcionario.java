/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.model.domain;

import java.time.LocalDate;
/**
 *
 * @author Thomaz
 */
public class Funcionario extends Pessoa{
    private Long idFuncionario;
    protected String turno;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString(){
        return "Funcioanrio{" + super.toString() + " - " + "idFuncionario=" + idFuncionario + ", turno=" + turno + "}";
    }
   
}
