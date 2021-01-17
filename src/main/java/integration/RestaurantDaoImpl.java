package integration;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.*;


public class RestaurantDaoImpl implements RestaurantDao {

	final static JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

	private static final String CONNECTION_STRING = "mongodb://127.0.0.1";

	@Override
	public MongoCollection<Document> RestaurantDaoCollection() {
		MongoClient mongo = MongoClients.create(CONNECTION_STRING);
		MongoDatabase db = mongo.getDatabase("lab3");

		return db.getCollection("restaurants");
	}

	@Override
	public Document createRestaurant(String id, String name, int stars, String[] categories) {

		Document doc = new Document("_id", id)
				.append("name", name)
				.append("stars", stars)
				.append("categories", Arrays.asList(categories));

		return doc;
	}

	@Override
	public void insertRestaurant(Document doc, MongoCollection<Document> collection) {

		collection.insertOne(doc);

	}

	@Override
	public void deleteRestaurant(String id, MongoCollection<Document> collection) {
		collection.deleteOne(eq("_id", new ObjectId(id)));
	}

	@Override
	public void printCollection(MongoCollection<Document> collection) {

		MongoCursor<Document> cursor = collection.find().iterator();

		try (cursor) {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson(prettyPrint));
			}
		}
	}

	/**
	 * Prints a list of documents based on filter parameters
	 *
	 * @param collection to iterate
	 * @param fieldName  field to search
	 * @param value      to search for
	 * @param include    field to print
	 */
	@Override
	public void findStringPrintName(MongoCollection<Document> collection, String fieldName, String value, String include) {

		List<Document> documents =
				collection.find(eq(fieldName, value))
						.projection(fields(excludeId(), include(include)))
						.into(new ArrayList<>());

		for (Document restaurant : documents) {
			System.out.println(restaurant.toJson(prettyPrint));
		}
	}

	@Override
	public void updateDocumentInc(MongoCollection<Document> collection, String findField, String findValue, String incField, int incBy) {

		Bson filter = eq(findField, findValue);
		Bson update = inc(incField, incBy);
		Bson updates = combine(update);

		collection.findOneAndUpdate(filter, updates);

	}

	@Override
	public void updateDocumentName(MongoCollection<Document> collection, String findField, String findValue, String updateField, String updateValue) {

		Bson filter = eq(findField, findValue);
		Bson update = set(updateField, updateValue);
		Bson updates = combine(update);

		collection.updateOne(filter, updates);

	}

	@Override
	public void findByGteStars(MongoCollection<Document> collection, int stars) {

		List<Document> documents =
				collection.find(gte("stars", stars))
						.projection(fields(include("name", "stars")))
						.into(new ArrayList<>());

		for (Document restaurant : documents) {
			System.out.println(restaurant.toJson(prettyPrint));
		}

	}
}
