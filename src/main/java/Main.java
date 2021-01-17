import com.mongodb.client.MongoCollection;
import integration.RestaurantDao;
import integration.RestaurantDaoImpl;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Projections.excludeId;


public class Main {

	public static void main(String[] args) {

//		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
//		mongoLogger.setLevel(Level.SEVERE);

		RestaurantDao restaurantDao = new RestaurantDaoImpl();

		MongoCollection<Document> restaurantCollection = restaurantDao.RestaurantDaoCollection();

		restaurantCollection.drop();

		Document rest = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bca",
				"Sun Bakery Trattoria",
				4,
				new String[]{"Pizza", "Pasta", "Italian", "Coffee", "Sandwiches"});

		Document restOne = restaurantDao.createRestaurant(
				"5c3a0af0df83137e38e4b0db",
				"Blue Bagels Grill",
				3,
				new String[]{"Bagels", "Cookies", "Sandwiches"});

		Document restTwo = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bcc",
				"Hot Bakery Cafe",
				4,
				new String[]{"Bakery", "Cafe", "Coffee", "Dessert"});

		Document restThree = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bcd",
				"XYZ Coffee Bar",
				5,
				new String[]{"Cafe", "Coffee", "Bakery", "Chocolates"});

		Document restFour = restaurantDao.createRestaurant(
				"5c39f9b5df831369c19b6bce",
				"456 Cookies Shop",
				4,
				new String[]{"Bakery", "Cookies", "Cake", "Coffee"});


		List<Document> restaurantList = new ArrayList<>();

		restaurantList.add(rest);
		restaurantList.add(restOne);
		restaurantList.add(restTwo);
		restaurantList.add(restThree);
		restaurantList.add(restFour);

		restaurantCollection.insertMany(restaurantList);

		restaurantDao.findStringPrintName(restaurantCollection,"categories", "Cafe", "name");

		restaurantDao.updateDocumentInc(restaurantCollection,
				"name","XYZ Coffee Bar",
				"stars",1 );

		restaurantDao.printCollection(restaurantCollection);

		restaurantDao.updateDocumentName(restaurantCollection,
				"name","456 Cookies Shop",
				"name","123 Cookies Heaven");

/*
		Skriv en metod som aggregerar en lista med alla restauranger som har
		4 eller fler “stars” och skriver ut endast “name” och “stars”

		r
*/


		restaurantDao.findByGteStars(restaurantCollection, 4);

	}
}
