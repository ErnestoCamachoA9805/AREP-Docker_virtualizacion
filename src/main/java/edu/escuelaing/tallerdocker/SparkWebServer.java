package edu.escuelaing.tallerdocker;

import static spark.Spark.get;
import static spark.Spark.port;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import spark.Request;
import spark.Response;


import org.json.JSONObject;


public class SparkWebServer {

    private static MongoClient mongoClient;
    public static void main(String... args) {
        port(getPort());

        mongoClient();

        get("hello", (req,res) -> "Hello Docker!");
        get("/logservice", (req,res) -> logServiceResponse(req,res));
    }

    private static void mongoClient(){
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static boolean dataBaseSave(String cadena){
        DB database= mongoClient.getDB("cadenas");
        DBCollection collection= database.getCollection("cadenas");
        DBObject cadenaAGuardar= new BasicDBObject("cadena",cadena);
        collection.insert(cadenaAGuardar);
        return true;
    }
    private static JSONObject logServiceResponse(Request req, Response res){
        res.type("application/json");
        String cadena= req.queryParams("cadena");
        dataBaseSave(cadena);
        return null;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}