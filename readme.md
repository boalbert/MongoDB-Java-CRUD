

### Kurs: Utveckling mot databas och databasadministration

Innehåller min lösning för CRUD-operationer i MongoDB med Java.

* Main.java
* integration
  * RestaurantDao.java
  * RestaurantDaoImpl.java

Beskrivning av uppgift nedan.

------

Skriv en C# klass (ni bestämmer själva vad den ska hetta). 
Ni kan använda lämpliga MongoDB paket ifrån NuGet
Ert program ska: 
Ansluta till mongodb (skapa en klient) 

Skapa en ny databas via klienten till exempel “lab3” 

Skapa en kollektion vid namn “restaurants” med följande innehåll: OBS! Ni ska skapa detta i ert program med C#

```
{ 
  "_id" : ObjectId("5c39f9b5df831369c19b6bca"),
  "name" : "Sun Bakery Trattoria", 
  "stars" : 4, 
  "categories" : [ "Pizza", "Pasta", "Italian", "Coffee", "Sandwiches" ] 
},
{
  "_id": ObjectId("5c39f9b5df831369c19b6bcb"),
  "name" : "Blue Bagels Grill",
  "stars" : 3,
  "categories" : [ 
    "Bagels", "Cookies", "Sandwiches" 
  ] 
}, 
{ 
  "_id" : ObjectId("5c39f9b5df831369c19b6bcc"),
  "name" : "Hot Bakery Cafe",
  "stars" : 4,
  "categories" : [ 
    "Bakery",
    "Cafe",
    "Coffee",
    "Dessert" 
  ] 
}, 
{ 
  "_id" : ObjectId("5c39f9b5df831369c19b6bcd"),
  "name" : "XYZ Coffee Bar",
  "stars" : 5,
  "categories" : [ 
    "Coffee", "Cafe", "Bakery", "Chocolates" 
  ] 
},
{ 
  "_id" : ObjectId("5c39f9b5df831369c19b6bce"),
  "name" : "456 Cookies Shop",
  "stars" : 4,
  "categories" : [ 
    "Bakery", "Cookies", "Cake", "Coffee" 
  ] 
} 
```


Skriv en metod som skriver ut (Console.Writeline) alla dokument i samlingen. 

Skriv en metod som skriver ut namnet på alla dokument som har kategorin “Cafe” 
OBS! Exkludera id så att bara namn visas 

Skriv en metod som uppdaterar genom increment “stars” för den restaurang som har “name” “XYZ Coffee Bar” så att nya värdet på stars blir 6. 
OBS! Ni ska använda increment . 
OBS! Skriv ut alla restauranger igen, så att jag kan se att “stars” blivit 6, för denna restaurang när jag kör ert program. 

Skriv en metod som uppdaterar “name” för "456 Cookies Shop" till “123 Cookies Heaven” 
OBS! Skriv ut alla restauranger igen, så att jag kan se att namnet ändrats för denna restaurang när jag kör ert program. 

Skriv en metod som aggregerar en lista med alla restauranger som har 4 eller fler “stars” och skriver ut endast “name” och “stars” 
OBS! Metoderna ska skriva ut via Console.Writeline resultatet, det vill säga, när jag kör ert program ska jag se resultatet från utskrifterna. 

Exempel på utskrifer från ert program Alla dokument:

```
{ 
  "_id" : 
  { 
    "$oid" : "5c3a0af0df83137e38e4b0da" 
  }, 
  "name" : "Sun Bakery Trattoria", 
  "stars" : 4,
  "categories" : [ 
    "Pizza", "Pasta", "Italian", "Coffee", "Sandwiches" 
  ] 
}, 
{ 
  "_id" : { 
    "$oid" : "5c3a0af0df83137e38e4b0db" 
  },
  "name" : "Blue Bagels Grill",
  "stars" : 3,
  "categories" : [ 
    "Bagels", "Cookies", "Sandwiches" 
    ] 
}, 
{ 
  "_id" : 
  { 
    "$oid" : "5c3a0af0df83137e38e4b0dc" 
  },
  "name" : "Hot Bakery Cafe",
  "stars" : 4,
  "categories" : [ 
    "Bakery", "Cafe", "Coffee", "Dessert" 
  ] 
},
{ 
  "_id" : {
    "$oid" : "5c3a0af0df83137e38e4b0dd" 
  },
  "name" : "XYZ Coffee Bar",
  "stars" : 5,
  "categories" : [ 
    "Coffee", "Cafe", "Bakery", "Chocolates" 
  ] 
},
{ 
  "_id" : { 
    "$oid" : "5c3a0af0df83137e38e4b0de" 
  },
  "name" : "456 Cookies Shop",
  "stars" : 4,
  "categories" : [ 
    "Bakery", "Cookies", "Cake", "Coffee" 
  ] 
} 
```

Alla namn på rest. med kategori Cafe: 

```
{  "name" : "Hot Bakery Cafe" } 
{ "name" : "XYZ Coffee Bar" } 
```

Och så vidare för varje VG-uppgift... Lycka till! 