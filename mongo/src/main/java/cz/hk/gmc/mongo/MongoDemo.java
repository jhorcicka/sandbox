package cz.hk.gmc.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.UpdateResult;

public class MongoDemo {
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DATABASE = "test";
    private static final String COLLECTION = "people";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private final MongoClient client;
    private MongoDatabase db;

    public static void main(String args[]) {
        final MongoDemo mongo = new MongoDemo();
        mongo.connectToDB(DATABASE);
        //mongo.printAllDatabases();
        //mongo.testRecordUpdate();
        mongo.updateAllRecords();
    }

    public void updateAllRecords() {
        final MongoCollection collection = db.getCollection(COLLECTION);
        final FindIterable iterable = collection.find();
        iterable.noCursorTimeout(true);
        final MongoCursor cursor = iterable.iterator();

        while (cursor.hasNext()) {
            final Document doc = (Document) cursor.next();
            final String docId = (String) doc.get("id");
            final long matchedCount = update(docId, "ccc");

            if (matchedCount > 0) {
                System.err.println("MYTODO: successful update of record #" + docId);
            }
        }
    }

    public void testRecordUpdate() {
        final String id = "1";
        System.err.println("MYTODO: record1=" + get(id));
        update(id, "aaa-");
        System.err.println("MYTODO: record1=" + get(id));
    }

    public MongoDemo() {
        //final String textUri = "mongodb://" + USERNAME + ":" + PASSWORD + "@" + HOST + ":" + PORT;
        final String textUri = "mongodb://" + HOST + ":" + PORT;
        final MongoClientURI uri = new MongoClientURI(textUri);
        client = new MongoClient(uri);
    }

    public boolean connectToDB(String dbName) {
        db = client.getDatabase(dbName);
        return this.db != null;
    }

    public void printAllDatabases() {
        final MongoIterable<String> cursor = client.listDatabaseNames();
        final List<String> list = new ArrayList<>();

        for (final String name : cursor) {
            list.add(name);
        }

        System.out.println("All databases: " + list.toString());
    }

    public Document get(final String id) {
        final MongoCollection collection = db.getCollection(COLLECTION);
        final Document document = new Document();
        document.append("id", id);
        final MongoCursor cursor = collection.find(document).iterator();
        return (Document) cursor.next();
    }

    public long update(final String id, final String newNamePrefix) {
        final BasicDBObject searchQuery = new BasicDBObject("id", id);
        final BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("name", newNamePrefix + id);
        final BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        final MongoCollection collection = db.getCollection(COLLECTION);
        final UpdateResult updateResult = collection.updateOne(searchQuery, setQuery);
        return updateResult.getMatchedCount();
    }
}
