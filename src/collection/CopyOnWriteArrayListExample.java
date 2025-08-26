package collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteArrayListExample {
    
    public static void main(String[] args) {
        System.out.println("=== CopyOnWriteArrayList Basic Example ===");
        
        // Création d'un CopyOnWriteArrayList
        CopyOnWriteArrayList<String> threadSafeList = new CopyOnWriteArrayList<>();
        
        // Ajout d'éléments
        threadSafeList.add("Java");
        threadSafeList.add("Python");
        threadSafeList.add("JavaScript");
        threadSafeList.add("C++");
        
        System.out.println("Initial list: " + threadSafeList);
        
        // Ajout à un index spécifique
        threadSafeList.add(1, "Ruby");
        System.out.println("After adding Ruby at index 1: " + threadSafeList);
        
        // Suppression d'éléments
        threadSafeList.remove("Python");
        System.out.println("After removing Python: " + threadSafeList);
        
        // Remplacement d'élément
        threadSafeList.set(0, "Java SE");
        System.out.println("After replacing Java with Java SE: " + threadSafeList);
        
        System.out.println("\n=== CopyOnWriteArrayList Thread Safety ===");
        
        // Démonstration de la thread-safety
        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>();
        
        // Création d'un pool de threads
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        // Threads qui ajoutent des éléments
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 5; j++) {
                    int value = threadId * 10 + j;
                    numbers.add(value);
                    System.out.println("Thread " + threadId + " added: " + value);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
        
        // Thread qui lit la liste
        executor.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Reader thread - Current list: " + numbers);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Attendre la fin des threads
        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Final list: " + numbers);
        
        System.out.println("\n=== CopyOnWriteArrayList vs ArrayList ===");
        
        // Comparaison avec ArrayList
        List<String> regularList = new java.util.ArrayList<>();
        CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        
        // Test de performance pour les lectures
        for (int i = 0; i < 1000; i++) {
            regularList.add("Item" + i);
            copyOnWriteList.add("Item" + i);
        }
        
        // Test de lecture
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            regularList.get(i % regularList.size());
        }
        long endTime = System.nanoTime();
        System.out.println("ArrayList read time: " + (endTime - startTime) / 1000000 + " ms");
        
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            copyOnWriteList.get(i % copyOnWriteList.size());
        }
        endTime = System.nanoTime();
        System.out.println("CopyOnWriteArrayList read time: " + (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("\n=== CopyOnWriteArrayList Use Cases ===");
        
        // Cas d'usage: Configuration thread-safe
        CopyOnWriteArrayList<ConfigurationItem> config = new CopyOnWriteArrayList<>();
        
        config.add(new ConfigurationItem("database.url", "localhost:3306"));
        config.add(new ConfigurationItem("database.user", "admin"));
        config.add(new ConfigurationItem("database.password", "secret"));
        config.add(new ConfigurationItem("app.port", "8080"));
        
        System.out.println("Configuration items:");
        for (ConfigurationItem item : config) {
            System.out.println(item.key + " = " + item.value);
        }
        
        // Mise à jour de configuration
        config.add(new ConfigurationItem("app.debug", "true"));
        System.out.println("\nAfter adding debug config:");
        for (ConfigurationItem item : config) {
            System.out.println(item.key + " = " + item.value);
        }
        
        System.out.println("\n=== CopyOnWriteArrayList Operations ===");
        
        // Opérations de base
        System.out.println("Size: " + copyOnWriteList.size());
        System.out.println("Is empty: " + copyOnWriteList.isEmpty());
        System.out.println("Contains 'Java': " + copyOnWriteList.contains("Java"));
        
        // Index des éléments
        System.out.println("Index of 'JavaScript': " + copyOnWriteList.indexOf("JavaScript"));
        System.out.println("Last index of 'JavaScript': " + copyOnWriteList.lastIndexOf("JavaScript"));
        
        // Conversion en tableau
        Object[] array = copyOnWriteList.toArray();
        System.out.println("Array length: " + array.length);
        
        // Sous-liste
        List<String> subList = copyOnWriteList.subList(1, 3);
        System.out.println("SubList (1-3): " + subList);
        
        // Itération avec forEach
        System.out.println("Iterating with forEach:");
        copyOnWriteList.forEach(item -> System.out.println("Item: " + item));
        
        // Itération avec iterator
        System.out.println("Iterating with iterator:");
        java.util.Iterator<String> iterator = copyOnWriteList.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator item: " + iterator.next());
        }
        
        System.out.println("\n=== CopyOnWriteArrayList Performance Considerations ===");
        
        // Démonstration de l'impact des modifications
        CopyOnWriteArrayList<String> performanceList = new CopyOnWriteArrayList<>();
        
        System.out.println("Adding 1000 elements...");
        long addStartTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            performanceList.add("Element" + i);
        }
        long addEndTime = System.nanoTime();
        System.out.println("Time to add 1000 elements: " + (addEndTime - addStartTime) / 1000000 + " ms");
        
        System.out.println("Removing 100 elements...");
        long removeStartTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            performanceList.remove(0);
        }
        long removeEndTime = System.nanoTime();
        System.out.println("Time to remove 100 elements: " + (removeEndTime - removeStartTime) / 1000000 + " ms");
        
        System.out.println("Final size: " + performanceList.size());
        
        System.out.println("\n=== CopyOnWriteArrayList Best Practices ===");
        
        System.out.println("✅ Use CopyOnWriteArrayList when:");
        System.out.println("   - Reads are much more frequent than writes");
        System.out.println("   - You need thread-safety without external synchronization");
        System.out.println("   - The list is small to medium size");
        System.out.println("   - You can tolerate the memory overhead of copying");
        
        System.out.println("\n❌ Avoid CopyOnWriteArrayList when:");
        System.out.println("   - Writes are frequent");
        System.out.println("   - The list is very large");
        System.out.println("   - Memory is a constraint");
        System.out.println("   - You need real-time consistency");
    }
    
    // Classe pour représenter un élément de configuration
    static class ConfigurationItem {
        String key;
        String value;
        
        public ConfigurationItem(String key, String value) {
            this.key = key;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
