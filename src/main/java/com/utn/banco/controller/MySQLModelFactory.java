package com.utn.banco.controller;

import com.utn.banco.model.ClienteModel;
import com.utn.banco.model.CuentaModel;
import com.utn.banco.model.MovimientoModel;
import com.utn.banco.model.mysql.ClienteMySQLModel;
import com.utn.banco.model.mysql.CuentaMySQLModel;
import com.utn.banco.model.mysql.MovimientoMySQLModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pablis on 18/04/17.
 */
public class MySQLModelFactory extends ModelFactory {
    Connection conn;

    private static ModelFactory instance;

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new MySQLModelFactory();
        }
        return instance;
    }

    private MySQLModelFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banco?user=root&password=a");
            clienteModel = new ClienteMySQLModel(conn);
            cuentaModel = new CuentaMySQLModel(conn, clienteModel);
            movimientoModel = new MovimientoMySQLModel(conn, cuentaModel);
        } catch(Exception e) {
            e.printStackTrace();;
        }
    }
}
