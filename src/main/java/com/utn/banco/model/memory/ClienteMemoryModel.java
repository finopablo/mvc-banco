package com.utn.banco.model.memory;

import com.utn.banco.domain.Cliente;
import com.utn.banco.domain.Cuenta;
import com.utn.banco.model.ClienteModel;
import com.utn.banco.model.mysql.MySQLModel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class ClienteMemoryModel  implements ClienteModel {


    List<Cliente> list = new ArrayList<>();
    static int LAST_ID=1;
@Override
    public List<Cliente> getByDescripcion(String descripcion) {
        List<Cliente> result = new ArrayList<>();
        for (Cliente m : list) {
            if (m.getDescripcion().startsWith(descripcion)) {
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public Cliente add(Cliente value) throws UnsupportedOperationException {
        value.setId(Integer.toString(LAST_ID++));
        list.add(value);
        return value;
    }

    @Override
    public void remove(Cliente value) throws UnsupportedOperationException {
        list.remove(value);
    }

    @Override
    public Cliente update(Cliente value) throws UnsupportedOperationException {
        int index = list.indexOf(value);
        list.set(index, value);
        return value;
    }

    @Override
    public void remove(String id) throws UnsupportedOperationException {
        for (Cliente m : list) {
            if (m.getId().equals(id)) {
                list.remove(m);
            }
        }
    }

    @Override
    public Cliente getById(String id) throws UnsupportedOperationException {
        for (Cliente m : list) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> getAll() throws UnsupportedOperationException {
        return list;
    }
}
