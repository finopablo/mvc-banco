package com.utn.banco.model.mysql;

import com.utn.banco.domain.Cliente;
import com.utn.banco.domain.TipoCliente;
import com.utn.banco.model.ClienteModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public class ClienteMySQLModel extends MySQLModel implements ClienteModel {
    public ClienteMySQLModel(Connection conn) {
        super(conn);
    }

    @Override
    public List<Cliente> getByDescripcion(String descripcion) {

        try {
            List<Cliente> list = new ArrayList<>();
            String sql = "select c.id_cliente, c.descripcion, c.cuit, tc.id_tipo_cliente, tc.descripcion as descrip_tipo from clientes c join tipo_cliente tc on c.id_tipo_cliente = tc.id_tipo_cliente where c.descripcion like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,  descripcion + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createCliente(rs.getString("id_cliente"),rs.getString("descripcion"), rs.getString("cuit"),rs.getInt("id_tipo_cliente"), rs.getString("descrip_tipo")));
            }
            return list;

        } catch(Exception e){
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public Cliente add(Cliente value) throws UnsupportedOperationException {
        try {
            String sql = "insert into clientes(descripcion,cuit, id_tipo_cliente, nro_cuit) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, value.getDescripcion());
            ps.setString(2, value.getCuit());
            ps.setInt(3, Integer.parseInt(value.getTipoCliente().getId()));
            ps.setInt(4,Integer.parseInt(value.getCuit()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            value.setId(rs.getString(1));
            return value;
        } catch(Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private Cliente createCliente(String id, String descripcion, String cuit, int idTipoCliente, String descripcionTipo) {
        return new Cliente( id,  descripcion,  cuit, new TipoCliente(Integer.toString(idTipoCliente), descripcionTipo));
    }

    @Override
    public void remove(Cliente value) throws UnsupportedOperationException {
        this.remove(value.getId());
    }

    @Override
    public Cliente update(Cliente value) throws UnsupportedOperationException {
        try {
            String sql = "update clientes set descripcion= ? ,cuit = ? , id_tipo_cliente = ? , nro_cuit = ? ) where id_cliente = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, value.getDescripcion());
            ps.setString(2, value.getCuit());
            ps.setInt(3, Integer.parseInt(value.getTipoCliente().getId()));
            ps.setInt(4, Integer.parseInt(value.getCuit()));
            ps.setInt(5, Integer.parseInt(value.getId()));
            ps.executeUpdate();
            return value;
    } catch(Exception e) {
        throw new UnsupportedOperationException(e);
    }
    }

    @Override
    public void remove(String id) throws UnsupportedOperationException {

        try {
            String sql = "delete from clientes where id_cliente = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.execute();
        } catch(Exception e){
            throw new UnsupportedOperationException(e);
        }

    }

    @Override
    public Cliente getById(String id) throws UnsupportedOperationException {
        try {
            List<Cliente> list = new ArrayList<>();
            String sql = "select c.id_cliente, c.descripcion, c.cuit, tc.id_tipo_cliente, tc.descripcion as descrip_tipo from clientes c join tipo_cliente tc on c.id_tipo_cliente = tc.id_tipo_cliente where c.id_cliente = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,  Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return createCliente(rs.getString("id_cliente"),rs.getString("descripcion"), rs.getString("cuit"),rs.getInt("id_tipo_cliente"), rs.getString("descrip_tipo"));
            } else {
                return null;
            }
        } catch(Exception e){
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public List<Cliente> getAll() throws UnsupportedOperationException {
        try {
            List<Cliente> list = new ArrayList<>();
            String sql = "select c.id_cliente, c.descripcion, c.cuit, tc.id_tipo_cliente, tc.descripcion as descrip_tipo from clientes c join tipo_cliente tc on c.id_tipo_cliente = tc.id_tipo_cliente";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createCliente(rs.getString("id_cliente"),rs.getString("descripcion"), rs.getString("cuit"),rs.getInt("id_tipo_cliente"), rs.getString("descrip_tipo")));
            }
            return list;
        } catch(Exception e){
            throw new UnsupportedOperationException(e);
        }
    }
}
