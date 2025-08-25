package modernjava;

import java.util.*;
import java.util.stream.*;

/**
 * Modern Java 8+ Streams API Examples
 * 
 * This class demonstrates the powerful Streams API including:
 * - Stream creation and operations
 * - Intermediate operations (filter, map, flatMap, etc.)
 * - Terminal operations (collect, reduce, forEach, etc.)
 * - Parallel streams
 * - Custom collectors
 * 
 * @author JavaStud Team
 * @version 2.0
 * @since Java 8
 */
public class StreamsAPI {
    
    public static void main(String[] args) {
        System.out.println("=== Streams API Examples ===\n");
        
        // 1. Basic Stream Operations
        basicStreamOperations();
        
        // 2. Intermediate Operations
        intermediateOperations();
        
        // 3. Terminal Operations
        terminalOperations();
        
        // 4. Stream Collectors
        streamCollectors();
        
        // 5. Parallel Streams
        parallelStreams();
        
        // 6. Advanced Stream Operations
        advancedStreamOperations();
        
        // 7. Custom Collectors
        customCollectors();
    }
    
    /**
     * Demonstrates basic stream operations
     */
    private static void basicStreamOperations() {
        System.out.println("1. Basic Stream Operations:");
        
        // Creating streams from collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        Stream<String> nameStream = names.stream();
        
        // Creating streams from arrays
        String[] words = {"hello", "world", "java", "streams"};
        Stream<String> wordStream = Arrays.stream(words);
        
        // Creating streams from individual elements
        Stream<String> singleStream = Stream.of("single", "element");
        
        // Creating infinite streams
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        infiniteStream.limit(5).forEach(System.out::print);
        System.out.println();
        
        // Creating streams with generate
        Stream<Double> randomStream = Stream.generate(Math::random);
        randomStream.limit(3).forEach(r -> System.out.print(r + " "));
        System.out.println("\n");
    }
    
    /**
     * Demonstrates intermediate operations
     */
    private static void intermediateOperations() {
        System.out.println("2. Intermediate Operations:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter operation
        System.out.println("Even numbers:");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Map operation
        System.out.println("Squares of numbers:");
        numbers.stream()
               .map(n -> n * n)
               .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // FlatMap operation
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("a", "b", "c"),
            Arrays.asList("d", "e", "f"),
            Arrays.asList("g", "h", "i")
        );
        
        System.out.println("Flattened list:");
        nestedList.stream()
                  .flatMap(List::stream)
                  .forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        // Distinct operation
        List<String> duplicates = Arrays.asList("a", "b", "a", "c", "b", "d");
        System.out.println("Unique elements:");
        duplicates.stream()
                  .distinct()
                  .forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        // Sorted operation
        System.out.println("Sorted numbers:");
        numbers.stream()
               .sorted(Comparator.reverseOrder())
               .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Limit and Skip operations
        System.out.println("First 3 numbers:");
        numbers.stream()
               .limit(3)
               .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.println("Skip first 3 numbers:");
        numbers.stream()
               .skip(3)
               .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
    }
    
    /**
     * Demonstrates terminal operations
     */
    private static void terminalOperations() {
        System.out.println("3. Terminal Operations:");
        
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "api");
        
        // forEach operation
        System.out.println("Printing all words:");
        words.stream().forEach(System.out::println);
        
        // count operation
        long count = words.stream().count();
        System.out.println("Total words: " + count);
        
        // anyMatch, allMatch, noneMatch operations
        boolean anyLongWord = words.stream().anyMatch(w -> w.length() > 5);
        boolean allShortWords = words.stream().allMatch(w -> w.length() < 10);
        boolean noneEmpty = words.stream().noneMatch(String::isEmpty);
        
        System.out.println("Any word longer than 5: " + anyLongWord);
        System.out.println("All words shorter than 10: " + allShortWords);
        System.out.println("No empty words: " + noneEmpty);
        
        // findFirst and findAny operations
        Optional<String> first = words.stream().findFirst();
        Optional<String> any = words.stream().findAny();
        
        first.ifPresent(f -> System.out.println("First word: " + f));
        any.ifPresent(a -> System.out.println("Any word: " + a));
        
        // min and max operations
        Optional<String> shortest = words.stream().min(Comparator.comparing(String::length));
        Optional<String> longest = words.stream().max(Comparator.comparing(String::length));
        
        shortest.ifPresent(s -> System.out.println("Shortest word: " + s));
        longest.ifPresent(l -> System.out.println("Longest word: " + l));
        
        System.out.println();
    }
    
    /**
     * Demonstrates stream collectors
     */
    private static void streamCollectors() {
        System.out.println("4. Stream Collectors:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");
        
        // Collect to List
        List<String> upperNames = names.stream()
                                      .map(String::toUpperCase)
                                      .collect(Collectors.toList());
        System.out.println("Upper names: " + upperNames);
        
        // Collect to Set
        Set<String> uniqueNames = names.stream()
                                      .collect(Collectors.toSet());
        System.out.println("Unique names: " + uniqueNames);
        
        // Collect to Map
        Map<String, Integer> nameLengthMap = names.stream()
                                                 .collect(Collectors.toMap(
                                                     name -> name,
                                                     String::length
                                                 ));
        System.out.println("Name length map: " + nameLengthMap);
        
        // Collect to String
        String joinedNames = names.stream()
                                 .collect(Collectors.joining(", "));
        System.out.println("Joined names: " + joinedNames);
        
        // Grouping by
        Map<Integer, List<String>> groupedByLength = names.stream()
                                                         .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by length: " + groupedByLength);
        
        // Partitioning by
        Map<Boolean, List<String>> partitioned = names.stream()
                                                     .collect(Collectors.partitioningBy(name -> name.length() > 4));
        System.out.println("Partitioned by length > 4: " + partitioned);
        
        // Counting
        long count = names.stream().collect(Collectors.counting());
        System.out.println("Count: " + count);
        
        // Summarizing
        IntSummaryStatistics stats = names.stream()
                                         .mapToInt(String::length)
                                         .summaryStatistics();
        System.out.println("Length statistics: " + stats);
        
        System.out.println();
    }
    
    /**
     * Demonstrates parallel streams
     */
    private static void parallelStreams() {
        System.out.println("5. Parallel Streams:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Sequential stream
        long startTime = System.currentTimeMillis();
        int sequentialSum = numbers.stream()
                                  .mapToInt(Integer::intValue)
                                  .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        // Parallel stream
        startTime = System.currentTimeMillis();
        int parallelSum = numbers.parallelStream()
                                .mapToInt(Integer::intValue)
                                .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Sequential sum: " + sequentialSum + " (time: " + sequentialTime + "ms)");
        System.out.println("Parallel sum: " + parallelSum + " (time: " + parallelTime + "ms)");
        
        // Parallel stream with complex operations
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "parallel", "processing");
        
        List<String> upperWords = words.parallelStream()
                                      .map(String::toUpperCase)
                                      .collect(Collectors.toList());
        
        System.out.println("Parallel processed words: " + upperWords);
        System.out.println();
    }
    
    /**
     * Demonstrates advanced stream operations
     */
    private static void advancedStreamOperations() {
        System.out.println("6. Advanced Stream Operations:");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer"),
            new Person("Bob", 30, "Manager"),
            new Person("Charlie", 35, "Engineer"),
            new Person("David", 28, "Developer"),
            new Person("Eve", 32, "Manager")
        );
        
        // Complex filtering and mapping
        List<String> engineerNames = people.stream()
                                          .filter(p -> "Engineer".equals(p.getJob()))
                                          .map(Person::getName)
                                          .collect(Collectors.toList());
        System.out.println("Engineer names: " + engineerNames);
        
        // Grouping by job and counting
        Map<String, Long> jobCounts = people.stream()
                                           .collect(Collectors.groupingBy(
                                               Person::getJob,
                                               Collectors.counting()
                                           ));
        System.out.println("Job counts: " + jobCounts);
        
        // Average age by job
        Map<String, Double> avgAgeByJob = people.stream()
                                               .collect(Collectors.groupingBy(
                                                   Person::getJob,
                                                   Collectors.averagingInt(Person::getAge)
                                               ));
        System.out.println("Average age by job: " + avgAgeByJob);
        
        // Reducing to find oldest person
        Optional<Person> oldest = people.stream()
                                       .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2);
        oldest.ifPresent(p -> System.out.println("Oldest person: " + p.getName()));
        
        System.out.println();
    }
    
    /**
     * Demonstrates custom collectors
     */
    private static void customCollectors() {
        System.out.println("7. Custom Collectors:");
        
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "custom", "collector");
        
        // Custom collector to concatenate with custom separator
        String result = words.stream()
                            .collect(Collector.of(
                                StringBuilder::new,
                                (sb, str) -> sb.append(str).append(" | "),
                                (sb1, sb2) -> sb1.append(sb2),
                                sb -> sb.toString()
                            ));
        System.out.println("Custom concatenated: " + result);
        
        // Custom collector to find min and max
        MinMaxResult<String> minMax = words.stream()
                                          .collect(Collector.of(
                                              MinMaxResult::new,
                                              MinMaxResult::accept,
                                              MinMaxResult::combine,
                                              MinMaxResult::finish
                                          ));
        System.out.println("Min: " + minMax.getMin() + ", Max: " + minMax.getMax());
        
        System.out.println();
    }
}

/**
 * Person class for demonstrating complex stream operations
 */
class Person {
    private String name;
    private int age;
    private String job;
    
    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getJob() { return job; }
    
    @Override
    public String toString() {
        return name + "(" + age + ", " + job + ")";
    }
}

/**
 * Custom result class for min/max operations
 */
class MinMaxResult<T extends Comparable<T>> {
    private T min;
    private T max;
    
    public void accept(T t) {
        if (min == null || t.compareTo(min) < 0) {
            min = t;
        }
        if (max == null || t.compareTo(max) > 0) {
            max = t;
        }
    }
    
    public MinMaxResult<T> combine(MinMaxResult<T> other) {
        if (other.min != null) accept(other.min);
        if (other.max != null) accept(other.max);
        return this;
    }
    
    public MinMaxResult<T> finish() {
        return this;
    }
    
    public T getMin() { return min; }
    public T getMax() { return max; }
}
