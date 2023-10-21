package me.CarsCupcake.SkyblockRemake.configs;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Getter
@SuppressWarnings("unused")
public class SQL {

    private Connection connection;

    public boolean isConnected() {
        return connection != null;

    }

    public void connect() throws SQLException {
        String host = "localhost";
        String port = "3306";
        String database = "skyblock";
        String username = "root";
        String password = "";
        if (!isConnected())
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

}
