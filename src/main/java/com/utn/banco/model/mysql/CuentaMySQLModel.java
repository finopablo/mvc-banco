package com.utn.banco.model.mysql;

import com.utn.banco.domain.Cliente;
import com.utn.banco.domain.Cuenta;
import com.utn.banco.domain.TipoCliente;
import com.utn.banco.model.ClienteModel;
import com.utn.banco.model.CuentaModel;
import com.utn.banco.model.MovimientoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class CuentaMySQLModel extends MySQLModel implements CuentaModel {

    ClienteModel clienteModel;

    public CuentaMySQLModel(Connection conn) {
        super(conn);
    }

    public CuentaMySQLModel(Connection conn, ClienteModel clienteModel) {
        super(conn);
        this.clienteModel = clienteModel;

    }

    @Override
    public Cuenta add(Cuenta value) throws UnsupportedOperationException {
        try {
            String sql = "insert into cuentas(id_cliente,numero_cuenta) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,Integer.parseInt(value.getCliente().getId()));
            ps.setString(2, value.getNumeroCuenta());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            value.setId(rs.getString(1));
            return value;
        } catch(Exception e) {
            throw new UnsupportedOperationException(e);
        }

    }

    @Override
    public void remove(Cuenta value) throws UnsupportedOperationException {
        this.remove(value.getId());
    }

    @Override
    public Cuenta update(Cuenta value) throws UnsupportedOperationException {
        try {
            String sql = "update cuentas set numero_cuenta = ?, id_cliente = ? where id_cuenta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(value.getCliente().getId()));
            ps.setString(2, value.getNumeroCuenta());
            ps.setInt(3, Integer.parseInt(value.getId()));
            ps.executeUpdate();
            return value;
        } catch (Exception e ){
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public void remove(String id) throws UnsupportedOperationException {
        try {
            String sql = "delete from cuentas where id_cuenta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch(Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public Cuenta getById(String id) throws UnsupportedOperationException {
        try {
            String sql = "select * from cuentas where id_cuenta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            boolean result = rs.next();

            if (result) {
                return createCuenta(rs.getString("id_cuenta"), rs.getString("numero_cuenta"), rs.getString("id_cliente"));
            } else {
                return null;
            }
        } catch(Exception e){
            throw new UnsupportedOperationException(e);
        }
    }

    private Cuenta createCuenta(String id, String numeroCuenta, String idCliente) {
        return new Cuenta(id, clienteModel.getById(idCliente),numeroCuenta, null);
    }


    @Override
    public List<Cuenta> getAll() throws UnsupportedOperationException {
        try {
            String sql = "select * from cuentas";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Cuenta> list = new ArrayList<>();
            while (rs.next()) {
                list.add(createCuenta(rs.getString("id_cuenta"), rs.getString("numero_cuenta"), rs.getString("id_cliente")));
            }
            return list;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public Cuenta getByNumero(String numero) {
        try {
            String sql = "select * from cuentas where numero_cuenta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            boolean result = rs.next();
            if (result) {
                return createCuenta(rs.getString("id_cuenta"), rs.getString("numero_cuenta"), rs.getString("id_cliente"));
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
