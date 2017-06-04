package edu.pucmm;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
            attributes.put("titulo", "Formulario en FreeMarker");
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);
    }


    /**
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
