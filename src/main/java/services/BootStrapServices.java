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

        String sql2= "CREATE TABLE  IF NOT EXISTS ARTICULO\n"+
                "(\n"+
                        "ID bigint PRIMARY KEY NOT NULL,\n"+
                        "TITULO varchar(30) NOT NULL,\n"+
                        "CUERPO varchar(30),\n"+
                        "AUTOR varchar(40) NOT NULL,\n"+
                        "FECHA date,\n"+
                        "CONSTRAINT ARTICULO_USUARIO_USERNAME_FK FOREIGN KEY (AUTOR) REFERENCES USUARIO (USERNAME)\n"+
                ");";

        String sql3="CREATE TABLE IF NOT EXISTS COMENTARIO\n" +
                "(\n" +
                "    ID bigint PRIMARY KEY NOT NULL,\n" +
                "    COMENTARIO clob,\n" +
                "    ARTICULO bigint NOT NULL,\n" +
                "    AUTOR varchar(40) NOT NULL,\n" +
                "    CONSTRAINT COMENTARIO_ARTICULO_ID_FK FOREIGN KEY (ARTICULO) REFERENCES ARTICULO (ID),\n" +
                "    CONSTRAINT COMENTARIO_USUARIO_USERNAME_FK FOREIGN KEY (AUTOR) REFERENCES USUARIO (USERNAME)\n" +
                ")";

        String sql4 ="CREATE TABLE IF NOT EXISTS ETIQUETA\n" +
                "(\n" +
                "    ID bigint DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_8DB18914_03E1_4A7C_A2D5_C879283305B8) AUTO_INCREMENT NOT NULL,\n" +
                "    ETIQUETA clob,\n" +
                "    ARTICULO bigint NOT NULL,\n" +
                "    CONSTRAINT ETIQUETA_ARTICULO_ID_FK FOREIGN KEY (ARTICULO) REFERENCES ARTICULO (ID)\n" +
                ");\n" +
                "CREATE UNIQUE INDEX ETIQUETA_ID_UINDEX ON ETIQUETA (ID)";

        String sql5 ="CREATE TABLE IF NOT EXISTS USUARIO\n" +
                "(\n" +
                "    USERNAME varchar(40) PRIMARY KEY NOT NULL,\n" +
                "    NOMBRE varchar(40) NOT NULL,\n" +
                "    PASSWORD varchar(30) NOT NULL,\n" +
                "    ADMINISTRADOR boolean NOT NULL,\n" +
                "    AUTOR boolean NOT NULL\n" +
                ")";
        Connection con = DataBaseServices.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql5);
        statement.execute(sql2);
        statement.execute(sql4);
        statement.execute(sql3);
        statement.close();
        con.close();
    }

}