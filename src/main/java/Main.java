import com.mongodb.client.MongoCollection;
import integration.RestaurantDao;
import integration.RestaurantDaoImpl;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {

		RestaurantDao restaurantDao = new RestaurantDaoImpl();

		MongoCollection<Document> restaurantCollection = restaurantDao.RestaurantCollection();

		restaurantCollection.drop();

		Document restOne = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bca",
				"Sun Bakery Trattoria",
				4,
				new String[]{"Pizza", "Pasta", "Italian", "Coffee", "Sandwiches"});

		Document restTwo = restaurantDao.createRestaurant(
				"5c3a0af0df83137e38e4b0db",
				"Blue Bagels Grill",
				3,
				new String[]{"Bagels", "Cookies", "Sandwiches"});

		Document restThree = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bcc",
				"Hot Bakery Cafe",
				4,
				new String[]{"Bakery", "Cafe", "Coffee", "Dessert"});

		Document restFour = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bcd",
				"XYZ Coffee Bar",
				5,
				new String[]{"Cafe", "Coffee", "Bakery", "Chocolates"});

		Document restFive = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bce",
				"456 Cookies Shop",
				4,
				new String[]{"Bakery", "Cookies", "Cake", "Coffee"});


		List<Document> restaurantList = new ArrayList<>();

		restaurantList.add(restOne);
		restaurantList.add(restTwo);
		restaurantList.add(restThree);
		restaurantList.add(restFour);
		restaurantList.add(restFive);

		restaurantCollection.insertMany(restaurantList);

		restaurantDao.findStringPrintName(restaurantCollection,"categories", "Cafe", "name");

		restaurantDao.updateDocumentIncrementInt(restaurantCollection,
				"name","XYZ Coffee Bar",
				"stars",1 );

		restaurantDao.printCollection(restaurantCollection);

		restaurantDao.updateDocumentString(restaurantCollection,
				"name","456 Cookies Shop",
				"name","123 Cookies Heaven");

		restaurantDao.findByGteStars(restaurantCollection, 4);

	}
}
