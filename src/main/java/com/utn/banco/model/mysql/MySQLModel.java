package com.utn.banco.model.mysql;

import java.sql.Connection;

/**
 * Created by pablis on 17/04/17.
 */
public class MySQLModel {

    Connection conn;

    public MySQLModel(Connection conn) {
        this.conn = conn;
    }

}
