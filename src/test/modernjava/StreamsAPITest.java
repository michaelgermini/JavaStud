package modernjava;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit tests for StreamsAPI class
 * 
 * @author JavaStud Team
 * @version 2.0
 * @since Java 8
 */
@DisplayName("Streams API Tests")
class StreamsAPITest {
    
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @Nested
    @DisplayName("Basic Stream Operations Tests")
    class BasicStreamOperationsTests {
        
        @Test
        @DisplayName("Stream from collection should work correctly")
        void testStreamFromCollection() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
            
            // When
            Stream<String> nameStream = names.stream();
            
            // Then
            assertNotNull(nameStream);
            assertEquals(3, nameStream.count());
        }
        
        @Test
        @DisplayName("Stream from array should work correctly")
        void testStreamFromArray() {
            // Given
            String[] words = {"hello", "world", "java"};
            
            // When
            Stream<String> wordStream = Arrays.stream(words);
            
            // Then
            assertNotNull(wordStream);
            assertEquals(3, wordStream.count());
        }
        
        @Test
        @DisplayName("Stream.of should work correctly")
        void testStreamOf() {
            // Given
            Stream<String> singleStream = Stream.of("single", "element");
            
            // When & Then
            assertNotNull(singleStream);
            assertEquals(2, singleStream.count());
        }
        
        @Test
        @DisplayName("Infinite stream should work correctly")
        void testInfiniteStream() {
            // Given
            Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
            
            // When
            List<Integer> firstFive = infiniteStream.limit(5).collect(Collectors.toList());
            
            // Then
            List<Integer> expected = Arrays.asList(0, 2, 4, 6, 8);
            assertEquals(expected, firstFive);
        }
        
        @Test
        @DisplayName("Stream.generate should work correctly")
        void testStreamGenerate() {
            // Given
            Stream<Double> randomStream = Stream.generate(Math::random);
            
            // When
            List<Double> firstThree = randomStream.limit(3).collect(Collectors.toList());
            
            // Then
            assertEquals(3, firstThree.size());
            firstThree.forEach(random -> {
                assertTrue(random >= 0.0 && random < 1.0);
            });
        }
    }
    
    @Nested
    @DisplayName("Intermediate Operations Tests")
    class IntermediateOperationsTests {
        
        @Test
        @DisplayName("Filter operation should work correctly")
        void testFilterOperation() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            
            // When
            List<Integer> evenNumbers = numbers.stream()
                                              .filter(n -> n % 2 == 0)
                                              .collect(Collectors.toList());
            
            // Then
            List<Integer> expected = Arrays.asList(2, 4, 6, 8, 10);
            assertEquals(expected, evenNumbers);
        }
        
        @Test
        @DisplayName("Map operation should work correctly")
        void testMapOperation() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            
            // When
            List<Integer> squares = numbers.stream()
                                          .map(n -> n * n)
                                          .collect(Collectors.toList());
            
            // Then
            List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25);
            assertEquals(expected, squares);
        }
        
        @Test
        @DisplayName("FlatMap operation should work correctly")
        void testFlatMapOperation() {
            // Given
            List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e", "f"),
                Arrays.asList("g", "h", "i")
            );
            
            // When
            List<String> flattened = nestedList.stream()
                                              .flatMap(List::stream)
                                              .collect(Collectors.toList());
            
            // Then
            List<String> expected = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
            assertEquals(expected, flattened);
        }
        
        @Test
        @DisplayName("Distinct operation should work correctly")
        void testDistinctOperation() {
            // Given
            List<String> duplicates = Arrays.asList("a", "b", "a", "c", "b", "d");
            
            // When
            List<String> unique = duplicates.stream()
                                           .distinct()
                                           .collect(Collectors.toList());
            
            // Then
            List<String> expected = Arrays.asList("a", "b", "c", "d");
            assertEquals(expected, unique);
        }
        
        @Test
        @DisplayName("Sorted operation should work correctly")
        void testSortedOperation() {
            // Given
            List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6);
            
            // When
            List<Integer> sorted = numbers.stream()
                                         .sorted()
                                         .collect(Collectors.toList());
            
            // Then
            List<Integer> expected = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 9);
            assertEquals(expected, sorted);
        }
        
        @Test
        @DisplayName("Limit operation should work correctly")
        void testLimitOperation() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            
            // When
            List<Integer> limited = numbers.stream()
                                          .limit(3)
                                          .collect(Collectors.toList());
            
            // Then
            List<Integer> expected = Arrays.asList(1, 2, 3);
            assertEquals(expected, limited);
        }
        
        @Test
        @DisplayName("Skip operation should work correctly")
        void testSkipOperation() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            
            // When
            List<Integer> skipped = numbers.stream()
                                          .skip(3)
                                          .collect(Collectors.toList());
            
            // Then
            List<Integer> expected = Arrays.asList(4, 5, 6, 7, 8, 9, 10);
            assertEquals(expected, skipped);
        }
    }
    
    @Nested
    @DisplayName("Terminal Operations Tests")
    class TerminalOperationsTests {
        
        @Test
        @DisplayName("Count operation should work correctly")
        void testCountOperation() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "streams", "api");
            
            // When
            long count = words.stream().count();
            
            // Then
            assertEquals(5, count);
        }
        
        @Test
        @DisplayName("AnyMatch operation should work correctly")
        void testAnyMatchOperation() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "streams", "api");
            
            // When
            boolean anyLongWord = words.stream().anyMatch(w -> w.length() > 5);
            boolean anyShortWord = words.stream().anyMatch(w -> w.length() < 3);
            
            // Then
            assertTrue(anyLongWord);
            assertFalse(anyShortWord);
        }
        
        @Test
        @DisplayName("AllMatch operation should work correctly")
        void testAllMatchOperation() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "streams", "api");
            
            // When
            boolean allShortWords = words.stream().allMatch(w -> w.length() < 10);
            boolean allLongWords = words.stream().allMatch(w -> w.length() > 3);
            
            // Then
            assertTrue(allShortWords);
            assertFalse(allLongWords);
        }
        
        @Test
        @DisplayName("NoneMatch operation should work correctly")
        void testNoneMatchOperation() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "streams", "api");
            
            // When
            boolean noneEmpty = words.stream().noneMatch(String::isEmpty);
            boolean noneLong = words.stream().noneMatch(w -> w.length() > 10);
            
            // Then
            assertTrue(noneEmpty);
            assertTrue(noneLong);
        }
        
        @Test
        @DisplayName("FindFirst operation should work correctly")
        void testFindFirstOperation() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "streams", "api");
            
            // When
            java.util.Optional<String> first = words.stream().findFirst();
            
            // Then
            assertTrue(first.isPresent());
            assertEquals("hello", first.get());
        }
        
        @Test
        @DisplayName("Min and Max operations should work correctly")
        void testMinMaxOperations() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "streams", "api");
            
            // When
            java.util.Optional<String> shortest = words.stream()
                                                      .min(java.util.Comparator.comparing(String::length));
            java.util.Optional<String> longest = words.stream()
                                                     .max(java.util.Comparator.comparing(String::length));
            
            // Then
            assertTrue(shortest.isPresent());
            assertTrue(longest.isPresent());
            assertEquals("api", shortest.get());
            assertEquals("streams", longest.get());
        }
    }
    
    @Nested
    @DisplayName("Stream Collectors Tests")
    class StreamCollectorsTests {
        
        @Test
        @DisplayName("Collect to List should work correctly")
        void testCollectToList() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");
            
            // When
            List<String> upperNames = names.stream()
                                          .map(String::toUpperCase)
                                          .collect(Collectors.toList());
            
            // Then
            List<String> expected = Arrays.asList("ALICE", "BOB", "CHARLIE", "DAVID", "EVE", "FRANK");
            assertEquals(expected, upperNames);
        }
        
        @Test
        @DisplayName("Collect to Set should work correctly")
        void testCollectToSet() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");
            
            // When
            Set<String> uniqueNames = names.stream().collect(Collectors.toSet());
            
            // Then
            assertEquals(6, uniqueNames.size());
            assertTrue(uniqueNames.contains("Alice"));
            assertTrue(uniqueNames.contains("Bob"));
        }
        
        @Test
        @DisplayName("Collect to Map should work correctly")
        void testCollectToMap() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
            
            // When
            Map<String, Integer> nameLengthMap = names.stream()
                                                     .collect(Collectors.toMap(
                                                         name -> name,
                                                         String::length
                                                     ));
            
            // Then
            assertEquals(3, nameLengthMap.size());
            assertEquals(5, nameLengthMap.get("Alice"));
            assertEquals(3, nameLengthMap.get("Bob"));
            assertEquals(7, nameLengthMap.get("Charlie"));
        }
        
        @Test
        @DisplayName("Collect joining should work correctly")
        void testCollectJoining() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
            
            // When
            String joinedNames = names.stream().collect(Collectors.joining(", "));
            
            // Then
            assertEquals("Alice, Bob, Charlie", joinedNames);
        }
        
        @Test
        @DisplayName("Grouping by should work correctly")
        void testGroupingBy() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
            
            // When
            Map<Integer, List<String>> groupedByLength = names.stream()
                                                             .collect(Collectors.groupingBy(String::length));
            
            // Then
            assertEquals(3, groupedByLength.size());
            assertTrue(groupedByLength.containsKey(3));
            assertTrue(groupedByLength.containsKey(4));
            assertTrue(groupedByLength.containsKey(5));
            assertEquals(2, groupedByLength.get(4).size()); // Alice, David
        }
        
        @Test
        @DisplayName("Partitioning by should work correctly")
        void testPartitioningBy() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
            
            // When
            Map<Boolean, List<String>> partitioned = names.stream()
                                                         .collect(Collectors.partitioningBy(name -> name.length() > 4));
            
            // Then
            assertEquals(2, partitioned.size());
            assertTrue(partitioned.containsKey(true));
            assertTrue(partitioned.containsKey(false));
            assertEquals(2, partitioned.get(true).size()); // Alice, Charlie
            assertEquals(3, partitioned.get(false).size()); // Bob, David, Eve
        }
    }
    
    @Nested
    @DisplayName("Parallel Streams Tests")
    class ParallelStreamsTests {
        
        @Test
        @DisplayName("Parallel stream should work correctly")
        void testParallelStream() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            
            // When
            int parallelSum = numbers.parallelStream()
                                    .mapToInt(Integer::intValue)
                                    .sum();
            
            // Then
            assertEquals(55, parallelSum);
        }
        
        @Test
        @DisplayName("Parallel stream with complex operations should work correctly")
        void testParallelStreamComplexOperations() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "streams", "parallel", "processing");
            
            // When
            List<String> upperWords = words.parallelStream()
                                          .map(String::toUpperCase)
                                          .collect(Collectors.toList());
            
            // Then
            List<String> expected = Arrays.asList("HELLO", "WORLD", "JAVA", "STREAMS", "PARALLEL", "PROCESSING");
            assertEquals(expected, upperWords);
        }
    }
    
    @Nested
    @DisplayName("Advanced Stream Operations Tests")
    class AdvancedStreamOperationsTests {
        
        @Test
        @DisplayName("Complex filtering and mapping should work correctly")
        void testComplexFilteringAndMapping() {
            // Given
            List<Person> people = Arrays.asList(
                new Person("Alice", 25, "Engineer"),
                new Person("Bob", 30, "Manager"),
                new Person("Charlie", 35, "Engineer"),
                new Person("David", 28, "Developer"),
                new Person("Eve", 32, "Manager")
            );
            
            // When
            List<String> engineerNames = people.stream()
                                              .filter(p -> "Engineer".equals(p.getJob()))
                                              .map(Person::getName)
                                              .collect(Collectors.toList());
            
            // Then
            List<String> expected = Arrays.asList("Alice", "Charlie");
            assertEquals(expected, engineerNames);
        }
        
        @Test
        @DisplayName("Grouping by job and counting should work correctly")
        void testGroupingByJobAndCounting() {
            // Given
            List<Person> people = Arrays.asList(
                new Person("Alice", 25, "Engineer"),
                new Person("Bob", 30, "Manager"),
                new Person("Charlie", 35, "Engineer"),
                new Person("David", 28, "Developer"),
                new Person("Eve", 32, "Manager")
            );
            
            // When
            Map<String, Long> jobCounts = people.stream()
                                               .collect(Collectors.groupingBy(
                                                   Person::getJob,
                                                   Collectors.counting()
                                               ));
            
            // Then
            assertEquals(3, jobCounts.size());
            assertEquals(2L, jobCounts.get("Engineer"));
            assertEquals(2L, jobCounts.get("Manager"));
            assertEquals(1L, jobCounts.get("Developer"));
        }
        
        @Test
        @DisplayName("Average age by job should work correctly")
        void testAverageAgeByJob() {
            // Given
            List<Person> people = Arrays.asList(
                new Person("Alice", 25, "Engineer"),
                new Person("Bob", 30, "Manager"),
                new Person("Charlie", 35, "Engineer"),
                new Person("David", 28, "Developer"),
                new Person("Eve", 32, "Manager")
            );
            
            // When
            Map<String, Double> avgAgeByJob = people.stream()
                                                   .collect(Collectors.groupingBy(
                                                       Person::getJob,
                                                       Collectors.averagingInt(Person::getAge)
                                                   ));
            
            // Then
            assertEquals(3, avgAgeByJob.size());
            assertEquals(30.0, avgAgeByJob.get("Engineer"), 0.01);
            assertEquals(31.0, avgAgeByJob.get("Manager"), 0.01);
            assertEquals(28.0, avgAgeByJob.get("Developer"), 0.01);
        }
        
        @Test
        @DisplayName("Reducing to find oldest person should work correctly")
        void testReducingToFindOldestPerson() {
            // Given
            List<Person> people = Arrays.asList(
                new Person("Alice", 25, "Engineer"),
                new Person("Bob", 30, "Manager"),
                new Person("Charlie", 35, "Engineer"),
                new Person("David", 28, "Developer"),
                new Person("Eve", 32, "Manager")
            );
            
            // When
            java.util.Optional<Person> oldest = people.stream()
                                                     .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2);
            
            // Then
            assertTrue(oldest.isPresent());
            assertEquals("Charlie", oldest.get().getName());
            assertEquals(35, oldest.get().getAge());
        }
    }
}
