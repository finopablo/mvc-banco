package com.utn.jdbc;

import java.sql.*;

/**
 * Created by pablis on 18/04/17.
 */
public class Main {



    public static void main(String args[]) {

        try {
            //Primero se obtiene el driver de conexion
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Se genera la conexion
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banco?user=root&password=a");
            //Como hacer una query sin parametros no es conveniente
            Statement st = conn.createStatement();
            String descripcion = "persona comercial";
            boolean result = st.execute("insert into tipo_cliente(descripcion) values ('"+descripcion+"')");

            st = conn.createStatement();
            result = st.execute("delete from tipo_cliente where descripcion = '"+descripcion+"'");

            //con parametros
            descripcion = "Persona comercial mejor insertada";
            PreparedStatement ps = conn.prepareStatement("insert into tipo_cliente(descripcion) values (?)");
            ps.setString(1, descripcion );
            result = ps.execute();

            ps = conn.prepareStatement("delete from tipo_cliente where descripcion = ?");
            ps.setString(1, descripcion);
            int rowsAffected = ps.executeUpdate();

            /*Retornar informacion*/
            int idCliente = 2;
            ps = conn.prepareStatement("select * from cuentas where id_cliente = ?");
            ps.setInt(1,idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id_cuenta") + " - " + rs.getString("numero_cuenta"));
            }

            /**TRANSACCIONES**/
            try {
                conn.setAutoCommit(false);
                String sql = "insert into cuentas(id_cliente,numero_cuenta) values (?,?)";
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, 1);
                ps.setString(2, "Hiper cuenta");
                ps.executeUpdate();

                rs = ps.getGeneratedKeys();
                rs.next();

                int idCuenta = rs.getInt(1);


                sql = "insert into movimientos(id_tipo_movimiento,monto,id_cuenta, fecha) values (?,?,?,?)";
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int idTipoMovimiento = 1;
                ps.setInt(1, idTipoMovimiento);
                ps.setDouble(2, 999.66);
                ps.setInt(3, idCuenta);
                ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
                ps.executeUpdate();

                conn.commit();
            } catch(Exception e){
                conn.rollback();
            }



        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
