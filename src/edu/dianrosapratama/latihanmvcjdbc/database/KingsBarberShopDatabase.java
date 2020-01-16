/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dianrosapratama.latihanmvcjdbc.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import edu.dianrosapratama.latihanmvcjdbc.impl.PelangganDaoImpl;
import edu.dianrosapratama.latihanmvcjdbc.service.PelangganDAO;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author user
 */
public class KingsBarberShopDatabase {

    private static Connection conn;
    private static PelangganDAO pelangganDAO;

    public static Connection getConnection() throws SQLException {

        if (conn == null) {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/db_kingsbarbershop");
            ds.setUser("root");
            ds.setPassword("");
            conn = ds.getConnection();
        }

        return conn;
    }

    public static PelangganDAO getPelangganDAO() throws SQLException {
        if (pelangganDAO == null) {
            pelangganDAO = new PelangganDaoImpl(getConnection());
        }
        return pelangganDAO;

    }
}
