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
// extends é usado para puxar uma classe; aqui está puxando a de Pessoa
public class Gerente extends Pessoa {

    private Long idGerente;
    private Agencia agencia;

    public Gerente() {
    }

    public Long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Long idGerente) {
        this.idGerente = idGerente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    @Override
    public String toString() {
        return "Gerente{" + super.toString() + "idGerente=" + idGerente + ", agencia=" + agencia + "}";
    }
}
