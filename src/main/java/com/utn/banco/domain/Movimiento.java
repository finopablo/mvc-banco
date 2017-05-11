package com.utn.banco.domain;


import java.util.Date;

/**
 * Created by pablis on 17/04/17.
 */
public class Movimiento {

    String id;
    double monto;
    Cuenta cuenta;
    java.util.Date fecha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movimiento that = (Movimiento) o;

        if (Double.compare(that.monto, monto) != 0) return false;
        if (!id.equals(that.id)) return false;
        if (cuenta != null ? !cuenta.equals(that.cuenta) : that.cuenta != null) return false;
        return !(fecha != null ? !fecha.equals(that.fecha) : that.fecha != null);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {

        return id;
    }


    public Movimiento(){

    }
    public Movimiento(String id, double monto, Cuenta cuenta, Date fecha) {
        this.id = id;
        this.monto = monto;
        this.cuenta = cuenta;
        this.fecha = fecha;
    }

    public void setId(String id) {

        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }
}
