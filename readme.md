

### Kurs: Utveckling mot databas och databasadministration

Innehåller min lösning för CRUD-operationer i MongoDB löst med Java.

* Main.java
* integration
  * RestaurantDao.java
  * RestaurantDaoImpl.java

Sammanfattad beskrivning av uppgift samt utdrag ur min kod nedan.

------

Skriv en C# klass (ni bestämmer själva vad den ska hetta). 
Ni kan använda lämpliga MongoDB paket ifrån NuGet
Ert program ska: 

Ansluta till mongodb (skapa en klient) 

Skapa en ny databas via klienten till exempel “lab3” 

Skapa en kollektion vid namn “restaurants” med följande innehåll (se "restaurants.json").

```java
private static final String CONNECTION_STRING = "mongodb://127.0.0.1";

public MongoCollection<Document> RestaurantDaoCollection() {
    MongoClient mongo = MongoClients.create(CONNECTION_STRING);
    MongoDatabase db = mongo.getDatabase("lab3");

    return db.getCollection("restaurants");
}
```

```java
RestaurantDao restaurantDao = new RestaurantDaoImpl();
MongoCollection<Document> restaurantCollection = restaurantDao.RestaurantDaoCollection();
```



Skriv en metod som skriver ut (Console.Writeline) alla dokument i samlingen. 

```java
public void printCollection(MongoCollection<Document> collection) {

    MongoCursor<Document> cursor = collection.find().iterator();

    try (cursor) {
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson(prettyPrint));
        }
    }
}
```

```java
restaurantDao.printCollection(restaurantCollection);
```



Skriv en metod som skriver ut namnet på alla dokument som har kategorin “Cafe” 
OBS! Exkludera id så att bara namn visas 

```java
public void findStringPrintName(MongoCollection<Document> collection, String fieldName, String value, String include) {

    List<Document> documents =
        collection.find(eq(fieldName, value))
        .projection(fields(excludeId(), include(include)))
        .into(new ArrayList<>());

    for (Document restaurant : documents) {
        System.out.println(restaurant.toJson(prettyPrint));
    }
}
```

```java
restaurantDao.findStringPrintName(restaurantCollection,"categories", "Cafe", "name");
```



Skriv en metod som uppdaterar genom increment “stars” för den restaurang som har “name” “XYZ Coffee Bar” så att nya värdet på stars blir 6. 
OBS! Ni ska använda increment . 

OBS! Skriv ut alla restauranger igen, så att jag kan se att “stars” blivit 6, för denna restaurang när jag kör ert program. 

```java
public void updateDocumentInc(MongoCollection<Document> collection, String findField, String findValue, String incField, int incBy) {

    Bson filter = eq(findField, findValue);
    Bson update = inc(incField, incBy);
    Bson updates = combine(update);

    collection.findOneAndUpdate(filter, updates);

}
```

```java
restaurantDao.updateDocumentInc(restaurantCollection,
                                "name","XYZ Coffee Bar",
                                "stars", 1 );
```

```java
restaurantDao.printCollection(restaurantCollection);
```



Skriv en metod som uppdaterar “name” för "456 Cookies Shop" till “123 Cookies Heaven” 
OBS! Skriv ut alla restauranger igen, så att jag kan se att namnet ändrats för denna restaurang när jag kör ert program. 

```java
public void updateDocumentName(MongoCollection<Document> collection, String findField, String findValue, String updateField, String updateValue) {

    Bson filter = eq(findField, findValue);
    Bson update = set(updateField, updateValue);
    Bson updates = combine(update);

    collection.updateOne(filter, updates);

}
```

```java
restaurantDao.updateDocumentName(restaurantCollection,
      "name","456 Cookies Shop",
      "name","123 Cookies Heaven");
```

```java
restaurantDao.printCollection(restaurantCollection);
```



Skriv en metod som aggregerar en lista med alla restauranger som har 4 eller fler “stars” och skriver ut endast “name” och “stars” 

```java
public void findByGteStars(MongoCollection<Document> collection, int stars) {

    List<Document> documents =
        collection.find(gte("stars", stars))
        .projection(fields(include("name", "stars")))
        .into(new ArrayList<>());

    for (Document restaurant : documents) {
        System.out.println(restaurant.toJson(prettyPrint));
    }

}
```

```java
restaurantDao.findByGteStars(restaurantCollection, 4);
```