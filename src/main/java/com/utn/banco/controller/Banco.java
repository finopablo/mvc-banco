package com.utn.banco.controller;


import com.utn.banco.domain.Cliente;
import com.utn.banco.domain.Cuenta;
import com.utn.banco.domain.Movimiento;
import com.utn.banco.domain.TipoCliente;
import com.utn.banco.model.ClienteModel;
import com.utn.banco.model.CuentaModel;
import com.utn.banco.model.MovimientoModel;

import java.util.Date;
import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class Banco {

        ModelFactory modelFactory;

        public static void main(String args[]) {
            Banco b = new Banco();
            b.modelFactory = MemoryModelFactory.getInstance();
            Cliente cli = b.addCliente("CLIENTE PRUEBA3", "141414", new TipoCliente("1", "Persona Fisica"));
            Cuenta cu = b.addCuenta("141414", cli);
            Movimiento m = b.addMovimiento(999.36, new Date(), cu);
            Cuenta cuenta = b.getCuenta("141414", true);

            System.out.println(cuenta);
        }

        public Cliente addCliente(String description, String cuit,TipoCliente tipoCliente) {
            ClienteModel model = modelFactory.getClienteModel();
            Cliente cli = new Cliente(null, description, cuit, tipoCliente);
            cli = model.add(cli);
            return cli;

        }

        public Cuenta addCuenta(String numeroCuenta, Cliente  cliente){
            CuentaModel model = modelFactory.getCuentaModel();
            return model.add(new Cuenta(numeroCuenta, cliente));
        }

        public Movimiento addMovimiento(double monto, Date fecha, Cuenta c) {
            MovimientoModel model = modelFactory.getMovimientoModel();
            Movimiento m = new Movimiento();
            m.setFecha(fecha);
            m.setMonto(-500);
            m.setCuenta(c);
            return model.add(m);
        }

        public Cuenta getCuenta(String numero, boolean includeMovimientos) {
            Cuenta c = modelFactory.getCuentaModel().getByNumero(numero);
            if (includeMovimientos) {
                List<Movimiento> list = modelFactory.getMovimientoModel().getByCuenta(c);
                c.setMovimientos(list);
            }
            return c;
        }

}
