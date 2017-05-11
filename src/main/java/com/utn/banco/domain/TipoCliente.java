package com.utn.banco.domain;

/**
 * Created by pablis on 17/04/17.
 */
public class TipoCliente {
    String id;
    String descripcion;

    public String getId() {
        return id;
    }

    public TipoCliente(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public void setId(String idTipoCliente) {

        this.id = idTipoCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
