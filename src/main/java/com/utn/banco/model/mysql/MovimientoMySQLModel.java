package com.utn.banco.model.mysql;

import com.utn.banco.domain.Cuenta;
import com.utn.banco.domain.Movimiento;
import com.utn.banco.model.CuentaModel;
import com.utn.banco.model.MovimientoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class MovimientoMySQLModel extends MySQLModel implements MovimientoModel {

    CuentaModel cuentaModel;


    public MovimientoMySQLModel(Connection conn) {
        super(conn);
    }

    public MovimientoMySQLModel(Connection conn, CuentaModel model) {
        super(conn);
        cuentaModel = model;
    }

    @Override
    public List<Movimiento> getByCuenta(Cuenta c) {
        try {
            String sql = "select * from movimientos where id_cuenta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(c.getId()));
            ResultSet rs = ps.executeQuery();
            List<Movimiento> movimientoList = new ArrayList<Movimiento>();
            while (rs.next()) {
                movimientoList.add(createMovimiento(rs.getInt("id_movimiento"), rs.getInt("monto"), rs.getDate("fecha"), rs.getInt("id_tipo_movimiento"), rs.getInt("id_cuenta")));
            }
            return movimientoList;

        } catch(Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public Movimiento add(Movimiento value) {
        try {
            String sql = "insert into movimientos(id_tipo_movimiento,monto,id_cuenta, fecha) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int idTipoMovimiento = 1;
            if (value.getMonto() < 0) {
                idTipoMovimiento = 2;
            }
            ps.setInt(1, idTipoMovimiento);
            ps.setDouble(2, value.getMonto());
            ps.setInt(3, Integer.parseInt(value.getCuenta().getId()));
            ps.setDate(4, new java.sql.Date(value.getFecha().getTime()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            value.setId(rs.getString(1));
            return value;

        } catch(Exception e){
            throw new UnsupportedOperationException(e);
        }

    }

    @Override
    public void remove(Movimiento value) {
        this.remove(value.getId());
    }

    @Override
    public Movimiento update(Movimiento value) {

        try {
            String sql = "update movimientos set id_tipo_movimiento = ?, monto = ? , id_cuenta = ? , fecha = ? where id_movimiento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            int idTipoMovimiento = 1;
            if (value.getMonto() < 0) {
                idTipoMovimiento = 2;
            }
            ps.setInt(1, idTipoMovimiento);
            ps.setDouble(2, value.getMonto());
            ps.setInt(3, Integer.parseInt(value.getCuenta().getId()));
            ps.setDate(4, new java.sql.Date(value.getFecha().getTime()));
            ps.setInt(5, Integer.parseInt(value.getId()));
            ps.executeUpdate();
            return value;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }


    }

    @Override
    public void remove(String id) {
        try {
            String sql = "delete from movimientos where id_movimiento  = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            if (!ps.execute()) {
                throw new UnsupportedOperationException();
            }
        } catch(SQLException  e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public Movimiento getById(String id) {
        try {
            String sql = "select * from movimientos where id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return createMovimiento(rs.getInt("id_movimiento"), rs.getInt("monto"), rs.getDate("fecha"), rs.getInt("id_tipo_movimiento"), rs.getInt("id_cuenta"));
            }
            throw new UnsupportedOperationException();

        } catch(Exception e) {
            throw new UnsupportedOperationException(e);

        }
    }


    private Movimiento createMovimiento(int id, double monto, Date fecha , int idTipoMovimiento, int idCuenta ) {
            Movimiento m = new Movimiento();
            m.setId(Integer.toString(id));
            if (idTipoMovimiento == 2) {
                monto = monto * (-1);
            }
            m.setMonto(monto);
            m.setFecha(new java.util.Date(fecha.getTime()));
            m.setCuenta(cuentaModel.getById(Integer.toString(idCuenta)));
            return m;
    }

    @Override
    public List<Movimiento> getAll() {
        try {
            String sql = "select * from movimientos ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Movimiento> movimientoList = new ArrayList<Movimiento>();
            while (rs.next()) {
                 movimientoList.add(createMovimiento(rs.getInt("id_movimiento"), rs.getInt("monto"), rs.getDate("fecha"), rs.getInt("id_tipo_movimiento"), rs.getInt("id_cuenta")));
            }
            return movimientoList;

        } catch(Exception e) {
            throw new UnsupportedOperationException(e);

        }
    }
}
