/**
 * Created by eve0017280 on 06/01/16.
 */
package com.grid.ventas.cr.refundrestserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Historico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int transaccionOriginal;

    private int formadepago;

    private double montoDisponible;

    private int numeroDevolucion;

    private long fechaDevolucion;

    public long getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(long fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getFormadepago() {
        return formadepago;
    }

    public void setFormadepago(int formadepago) {
        this.formadepago = formadepago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(double montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public int getNumeroDevolucion() {
        return numeroDevolucion;
    }

    public void setNumeroDevolucion(int numeroDevolucion) {
        this.numeroDevolucion = numeroDevolucion;
    }

    public int getTransaccionOriginal() {
        return transaccionOriginal;
    }

    public void setTransaccionOriginal(int transaccionOriginal) {
        this.transaccionOriginal = transaccionOriginal;
    }
}
/*
 * public final class Historico { }
 */
