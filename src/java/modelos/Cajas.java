/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author alumno
 */
public class Cajas {
    private int id_caja;
    private String nombre_caja;
    private Date fecha;
    private Time hora_apertura;
    private Time hora_cierre;

    public Cajas() {
    }

    public Cajas(int id_caja, String nombre_caja, Date fecha, Time hora_apertura, Time hora_cierre) {
        this.id_caja = id_caja;
        this.nombre_caja = nombre_caja;
        this.fecha = fecha;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public String getNombre_caja() {
        return nombre_caja;
    }

    public void setNombre_caja(String nombre_caja) {
        this.nombre_caja = nombre_caja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(Time hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public Time getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(Time hora_cierre) {
        this.hora_cierre = hora_cierre;
    }

    
  
}
