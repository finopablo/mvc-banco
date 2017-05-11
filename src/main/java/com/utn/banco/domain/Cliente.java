package com.utn.banco.domain;

/**
 * Created by pablis on 17/04/17.
 */
public class Cliente {

    String id;
    String descripcion;
    String cuit;

    public Cliente(String id, String descripcion, String cuit, TipoCliente tipoCliente) {
        this.id = id;
        this.descripcion = descripcion;
        this.cuit = cuit;
        this.tipoCliente = tipoCliente;
    }

    public Cliente(String descripcion, String cuit, TipoCliente tipoCliente) {
            this.descripcion = descripcion;
        this.cuit = cuit;
        this.tipoCliente = tipoCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    TipoCliente tipoCliente;

}
