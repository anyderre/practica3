package services;

import com.modelo.Articulo;
import com.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by john on 05/06/17.
 */
public class ArticuloServices {

    public List<Articulo> listarArticulos(){
        UsuarioServices usuarioServices=new UsuarioServices();
        List<Articulo> articulos= new ArrayList<>();
        Connection connection = null;
        String query = "select * from articulo;";

        try {

            connection= DataBaseServices.getInstancia().getConexion();

            PreparedStatement preparedStatement =connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet!=null){

                Articulo articulo=new Articulo();
                articulo.setId(resultSet.getLong("id"));
                articulo.setAutor(usuarioServices.getUsuario(resultSet.getString("username")));
                articulo.setCuerpo(resultSet.getString("cuerpo"));
                articulo.setTitulo(resultSet.getString("titulo"));
                articulo.setFecha(resultSet.getDate("fecha"));

                articulos.add(articulo);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulos;
    }


    public Articulo getArticulo(String articulo){
        UsuarioServices usuarioServices=new UsuarioServices();
        Articulo articulo1= null;
        String query = "select * from articulo where id=?;";

        Connection connection = DataBaseServices.getInstancia().getConexion();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,articulo);
            ResultSet resultSet = preparedStatement.executeQuery();
            articulo1.setId(resultSet.getLong("id"));
            articulo1.setAutor(usuarioServices.getUsuario(resultSet.getString("username")));
            articulo1.setCuerpo(resultSet.getString("cuerpo"));
            articulo1.setTitulo(resultSet.getString("titulo"));
            articulo1.setFecha(resultSet.getDate("fecha"));


        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return articulo1;
    }

    public boolean crearArticulo(Articulo articulo){

        UsuarioServices usuarioServices=new UsuarioServices();
        boolean ok = false;
        Connection connection= null;
        String query = "insert into articulo(titulo,cuerpo,autor,fecha)values(?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,articulo.getTitulo());
            preparedStatement.setString(2,articulo.getCuerpo());
            preparedStatement.setString(3,articulo.getAutor().getUsername());
            preparedStatement.setDate(4,(Date) articulo.getFecha());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }

    public boolean actualizarArticulos(Articulo articulo){
        boolean ok = false;
        Connection connection= null;
        String query = "update articulo set id=?,titulo=?, cuerpo=?, autor=?, fecha=? WHERE id=?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,articulo.getId());
            preparedStatement.setString(2,articulo.getTitulo());
            preparedStatement.setString(3,articulo.getCuerpo());
            preparedStatement.setString(4,articulo.getAutor().getUsername());
            preparedStatement.setDate(5,(Date) articulo.getFecha());
            //resolving where
            preparedStatement.setLong(6,articulo.getId());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }


    public boolean borrarArticulo(Articulo articulo){
        boolean ok = false;

        Connection connection = null;
        String query = "delete from articulo where username=?;";
        connection = DataBaseServices.getInstancia().getConexion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //resolving where
            preparedStatement.setLong(1,articulo.getId());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return  ok;

    }

}
