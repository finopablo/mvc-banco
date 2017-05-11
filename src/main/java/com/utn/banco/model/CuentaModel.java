package com.utn.banco.model;

import com.utn.banco.domain.Cuenta;

/**
 * Created by pablis on 17/04/17.
 */
public interface CuentaModel extends AbstractModel<Cuenta>  {


    Cuenta getByNumero(String numero);

}
