package com.utn.banco.controller;

import com.utn.banco.model.ClienteModel;
import com.utn.banco.model.CuentaModel;
import com.utn.banco.model.MovimientoModel;
import com.utn.banco.model.memory.ClienteMemoryModel;
import com.utn.banco.model.memory.CuentaMemoryModel;
import com.utn.banco.model.memory.MovimientoMemoryModel;
import com.utn.banco.model.mysql.ClienteMySQLModel;
import com.utn.banco.model.mysql.CuentaMySQLModel;
import com.utn.banco.model.mysql.MovimientoMySQLModel;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by pablis on 17/04/17.
 */
public abstract class ModelFactory {

    MovimientoModel movimientoModel;
    ClienteModel clienteModel;
    CuentaModel cuentaModel;


    public MovimientoModel getMovimientoModel() {
        return movimientoModel;
    }


    public ClienteModel getClienteModel() {
        return clienteModel;
    }


    public CuentaModel getCuentaModel() {
        return cuentaModel;
    }


}
