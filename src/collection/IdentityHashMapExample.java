package collection;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapExample {
    
    public static void main(String[] args) {
        System.out.println("=== IdentityHashMap vs HashMap ===");
        
        // Création d'un IdentityHashMap
        IdentityHashMap<String, Integer> identityMap = new IdentityHashMap<>();
        
        // Création de chaînes avec le même contenu mais des références différentes
        String key1 = new String("Java");
        String key2 = new String("Java");
        String key3 = "Java"; // String literal (même référence)
        String key4 = "Java"; // String literal (même référence)
        
        System.out.println("key1 == key2: " + (key1 == key2)); // false (références différentes)
        System.out.println("key1.equals(key2): " + key1.equals(key2)); // true (contenu identique)
        System.out.println("key3 == key4: " + (key3 == key4)); // true (même référence)
        
        // Ajout dans IdentityHashMap
        identityMap.put(key1, 1);
        identityMap.put(key2, 2);
        identityMap.put(key3, 3);
        identityMap.put(key4, 4);
        
        System.out.println("\nIdentityHashMap contents:");
        for (Map.Entry<String, Integer> entry : identityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        System.out.println("\n=== IdentityHashMap with Custom Objects ===");
        
        // Création d'un IdentityHashMap avec des objets personnalisés
        IdentityHashMap<Person, String> personMap = new IdentityHashMap<>();
        
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Alice", 25); // Même contenu, référence différente
        Person person3 = person1; // Même référence
        
        personMap.put(person1, "Employee");
        personMap.put(person2, "Manager");
        personMap.put(person3, "Director");
        
        System.out.println("Person IdentityHashMap contents:");
        for (Map.Entry<Person, String> entry : personMap.entrySet()) {
            Person person = entry.getKey();
            System.out.println(person + " -> " + entry.getValue());
        }
        
        System.out.println("\n=== IdentityHashMap Use Cases ===");
        
        // Cas d'usage: Cache d'objets avec égalité de référence
        IdentityHashMap<Object, Long> objectCache = new IdentityHashMap<>();
        
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = obj1; // Même référence
        
        objectCache.put(obj1, System.currentTimeMillis());
        objectCache.put(obj2, System.currentTimeMillis());
        objectCache.put(obj3, System.currentTimeMillis()); // Écrase obj1
        
        System.out.println("Object cache size: " + objectCache.size());
        System.out.println("Contains obj1: " + objectCache.containsKey(obj1));
        System.out.println("Contains obj3: " + objectCache.containsKey(obj3));
        
        System.out.println("\n=== IdentityHashMap Performance ===");
        
        // Test de performance pour l'accès
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            objectCache.get(obj1);
        }
        long endTime = System.nanoTime();
        
        System.out.println("Time to access IdentityHashMap 100,000 times: " + 
            (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("\n=== IdentityHashMap Operations ===");
        
        // Opérations de base
        System.out.println("Size: " + identityMap.size());
        System.out.println("Is empty: " + identityMap.isEmpty());
        
        // Vérification de présence
        System.out.println("Contains key1: " + identityMap.containsKey(key1));
        System.out.println("Contains value 1: " + identityMap.containsValue(1));
        
        // Récupération de valeurs
        System.out.println("Value for key1: " + identityMap.get(key1));
        System.out.println("Value for key2: " + identityMap.get(key2));
        
        // Suppression
        Integer removedValue = identityMap.remove(key1);
        System.out.println("Removed value: " + removedValue);
        System.out.println("Size after removal: " + identityMap.size());
        
        // Remplacement
        Integer oldValue = identityMap.put(key2, 100);
        System.out.println("Old value for key2: " + oldValue);
        System.out.println("New value for key2: " + identityMap.get(key2));
        
        System.out.println("\n=== IdentityHashMap vs Regular HashMap ===");
        
        // Comparaison avec HashMap
        java.util.HashMap<String, Integer> regularMap = new java.util.HashMap<>();
        
        regularMap.put(key1, 1);
        regularMap.put(key2, 2);
        regularMap.put(key3, 3);
        regularMap.put(key4, 4);
        
        System.out.println("Regular HashMap size: " + regularMap.size());
        System.out.println("IdentityHashMap size: " + identityMap.size());
        
        System.out.println("\nRegular HashMap contents:");
        for (Map.Entry<String, Integer> entry : regularMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        System.out.println("\nIdentityHashMap contents:");
        for (Map.Entry<String, Integer> entry : identityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        System.out.println("\n=== IdentityHashMap with Weak References ===");
        
        // Démonstration avec des références faibles
        IdentityHashMap<WeakReferenceObject, String> weakMap = new IdentityHashMap<>();
        
        WeakReferenceObject weakObj1 = new WeakReferenceObject("Data1");
        WeakReferenceObject weakObj2 = new WeakReferenceObject("Data2");
        
        weakMap.put(weakObj1, "Reference1");
        weakMap.put(weakObj2, "Reference2");
        
        System.out.println("Weak reference map size: " + weakMap.size());
        
        // Simulation de garbage collection
        weakObj1 = null;
        System.gc(); // Suggestion de garbage collection
        
        System.out.println("After GC, map size: " + weakMap.size());
    }
    
    // Classe personnalisée pour démontrer l'égalité de référence
    static class Person {
        String name;
        int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }
        
        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, age);
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
    
    // Classe pour démontrer les références faibles
    static class WeakReferenceObject {
        String data;
        
        public WeakReferenceObject(String data) {
            this.data = data;
        }
        
        @Override
        public String toString() {
            return "WeakReferenceObject{data='" + data + "'}";
        }
    }
}
