package com.utn.banco.controller;

import com.utn.banco.model.memory.ClienteMemoryModel;
import com.utn.banco.model.memory.CuentaMemoryModel;
import com.utn.banco.model.memory.MovimientoMemoryModel;
import com.utn.banco.model.mysql.ClienteMySQLModel;
import com.utn.banco.model.mysql.CuentaMySQLModel;
import com.utn.banco.model.mysql.MovimientoMySQLModel;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by pablis on 18/04/17.
 */
public class MemoryModelFactory extends ModelFactory {
    Connection conn;

    private static ModelFactory instance;

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new MemoryModelFactory();
        }
        return instance;
    }

    private MemoryModelFactory() {
        try {
          clienteModel = new ClienteMemoryModel();
          cuentaModel = new CuentaMemoryModel(clienteModel);
          movimientoModel = new MovimientoMemoryModel(cuentaModel);
        } catch(Exception e) {
            e.printStackTrace();;
        }
    }
}
