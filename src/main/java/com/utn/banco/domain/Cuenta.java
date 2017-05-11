package com.utn.banco.domain;

import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class Cuenta {
    String id;
    Cliente cliente;
    String numeroCuenta;
    List<Movimiento> movimientos;


    public void setMovimientos(List<Movimiento> movs) {
        this.movimientos = movs;
    }

    public List<Movimiento>  getMovimientos() {
        return this.movimientos;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cuenta cuenta = (Cuenta) o;

        return id.equals(cuenta.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Cuenta(String id, Cliente cliente, String numeroCuenta, List<Movimiento> movimientos) {
        this.id = id;
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.movimientos = movimientos;
    }

    public Cuenta(String numeroCuenta, Cliente cliente) {
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
    }

    public void setId(String idCuenta) {
        this.id = idCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
