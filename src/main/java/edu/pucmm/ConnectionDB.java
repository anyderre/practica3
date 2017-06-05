package edu.pucmm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by anyderre on 04/06/17.
 */
public class ConnectionDB {
    private final String url="jdbc:h2:./practica3db";
    private final String username="sa";
    private final String password="";

    public ConnectionDB(){
        RegistrarDriver();
    }

    public Connection getConnection(){
        Connection connection= null;
        try {
            connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void RegistrarDriver (){
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
