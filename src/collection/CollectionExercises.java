package collection;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Collection Exercises - Practical hands-on exercises for Java Collections Framework
 * Inspired by the original JavaStud project patterns and real-world scenarios
 */
public class CollectionExercises {
    
    public static void main(String[] args) {
        System.out.println("=== Java Collections Framework Exercises ===\n");
        
        // Exercise 1: Basic List Operations
        exercise1_BasicListOperations();
        
        // Exercise 2: Set Operations and Deduplication
        exercise2_SetOperations();
        
        // Exercise 3: Map Operations and Data Processing
        exercise3_MapOperations();
        
        // Exercise 4: Queue and Priority Processing
        exercise4_QueueOperations();
        
        // Exercise 5: Advanced Stream Operations
        exercise5_StreamOperations();
        
        // Exercise 6: Concurrent Collections
        exercise6_ConcurrentCollections();
        
        // Exercise 7: Specialized Collections
        exercise7_SpecializedCollections();
        
        // Exercise 8: Real-world Application
        exercise8_RealWorldApplication();
        
        System.out.println("\n=== All Exercises Completed Successfully! ===");
    }
    
    /**
     * Exercise 1: Basic List Operations
     * Create and manipulate different types of lists
     */
    public static void exercise1_BasicListOperations() {
        System.out.println("Exercise 1: Basic List Operations");
        System.out.println("=================================");
        
        // Task 1.1: Create an ArrayList and perform basic operations
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");
        names.add("Eve");
        
        System.out.println("1.1 Original list: " + names);
        
        // Task 1.2: Insert element at specific position
        names.add(2, "Frank");
        System.out.println("1.2 After inserting 'Frank' at index 2: " + names);
        
        // Task 1.3: Remove element by value and index
        names.remove("Charlie");
        names.remove(1);
        System.out.println("1.3 After removing 'Charlie' and element at index 1: " + names);
        
        // Task 1.4: Sort the list
        Collections.sort(names);
        System.out.println("1.4 After sorting: " + names);
        
        // Task 1.5: Reverse the list
        Collections.reverse(names);
        System.out.println("1.5 After reversing: " + names);
        
        // Task 1.6: Create LinkedList and demonstrate differences
        LinkedList<String> linkedNames = new LinkedList<>(names);
        linkedNames.addFirst("Zoe");
        linkedNames.addLast("Yara");
        System.out.println("1.6 LinkedList with first/last operations: " + linkedNames);
        
        // Task 1.7: Find maximum and minimum elements
        List<Integer> numbers = Arrays.asList(45, 12, 89, 34, 67, 23, 78);
        System.out.println("1.7 Numbers: " + numbers);
        System.out.println("   Max: " + Collections.max(numbers));
        System.out.println("   Min: " + Collections.min(numbers));
        
        System.out.println();
    }
    
    /**
     * Exercise 2: Set Operations and Deduplication
     * Work with different Set implementations
     */
    public static void exercise2_SetOperations() {
        System.out.println("Exercise 2: Set Operations and Deduplication");
        System.out.println("=============================================");
        
        // Task 2.1: Create HashSet and demonstrate uniqueness
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Alice"); // Duplicate
        uniqueNames.add("Charlie");
        uniqueNames.add("Bob");   // Duplicate
        System.out.println("2.1 HashSet (duplicates removed): " + uniqueNames);
        
        // Task 2.2: Create LinkedHashSet to maintain insertion order
        Set<String> orderedNames = new LinkedHashSet<>();
        orderedNames.add("Zoe");
        orderedNames.add("Alice");
        orderedNames.add("Bob");
        orderedNames.add("Zoe"); // Duplicate
        System.out.println("2.2 LinkedHashSet (maintains insertion order): " + orderedNames);
        
        // Task 2.3: Create TreeSet for natural ordering
        Set<String> sortedNames = new TreeSet<>(uniqueNames);
        System.out.println("2.3 TreeSet (natural ordering): " + sortedNames);
        
        // Task 2.4: Set operations (union, intersection, difference)
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("2.4 Set1: " + set1);
        System.out.println("   Set2: " + set2);
        System.out.println("   Union: " + union);
        
        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("   Intersection: " + intersection);
        
        // Difference
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("   Difference (Set1 - Set2): " + difference);
        
        System.out.println();
    }
    
    /**
     * Exercise 3: Map Operations and Data Processing
     * Work with different Map implementations
     */
    public static void exercise3_MapOperations() {
        System.out.println("Exercise 3: Map Operations and Data Processing");
        System.out.println("==============================================");
        
        // Task 3.1: Create HashMap and perform basic operations
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("David", 78);
        scores.put("Eve", 89);
        
        System.out.println("3.1 Original scores: " + scores);
        
        // Task 3.2: Update values and check for existence
        scores.put("Alice", 98); // Update existing
        scores.putIfAbsent("Frank", 85); // Add only if not exists
        System.out.println("3.2 After updates: " + scores);
        System.out.println("   Contains 'Alice': " + scores.containsKey("Alice"));
        System.out.println("   Contains score 95: " + scores.containsValue(95));
        
        // Task 3.3: Create LinkedHashMap to maintain insertion order
        Map<String, Integer> orderedScores = new LinkedHashMap<>();
        orderedScores.put("Zoe", 91);
        orderedScores.put("Alice", 98);
        orderedScores.put("Bob", 87);
        System.out.println("3.3 LinkedHashMap (maintains insertion order): " + orderedScores);
        
        // Task 3.4: Create TreeMap for sorted keys
        Map<String, Integer> sortedScores = new TreeMap<>(scores);
        System.out.println("3.4 TreeMap (sorted by key): " + sortedScores);
        
        // Task 3.5: Find highest and lowest scores
        Map.Entry<String, Integer> maxEntry = Collections.max(scores.entrySet(), 
            Map.Entry.comparingByValue());
        Map.Entry<String, Integer> minEntry = Collections.min(scores.entrySet(), 
            Map.Entry.comparingByValue());
        
        System.out.println("3.5 Highest score: " + maxEntry.getKey() + " (" + maxEntry.getValue() + ")");
        System.out.println("   Lowest score: " + minEntry.getKey() + " (" + minEntry.getValue() + ")");
        
        // Task 3.6: Group students by score ranges
        Map<String, List<String>> scoreGroups = new HashMap<>();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String range = entry.getValue() >= 90 ? "A" : 
                          entry.getValue() >= 80 ? "B" : 
                          entry.getValue() >= 70 ? "C" : "D";
            scoreGroups.computeIfAbsent(range, k -> new ArrayList<>()).add(entry.getKey());
        }
        System.out.println("3.6 Score groups: " + scoreGroups);
        
        System.out.println();
    }
    
    /**
     * Exercise 4: Queue and Priority Processing
     * Work with different Queue implementations
     */
    public static void exercise4_QueueOperations() {
        System.out.println("Exercise 4: Queue and Priority Processing");
        System.out.println("=========================================");
        
        // Task 4.1: Create PriorityQueue for natural ordering
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        numbers.add(45);
        numbers.add(12);
        numbers.add(89);
        numbers.add(34);
        numbers.add(67);
        
        System.out.println("4.1 PriorityQueue (min-heap): " + numbers);
        System.out.println("   Peek (smallest): " + numbers.peek());
        
        // Task 4.2: Process elements in priority order
        System.out.print("4.2 Processing in priority order: ");
        while (!numbers.isEmpty()) {
            System.out.print(numbers.poll() + " ");
        }
        System.out.println();
        
        // Task 4.3: Create PriorityQueue with custom comparator (max-heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(Arrays.asList(45, 12, 89, 34, 67));
        System.out.println("4.3 Max-heap: " + maxHeap);
        System.out.println("   Peek (largest): " + maxHeap.peek());
        
        // Task 4.4: Create custom objects with priority
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.add(new Task("Send email", 3));
        taskQueue.add(new Task("Process data", 1));
        taskQueue.add(new Task("Generate report", 2));
        taskQueue.add(new Task("Backup database", 5));
        
        System.out.println("4.4 Task queue (by priority):");
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            System.out.println("   Processing: " + task.getName() + " (Priority: " + task.getPriority() + ")");
        }
        
        // Task 4.5: Create ArrayBlockingQueue for bounded operations
        BlockingQueue<String> boundedQueue = new ArrayBlockingQueue<>(3);
        try {
            boundedQueue.put("First");
            boundedQueue.put("Second");
            boundedQueue.put("Third");
            System.out.println("4.5 Bounded queue: " + boundedQueue);
            
            // Try to add more (will block)
            boolean added = boundedQueue.offer("Fourth", 1, TimeUnit.SECONDS);
            System.out.println("   Added 'Fourth': " + added);
            
            // Remove elements
            System.out.println("   Removed: " + boundedQueue.take());
            System.out.println("   Remaining: " + boundedQueue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    /**
     * Exercise 5: Advanced Stream Operations
     * Demonstrate modern Java stream processing
     */
    public static void exercise5_StreamOperations() {
        System.out.println("Exercise 5: Advanced Stream Operations");
        System.out.println("======================================");
        
        // Create sample data
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, "Computer Science", 3.8),
            new Student("Bob", 22, "Mathematics", 3.5),
            new Student("Charlie", 21, "Computer Science", 3.9),
            new Student("David", 23, "Physics", 3.2),
            new Student("Eve", 20, "Computer Science", 3.7),
            new Student("Frank", 24, "Mathematics", 3.6)
        );
        
        // Task 5.1: Filter and map operations
        List<String> csStudents = students.stream()
            .filter(s -> s.getMajor().equals("Computer Science"))
            .map(Student::getName)
            .collect(Collectors.toList());
        System.out.println("5.1 Computer Science students: " + csStudents);
        
        // Task 5.2: Grouping by major
        Map<String, List<Student>> byMajor = students.stream()
            .collect(Collectors.groupingBy(Student::getMajor));
        System.out.println("5.2 Students by major:");
        byMajor.forEach((major, studentList) -> {
            System.out.println("   " + major + ": " + studentList.size() + " students");
        });
        
        // Task 5.3: Calculate average GPA by major
        Map<String, Double> avgGpaByMajor = students.stream()
            .collect(Collectors.groupingBy(Student::getMajor, 
                Collectors.averagingDouble(Student::getGpa)));
        System.out.println("5.3 Average GPA by major: " + avgGpaByMajor);
        
        // Task 5.4: Find top 3 students by GPA
        List<Student> topStudents = students.stream()
            .sorted(Comparator.comparing(Student::getGpa).reversed())
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("5.4 Top 3 students by GPA:");
        topStudents.forEach(s -> System.out.println("   " + s.getName() + " (" + s.getGpa() + ")"));
        
        // Task 5.5: Reduce operation - find student with highest GPA
        Student bestStudent = students.stream()
            .reduce((s1, s2) -> s1.getGpa() > s2.getGpa() ? s1 : s2)
            .orElse(null);
        System.out.println("5.5 Best student: " + bestStudent.getName() + " (GPA: " + bestStudent.getGpa() + ")");
        
        // Task 5.6: Parallel stream processing
        long count = students.parallelStream()
            .filter(s -> s.getAge() > 20)
            .count();
        System.out.println("5.6 Students older than 20: " + count);
        
        System.out.println();
    }
    
    /**
     * Exercise 6: Concurrent Collections
     * Work with thread-safe collections
     */
    public static void exercise6_ConcurrentCollections() {
        System.out.println("Exercise 6: Concurrent Collections");
        System.out.println("==================================");
        
        // Task 6.1: ConcurrentHashMap operations
        ConcurrentHashMap<String, Integer> concurrentScores = new ConcurrentHashMap<>();
        concurrentScores.put("Alice", 95);
        concurrentScores.put("Bob", 87);
        concurrentScores.put("Charlie", 92);
        
        System.out.println("6.1 ConcurrentHashMap: " + concurrentScores);
        
        // Atomic operations
        concurrentScores.computeIfPresent("Alice", (k, v) -> v + 5);
        concurrentScores.computeIfAbsent("David", k -> 85);
        System.out.println("6.1 After atomic operations: " + concurrentScores);
        
        // Task 6.2: CopyOnWriteArrayList for read-heavy workloads
        CopyOnWriteArrayList<String> threadSafeList = new CopyOnWriteArrayList<>();
        threadSafeList.add("Item1");
        threadSafeList.add("Item2");
        threadSafeList.add("Item3");
        
        System.out.println("6.2 CopyOnWriteArrayList: " + threadSafeList);
        
        // Task 6.3: ConcurrentSkipListMap for sorted concurrent map
        ConcurrentSkipListMap<String, Integer> sortedConcurrentMap = new ConcurrentSkipListMap<>();
        sortedConcurrentMap.put("Zoe", 91);
        sortedConcurrentMap.put("Alice", 98);
        sortedConcurrentMap.put("Bob", 87);
        
        System.out.println("6.3 ConcurrentSkipListMap (sorted): " + sortedConcurrentMap);
        System.out.println("   First entry: " + sortedConcurrentMap.firstEntry());
        System.out.println("   Last entry: " + sortedConcurrentMap.lastEntry());
        
        // Task 6.4: DelayQueue for time-based processing
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        delayQueue.put(new DelayedTask("Task1", now + 1000));
        delayQueue.put(new DelayedTask("Task2", now + 500));
        delayQueue.put(new DelayedTask("Task3", now + 2000));
        
        System.out.println("6.4 DelayQueue size: " + delayQueue.size());
        
        // Process delayed tasks
        try {
            System.out.print("6.4 Processing delayed tasks: ");
            for (int i = 0; i < 3; i++) {
                DelayedTask task = delayQueue.take();
                System.out.print(task.getName() + " ");
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    /**
     * Exercise 7: Specialized Collections
     * Work with specialized collection types
     */
    public static void exercise7_SpecializedCollections() {
        System.out.println("Exercise 7: Specialized Collections");
        System.out.println("===================================");
        
        // Task 7.1: EnumMap for enum-based keys
        enum DayOfWeek { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
        
        EnumMap<DayOfWeek, String> schedule = new EnumMap<>(DayOfWeek.class);
        schedule.put(DayOfWeek.MONDAY, "Work");
        schedule.put(DayOfWeek.TUESDAY, "Study");
        schedule.put(DayOfWeek.WEDNESDAY, "Exercise");
        schedule.put(DayOfWeek.THURSDAY, "Work");
        schedule.put(DayOfWeek.FRIDAY, "Leisure");
        
        System.out.println("7.1 EnumMap schedule: " + schedule);
        
        // Task 7.2: IdentityHashMap for reference-based comparison
        IdentityHashMap<String, Integer> identityMap = new IdentityHashMap<>();
        String key1 = new String("Java");
        String key2 = new String("Java");
        String key3 = "Java"; // String literal
        
        identityMap.put(key1, 1);
        identityMap.put(key2, 2);
        identityMap.put(key3, 3);
        
        System.out.println("7.2 IdentityHashMap: " + identityMap);
        System.out.println("   key1 == key2: " + (key1 == key2));
        System.out.println("   key1 == key3: " + (key1 == key3));
        
        // Task 7.3: Properties for configuration
        Properties config = new Properties();
        config.setProperty("database.url", "localhost:3306");
        config.setProperty("database.user", "admin");
        config.setProperty("app.port", "8080");
        config.setProperty("app.debug", "true");
        
        System.out.println("7.3 Configuration properties:");
        config.forEach((key, value) -> System.out.println("   " + key + " = " + value));
        
        // Task 7.4: WeakHashMap for garbage collection friendly maps
        WeakHashMap<Object, String> weakMap = new WeakHashMap<>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        
        weakMap.put(obj1, "Reference1");
        weakMap.put(obj2, "Reference2");
        
        System.out.println("7.4 WeakHashMap size: " + weakMap.size());
        
        // Remove reference to obj1
        obj1 = null;
        System.gc(); // Suggest garbage collection
        
        System.out.println("7.4 After GC, WeakHashMap size: " + weakMap.size());
        
        System.out.println();
    }
    
    /**
     * Exercise 8: Real-world Application
     * Build a complete application using collections
     */
    public static void exercise8_RealWorldApplication() {
        System.out.println("Exercise 8: Real-world Application");
        System.out.println("==================================");
        
        // Simulate a simple e-commerce system
        System.out.println("Building an E-commerce System with Collections...");
        
        // Product catalog (HashMap)
        Map<String, Product> catalog = new HashMap<>();
        catalog.put("P001", new Product("Laptop", 999.99, 10));
        catalog.put("P002", new Product("Mouse", 29.99, 50));
        catalog.put("P003", new Product("Keyboard", 89.99, 25));
        catalog.put("P004", new Product("Monitor", 299.99, 15));
        
        // Shopping cart (LinkedHashMap to maintain order)
        Map<String, Integer> cart = new LinkedHashMap<>();
        cart.put("P001", 1);
        cart.put("P002", 2);
        cart.put("P003", 1);
        
        // Order queue (PriorityBlockingQueue for processing)
        BlockingQueue<Order> orderQueue = new PriorityBlockingQueue<>();
        
        // Customer database (ConcurrentHashMap for thread safety)
        ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<>();
        customers.put("C001", new Customer("Alice", "alice@email.com"));
        customers.put("C002", new Customer("Bob", "bob@email.com"));
        
        // Process the order
        System.out.println("8.1 Processing order...");
        
        // Calculate total
        double total = cart.entrySet().stream()
            .mapToDouble(entry -> {
                Product product = catalog.get(entry.getKey());
                return product.getPrice() * entry.getValue();
            })
            .sum();
        
        System.out.println("8.2 Order total: $" + String.format("%.2f", total));
        
        // Create order
        Order order = new Order("C001", cart, total, 1);
        orderQueue.offer(order);
        
        // Process orders by priority
        System.out.println("8.3 Processing orders by priority:");
        while (!orderQueue.isEmpty()) {
            try {
                Order processedOrder = orderQueue.take();
                System.out.println("   Processing order for customer: " + processedOrder.getCustomerId());
                System.out.println("   Total: $" + String.format("%.2f", processedOrder.getTotal()));
                System.out.println("   Priority: " + processedOrder.getPriority());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        // Generate sales report using streams
        System.out.println("8.4 Sales Report:");
        Map<String, Double> salesByProduct = cart.entrySet().stream()
            .collect(Collectors.groupingBy(
                entry -> catalog.get(entry.getKey()).getName(),
                Collectors.summingDouble(entry -> 
                    catalog.get(entry.getKey()).getPrice() * entry.getValue())
            ));
        
        salesByProduct.forEach((product, sales) -> 
            System.out.println("   " + product + ": $" + String.format("%.2f", sales)));
        
        System.out.println();
    }
    
    // Helper classes for exercises
    static class Task implements Comparable<Task> {
        private String name;
        private int priority;
        
        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        public String getName() { return name; }
        public int getPriority() { return priority; }
        
        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
    }
    
    static class Student {
        private String name;
        private int age;
        private String major;
        private double gpa;
        
        public Student(String name, int age, String major, double gpa) {
            this.name = name;
            this.age = age;
            this.major = major;
            this.gpa = gpa;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getMajor() { return major; }
        public double getGpa() { return gpa; }
        
        @Override
        public String toString() {
            return name + "(" + major + ", GPA: " + gpa + ")";
        }
    }
    
    static class DelayedTask implements Delayed {
        private String name;
        private long executionTime;
        
        public DelayedTask(String name, long executionTime) {
            this.name = name;
            this.executionTime = executionTime;
        }
        
        public String getName() { return name; }
        
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = executionTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }
        
        @Override
        public int compareTo(Delayed other) {
            return Long.compare(this.executionTime, ((DelayedTask) other).executionTime);
        }
    }
    
    static class Product {
        private String name;
        private double price;
        private int stock;
        
        public Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
        
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }
    }
    
    static class Customer {
        private String name;
        private String email;
        
        public Customer(String name, String email) {
            this.name = name;
            this.email = email;
        }
        
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
    
    static class Order implements Comparable<Order> {
        private String customerId;
        private Map<String, Integer> items;
        private double total;
        private int priority;
        
        public Order(String customerId, Map<String, Integer> items, double total, int priority) {
            this.customerId = customerId;
            this.items = new HashMap<>(items);
            this.total = total;
            this.priority = priority;
        }
        
        public String getCustomerId() { return customerId; }
        public Map<String, Integer> getItems() { return items; }
        public double getTotal() { return total; }
        public int getPriority() { return priority; }
        
        @Override
        public int compareTo(Order other) {
            return Integer.compare(this.priority, other.priority);
        }
    }
}
