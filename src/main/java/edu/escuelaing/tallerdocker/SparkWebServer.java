package edu.escuelaing.tallerdocker;

import static spark.Spark.get;
import static spark.Spark.port;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DBCursor;

import spark.Request;
import spark.Response;


import org.json.JSONObject;


public class SparkWebServer {

    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection collection;
    public static void main(String... args) {
        port(getPort());

        mongoSetup();

        get("hello", (req,res) -> "Hello Docker!");
        get("/logservice", (req,res) -> logServiceResponse(req,res));
    }

    private static void mongoSetup(){
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            database= mongoClient.getDB("cadenas");
            collection= database.getCollection("cadenas");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static List<DBObject> saveData(String cadena){
        List<DBObject> response = new ArrayList<>();
        collection.insert(new BasicDBObject("data", cadena).append("date", new Date()));
        DBCursor cursor = collection.find().limit(10);
        while(cursor.hasNext()){
            response.add(cursor.next());
        }
        return response;
    }

    private static List<DBObject> logServiceResponse(Request req, Response res){
        res.type("application/json");
        String cadena= req.queryParams("cadena");
        List<DBObject> infoResponse= saveData(cadena);
        return infoResponse;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}