package com.utn.test.banco;

import com.utn.banco.domain.Cliente;
import com.utn.banco.model.mysql.ClienteMySQLModel;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Created by pablis on 25/04/17.
 */


public class TestMySQLClienteModel extends TestCase {



    ClienteMySQLModel model;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;


    public void setUp() {
        conn = mock(Connection.class);
        model = new ClienteMySQLModel(conn);
        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
    }


    public void testGetByIdOk() {
    //Uso de Mockito
        try {
            when(conn.prepareStatement("select c.id_cliente, c.descripcion, c.cuit, tc.id_tipo_cliente, tc.descripcion as descrip_tipo from clientes c join tipo_cliente tc on c.id_tipo_cliente = tc.id_tipo_cliente where c.id_cliente = ?")).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.getString("id_cliente")).thenReturn("10");
            when(rs.getString("descripcion")).thenReturn("Mi Descripcion");
            when(rs.getInt("id_tipo_cliente")).thenReturn(1);
            when(rs.getString("descrip_tipo")).thenReturn("Tipo Cliente");
            when(rs.next()).thenReturn(true);
            Cliente c = model.getById("10");
            assertTrue(c.getDescripcion().equals("Mi Descripcion"));
            assertEquals("10",c.getId());
        } catch(Exception e) {
            fail();
        }
    }

    public void testGetByIdNull() {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);
            Cliente c = model.getById("10");
            assertNull(c);

        } catch(Exception e){
            fail();
        }

    }


    public void testGetByIdError() {
        try {
            when(conn.prepareStatement(anyString())).thenThrow(new Exception());
            Cliente c = model.getById("10");
            fail();
        } catch(Exception e){
            assertTrue(true);
        }

    }
}
