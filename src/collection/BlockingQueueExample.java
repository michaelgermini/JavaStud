package collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {
    
    public static void main(String[] args) {
        System.out.println("=== BlockingQueue Basic Example ===");
        
        // Création d'une BlockingQueue avec capacité limitée
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        
        // Ajout d'éléments
        try {
            queue.put("First");
            queue.put("Second");
            queue.put("Third");
            System.out.println("Queue after adding 3 elements: " + queue);
            
            // Tentative d'ajout d'un 4ème élément (bloquant)
            System.out.println("Attempting to add 4th element...");
            queue.put("Fourth"); // Bloque jusqu'à ce qu'un élément soit retiré
            System.out.println("4th element added successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted while adding elements");
        }
        
        System.out.println("\n=== Producer-Consumer Pattern ===");
        
        // Démonstration du pattern producteur-consommateur
        BlockingQueue<Integer> numberQueue = new ArrayBlockingQueue<>(5);
        
        // Producteur
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    numberQueue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
                // Signal de fin
                numberQueue.put(-1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Consommateur
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer value = numberQueue.take();
                    if (value == -1) {
                        System.out.println("Consumer received end signal");
                        break;
                    }
                    System.out.println("Consumed: " + value);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Démarrage des threads
        producer.start();
        consumer.start();
        
        // Attendre la fin
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== Different BlockingQueue Implementations ===");
        
        // ArrayBlockingQueue
        System.out.println("--- ArrayBlockingQueue ---");
        BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<>(2);
        demonstrateQueue(arrayQueue, "ArrayBlockingQueue");
        
        // LinkedBlockingQueue
        System.out.println("\n--- LinkedBlockingQueue ---");
        BlockingQueue<String> linkedQueue = new LinkedBlockingQueue<>(2);
        demonstrateQueue(linkedQueue, "LinkedBlockingQueue");
        
        // PriorityBlockingQueue
        System.out.println("\n--- PriorityBlockingQueue ---");
        BlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>(3);
        demonstratePriorityQueue(priorityQueue);
        
        System.out.println("\n=== BlockingQueue Methods ===");
        
        BlockingQueue<String> demoQueue = new ArrayBlockingQueue<>(3);
        
        // Méthodes d'ajout
        System.out.println("Adding elements...");
        try {
            demoQueue.put("Element1"); // Bloquant
            demoQueue.offer("Element2"); // Non-bloquant
            demoQueue.offer("Element3", 1, TimeUnit.SECONDS); // Avec timeout
            System.out.println("Queue: " + demoQueue);
            
            // Tentative d'ajout avec timeout
            boolean added = demoQueue.offer("Element4", 1, TimeUnit.SECONDS);
            System.out.println("Element4 added with timeout: " + added);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Méthodes de récupération
        System.out.println("\nRetrieving elements...");
        try {
            String element1 = demoQueue.take(); // Bloquant
            System.out.println("Taken: " + element1);
            
            String element2 = demoQueue.poll(); // Non-bloquant
            System.out.println("Polled: " + element2);
            
            String element3 = demoQueue.poll(1, TimeUnit.SECONDS); // Avec timeout
            System.out.println("Polled with timeout: " + element3);
            
            // Tentative de récupération avec timeout
            String element4 = demoQueue.poll(1, TimeUnit.SECONDS);
            System.out.println("Polled empty queue with timeout: " + element4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Méthodes d'inspection
        System.out.println("\nInspection methods...");
        demoQueue.offer("NewElement");
        System.out.println("Peek: " + demoQueue.peek());
        System.out.println("Element: " + demoQueue.element());
        System.out.println("Queue: " + demoQueue);
        
        System.out.println("\n=== BlockingQueue Use Cases ===");
        
        // Cas d'usage: Gestionnaire de tâches
        BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>(10);
        
        // Ajout de tâches
        taskQueue.offer(new Task("Send Email", 1));
        taskQueue.offer(new Task("Process Data", 3));
        taskQueue.offer(new Task("Generate Report", 2));
        taskQueue.offer(new Task("Backup Database", 5));
        
        System.out.println("Task queue: " + taskQueue);
        
        // Traitement des tâches par priorité
        System.out.println("Processing tasks by priority:");
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            if (task != null) {
                System.out.println("Processing: " + task);
            }
        }
        
        System.out.println("\n=== BlockingQueue Performance ===");
        
        // Test de performance
        BlockingQueue<Integer> perfQueue = new ArrayBlockingQueue<>(1000);
        
        // Test d'ajout
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            try {
                perfQueue.put(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 1000 elements: " + (endTime - startTime) / 1000000 + " ms");
        
        // Test de récupération
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            try {
                perfQueue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Time to take 1000 elements: " + (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("\n=== BlockingQueue Best Practices ===");
        
        System.out.println("✅ Use BlockingQueue when:");
        System.out.println("   - You need thread-safe communication between threads");
        System.out.println("   - Implementing producer-consumer patterns");
        System.out.println("   - Need to control memory usage with bounded queues");
        System.out.println("   - Want to avoid busy-waiting with blocking operations");
        
        System.out.println("\n❌ Consider alternatives when:");
        System.out.println("   - You need non-blocking operations");
        System.out.println("   - Performance is critical and you can handle synchronization manually");
        System.out.println("   - You need complex ordering or filtering");
    }
    
    // Méthode pour démontrer les opérations de base d'une queue
    private static void demonstrateQueue(BlockingQueue<String> queue, String queueType) {
        try {
            queue.put("A");
            queue.put("B");
            System.out.println(queueType + " after adding A, B: " + queue);
            
            String element = queue.take();
            System.out.println("Taken from " + queueType + ": " + element);
            System.out.println(queueType + " after taking: " + queue);
            
            queue.put("C");
            System.out.println(queueType + " after adding C: " + queue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Méthode pour démontrer PriorityBlockingQueue
    private static void demonstratePriorityQueue(BlockingQueue<Integer> queue) {
        try {
            queue.put(5);
            queue.put(1);
            queue.put(3);
            System.out.println("PriorityQueue after adding 5, 1, 3: " + queue);
            
            System.out.println("Taking elements in priority order:");
            while (!queue.isEmpty()) {
                Integer element = queue.take();
                System.out.println("Taken: " + element);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Classe pour représenter une tâche
    static class Task implements Comparable<Task> {
        String name;
        int priority;
        
        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
        
        @Override
        public String toString() {
            return name + " (Priority: " + priority + ")";
        }
    }
}
