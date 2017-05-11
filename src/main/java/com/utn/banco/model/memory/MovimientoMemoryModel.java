package com.utn.banco.model.memory;

import com.utn.banco.domain.Cuenta;
import com.utn.banco.domain.Movimiento;
import com.utn.banco.model.CuentaModel;
import com.utn.banco.model.MovimientoModel;
import com.utn.banco.model.mysql.MySQLModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class MovimientoMemoryModel implements MovimientoModel {

    static int LAST_ID = 1;

    CuentaModel cuentaModel;
    List<Movimiento> list = new ArrayList<>();

    public MovimientoMemoryModel(CuentaModel model) {
        cuentaModel = model;
    }

    @Override
    public List<Movimiento> getByCuenta(Cuenta c) {

        List<Movimiento> result = new ArrayList<>();
        for (Movimiento m : list) {
            if (m.getCuenta().equals(c)) {
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public Movimiento add(Movimiento value) {
        value.setId(Integer.toString(LAST_ID++));
        list.add(value);
        return value;
    }

    @Override
    public void remove(Movimiento value) {
        list.remove(value);
    }

    @Override
    public Movimiento update(Movimiento value) {
        int index = list.indexOf(value);
        list.set(index, value);
        return value;
    }

    @Override
    public void remove(String id) {

        for (Movimiento m : list) {
            if (m.getId().equals(id)) {
                list.remove(m);
            }
        }
    }

    @Override
    public Movimiento getById(String id) {

        for (Movimiento m : list) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }



    @Override
    public List<Movimiento> getAll() {
            return list;
    }
}
