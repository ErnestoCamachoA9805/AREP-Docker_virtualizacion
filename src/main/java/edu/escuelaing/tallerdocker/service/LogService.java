package edu.escuelaing.tallerdocker.service;

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

public class LogService implements LogServiceInterface{
    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection collection;

    public LogService(){
        mongoSetup();
    }
    /**
     * Realiza la coneccion a la base de datos 
     */
    private static void mongoSetup(){
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://ec2-34-227-27-85.compute-1.amazonaws.com:27017"));
            database= mongoClient.getDB("cadenas");
            collection= database.getCollection("cadenas");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * recibe una cadena y retorna las ultimas 10 cadenas de la base de datos 
     * @param cadena 
     * @return lista de objetos 
     */
    public List<DBObject> saveData(String cadena){
        List<DBObject> response = new ArrayList<>();
        collection.insert(new BasicDBObject("data", cadena).append("date", new Date()));
        DBCursor cursor = collection.find().limit(10);
        while(cursor.hasNext()){
            response.add(cursor.next());
        }
        return response;
    }
}
