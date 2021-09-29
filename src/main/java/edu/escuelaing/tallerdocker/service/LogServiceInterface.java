package edu.escuelaing.tallerdocker.service;

import java.util.List;
import com.mongodb.DBObject;

public interface LogServiceInterface {
    List<DBObject> saveData(String cadena);
}
