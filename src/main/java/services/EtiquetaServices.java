package services;

import com.modelo.Articulo;
import com.modelo.Etiqueta;
import com.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by john on 05/06/17.
 */
public class EtiquetaServices {


    public List<Etiqueta> getAlletiquetas(){
        List<Etiqueta> etiquetas = new ArrayList<>();
        Connection connection = null;
        String query = "select * from etiqueta;";

        try {

            connection= DataBaseServices.getInstancia().getConexion();
            ArticuloServices articuloServices = new ArticuloServices();

            PreparedStatement preparedStatement =connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet!=null){
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setEtiqueta(resultSet.getString("etiqueta"));
                etiqueta.setId(resultSet.getLong("id"));
                etiqueta.setArticulo(articuloServices.getArticulo(resultSet.getLong("articulo")));
                //adding etiqueta to list
                etiquetas.add(etiqueta);
            }
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return etiquetas;
    }
    public Etiqueta getEtiqueta(Long id){
        Etiqueta etiqueta = null;
        String query = "select * from etiqueta where id=?;";
        ArticuloServices articuloServices = new ArticuloServices();
        Connection connection = DataBaseServices.getInstancia().getConexion();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet!=null) {
                etiqueta.setId(resultSet.getLong("id"));
                etiqueta.setArticulo(articuloServices.getArticulo(resultSet.getLong("articulo")));
                etiqueta.setId(resultSet.getLong("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return etiqueta;
    }

    public boolean crearEtiqueta(Etiqueta etiqueta){
        boolean ok = false;
        Connection connection= null;
        String query = "insert into etiqueta (etiqueta,articulo)values(?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,etiqueta.getEtiqueta());
            preparedStatement.setLong(2,etiqueta.getArticulo().getId());
           ;
            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }
    public boolean actualizarUsuario(Usuario usuario){
        boolean ok = false;
        Connection connection= null;
        String query = "update usuario set username=?,nombre=?, password=?, administrador=?, autor=? WHERE username=?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,usuario.getUsername());
            preparedStatement.setString(2,usuario.getNombre());
            preparedStatement.setString(3,usuario.getPassword());
            preparedStatement.setBoolean(4,usuario.getAdministrador());
            preparedStatement.setBoolean(5,usuario.getAutor());
            //resolving where
            preparedStatement.setString(6,usuario.getUsername());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }
    public boolean borrarUsuario(Usuario usuario){
        boolean ok = false;

        Connection connection = null;
        String query = "delete from estudiante where=username=?;";
        connection = DataBaseServices.getInstancia().getConexion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //resolving where
            preparedStatement.setString(1,usuario.getUsername());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return  ok;

    }

}
