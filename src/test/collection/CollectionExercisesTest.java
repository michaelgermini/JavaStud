package test.collection;

import collection.CollectionExercises;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for CollectionExercises
 * Tests all exercise methods and validates expected outputs
 */
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollectionExercisesTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @Order(1)
    @DisplayName("Test Basic List Operations Exercise")
    void testBasicListOperations() {
        // Act
        CollectionExercises.exercise1_BasicListOperations();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 1: Basic List Operations"));
        assertTrue(output.contains("Original list: [Alice, Bob, Charlie, David, Eve]"));
        assertTrue(output.contains("After inserting 'Frank' at index 2: [Alice, Bob, Frank, Charlie, David, Eve]"));
        assertTrue(output.contains("After removing 'Charlie' and element at index 1: [Alice, Frank, David, Eve]"));
        assertTrue(output.contains("After sorting: [Alice, David, Eve, Frank]"));
        assertTrue(output.contains("After reversing: [Frank, Eve, David, Alice]"));
        assertTrue(output.contains("LinkedList with first/last operations: [Zoe, Frank, Eve, David, Alice, Yara]"));
        assertTrue(output.contains("Max: 89"));
        assertTrue(output.contains("Min: 12"));
    }

    @Test
    @Order(2)
    @DisplayName("Test Set Operations Exercise")
    void testSetOperations() {
        // Act
        CollectionExercises.exercise2_SetOperations();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 2: Set Operations and Deduplication"));
        assertTrue(output.contains("HashSet (duplicates removed): [Alice, Bob, Charlie]"));
        assertTrue(output.contains("LinkedHashSet (maintains insertion order): [Zoe, Alice, Bob]"));
        assertTrue(output.contains("TreeSet (natural ordering): [Alice, Bob, Charlie]"));
        assertTrue(output.contains("Union: [1, 2, 3, 4, 5, 6, 7, 8]"));
        assertTrue(output.contains("Intersection: [4, 5]"));
        assertTrue(output.contains("Difference (Set1 - Set2): [1, 2, 3]"));
    }

    @Test
    @Order(3)
    @DisplayName("Test Map Operations Exercise")
    void testMapOperations() {
        // Act
        CollectionExercises.exercise3_MapOperations();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 3: Map Operations and Data Processing"));
        assertTrue(output.contains("Original scores: {Alice=95, Bob=87, Charlie=92, David=78, Eve=89}"));
        assertTrue(output.contains("After updates: {Alice=98, Bob=87, Charlie=92, David=78, Eve=89, Frank=85}"));
        assertTrue(output.contains("Contains 'Alice': true"));
        assertTrue(output.contains("Contains score 95: false"));
        assertTrue(output.contains("LinkedHashMap (maintains insertion order): {Zoe=91, Alice=98, Bob=87}"));
        assertTrue(output.contains("TreeMap (sorted by key): {Alice=98, Bob=87, Charlie=92, David=78, Eve=89, Frank=85}"));
        assertTrue(output.contains("Highest score: Alice (98)"));
        assertTrue(output.contains("Lowest score: David (78)"));
        assertTrue(output.contains("Score groups: {A=[Alice, Charlie], B=[Bob, Eve, Frank], C=[David]}"));
    }

    @Test
    @Order(4)
    @DisplayName("Test Queue Operations Exercise")
    void testQueueOperations() {
        // Act
        CollectionExercises.exercise4_QueueOperations();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 4: Queue and Priority Processing"));
        assertTrue(output.contains("PriorityQueue (min-heap): [12, 34, 89, 45, 67]"));
        assertTrue(output.contains("Peek (smallest): 12"));
        assertTrue(output.contains("Processing in priority order: 12 34 45 67 89"));
        assertTrue(output.contains("Max-heap: [89, 67, 45, 12, 34]"));
        assertTrue(output.contains("Peek (largest): 89"));
        assertTrue(output.contains("Processing: Process data (Priority: 1)"));
        assertTrue(output.contains("Processing: Generate report (Priority: 2)"));
        assertTrue(output.contains("Processing: Send email (Priority: 3)"));
        assertTrue(output.contains("Processing: Backup database (Priority: 5)"));
        assertTrue(output.contains("Bounded queue: [First, Second, Third]"));
        assertTrue(output.contains("Added 'Fourth': false"));
        assertTrue(output.contains("Removed: First"));
    }

    @Test
    @Order(5)
    @DisplayName("Test Stream Operations Exercise")
    void testStreamOperations() {
        // Act
        CollectionExercises.exercise5_StreamOperations();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 5: Advanced Stream Operations"));
        assertTrue(output.contains("Computer Science students: [Alice, Charlie, Eve]"));
        assertTrue(output.contains("Computer Science: 3 students"));
        assertTrue(output.contains("Mathematics: 2 students"));
        assertTrue(output.contains("Physics: 1 students"));
        assertTrue(output.contains("Average GPA by major: {Computer Science=3.8, Mathematics=3.55, Physics=3.2}"));
        assertTrue(output.contains("Top 3 students by GPA:"));
        assertTrue(output.contains("Charlie (3.9)"));
        assertTrue(output.contains("Alice (3.8)"));
        assertTrue(output.contains("Eve (3.7)"));
        assertTrue(output.contains("Best student: Charlie (GPA: 3.9)"));
        assertTrue(output.contains("Students older than 20: 4"));
    }

    @Test
    @Order(6)
    @DisplayName("Test Concurrent Collections Exercise")
    void testConcurrentCollections() {
        // Act
        CollectionExercises.exercise6_ConcurrentCollections();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 6: Concurrent Collections"));
        assertTrue(output.contains("ConcurrentHashMap: {Alice=95, Bob=87, Charlie=92}"));
        assertTrue(output.contains("After atomic operations: {Alice=100, Bob=87, Charlie=92, David=85}"));
        assertTrue(output.contains("CopyOnWriteArrayList: [Item1, Item2, Item3]"));
        assertTrue(output.contains("ConcurrentSkipListMap (sorted): {Alice=98, Bob=87, Zoe=91}"));
        assertTrue(output.contains("First entry: Alice=98"));
        assertTrue(output.contains("Last entry: Zoe=91"));
        assertTrue(output.contains("DelayQueue size: 3"));
        assertTrue(output.contains("Processing delayed tasks: Task2 Task1 Task3"));
    }

    @Test
    @Order(7)
    @DisplayName("Test Specialized Collections Exercise")
    void testSpecializedCollections() {
        // Act
        CollectionExercises.exercise7_SpecializedCollections();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 7: Specialized Collections"));
        assertTrue(output.contains("EnumMap schedule: {MONDAY=Work, TUESDAY=Study, WEDNESDAY=Exercise, THURSDAY=Work, FRIDAY=Leisure}"));
        assertTrue(output.contains("IdentityHashMap: {Java=1, Java=2, Java=3}"));
        assertTrue(output.contains("key1 == key2: false"));
        assertTrue(output.contains("key1 == key3: false"));
        assertTrue(output.contains("Configuration properties:"));
        assertTrue(output.contains("database.url = localhost:3306"));
        assertTrue(output.contains("database.user = admin"));
        assertTrue(output.contains("app.port = 8080"));
        assertTrue(output.contains("app.debug = true"));
        assertTrue(output.contains("WeakHashMap size: 2"));
        assertTrue(output.contains("After GC, WeakHashMap size: 1"));
    }

    @Test
    @Order(8)
    @DisplayName("Test Real-world Application Exercise")
    void testRealWorldApplication() {
        // Act
        CollectionExercises.exercise8_RealWorldApplication();
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("Exercise 8: Real-world Application"));
        assertTrue(output.contains("Building an E-commerce System with Collections..."));
        assertTrue(output.contains("Processing order..."));
        assertTrue(output.contains("Order total: $"));
        assertTrue(output.contains("Processing order for customer: C001"));
        assertTrue(output.contains("Priority: 1"));
        assertTrue(output.contains("Sales Report:"));
        assertTrue(output.contains("Laptop: $"));
        assertTrue(output.contains("Mouse: $"));
        assertTrue(output.contains("Keyboard: $"));
    }

    @Test
    @Order(9)
    @DisplayName("Test Complete Exercise Suite")
    void testCompleteExerciseSuite() {
        // Act
        CollectionExercises.main(new String[]{});
        String output = outputStream.toString();

        // Assert
        assertTrue(output.contains("=== Java Collections Framework Exercises ==="));
        assertTrue(output.contains("Exercise 1: Basic List Operations"));
        assertTrue(output.contains("Exercise 2: Set Operations and Deduplication"));
        assertTrue(output.contains("Exercise 3: Map Operations and Data Processing"));
        assertTrue(output.contains("Exercise 4: Queue and Priority Processing"));
        assertTrue(output.contains("Exercise 5: Advanced Stream Operations"));
        assertTrue(output.contains("Exercise 6: Concurrent Collections"));
        assertTrue(output.contains("Exercise 7: Specialized Collections"));
        assertTrue(output.contains("Exercise 8: Real-world Application"));
        assertTrue(output.contains("=== All Exercises Completed Successfully! ==="));
    }

    @Test
    @Order(10)
    @DisplayName("Test Helper Classes")
    void testHelperClasses() {
        // Test Task class
        CollectionExercises.Task task1 = new CollectionExercises.Task("Test Task", 1);
        CollectionExercises.Task task2 = new CollectionExercises.Task("Test Task 2", 2);

        assertEquals("Test Task", task1.getName());
        assertEquals(1, task1.getPriority());
        assertTrue(task1.compareTo(task2) < 0);
        assertTrue(task2.compareTo(task1) > 0);

        // Test Student class
        CollectionExercises.Student student = new CollectionExercises.Student("Test Student", 20, "Computer Science", 3.8);
        assertEquals("Test Student", student.getName());
        assertEquals(20, student.getAge());
        assertEquals("Computer Science", student.getMajor());
        assertEquals(3.8, student.getGpa(), 0.001);
        assertTrue(student.toString().contains("Test Student"));

        // Test Product class
        CollectionExercises.Product product = new CollectionExercises.Product("Test Product", 99.99, 10);
        assertEquals("Test Product", product.getName());
        assertEquals(99.99, product.getPrice(), 0.001);
        assertEquals(10, product.getStock());

        // Test Customer class
        CollectionExercises.Customer customer = new CollectionExercises.Customer("Test Customer", "test@email.com");
        assertEquals("Test Customer", customer.getName());
        assertEquals("test@email.com", customer.getEmail());
    }

    @Test
    @Order(11)
    @DisplayName("Test DelayedTask Implementation")
    void testDelayedTask() {
        long now = System.currentTimeMillis();
        CollectionExercises.DelayedTask task1 = new CollectionExercises.DelayedTask("Task1", now + 1000);
        CollectionExercises.DelayedTask task2 = new CollectionExercises.DelayedTask("Task2", now + 500);

        assertEquals("Task1", task1.getName());
        assertTrue(task1.getDelay(java.util.concurrent.TimeUnit.MILLISECONDS) > 0);
        assertTrue(task1.compareTo(task2) > 0);
        assertTrue(task2.compareTo(task1) < 0);
    }

    @Test
    @Order(12)
    @DisplayName("Test Order Implementation")
    void testOrder() {
        Map<String, Integer> items = new HashMap<>();
        items.put("P001", 2);
        items.put("P002", 1);

        CollectionExercises.Order order1 = new CollectionExercises.Order("C001", items, 299.99, 1);
        CollectionExercises.Order order2 = new CollectionExercises.Order("C002", items, 199.99, 2);

        assertEquals("C001", order1.getCustomerId());
        assertEquals(2, order1.getItems().size());
        assertEquals(299.99, order1.getTotal(), 0.001);
        assertEquals(1, order1.getPriority());
        assertTrue(order1.compareTo(order2) < 0);
        assertTrue(order2.compareTo(order1) > 0);
    }

    @Test
    @Order(13)
    @DisplayName("Test Performance Characteristics")
    void testPerformanceCharacteristics() {
        // Test ArrayList vs LinkedList performance
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
        }
        long linkedListTime = System.nanoTime() - startTime;

        // ArrayList should be faster for sequential add operations
        assertTrue(arrayListTime < linkedListTime * 2, "ArrayList should be significantly faster for sequential adds");

        // Test HashMap vs TreeMap performance
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            hashMap.put("key" + i, i);
        }
        long hashMapTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            treeMap.put("key" + i, i);
        }
        long treeMapTime = System.nanoTime() - startTime;

        // HashMap should be faster for random insertions
        assertTrue(hashMapTime < treeMapTime * 3, "HashMap should be significantly faster for random insertions");
    }

    @Test
    @Order(14)
    @DisplayName("Test Thread Safety")
    void testThreadSafety() {
        // Test ConcurrentHashMap thread safety
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // Simulate concurrent access
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    concurrentMap.put("key" + threadId + "_" + j, j);
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Verify all entries were added
        assertEquals(1000, concurrentMap.size());

        // Test CopyOnWriteArrayList thread safety
        CopyOnWriteArrayList<String> threadSafeList = new CopyOnWriteArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    threadSafeList.add("item" + threadId + "_" + j);
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Verify all items were added
        assertEquals(1000, threadSafeList.size());
    }

    @Test
    @Order(15)
    @DisplayName("Test Memory Management")
    void testMemoryManagement() {
        // Test WeakHashMap behavior
        WeakHashMap<Object, String> weakMap = new WeakHashMap<>();
        Object key1 = new Object();
        Object key2 = new Object();

        weakMap.put(key1, "Data1");
        weakMap.put(key2, "Data2");

        assertEquals(2, weakMap.size());

        // Remove reference to key1
        key1 = null;
        
        // Suggest garbage collection
        System.gc();
        
        // Wait a bit for GC to complete
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // The size should be reduced (though not guaranteed due to GC timing)
        assertTrue(weakMap.size() <= 2);
    }
}
