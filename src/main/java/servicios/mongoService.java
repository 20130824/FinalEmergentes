package servicios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Isaac Perez on 11/7/2017.
 */
public class mongoService {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("final");
        MongoCollection<Document> articulos = database.getCollection("articulos");
        MongoCollection<Document> suplidores = database.getCollection("suplidores");
        MongoCollection<Document> ordenes = database.getCollection("ordenes");
        MongoCollection<Document> movimientos = database.getCollection("movimientos");

      //  long n_elements = collection.count();


    }


}
