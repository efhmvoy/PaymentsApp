package ua.training.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DBCPDataSource {

    private final BasicDataSource ds;

    private static DBCPDataSource instance;

    private ResourceBundle rb;

    public static synchronized DBCPDataSource getInstance() {
        if (instance == null) {
            instance = new DBCPDataSource();
        }
        return instance;
    }

    private DBCPDataSource() {
        rb = ResourceBundle.getBundle("application");
        ds = new BasicDataSource();
        ds.setUrl(rb.getString("connection.url"));
        ds.setUsername(rb.getString("connection.username"));
        ds.setPassword(rb.getString("connection.password"));
        ds.setMinIdle(Integer.parseInt(rb.getString("connection.minIdle")));
        ds.setMaxIdle(Integer.parseInt(rb.getString("connection.maxIdle")));
        ds.setMaxOpenPreparedStatements(Integer.parseInt(rb.getString("connection.maxOpenPreparedStatements")));
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(rb.getString("connection.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = ds.getConnection();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }

    public static void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
