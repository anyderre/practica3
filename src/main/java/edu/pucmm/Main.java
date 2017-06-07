package edu.pucmm;

import com.modelo.*;
import com.sun.org.apache.regexp.internal.RE;
import freemarker.template.Configuration;
import services.ArticuloServices;
import services.ComentarioServices;
import services.EtiquetaServices;
import services.UsuarioServices;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static java.lang.Class.forName;
import static spark.Spark.*;
import static spark.route.HttpMethod.*;

/**
 * Created by john on 03/06/17.
 */
public class Main {

    public static void main(String[] args) {

        //Seteando el puerto en Heroku
        port(getHerokuAssignedPort());


        //indicando los recursos publicos.
        staticFiles.location("/publico");


        //Linea para agregar la pantalla de debug. En productivo se debe quitar.
        //enableDebugScreen();

        //Indicando la carpeta por defecto que estaremos usando.
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Session session = request.session(true);
            Usuario usuario = session.attribute("Usuario");
            if(usuario==null){
                response.redirect("/login");
            }
            attributes.put("titulo", "Welcome");
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);


        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        post("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            //Usuario currentUserLogin = new Usuario();
            Session session = request.session(true);
            Usuario usuario = session.attribute("Usuario");
            if(usuario==null){
                response.redirect("/login");
            }
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);

        get("/registrar", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("titulo", "Registrar");
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);


        post("/registrar", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            //Usuario currentUserLogin = new Usuario();
            Usuario usuario=null;
            ConnectionDB connectionDB = new ConnectionDB();
            Connection connection= connectionDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * FROM USUARIO");
            if(resultSet==null){
               usuario = new Usuario(request.queryParams("username"), request.queryParams("password"), request.queryParams("nombre"),true, true);
            }else{
                usuario = new Usuario(request.queryParams("username"), request.queryParams("password"), request.queryParams("nombre"),false, true);
            }
            request.session(true).attribute("usuario", usuario);

            response.redirect("/");
            return "";
        });


//<--------------------------------------------------Etiquetas crud------------------------------------------------------------------------------------------------------------------->
      delete("/etiqueta/:articulo/borrar/:id",(request, response)->{
          long id=0,articulo=0;
          try{
               id = Long.parseLong(request.params("id"));
               articulo = Long.parseLong(request.params("articulo"));
          }catch (Exception ex){
              ex.printStackTrace();
          }
          EtiquetaServices etiquetaServices = new EtiquetaServices();
          etiquetaServices.borrarEtiqueta(id);
          response.redirect("/ver/articulo/"+articulo);
          return "";
    });


//<--------------------------------------------------Comentario crud------------------------------------------------------------------------------------------------------------------->

        post("/agregar/comentario/:articulo", (request, response)->{
            long articulo=0;
            try{
                articulo = Long.parseLong(request.params("articulo"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            Session session = request.session(true);
            Usuario usuario = session.attribute("usuario");

            ArticuloServices  articuloServices = new ArticuloServices();

            String comentario = request.queryParams("comentario");
            ComentarioServices comentarioServices = new ComentarioServices();
            comentarioServices.crearComentario(new Comentario(comentario,usuario,articuloServices.getArticulo(articulo)));

            response.redirect("/ver/articulo/"+articulo);

          return "";
        });



//<--------------------------------------------------Articulo Crud------------------------------------------------------------------------------------------------------------------->

        post("/agregar/articulo",(request, response)->{
            String cuerpo=request.queryParams("cuerpo");
            String titulo = request.queryParams("titulo");
            String []etiquetas=request.queryParams("etiquetas").split(",");
            //String autor = request.queryParams("username");

            Session session = request.session(true);
            Usuario usuario = session.attribute("usuario");

            ArticuloServices  articuloServices = new ArticuloServices();
            long id=articuloServices.listarArticulos().size();

            Articulo articulo = new Articulo();
            articulo.setCuerpo(cuerpo);
            articulo.setAutor(usuario);
            articulo.setFecha(new Date());
            articulo.setId(id+1);

            articuloServices.crearArticulo(articulo);
            if(etiquetas!=null){
                EtiquetaServices etiquetaServices = null;

                for(String et: etiquetas){
                    etiquetaServices.crearEtiqueta(new Etiqueta(et,articulo));
                }
            }else{
                System.out.println("Error al entrar las etiquetas");
            }

            response.redirect("/");
            return "";
        });





    }  /**
     * Metodo para setear el puerto en Heroku
     * @return
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

