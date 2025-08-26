package collection;

import java.util.*;
import java.lang.ref.WeakReference;

public class SpecializedCollectionsExample {
    
    // Enum for demonstration
    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    public static void main(String[] args) {
        System.out.println("=== Specialized Collections Examples ===\n");
        
        System.out.println("=== WeakHashMap Example ===");
        demonstrateWeakHashMap();
        
        System.out.println("\n=== EnumMap Example ===");
        demonstrateEnumMap();
        
        System.out.println("\n=== IdentityHashMap Example ===");
        demonstrateIdentityHashMap();
        
        System.out.println("\n=== Properties Example ===");
        demonstrateProperties();
        
        System.out.println("\n=== Weak References Example ===");
        demonstrateWeakReferences();
    }
    
    private static void demonstrateWeakHashMap() {
        System.out.println("WeakHashMap allows garbage collection of keys");
        
        // Create a WeakHashMap
        WeakHashMap<Object, String> weakMap = new WeakHashMap<>();
        
        // Create some objects as keys
        Object key1 = new Object();
        Object key2 = new Object();
        Object key3 = new Object();
        
        // Add entries
        weakMap.put(key1, "Value1");
        weakMap.put(key2, "Value2");
        weakMap.put(key3, "Value3");
        
        System.out.println("Initial size: " + weakMap.size());
        System.out.println("Contains key1: " + weakMap.containsKey(key1));
        
        // Remove reference to key1
        key1 = null;
        
        // Suggest garbage collection
        System.gc();
        
        try {
            Thread.sleep(1000); // Give GC time to run
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("After GC, size: " + weakMap.size());
        System.out.println("Contains key2: " + weakMap.containsKey(key2));
        System.out.println("Contains key3: " + weakMap.containsKey(key3));
        
        // WeakHashMap is useful for caching scenarios
        System.out.println("\n=== Caching Example with WeakHashMap ===");
        
        WeakHashMap<String, ExpensiveObject> cache = new WeakHashMap<>();
        
        // Simulate expensive object creation
        cache.put("key1", new ExpensiveObject("Data1"));
        cache.put("key2", new ExpensiveObject("Data2"));
        
        System.out.println("Cache size: " + cache.size());
        System.out.println("Retrieved: " + cache.get("key1"));
        
        // When memory pressure occurs, unused cache entries can be garbage collected
    }
    
    private static void demonstrateEnumMap() {
        System.out.println("EnumMap is optimized for enum keys");
        
        // Create EnumMap with DayOfWeek enum
        EnumMap<DayOfWeek, String> schedule = new EnumMap<>(DayOfWeek.class);
        
        // Add schedule entries
        schedule.put(DayOfWeek.MONDAY, "Team Meeting");
        schedule.put(DayOfWeek.TUESDAY, "Code Review");
        schedule.put(DayOfWeek.WEDNESDAY, "Planning");
        schedule.put(DayOfWeek.THURSDAY, "Development");
        schedule.put(DayOfWeek.FRIDAY, "Demo");
        schedule.put(DayOfWeek.SATURDAY, "Weekend");
        schedule.put(DayOfWeek.SUNDAY, "Rest");
        
        System.out.println("Weekly Schedule:");
        for (Map.Entry<DayOfWeek, String> entry : schedule.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // EnumMap maintains natural enum order
        System.out.println("\nEnumMap is ordered by enum declaration:");
        schedule.keySet().forEach(day -> 
            System.out.println(day + " -> " + schedule.get(day)));
        
        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");
        
        // EnumMap vs HashMap for enum keys
        Map<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);
        Map<DayOfWeek, Integer> hashMap = new HashMap<>();
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            for (DayOfWeek day : DayOfWeek.values()) {
                enumMap.put(day, i);
            }
        }
        long enumMapTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            for (DayOfWeek day : DayOfWeek.values()) {
                hashMap.put(day, i);
            }
        }
        long hashMapTime = System.nanoTime() - startTime;
        
        System.out.println("EnumMap time: " + enumMapTime + "ns");
        System.out.println("HashMap time: " + hashMapTime + "ns");
        System.out.println("EnumMap is " + (hashMapTime / enumMapTime) + "x faster for enum keys");
    }
    
    private static void demonstrateIdentityHashMap() {
        System.out.println("IdentityHashMap uses == for key comparison instead of equals()");
        
        // Create two equal strings
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        
        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s1 == s2: " + (s1 == s2));
        
        // Regular HashMap
        HashMap<String, String> regularMap = new HashMap<>();
        regularMap.put(s1, "Value1");
        regularMap.put(s2, "Value2");
        System.out.println("HashMap size: " + regularMap.size());
        System.out.println("HashMap content: " + regularMap);
        
        // IdentityHashMap
        IdentityHashMap<String, String> identityMap = new IdentityHashMap<>();
        identityMap.put(s1, "Value1");
        identityMap.put(s2, "Value2");
        System.out.println("IdentityHashMap size: " + identityMap.size());
        System.out.println("IdentityHashMap content: " + identityMap);
        
        // Demonstrate with custom objects
        System.out.println("\n=== Custom Objects Example ===");
        
        class Person {
            private String name;
            
            public Person(String name) { this.name = name; }
            
            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Person person = (Person) obj;
                return Objects.equals(name, person.name);
            }
            
            @Override
            public int hashCode() {
                return Objects.hash(name);
            }
            
            @Override
            public String toString() {
                return "Person(" + name + ")";
            }
        }
        
        Person p1 = new Person("John");
        Person p2 = new Person("John");
        
        IdentityHashMap<Person, String> personMap = new IdentityHashMap<>();
        personMap.put(p1, "First John");
        personMap.put(p2, "Second John");
        
        System.out.println("Person IdentityHashMap: " + personMap);
    }
    
    private static void demonstrateProperties() {
        System.out.println("Properties extends Hashtable and is used for configuration");
        
        Properties config = new Properties();
        
        // Set properties
        config.setProperty("database.url", "jdbc:mysql://localhost:3306/mydb");
        config.setProperty("database.username", "admin");
        config.setProperty("database.password", "secret");
        config.setProperty("app.name", "My Application");
        config.setProperty("app.version", "1.0.0");
        
        // Get properties
        System.out.println("Database URL: " + config.getProperty("database.url"));
        System.out.println("App Name: " + config.getProperty("app.name"));
        
        // Get with default value
        System.out.println("Timeout (with default): " + 
            config.getProperty("timeout", "30"));
        
        // List all properties
        System.out.println("\nAll properties:");
        config.list(System.out);
        
        // Properties can be loaded from files
        System.out.println("\n=== Loading from String (simulating file) ===");
        
        Properties loadedProps = new Properties();
        String configContent = 
            "# Configuration file\n" +
            "server.port=8080\n" +
            "server.host=localhost\n" +
            "debug.enabled=true\n";
        
        try {
            loadedProps.load(new java.io.StringReader(configContent));
            System.out.println("Loaded properties: " + loadedProps);
        } catch (Exception e) {
            System.err.println("Error loading properties: " + e.getMessage());
        }
    }
    
    private static void demonstrateWeakReferences() {
        System.out.println("Weak References allow objects to be garbage collected");
        
        // Create a weak reference
        Object strongRef = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(strongRef);
        
        System.out.println("Weak reference get(): " + weakRef.get());
        System.out.println("Is enqueued: " + weakRef.isEnqueued());
        
        // Remove strong reference
        strongRef = null;
        
        // Suggest garbage collection
        System.gc();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("After GC, weak reference get(): " + weakRef.get());
        System.out.println("Is enqueued: " + weakRef.isEnqueued());
        
        // WeakHashMap uses weak references internally
        System.out.println("\nWeakHashMap internally uses WeakReference for keys");
    }
    
    // Helper class for demonstration
    static class ExpensiveObject {
        private String data;
        
        public ExpensiveObject(String data) {
            this.data = data;
            System.out.println("Creating expensive object: " + data);
        }
        
        @Override
        public String toString() {
            return "ExpensiveObject(" + data + ")";
        }
    }
}
