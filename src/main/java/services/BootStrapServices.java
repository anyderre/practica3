package services;

/**
 * Created by john on 05/06/17.
 */
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BootStrapServices {

    /**
     *
     * @throws SQLException
     */
    public static void startDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     *
     * @throws SQLException
     */
    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }


    /**
     * Metodo para recrear las tablas necesarios
     * @throws SQLException
     */
    public static void crearTablas() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS ARTICULO\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY NOT NULL,\n" +
                "  TITULO VARCHAR(100) NOT NULL,\n" +
                "  CUERPO VARCHAR(100) NOT NULL,\n" +
                "  AUTOR VARCHAR(25) NOT NULL,\n" +
                "  FECHA VARCHAR(50) NOT NULL\n" +
                ");";

        String sql2 = "CREATE TABLE IF NOT EXISTS USUARIO\n" +
                "(\n" +
                "  USERNAME VARCHAR(100) NOT NULL,\n" +
                "  NOMBRE VARCHAR(100) NOT NULL,\n" +
                "  PASSWORD VARCHAR(25) NOT NULL,\n" +
                "  ADMINISTRADOR  NOT NULL\n" +
                ");";

        String sql3 = "CREATE TABLE IF NOT EXISTS COMENTARIO\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY NOT NULL,\n" +
                "  COMENTARIO VARCHAR(100) NOT NULL,\n" +
                "  ARTICULO VARCHAR(100) NOT NULL,\n" +
                "  AUTOR VARCHAR(25) NOT NULL,\n" +
                ");";

        String sql4 = "CREATE TABLE IF NOT EXISTS ETIQUETA\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY NOT NULL,\n" +
                "  TITULO VARCHAR(100) NOT NULL,\n" +
                "  CUERPO VARCHAR(100) NOT NULL,\n" +
                "  AUTOR VARCHAR(25) NOT NULL,\n" +
                "  FECHA VARCHAR(50) NOT NULL\n" +
                ");";

        Connection con = DataBaseServices.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();
    }

}