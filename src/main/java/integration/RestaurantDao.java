package integration;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface RestaurantDao {
	MongoCollection<Document> RestaurantCollection();

	Document createRestaurant(String id, String name, int stars, String[] categories);

	void insertRestaurant(Document doc, MongoCollection<Document> collection);

	void deleteRestaurant(String id, MongoCollection<Document> collection);

	void printCollection(MongoCollection<Document> collection);

	void findStringPrintName(MongoCollection<Document> collection, String fieldName, String value, String include);

	void updateDocumentIncrementInt(MongoCollection<Document> collection, String findField, String findValue, String incField, int incByThis);

	void updateDocumentString(MongoCollection<Document> collection, String findField, String findValue, String updateField, String updateValue);

	void findByGteStars(MongoCollection<Document> collection, int stars);
}
