package collection;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Collection Practice Exercises - Progressive difficulty exercises
 * Inspired by the original JavaStud project patterns
 */
public class CollectionPracticeExercises {
    
    public static void main(String[] args) {
        System.out.println("=== Collection Practice Exercises ===\n");
        
        // Beginner Level Exercises
        beginnerExercises();
        
        // Intermediate Level Exercises
        intermediateExercises();
        
        // Advanced Level Exercises
        advancedExercises();
        
        // Expert Level Exercises
        expertExercises();
        
        System.out.println("\n=== All Practice Exercises Completed! ===");
    }
    
    /**
     * Beginner Level Exercises (Level 1-2)
     */
    public static void beginnerExercises() {
        System.out.println("BEGINNER LEVEL EXERCISES");
        System.out.println("========================");
        
        // Exercise 1: Basic List Operations
        System.out.println("Exercise 1: Basic List Operations");
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        
        System.out.println("1.1 Original list: " + fruits);
        fruits.add(1, "Mango");
        System.out.println("1.2 After adding 'Mango' at index 1: " + fruits);
        fruits.remove("Banana");
        System.out.println("1.3 After removing 'Banana': " + fruits);
        System.out.println("1.4 Size of list: " + fruits.size());
        System.out.println("1.5 Contains 'Apple': " + fruits.contains("Apple"));
        
        // Exercise 2: Set Operations
        System.out.println("\nExercise 2: Set Operations");
        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(2); // Duplicate
        numbers.add(4);
        
        System.out.println("2.1 Set contents (duplicates removed): " + numbers);
        System.out.println("2.2 Set size: " + numbers.size());
        System.out.println("2.3 Contains 3: " + numbers.contains(3));
        
        // Exercise 3: Map Operations
        System.out.println("\nExercise 3: Map Operations");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        
        System.out.println("3.1 Map contents: " + scores);
        System.out.println("3.2 Alice's score: " + scores.get("Alice"));
        scores.put("Alice", 98); // Update
        System.out.println("3.3 After updating Alice's score: " + scores);
        System.out.println("3.4 All keys: " + scores.keySet());
        System.out.println("3.5 All values: " + scores.values());
        
        System.out.println();
    }
    
    /**
     * Intermediate Level Exercises (Level 3-4)
     */
    public static void intermediateExercises() {
        System.out.println("INTERMEDIATE LEVEL EXERCISES");
        System.out.println("============================");
        
        // Exercise 4: Sorting and Comparators
        System.out.println("Exercise 4: Sorting and Comparators");
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8),
            new Student("Bob", 22, 3.5),
            new Student("Charlie", 21, 3.9),
            new Student("David", 23, 3.2)
        );
        
        System.out.println("4.1 Original student list:");
        students.forEach(s -> System.out.println("   " + s));
        
        List<Student> sortedByName = new ArrayList<>(students);
        sortedByName.sort(Comparator.comparing(Student::getName));
        System.out.println("4.2 Sorted by name:");
        sortedByName.forEach(s -> System.out.println("   " + s));
        
        List<Student> sortedByGpa = new ArrayList<>(students);
        sortedByGpa.sort(Comparator.comparing(Student::getGpa).reversed());
        System.out.println("4.3 Sorted by GPA (descending):");
        sortedByGpa.forEach(s -> System.out.println("   " + s));
        
        // Exercise 5: Queue Operations
        System.out.println("\nExercise 5: Queue Operations");
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        
        System.out.println("5.1 Queue contents: " + queue);
        System.out.println("5.2 Peek (first element): " + queue.peek());
        System.out.println("5.3 Poll (remove and return): " + queue.poll());
        System.out.println("5.4 Queue after poll: " + queue);
        
        // Exercise 6: Priority Queue
        System.out.println("\nExercise 6: Priority Queue");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(45);
        priorityQueue.offer(12);
        priorityQueue.offer(89);
        priorityQueue.offer(34);
        
        System.out.println("6.1 Priority queue (min-heap): " + priorityQueue);
        System.out.println("6.2 Processing in priority order:");
        while (!priorityQueue.isEmpty()) {
            System.out.print("   " + priorityQueue.poll() + " ");
        }
        System.out.println();
        
        // Exercise 7: Stream Operations
        System.out.println("\nExercise 7: Stream Operations");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("7.1 Even numbers: " + evenNumbers);
        
        List<Integer> doubled = numbers.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("7.2 Doubled numbers: " + doubled);
        
        int sum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("7.3 Sum of all numbers: " + sum);
        
        System.out.println();
    }
    
    /**
     * Advanced Level Exercises (Level 5-6)
     */
    public static void advancedExercises() {
        System.out.println("ADVANCED LEVEL EXERCISES");
        System.out.println("========================");
        
        // Exercise 8: Concurrent Collections
        System.out.println("Exercise 8: Concurrent Collections");
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);
        concurrentMap.put("C", 3);
        
        System.out.println("8.1 ConcurrentHashMap: " + concurrentMap);
        concurrentMap.computeIfPresent("A", (k, v) -> v + 10);
        System.out.println("8.2 After computeIfPresent on 'A': " + concurrentMap);
        concurrentMap.computeIfAbsent("D", k -> 4);
        System.out.println("8.3 After computeIfAbsent for 'D': " + concurrentMap);
        
        // Exercise 9: CopyOnWriteArrayList
        System.out.println("\nExercise 9: CopyOnWriteArrayList");
        CopyOnWriteArrayList<String> threadSafeList = new CopyOnWriteArrayList<>();
        threadSafeList.add("Item1");
        threadSafeList.add("Item2");
        threadSafeList.add("Item3");
        
        System.out.println("9.1 CopyOnWriteArrayList: " + threadSafeList);
        threadSafeList.addIfAbsent("Item2"); // Won't add duplicate
        System.out.println("9.2 After addIfAbsent 'Item2': " + threadSafeList);
        
        // Exercise 10: BlockingQueue
        System.out.println("\nExercise 10: BlockingQueue");
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        
        try {
            blockingQueue.put("First");
            blockingQueue.put("Second");
            blockingQueue.put("Third");
            System.out.println("10.1 BlockingQueue: " + blockingQueue);
            
            String removed = blockingQueue.take();
            System.out.println("10.2 Removed: " + removed);
            System.out.println("10.3 Queue after removal: " + blockingQueue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Exercise 11: Advanced Stream Operations
        System.out.println("\nExercise 11: Advanced Stream Operations");
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 85000),
            new Employee("Bob", "Marketing", 55000),
            new Employee("Charlie", "Engineering", 75000),
            new Employee("David", "Sales", 90000),
            new Employee("Eve", "Engineering", 60000)
        );
        
        // Group by department
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("11.1 Employees by department:");
        byDepartment.forEach((dept, empList) -> {
            System.out.println("   " + dept + ": " + empList.size() + " employees");
        });
        
        // Average salary by department
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("11.2 Average salary by department:");
        avgSalaryByDept.forEach((dept, avgSalary) -> {
            System.out.printf("   %s: $%.2f%n", dept, avgSalary);
        });
        
        // Find highest paid employee
        Employee highestPaid = employees.stream()
            .max(Comparator.comparing(Employee::getSalary))
            .orElse(null);
        System.out.println("11.3 Highest paid employee: " + highestPaid.getName() + 
                          " ($" + highestPaid.getSalary() + ")");
        
        System.out.println();
    }
    
    /**
     * Expert Level Exercises (Level 7-8)
     */
    public static void expertExercises() {
        System.out.println("EXPERT LEVEL EXERCISES");
        System.out.println("======================");
        
        // Exercise 12: Custom Collection Implementation
        System.out.println("Exercise 12: Custom Collection Implementation");
        CircularBuffer<String> buffer = new CircularBuffer<>(5);
        for (int i = 0; i < 7; i++) {
            buffer.add("Item" + i);
        }
        
        System.out.println("12.1 Circular buffer contents:");
        for (int i = 0; i < 5; i++) {
            System.out.println("   " + buffer.get(i));
        }
        
        // Exercise 13: Multi-threaded Collection Operations
        System.out.println("\nExercise 13: Multi-threaded Collection Operations");
        ConcurrentHashMap<String, AtomicInteger> counters = new ConcurrentHashMap<>();
        
        // Simulate concurrent increments
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int threadId = i;
            executor.submit(() -> {
                String key = "Counter" + (threadId % 3);
                counters.computeIfAbsent(key, k -> new AtomicInteger(0)).incrementAndGet();
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("13.1 Concurrent counters: " + counters);
        
        // Exercise 14: Complex Data Processing
        System.out.println("\nExercise 14: Complex Data Processing");
        List<Order> orders = Arrays.asList(
            new Order("C001", Arrays.asList("Laptop", "Mouse"), 1200.0),
            new Order("C002", Arrays.asList("Keyboard"), 89.99),
            new Order("C001", Arrays.asList("Monitor"), 299.99),
            new Order("C003", Arrays.asList("Headphones", "Mouse", "Keyboard"), 250.0)
        );
        
        // Group orders by customer and calculate totals
        Map<String, List<Order>> ordersByCustomer = orders.stream()
            .collect(Collectors.groupingBy(Order::getCustomerId));
        
        System.out.println("14.1 Orders by customer:");
        ordersByCustomer.forEach((customerId, customerOrders) -> {
            double total = customerOrders.stream()
                .mapToDouble(Order::getTotal)
                .sum();
            System.out.println("   Customer " + customerId + ": " + 
                             customerOrders.size() + " orders, Total: $" + 
                             String.format("%.2f", total));
        });
        
        // Find most popular items
        Map<String, Long> itemFrequency = orders.stream()
            .flatMap(order -> order.getItems().stream())
            .collect(Collectors.groupingBy(item -> item, Collectors.counting()));
        
        System.out.println("14.2 Item frequency:");
        itemFrequency.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> {
                System.out.println("   " + entry.getKey() + ": " + entry.getValue() + " times");
            });
        
        // Exercise 15: Memory Management with Collections
        System.out.println("\nExercise 15: Memory Management with Collections");
        WeakHashMap<Object, String> weakMap = new WeakHashMap<>();
        Object key1 = new Object();
        Object key2 = new Object();
        
        weakMap.put(key1, "Data1");
        weakMap.put(key2, "Data2");
        
        System.out.println("15.1 WeakHashMap size before GC: " + weakMap.size());
        
        // Remove reference to key1
        key1 = null;
        System.gc(); // Suggest garbage collection
        
        System.out.println("15.2 WeakHashMap size after GC: " + weakMap.size());
        
        // Exercise 16: Performance Comparison
        System.out.println("\nExercise 16: Performance Comparison");
        int size = 10000;
        
        // ArrayList vs LinkedList performance
        List<Integer> arrayList = new ArrayList<>(size);
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
        
        System.out.println("16.1 Performance comparison (adding " + size + " elements):");
        System.out.println("   ArrayList: " + arrayListTime / 1000000 + " ms");
        System.out.println("   LinkedList: " + linkedListTime / 1000000 + " ms");
        
        // HashMap vs TreeMap performance
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashMap.put("key" + i, i);
        }
        long hashMapTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            treeMap.put("key" + i, i);
        }
        long treeMapTime = System.nanoTime() - startTime;
        
        System.out.println("16.2 Map performance comparison:");
        System.out.println("   HashMap: " + hashMapTime / 1000000 + " ms");
        System.out.println("   TreeMap: " + treeMapTime / 1000000 + " ms");
        
        System.out.println();
    }
    
    // Helper classes
    static class Student {
        private String name;
        private int age;
        private double gpa;
        
        public Student(String name, int age, double gpa) {
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public double getGpa() { return gpa; }
        
        @Override
        public String toString() {
            return name + " (Age: " + age + ", GPA: " + gpa + ")";
        }
    }
    
    static class Employee {
        private String name;
        private String department;
        private double salary;
        
        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
        
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }
    
    static class Order {
        private String customerId;
        private List<String> items;
        private double total;
        
        public Order(String customerId, List<String> items, double total) {
            this.customerId = customerId;
            this.items = items;
            this.total = total;
        }
        
        public String getCustomerId() { return customerId; }
        public List<String> getItems() { return items; }
        public double getTotal() { return total; }
    }
    
    static class CircularBuffer<T> {
        private final T[] buffer;
        private int head, tail, size;
        
        @SuppressWarnings("unchecked")
        public CircularBuffer(int capacity) {
            this.buffer = (T[]) new Object[capacity];
            this.head = 0;
            this.tail = 0;
            this.size = 0;
        }
        
        public void add(T item) {
            buffer[tail] = item;
            tail = (tail + 1) % buffer.length;
            if (size < buffer.length) {
                size++;
            } else {
                head = (head + 1) % buffer.length;
            }
        }
        
        public T get(int index) {
            if (index >= size) return null;
            return buffer[(head + index) % buffer.length];
        }
    }
}
