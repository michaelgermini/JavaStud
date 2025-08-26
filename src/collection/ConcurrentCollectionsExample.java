package collection;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ConcurrentCollectionsExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Concurrent Collections Examples ===\n");
        
        System.out.println("=== ConcurrentHashMap Example ===");
        demonstrateConcurrentHashMap();
        
        System.out.println("\n=== CopyOnWriteArrayList Example ===");
        demonstrateCopyOnWriteArrayList();
        
        System.out.println("\n=== BlockingQueue Examples ===");
        demonstrateBlockingQueues();
        
        System.out.println("\n=== Concurrent Collections vs Regular Collections ===");
        demonstrateThreadSafetyComparison();
        
        System.out.println("\n=== Atomic Operations ===");
        demonstrateAtomicOperations();
    }
    
    private static void demonstrateConcurrentHashMap() throws InterruptedException {
        // ConcurrentHashMap is thread-safe and doesn't require external synchronization
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // Create multiple threads that add elements concurrently
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        for (int i = 0; i < 4; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    String key = "Thread-" + threadId + "-Key-" + j;
                    concurrentMap.put(key, threadId * 100 + j);
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        
        System.out.println("ConcurrentHashMap size: " + concurrentMap.size());
        System.out.println("Sample entries:");
        concurrentMap.entrySet().stream()
            .limit(10)
            .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
        
        // Demonstrate atomic operations
        concurrentMap.putIfAbsent("TestKey", 100);
        concurrentMap.putIfAbsent("TestKey", 200); // Won't replace existing value
        System.out.println("TestKey value: " + concurrentMap.get("TestKey"));
        
        // Replace operation
        concurrentMap.replace("TestKey", 100, 150);
        System.out.println("After replace: " + concurrentMap.get("TestKey"));
    }
    
    private static void demonstrateCopyOnWriteArrayList() throws InterruptedException {
        // CopyOnWriteArrayList is thread-safe for read operations
        CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        
        // Add initial elements
        copyOnWriteList.add("Initial1");
        copyOnWriteList.add("Initial2");
        
        // Create a reader thread
        Thread readerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Reader iteration " + i + ": " + copyOnWriteList);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        // Create a writer thread
        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                copyOnWriteList.add("Writer-" + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        readerThread.start();
        writerThread.start();
        
        readerThread.join();
        writerThread.join();
        
        System.out.println("Final CopyOnWriteArrayList: " + copyOnWriteList);
    }
    
    private static void demonstrateBlockingQueues() throws InterruptedException {
        System.out.println("=== ArrayBlockingQueue ===");
        
        // ArrayBlockingQueue with capacity 3
        BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<>(3);
        
        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String item = "Item-" + i;
                    arrayQueue.put(item); // Blocks if queue is full
                    System.out.println("Produced: " + item);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String item = arrayQueue.take(); // Blocks if queue is empty
                    System.out.println("Consumed: " + item);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        producer.start();
        consumer.start();
        
        producer.join();
        consumer.join();
        
        System.out.println("\n=== PriorityBlockingQueue ===");
        
        // PriorityBlockingQueue maintains natural ordering
        PriorityBlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();
        
        // Add elements in random order
        priorityQueue.offer(5);
        priorityQueue.offer(1);
        priorityQueue.offer(8);
        priorityQueue.offer(3);
        priorityQueue.offer(9);
        
        System.out.println("Priority queue elements (will be consumed in order):");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }
        System.out.println();
    }
    
    private static void demonstrateThreadSafetyComparison() throws InterruptedException {
        System.out.println("=== Thread Safety Comparison ===");
        
        // Regular HashMap (not thread-safe)
        Map<String, Integer> regularMap = new HashMap<>();
        
        // ConcurrentHashMap (thread-safe)
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // Test with multiple threads
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        // Test regular HashMap (may cause issues)
        System.out.println("Testing regular HashMap (may have issues):");
        for (int i = 0; i < 4; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    String key = "Key-" + (threadId * 100 + j);
                    regularMap.put(key, j);
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        
        System.out.println("Regular HashMap size: " + regularMap.size());
        
        // Test ConcurrentHashMap
        executor = Executors.newFixedThreadPool(4);
        System.out.println("\nTesting ConcurrentHashMap (thread-safe):");
        
        for (int i = 0; i < 4; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    String key = "Key-" + (threadId * 100 + j);
                    concurrentMap.put(key, j);
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        
        System.out.println("ConcurrentHashMap size: " + concurrentMap.size());
    }
    
    private static void demonstrateAtomicOperations() {
        System.out.println("=== Atomic Operations ===");
        
        // AtomicInteger for thread-safe counter
        AtomicInteger counter = new AtomicInteger(0);
        
        // Multiple threads incrementing the counter
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        for (int i = 0; i < 4; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementAndGet();
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Final counter value: " + counter.get());
        System.out.println("Expected value: 4000");
        
        // Atomic operations on ConcurrentHashMap
        ConcurrentHashMap<String, AtomicInteger> atomicMap = new ConcurrentHashMap<>();
        
        // Thread-safe increment operation
        atomicMap.computeIfAbsent("counter", k -> new AtomicInteger(0));
        
        for (int i = 0; i < 4; i++) {
            executor = Executors.newFixedThreadPool(1);
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    atomicMap.get("counter").incrementAndGet();
                }
            });
            executor.shutdown();
        }
        
        System.out.println("Atomic counter in map: " + atomicMap.get("counter").get());
    }
}
