/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema_bancario.exceptions;

/**
 *
 * @author Thomaz
 */
/*Classe para dar mensagens de erro no código (sql, conversão de data e entre outros)*/
public class CadastroException extends Exception{

    public CadastroException(String message) {
        super(message);
    }
    
}
