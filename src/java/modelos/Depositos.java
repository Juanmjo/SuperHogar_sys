/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author alumno
 */
public class Depositos {
    private int id_deposito;
    private String nombre_deposito;

    public Depositos() {
    }

    public Depositos(int id_deposito, String nombre_deposito) {
        this.id_deposito = id_deposito;
        this.nombre_deposito = nombre_deposito;
    }

    public int getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(int id_deposito) {
        this.id_deposito = id_deposito;
    }

    public String getNombre_deposito() {
        return nombre_deposito;
    }

    public void setNombre_deposito(String nombre_deposito) {
        this.nombre_deposito = nombre_deposito;
    }

   
    
}
