package com.utn.banco.model;

import com.utn.banco.domain.Cliente;
import com.utn.banco.domain.Cuenta;

import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public interface ClienteModel extends AbstractModel<Cliente> {
        List<Cliente> getByDescripcion(String descripcion);
}

