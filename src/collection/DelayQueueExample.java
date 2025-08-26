package collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
    
    public static void main(String[] args) {
        System.out.println("=== DelayQueue Basic Example ===");
        
        // Création d'un DelayQueue
        DelayQueue<DelayedTask> taskQueue = new DelayQueue<>();
        
        // Ajout de tâches avec différents délais
        long currentTime = System.currentTimeMillis();
        taskQueue.put(new DelayedTask("Task 1", currentTime + 2000)); // 2 secondes
        taskQueue.put(new DelayedTask("Task 2", currentTime + 1000)); // 1 seconde
        taskQueue.put(new DelayedTask("Task 3", currentTime + 3000)); // 3 secondes
        taskQueue.put(new DelayedTask("Task 4", currentTime + 500));  // 0.5 seconde
        
        System.out.println("Added 4 tasks with different delays");
        System.out.println("Queue size: " + taskQueue.size());
        
        System.out.println("\n=== Processing Delayed Tasks ===");
        
        // Traitement des tâches expirées
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println("Waiting for next task to expire...");
                DelayedTask task = taskQueue.take(); // Bloque jusqu'à expiration
                System.out.println("Executing: " + task.getName() + " at " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted while waiting for tasks");
        }
        
        System.out.println("\n=== DelayQueue with Custom Delayed Objects ===");
        
        // Création d'un DelayQueue avec des objets personnalisés
        DelayQueue<CacheItem> cacheQueue = new DelayQueue<>();
        
        // Ajout d'éléments de cache avec TTL (Time To Live)
        cacheQueue.put(new CacheItem("user1", "data1", 3)); // Expire dans 3 secondes
        cacheQueue.put(new CacheItem("user2", "data2", 1)); // Expire dans 1 seconde
        cacheQueue.put(new CacheItem("user3", "data3", 5)); // Expire dans 5 secondes
        
        System.out.println("Added cache items with TTL");
        
        // Thread pour nettoyer les éléments expirés
        Thread cleanupThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    CacheItem expiredItem = cacheQueue.take();
                    System.out.println("Cache item expired: " + expiredItem.getKey() + 
                                     " at " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Cleanup thread interrupted");
            }
        });
        
        cleanupThread.start();
        
        // Attendre un peu pour voir les éléments expirer
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        cleanupThread.interrupt();
        
        System.out.println("\n=== DelayQueue Methods ===");
        
        // Création d'un nouveau DelayQueue pour démonstration
        DelayQueue<DelayedTask> demoQueue = new DelayQueue<>();
        
        // Ajout d'éléments
        currentTime = System.currentTimeMillis();
        demoQueue.put(new DelayedTask("Demo Task 1", currentTime + 1000));
        demoQueue.put(new DelayedTask("Demo Task 2", currentTime + 2000));
        
        // Méthodes d'inspection
        System.out.println("Queue size: " + demoQueue.size());
        System.out.println("Is empty: " + demoQueue.isEmpty());
        
        // Peek (ne retire pas l'élément)
        DelayedTask peekedTask = demoQueue.peek();
        System.out.println("Peeked task: " + (peekedTask != null ? peekedTask.getName() : "null"));
        
        // Poll avec timeout
        try {
            DelayedTask polledTask = demoQueue.poll(500, TimeUnit.MILLISECONDS);
            System.out.println("Polled task with timeout: " + (polledTask != null ? polledTask.getName() : "null"));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== DelayQueue Use Cases ===");
        
        // Cas d'usage: Système de notifications différées
        DelayQueue<Notification> notificationQueue = new DelayQueue<>();
        
        // Ajout de notifications avec délais
        currentTime = System.currentTimeMillis();
        notificationQueue.put(new Notification("Welcome", "Welcome to our platform!", currentTime + 1000));
        notificationQueue.put(new Notification("Reminder", "Don't forget your meeting!", currentTime + 3000));
        notificationQueue.put(new Notification("Alert", "System maintenance in 5 minutes", currentTime + 5000));
        
        System.out.println("Added notifications with delays");
        
        // Traitement des notifications
        try {
            for (int i = 0; i < 3; i++) {
                Notification notification = notificationQueue.take();
                System.out.println("Sending notification: " + notification.getTitle() + 
                                 " - " + notification.getMessage());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== DelayQueue Performance ===");
        
        // Test de performance
        DelayQueue<DelayedTask> perfQueue = new DelayQueue<>();
        
        // Test d'ajout
        long startTime = System.nanoTime();
        currentTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            perfQueue.put(new DelayedTask("PerfTask" + i, currentTime + i * 10));
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 1,000 delayed tasks: " + (endTime - startTime) / 1000000 + " ms");
        
        // Test de récupération
        startTime = System.nanoTime();
        try {
            for (int i = 0; i < 100; i++) {
                DelayedTask task = perfQueue.take();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        endTime = System.nanoTime();
        System.out.println("Time to take 100 tasks: " + (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("\n=== DelayQueue Best Practices ===");
        
        System.out.println("✅ Use DelayQueue when:");
        System.out.println("   - You need to process items after a specific delay");
        System.out.println("   - You need TTL (Time To Live) functionality");
        System.out.println("   - You need scheduled task execution");
        System.out.println("   - You need cache expiration management");
        
        System.out.println("\n❌ Consider alternatives when:");
        System.out.println("   - You need immediate processing");
        System.out.println("   - You need priority-based ordering");
        System.out.println("   - You need non-blocking operations");
        System.out.println("   - Performance is critical for high-frequency operations");
    }
    
    // Classe pour représenter une tâche différée
    static class DelayedTask implements Delayed {
        private String name;
        private long executionTime;
        
        public DelayedTask(String name, long executionTime) {
            this.name = name;
            this.executionTime = executionTime;
        }
        
        public String getName() {
            return name;
        }
        
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = executionTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }
        
        @Override
        public int compareTo(Delayed other) {
            return Long.compare(this.executionTime, ((DelayedTask) other).executionTime);
        }
        
        @Override
        public String toString() {
            return "DelayedTask{name='" + name + "', executionTime=" + executionTime + "}";
        }
    }
    
    // Classe pour représenter un élément de cache avec TTL
    static class CacheItem implements Delayed {
        private String key;
        private String value;
        private long expirationTime;
        
        public CacheItem(String key, String value, long ttlSeconds) {
            this.key = key;
            this.value = value;
            this.expirationTime = System.currentTimeMillis() + (ttlSeconds * 1000);
        }
        
        public String getKey() {
            return key;
        }
        
        public String getValue() {
            return value;
        }
        
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expirationTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }
        
        @Override
        public int compareTo(Delayed other) {
            return Long.compare(this.expirationTime, ((CacheItem) other).expirationTime);
        }
        
        @Override
        public String toString() {
            return "CacheItem{key='" + key + "', value='" + value + "', expirationTime=" + expirationTime + "}";
        }
    }
    
    // Classe pour représenter une notification différée
    static class Notification implements Delayed {
        private String title;
        private String message;
        private long deliveryTime;
        
        public Notification(String title, String message, long deliveryTime) {
            this.title = title;
            this.message = message;
            this.deliveryTime = deliveryTime;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getMessage() {
            return message;
        }
        
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = deliveryTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }
        
        @Override
        public int compareTo(Delayed other) {
            return Long.compare(this.deliveryTime, ((Notification) other).deliveryTime);
        }
        
        @Override
        public String toString() {
            return "Notification{title='" + title + "', message='" + message + "', deliveryTime=" + deliveryTime + "}";
        }
    }
}
