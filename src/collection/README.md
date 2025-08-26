# Java Collections Framework Examples

This directory contains comprehensive examples of Java Collections Framework, covering basic collections, advanced features, concurrent collections, and specialized collections.

## 📚 Collection Categories

### 1. Basic Collections

#### List Collections
- **`ArrayListExample.java`** - Dynamic array implementation
  - Fast random access
  - Good for frequent access, poor for frequent insertion/deletion
  - O(1) for get/set, O(n) for add/remove at beginning

- **`LinkedListExample.java`** - Doubly-linked list implementation
  - Fast insertion/deletion at both ends
  - Good for frequent insertion/deletion, poor for random access
  - O(1) for add/remove at ends, O(n) for random access

- **`VectorExample.java`** - Thread-safe dynamic array (legacy)
  - Synchronized version of ArrayList
  - Use ArrayList with external synchronization instead

#### Set Collections
- **`HashSetExample.java`** - Hash table implementation
  - No order guarantee
  - O(1) average case for add/remove/contains
  - Good for unique elements with no ordering requirements

- **`LinkedHashSetExample.java`** - Hash table + linked list
  - Maintains insertion order
  - Slightly slower than HashSet due to linked list overhead
  - Good when you need both uniqueness and insertion order

- **`TreeSetExample.java`** - Red-black tree implementation
  - Maintains natural ordering
  - O(log n) for add/remove/contains
  - Good when you need sorted unique elements

#### Map Collections
- **`HashMapExample.java`** - Hash table implementation
  - No order guarantee
  - O(1) average case for get/put/remove
  - Most commonly used map implementation

- **`LinkedHashMapExample.java`** - Hash table + linked list
  - Maintains insertion order or access order
  - Good for LRU cache implementation
  - Slightly slower than HashMap

- **`TreeMapExample.java`** - Red-black tree implementation
  - Maintains natural ordering of keys
  - O(log n) for get/put/remove
  - Good when you need sorted key-value pairs

### 2. Advanced Collections

#### Queue Collections
- **`PriorityQueueExample.java`** - Priority heap implementation
  - Orders elements by natural ordering or custom comparator
  - O(log n) for add/remove, O(1) for peek
  - Good for priority-based processing

#### Advanced Features
- **`AdvancedCollectionsExample.java`** - Comprehensive example covering:
  - Custom comparators with lambda expressions
  - Streams API with collections
  - Functional programming patterns
  - Collection utilities
  - Parallel streams
  - Optional with collections

### 3. Concurrent Collections

- **`ConcurrentCollectionsExample.java`** - Thread-safe collections:
  - **ConcurrentHashMap** - Thread-safe HashMap
  - **CopyOnWriteArrayList** - Thread-safe ArrayList for read-heavy workloads
  - **BlockingQueue** implementations (ArrayBlockingQueue, PriorityBlockingQueue)
  - Atomic operations
  - Thread safety comparison

### 4. Specialized Collections

- **`SpecializedCollectionsExample.java`** - Specialized use cases:
  - **WeakHashMap** - Allows garbage collection of keys
  - **EnumMap** - Optimized for enum keys
  - **IdentityHashMap** - Uses == instead of equals() for key comparison
  - **Properties** - Configuration management
  - Weak references

- **`EnumMapExample.java`** - Enum-based Map implementation:
  - Optimized for enum keys with array-based storage
  - Excellent performance for enum key lookups
  - Memory-efficient for enum-based mappings
  - Perfect for state machines and configuration

- **`IdentityHashMapExample.java`** - Reference-based Map:
  - Uses == instead of equals() for key comparison
  - Useful for object identity tracking
  - Good for caching and object lifecycle management
  - Demonstrates reference equality vs content equality

- **`CopyOnWriteArrayListExample.java`** - Thread-safe List:
  - Optimized for read-heavy workloads
  - Creates new copy on modifications
  - No external synchronization needed
  - Perfect for event listeners and configuration lists

- **`BlockingQueueExample.java`** - Thread-safe Queue:
  - Producer-consumer pattern implementation
  - Blocking operations for thread coordination
  - Multiple implementations (ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue)
  - Ideal for work queues and task processing

- **`PropertiesExample.java`** - Configuration Management:
  - String-based key-value pairs
  - File I/O operations (properties and XML)
  - System properties integration
  - Hierarchical configuration with defaults

- **`ConcurrentSkipListMapExample.java`** - Thread-safe Sorted Map:
  - Concurrent sorted map implementation
  - Navigation methods (first, last, higher, lower)
  - Submap operations for range queries
  - Perfect for ordered concurrent data structures

- **`DelayQueueExample.java`** - Delayed Processing Queue:
  - Queue with expiration-based ordering
  - TTL (Time To Live) functionality
  - Scheduled task execution
  - Cache expiration management

## 🚀 When to Use Each Collection

### Choose List Implementation:
- **ArrayList**: When you need fast random access and don't frequently insert/delete at the beginning
- **LinkedList**: When you frequently insert/delete at the beginning or end, or need queue/stack operations

### Choose Set Implementation:
- **HashSet**: When you need unique elements with no ordering requirements
- **LinkedHashSet**: When you need unique elements with insertion order
- **TreeSet**: When you need unique elements in sorted order

### Choose Map Implementation:
- **HashMap**: When you need key-value pairs with no ordering requirements
- **LinkedHashMap**: When you need key-value pairs with insertion/access order
- **TreeMap**: When you need key-value pairs in sorted order

### Choose Queue Implementation:
- **PriorityQueue**: When you need priority-based processing
- **LinkedList**: When you need a simple queue or stack

### Choose Concurrent Implementation:
- **ConcurrentHashMap**: When you need thread-safe map operations
- **CopyOnWriteArrayList**: When you have read-heavy workloads with occasional writes
- **BlockingQueue**: When you need producer-consumer patterns

### Choose Specialized Implementation:
- **WeakHashMap**: When you want keys to be garbage collected when no longer referenced
- **EnumMap**: When you have enum keys (much faster than HashMap)
- **IdentityHashMap**: When you need identity-based key comparison
- **Properties**: When you need configuration management
- **ConcurrentSkipListMap**: When you need a thread-safe sorted map with navigation methods
- **DelayQueue**: When you need to process items after specific delays or TTL functionality

## 📊 Performance Characteristics

| Collection | Get | Add | Remove | Contains | Order |
|------------|-----|-----|--------|----------|-------|
| ArrayList | O(1) | O(1) | O(n) | O(n) | Insertion |
| LinkedList | O(n) | O(1) | O(1) | O(n) | Insertion |
| HashSet | O(1) | O(1) | O(1) | O(1) | None |
| LinkedHashSet | O(1) | O(1) | O(1) | O(1) | Insertion |
| TreeSet | O(log n) | O(log n) | O(log n) | O(log n) | Sorted |
| HashMap | O(1) | O(1) | O(1) | O(1) | None |
| LinkedHashMap | O(1) | O(1) | O(1) | O(1) | Insertion/Access |
| TreeMap | O(log n) | O(log n) | O(log n) | O(log n) | Sorted |
| PriorityQueue | O(1) | O(log n) | O(log n) | O(n) | Priority |
| ConcurrentSkipListMap | O(log n) | O(log n) | O(log n) | O(log n) | Sorted |
| DelayQueue | O(1) | O(log n) | O(log n) | O(n) | Delay |

## 🔧 Running the Examples

To run any example, navigate to the collection directory and compile/run:

```bash
cd JavaStud/src/collection
javac *.java
java collection.ExampleName
```

## 📝 Best Practices

1. **Choose the right collection for your use case** - Don't use ArrayList when you need frequent insertions at the beginning
2. **Use generics** - Always specify the type parameter to avoid runtime errors
3. **Consider thread safety** - Use concurrent collections when multiple threads access the same collection
4. **Use streams for complex operations** - Streams provide a more readable way to process collections
5. **Prefer interfaces over implementations** - Use `List<String>` instead of `ArrayList<String>` in method signatures
6. **Initialize with capacity** - For large collections, specify initial capacity to avoid resizing
7. **Use appropriate comparators** - Custom comparators can make your code more readable and efficient

## 🎯 Common Patterns

### Sorting Collections
```java
// Natural ordering
list.sort(null);

// Custom comparator
list.sort(Comparator.comparing(Person::getAge).reversed());

// Multi-field sorting
list.sort(Comparator.comparing(Person::getDepartment)
    .thenComparing(Person::getSalary, Comparator.reverseOrder()));
```

### Stream Operations
```java
// Filter and map
List<String> names = people.stream()
    .filter(p -> p.getAge() > 18)
    .map(Person::getName)
    .collect(Collectors.toList());

// Grouping
Map<String, List<Person>> byDept = people.stream()
    .collect(Collectors.groupingBy(Person::getDepartment));
```

### Thread-Safe Operations
```java
// Use concurrent collections
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

// Or synchronize regular collections
Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
```

This comprehensive collection of examples should help you understand when and how to use each collection type effectively in your Java applications.
