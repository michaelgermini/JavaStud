package collection;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentSkipListMapExample {
    
    public static void main(String[] args) {
        System.out.println("=== ConcurrentSkipListMap Basic Example ===");
        
        // Création d'un ConcurrentSkipListMap (thread-safe et ordonné)
        ConcurrentSkipListMap<String, Integer> scoreMap = new ConcurrentSkipListMap<>();
        
        // Ajout d'éléments (automatiquement triés par clé)
        scoreMap.put("Alice", 95);
        scoreMap.put("Bob", 87);
        scoreMap.put("Charlie", 92);
        scoreMap.put("David", 78);
        scoreMap.put("Eve", 89);
        
        System.out.println("Initial score map (sorted by name):");
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== ConcurrentSkipListMap Thread Safety ===");
        
        // Démonstration de la thread-safety
        ConcurrentSkipListMap<Integer, String> threadSafeMap = new ConcurrentSkipListMap<>();
        
        // Création d'un pool de threads
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        // Threads qui ajoutent des éléments
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 5; j++) {
                    int key = threadId * 10 + j;
                    String value = "Thread" + threadId + "_Value" + j;
                    threadSafeMap.put(key, value);
                    System.out.println("Thread " + threadId + " added: " + key + " -> " + value);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
        
        // Thread qui lit la map
        executor.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Reader thread - Current map size: " + threadSafeMap.size());
                System.out.println("First entry: " + threadSafeMap.firstEntry());
                System.out.println("Last entry: " + threadSafeMap.lastEntry());
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
        
        System.out.println("\nFinal thread-safe map (sorted by key):");
        for (Map.Entry<Integer, String> entry : threadSafeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        System.out.println("\n=== ConcurrentSkipListMap Navigation Methods ===");
        
        // Méthodes de navigation
        System.out.println("First key: " + scoreMap.firstKey());
        System.out.println("Last key: " + scoreMap.lastKey());
        System.out.println("First entry: " + scoreMap.firstEntry());
        System.out.println("Last entry: " + scoreMap.lastEntry());
        
        // Entrées supérieures et inférieures
        System.out.println("Higher than 'Bob': " + scoreMap.higherEntry("Bob"));
        System.out.println("Lower than 'Charlie': " + scoreMap.lowerEntry("Charlie"));
        System.out.println("Ceiling of 'Carol': " + scoreMap.ceilingEntry("Carol"));
        System.out.println("Floor of 'Carol': " + scoreMap.floorEntry("Carol"));
        
        // Sous-maps
        System.out.println("\nSubmap from 'Bob' to 'David':");
        Map<String, Integer> subMap = scoreMap.subMap("Bob", "David");
        for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== ConcurrentSkipListMap Use Cases ===");
        
        // Cas d'usage: Gestionnaire de cache avec ordre
        ConcurrentSkipListMap<Long, CacheEntry> cache = new ConcurrentSkipListMap<>();
        
        // Ajout d'entrées de cache avec timestamp
        long now = System.currentTimeMillis();
        cache.put(now, new CacheEntry("user1", "data1"));
        cache.put(now + 1000, new CacheEntry("user2", "data2"));
        cache.put(now + 2000, new CacheEntry("user3", "data3"));
        
        System.out.println("Cache entries (sorted by timestamp):");
        for (Map.Entry<Long, CacheEntry> entry : cache.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Nettoyage des anciennes entrées (plus de 5 secondes)
        long cutoffTime = now + 5000;
        Map<Long, CacheEntry> oldEntries = cache.headMap(cutoffTime);
        System.out.println("Removing " + oldEntries.size() + " old entries");
        oldEntries.clear();
        
        System.out.println("Cache after cleanup: " + cache.size() + " entries");
        
        System.out.println("\n=== ConcurrentSkipListMap Performance ===");
        
        // Test de performance
        ConcurrentSkipListMap<Integer, String> perfMap = new ConcurrentSkipListMap<>();
        
        // Test d'ajout
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfMap.put(i, "Value" + i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 10,000 elements: " + (endTime - startTime) / 1000000 + " ms");
        
        // Test de recherche
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfMap.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time to get 10,000 elements: " + (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("\n=== ConcurrentSkipListMap Best Practices ===");
        
        System.out.println("✅ Use ConcurrentSkipListMap when:");
        System.out.println("   - You need a thread-safe sorted map");
        System.out.println("   - You need navigation methods (first, last, higher, lower)");
        System.out.println("   - You need submap operations");
        System.out.println("   - You can tolerate the overhead of maintaining order");
        
        System.out.println("\n❌ Consider alternatives when:");
        System.out.println("   - You don't need ordering (use ConcurrentHashMap)");
        System.out.println("   - Performance is critical and you can handle synchronization manually");
        System.out.println("   - You need frequent updates to existing keys");
    }
    
    // Classe pour représenter une entrée de cache
    static class CacheEntry {
        String key;
        String data;
        long timestamp;
        
        public CacheEntry(String key, String data) {
            this.key = key;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }
        
        @Override
        public String toString() {
            return "CacheEntry{key='" + key + "', data='" + data + "', timestamp=" + timestamp + "}";
        }
    }
}
