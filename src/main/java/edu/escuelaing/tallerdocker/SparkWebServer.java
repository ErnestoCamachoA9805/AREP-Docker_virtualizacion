package edu.escuelaing.tallerdocker;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.List;

import com.mongodb.DBObject;

import spark.Request;
import spark.Response;

import edu.escuelaing.tallerdocker.service.LogServiceInterface;
import edu.escuelaing.tallerdocker.service.LogService;


public class SparkWebServer {

    private static LogServiceInterface logService= new LogService();
    public static void main(String... args) {
        port(getPort());

        get("hello", (req,res) -> "Hello Docker!");
        get("/logservice", (req,res) -> logServiceResponse(req,res));
    }

    private static List<DBObject> logServiceResponse(Request req, Response res){
        res.type("application/json");
        String cadena= req.queryParams("cadena");
        List<DBObject> infoResponse= logService.saveData(cadena);
        return infoResponse;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}