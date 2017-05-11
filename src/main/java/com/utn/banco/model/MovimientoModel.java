package com.utn.banco.model;

import com.utn.banco.domain.Cuenta;
import com.utn.banco.domain.Movimiento;

import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public interface MovimientoModel extends AbstractModel<Movimiento> {

    List<Movimiento> getByCuenta(Cuenta c);
}
