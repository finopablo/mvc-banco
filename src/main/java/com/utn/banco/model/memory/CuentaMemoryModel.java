package com.utn.banco.model.memory;

import com.utn.banco.domain.Cliente;
import com.utn.banco.domain.Cuenta;
import com.utn.banco.domain.Movimiento;
import com.utn.banco.domain.TipoCliente;
import com.utn.banco.model.ClienteModel;
import com.utn.banco.model.CuentaModel;
import com.utn.banco.model.mysql.MySQLModel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class CuentaMemoryModel implements CuentaModel {

    ClienteModel clienteModel;
    List<Cuenta> list = new ArrayList<>();
    static int LAST_ID=1;

    public CuentaMemoryModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    @Override
    public Cuenta add(Cuenta value) throws UnsupportedOperationException {
        value.setId(Integer.toString(LAST_ID++));
        list.add(value);
        return value;
    }

    @Override
    public void remove(Cuenta value) throws UnsupportedOperationException {
        list.remove(value);
    }

    @Override
    public Cuenta update(Cuenta value) throws UnsupportedOperationException {
        int index = list.indexOf(value);
        list.set(index, value);
        return value;
    }

    @Override
    public void remove(String id) throws UnsupportedOperationException {
        for (Cuenta m : list) {
            if (m.getId().equals(id)) {
                list.remove(m);
            }
        }
    }

    @Override
    public Cuenta getById(String id) throws UnsupportedOperationException {

        for (Cuenta  c : list) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;


    }

    @Override
    public List<Cuenta> getAll() throws UnsupportedOperationException {
        return list;
    }

    @Override
    public Cuenta getByNumero(String numero) {
        for (Cuenta  c : list) {
            if (c.getNumeroCuenta().equals(numero)) {
                return c;
            }
        }
        return null;

    }
}
