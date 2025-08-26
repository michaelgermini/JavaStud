package collection;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Advanced Collection Exercises - Complex challenges for Java Collections Framework
 * Inspired by the original JavaStud project patterns and enterprise scenarios
 */
public class AdvancedCollectionExercises {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Java Collections Framework Exercises ===\n");
        
        // Challenge 1: Data Processing Pipeline
        challenge1_DataProcessingPipeline();
        
        // Challenge 2: Multi-threaded Collection Operations
        challenge2_MultiThreadedOperations();
        
        // Challenge 3: Custom Collection Implementation
        challenge3_CustomCollection();
        
        // Challenge 4: Performance Optimization
        challenge4_PerformanceOptimization();
        
        System.out.println("\n=== All Advanced Challenges Completed Successfully! ===");
    }
    
    /**
     * Challenge 1: Data Processing Pipeline
     * Build a complex data processing pipeline using streams and collectors
     */
    public static void challenge1_DataProcessingPipeline() {
        System.out.println("Challenge 1: Data Processing Pipeline");
        System.out.println("=====================================");
        
        // Create complex dataset
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", "Senior", 85000, 5),
            new Employee("Bob", "Marketing", "Junior", 55000, 2),
            new Employee("Charlie", "Engineering", "Mid", 75000, 3),
            new Employee("David", "Sales", "Senior", 90000, 7),
            new Employee("Eve", "Engineering", "Junior", 60000, 1)
        );
        
        // Task 1.1: Multi-level grouping and aggregation
        Map<String, Map<String, List<Employee>>> groupedByDeptAndLevel = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.groupingBy(Employee::getLevel)));
        
        System.out.println("1.1 Employees grouped by department and level:");
        groupedByDeptAndLevel.forEach((dept, levelMap) -> {
            System.out.println("   " + dept + ":");
            levelMap.forEach((level, empList) -> {
                System.out.println("     " + level + ": " + empList.size() + " employees");
            });
        });
        
        // Task 1.2: Complex statistics calculation
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)));
        
        System.out.println("1.2 Average salary by department:");
        avgSalaryByDept.forEach((dept, avgSalary) -> {
            System.out.printf("   %s: $%.2f%n", dept, avgSalary);
        });
        
        // Task 1.3: Custom collector for salary distribution
        Map<String, List<Employee>> salaryDistribution = employees.stream()
            .collect(Collectors.groupingBy(emp -> {
                if (emp.getSalary() >= 80000) return "High";
                else if (emp.getSalary() >= 65000) return "Medium";
                else return "Low";
            }));
        
        System.out.println("1.3 Salary distribution:");
        salaryDistribution.forEach((range, empList) -> {
            System.out.println("   " + range + " salary: " + empList.size() + " employees");
        });
        
        System.out.println();
    }
    
    /**
     * Challenge 2: Multi-threaded Collection Operations
     * Demonstrate safe concurrent operations on collections
     */
    public static void challenge2_MultiThreadedOperations() {
        System.out.println("Challenge 2: Multi-threaded Collection Operations");
        System.out.println("================================================");
        
        // Task 2.1: ConcurrentHashMap with atomic operations
        ConcurrentHashMap<String, AtomicInteger> voteCounts = new ConcurrentHashMap<>();
        String[] candidates = {"Alice", "Bob", "Charlie"};
        
        // Initialize vote counts
        for (String candidate : candidates) {
            voteCounts.put(candidate, new AtomicInteger(0));
        }
        
        // Simulate concurrent voting
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            final int voterId = i;
            executor.submit(() -> {
                String votedFor = candidates[voterId % candidates.length];
                voteCounts.get(votedFor).incrementAndGet();
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("2.1 Vote counts (concurrent voting):");
        voteCounts.forEach((candidate, votes) -> {
            System.out.println("   " + candidate + ": " + votes.get() + " votes");
        });
        
        // Task 2.2: BlockingQueue for producer-consumer pattern
        BlockingQueue<String> taskQueue = new LinkedBlockingQueue<>(5);
        
        // Producer
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    taskQueue.put("Task " + i);
                    System.out.println("2.2 Produced: Task " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        
        // Consumer
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String task = taskQueue.take();
                    System.out.println("2.2 Consumed: " + task);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    /**
     * Challenge 3: Custom Collection Implementation
     * Implement a custom collection with specific behavior
     */
    public static void challenge3_CustomCollection() {
        System.out.println("Challenge 3: Custom Collection Implementation");
        System.out.println("============================================");
        
        // Task 3.1: Implement a bounded priority queue
        BoundedPriorityQueue<String> boundedQueue = new BoundedPriorityQueue<>(3);
        boundedQueue.add("High Priority Task", 1);
        boundedQueue.add("Medium Priority Task", 2);
        boundedQueue.add("Low Priority Task", 3);
        boundedQueue.add("Very High Priority Task", 0); // Should replace lowest priority
        
        System.out.println("3.1 Bounded priority queue:");
        while (!boundedQueue.isEmpty()) {
            System.out.println("   " + boundedQueue.poll());
        }
        
        // Task 3.2: Implement a cache with LRU eviction
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        cache.get("A"); // Move A to front
        cache.put("D", 4); // Should evict B
        
        System.out.println("3.2 LRU Cache contents:");
        cache.forEach((key, value) -> System.out.println("   " + key + " -> " + value));
        
        System.out.println();
    }
    
    /**
     * Challenge 4: Performance Optimization
     * Optimize collection operations for better performance
     */
    public static void challenge4_PerformanceOptimization() {
        System.out.println("Challenge 4: Performance Optimization");
        System.out.println("====================================");
        
        // Task 4.1: Compare different collection implementations
        int size = 10000;
        
        // ArrayList vs LinkedList performance
        List<Integer> arrayList = new ArrayList<>(size); // Pre-allocate capacity
        List<Integer> linkedList = new LinkedList<>();
        
        long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        long linkedListTime = System.nanoTime() - startTime;
        
        System.out.println("4.1 Performance comparison (adding " + size + " elements):");
        System.out.println("   ArrayList: " + arrayListTime / 1000000 + " ms");
        System.out.println("   LinkedList: " + linkedListTime / 1000000 + " ms");
        
        // Task 4.2: Stream optimization
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            numbers.add(i);
        }
        
        startTime = System.nanoTime();
        long sequentialCount = numbers.stream()
            .filter(n -> n % 2 == 0)
            .count();
        long sequentialTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        long parallelCount = numbers.parallelStream()
            .filter(n -> n % 2 == 0)
            .count();
        long parallelTime = System.nanoTime() - startTime;
        
        System.out.println("4.2 Stream performance comparison:");
        System.out.println("   Sequential: " + sequentialTime / 1000000 + " ms, Count: " + sequentialCount);
        System.out.println("   Parallel: " + parallelTime / 1000000 + " ms, Count: " + parallelCount);
        
        System.out.println();
    }
    
    // Helper classes and implementations
    
    static class Employee {
        private String name, department, level;
        private double salary;
        private int experience;
        
        public Employee(String name, String department, String level, double salary, int experience) {
            this.name = name;
            this.department = department;
            this.level = level;
            this.salary = salary;
            this.experience = experience;
        }
        
        // Getters
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public String getLevel() { return level; }
        public double getSalary() { return salary; }
        public int getExperience() { return experience; }
    }
    
    // Custom collection implementations
    static class BoundedPriorityQueue<T> {
        private final int maxSize;
        private final PriorityQueue<PriorityItem<T>> queue;
        
        public BoundedPriorityQueue(int maxSize) {
            this.maxSize = maxSize;
            this.queue = new PriorityQueue<>();
        }
        
        public void add(T item, int priority) {
            if (queue.size() >= maxSize) {
                queue.poll(); // Remove lowest priority item
            }
            queue.offer(new PriorityItem<>(item, priority));
        }
        
        public T poll() {
            PriorityItem<T> item = queue.poll();
            return item != null ? item.getItem() : null;
        }
        
        public boolean isEmpty() {
            return queue.isEmpty();
        }
        
        private static class PriorityItem<T> implements Comparable<PriorityItem<T>> {
            private final T item;
            private final int priority;
            
            public PriorityItem(T item, int priority) {
                this.item = item;
                this.priority = priority;
            }
            
            public T getItem() { return item; }
            
            @Override
            public int compareTo(PriorityItem<T> other) {
                return Integer.compare(this.priority, other.priority);
            }
        }
    }
    
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;
        
        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }
        
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
}
