package edu.escuelaing.tallerdocker;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.*;

import java.util.List;
import com.mongodb.DBObject;

import spark.Request;
import spark.Response;
import edu.escuelaing.tallerdocker.service.LogServiceInterface;
import edu.escuelaing.tallerdocker.service.LogService;


public class SparkWebServer {

    private static LogServiceInterface logService= new LogService();
    public static void main(String... args) {
        staticFiles.location("/public");
        port(getPort());
        
        options("/*",
        (request, response) -> {

            String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("hello", (req,res) -> "Hello Docker!");
        get("/logservice", (req,res) -> logServiceResponse(req,res));
    }

    /**
     * Servicio de log service, recibe un pedido y retorna informacion de la base de 
     * datos 
     * @param req
     * @param res
     * @return lista de objetos de la base de datos 
     */
    private static List<DBObject> logServiceResponse(Request req, Response res){
        res.type("application/json");
        String cadena= req.queryParams("cadena");
        List<DBObject> infoResponse= logService.saveData(cadena);
        return infoResponse;
    }

    /**
     * Define el puerto de spark
     * @return
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}