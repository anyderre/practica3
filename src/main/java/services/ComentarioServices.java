package services;

import com.modelo.Articulo;
import com.modelo.Comentario;
import com.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by john on 05/06/17.
 */
public class ComentarioServices {


    public List<Comentario> listarComentarios(long articulo) {

        List<Comentario> comentarios = new ArrayList<>();
        Connection con = DataBaseServices.getInstancia().getConexion();
        try {
            String query = "select * from comentario where articulo=?;";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            UsuarioServices usuarioServices = new UsuarioServices();
            ArticuloServices articuloServices=new ArticuloServices();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setLong(1,articulo);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comentario com = new Comentario();
                com.setId( rs.getLong("ID"));
                com.setComentario(rs.getString("comentario"));
                com.setArticulo(articuloServices.getArticulo(rs.getLong("articulo")));
                com.setAutor(usuarioServices.getUsuario(rs.getString("autor")));//Esperando la funcion de Pierre
                comentarios.add(com);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return comentarios;
    }


    public Comentario getComentario(Long comentario){
        UsuarioServices usuarioServices=new UsuarioServices();
        ArticuloServices articuloServices=new ArticuloServices();

        Comentario comentario1= new Comentario();
        String query = "select * from comentario where id=?;";

        Connection connection = DataBaseServices.getInstancia().getConexion();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,comentario);
            ResultSet resultSet = preparedStatement.executeQuery();
            comentario1.setId(1);
            comentario1.setId( resultSet.getLong("ID"));
            comentario1.setComentario(resultSet.getString("comentario"));
            comentario1.setArticulo(articuloServices.getArticulo(resultSet.getLong("id")));
            comentario1.setAutor(usuarioServices.getUsuario(resultSet.getString("username")));//Esperando la funcion de Pierre



        } catch (SQLException ex) {
            Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return comentario1;
    }

    public boolean crearComentario(Comentario comentario){

        //UsuarioServices usuarioServices=new UsuarioServices();
        boolean ok = false;
        Connection connection= null;
        String query = "insert into comentario(comentario,articulo,autor)values(?,?,?)";
        try {
            connection = DataBaseServices.getInstancia().getConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,comentario.getComentario());
            preparedStatement.setLong(2,comentario.getArticulo().getId());
            preparedStatement.setString(3,comentario.getAutor().getUsername());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }


    public boolean actualizarComentario(Comentario comentario){
        boolean ok = false;
        Connection connection= null;
        String query = "update comentario set id=?,comentario=?, articulo=?, autor=? WHERE id=?);";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,comentario.getId());
            preparedStatement.setString(2,comentario.getComentario());
            preparedStatement.setLong(3,comentario.getArticulo().getId());
            preparedStatement.setString(4,comentario.getAutor().getUsername());

            //resolving where
            preparedStatement.setLong(5,comentario.getId());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }


    public boolean borrarComentario(long articulo){
        boolean ok = false;

        Connection connection = null;
        String query = "delete from comentario where articulo=?;";
        connection = DataBaseServices.getInstancia().getConexion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //resolving where
            preparedStatement.setLong(1,articulo);

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComentarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return  ok;

    }

}
