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
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Unit tests for OptionalExamples class
 * 
 * @author JavaStud Team
 * @version 2.0
 * @since Java 8
 */
@DisplayName("Optional Examples Tests")
class OptionalExamplesTest {
    
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @Nested
    @DisplayName("Creating Optional Objects Tests")
    class CreatingOptionalObjectsTests {
        
        @Test
        @DisplayName("Empty Optional should be empty")
        void testEmptyOptional() {
            // Given
            Optional<String> emptyOptional = Optional.empty();
            
            // When & Then
            assertFalse(emptyOptional.isPresent());
            assertTrue(emptyOptional.isEmpty());
        }
        
        @Test
        @DisplayName("Optional.of should contain value")
        void testOptionalOf() {
            // Given
            Optional<String> presentOptional = Optional.of("Hello Optional!");
            
            // When & Then
            assertTrue(presentOptional.isPresent());
            assertFalse(presentOptional.isEmpty());
            assertEquals("Hello Optional!", presentOptional.get());
        }
        
        @Test
        @DisplayName("Optional.ofNullable with null should be empty")
        void testOptionalOfNullableWithNull() {
            // Given
            String nullString = null;
            Optional<String> nullableOptional = Optional.ofNullable(nullString);
            
            // When & Then
            assertFalse(nullableOptional.isPresent());
            assertTrue(nullableOptional.isEmpty());
        }
        
        @Test
        @DisplayName("Optional.ofNullable with non-null should contain value")
        void testOptionalOfNullableWithNonNull() {
            // Given
            String nonNullString = "Not null";
            Optional<String> nonNullOptional = Optional.ofNullable(nonNullString);
            
            // When & Then
            assertTrue(nonNullOptional.isPresent());
            assertFalse(nonNullOptional.isEmpty());
            assertEquals("Not null", nonNullOptional.get());
        }
    }
    
    @Nested
    @DisplayName("Safe Value Access Tests")
    class SafeValueAccessTests {
        
        @Test
        @DisplayName("isPresent should return correct value")
        void testIsPresent() {
            // Given
            Optional<String> presentOptional = Optional.of("Hello World");
            Optional<String> emptyOptional = Optional.empty();
            
            // When & Then
            assertTrue(presentOptional.isPresent());
            assertFalse(emptyOptional.isPresent());
        }
        
        @Test
        @DisplayName("isEmpty should return correct value")
        void testIsEmpty() {
            // Given
            Optional<String> presentOptional = Optional.of("Hello World");
            Optional<String> emptyOptional = Optional.empty();
            
            // When & Then
            assertFalse(presentOptional.isEmpty());
            assertTrue(emptyOptional.isEmpty());
        }
        
        @Test
        @DisplayName("get should throw exception for empty Optional")
        void testGetThrowsException() {
            // Given
            Optional<String> emptyOptional = Optional.empty();
            
            // When & Then
            assertThrows(NoSuchElementException.class, () -> emptyOptional.get());
        }
        
        @Test
        @DisplayName("orElse should return value or default")
        void testOrElse() {
            // Given
            Optional<String> presentOptional = Optional.of("Hello World");
            Optional<String> emptyOptional = Optional.empty();
            
            // When & Then
            assertEquals("Hello World", presentOptional.orElse("Default Value"));
            assertEquals("Default Value", emptyOptional.orElse("Default Value"));
        }
        
        @Test
        @DisplayName("orElseGet should return value or computed default")
        void testOrElseGet() {
            // Given
            Optional<String> presentOptional = Optional.of("Hello World");
            Optional<String> emptyOptional = Optional.empty();
            
            // When & Then
            assertEquals("Hello World", presentOptional.orElseGet(() -> "Computed Default"));
            assertEquals("Computed Default", emptyOptional.orElseGet(() -> "Computed Default"));
        }
        
        @Test
        @DisplayName("orElseThrow should throw exception for empty Optional")
        void testOrElseThrow() {
            // Given
            Optional<String> emptyOptional = Optional.empty();
            
            // When & Then
            assertThrows(RuntimeException.class, () -> 
                emptyOptional.orElseThrow(() -> new RuntimeException("Value not found")));
        }
        
        @Test
        @DisplayName("orElseThrow should return value for present Optional")
        void testOrElseThrowWithPresent() {
            // Given
            Optional<String> presentOptional = Optional.of("Hello World");
            
            // When & Then
            assertEquals("Hello World", 
                presentOptional.orElseThrow(() -> new RuntimeException("Value not found")));
        }
    }
    
    @Nested
    @DisplayName("Functional Operations Tests")
    class FunctionalOperationsTests {
        
        @Test
        @DisplayName("map should transform value if present")
        void testMap() {
            // Given
            Optional<String> optional = Optional.of("hello world");
            Optional<String> emptyOptional = Optional.empty();
            
            // When
            Optional<String> upperCase = optional.map(String::toUpperCase);
            Optional<String> emptyUpperCase = emptyOptional.map(String::toUpperCase);
            
            // Then
            assertTrue(upperCase.isPresent());
            assertEquals("HELLO WORLD", upperCase.get());
            assertFalse(emptyUpperCase.isPresent());
        }
        
        @Test
        @DisplayName("flatMap should transform to another Optional")
        void testFlatMap() {
            // Given
            Optional<String> optional = Optional.of("hello world");
            Optional<String> emptyOptional = Optional.empty();
            
            // When
            Optional<Integer> length = optional.flatMap(s -> Optional.of(s.length()));
            Optional<Integer> emptyLength = emptyOptional.flatMap(s -> Optional.of(s.length()));
            
            // Then
            assertTrue(length.isPresent());
            assertEquals(11, length.get());
            assertFalse(emptyLength.isPresent());
        }
        
        @Test
        @DisplayName("filter should filter based on condition")
        void testFilter() {
            // Given
            Optional<String> optional = Optional.of("hello world");
            Optional<String> emptyOptional = Optional.empty();
            
            // When
            Optional<String> filtered = optional.filter(s -> s.length() > 5);
            Optional<String> emptyFiltered = emptyOptional.filter(s -> s.length() > 5);
            Optional<String> filteredOut = optional.filter(s -> s.length() < 5);
            
            // Then
            assertTrue(filtered.isPresent());
            assertEquals("hello world", filtered.get());
            assertFalse(emptyFiltered.isPresent());
            assertFalse(filteredOut.isPresent());
        }
        
        @Test
        @DisplayName("ifPresent should execute action if value present")
        void testIfPresent() {
            // Given
            Optional<String> optional = Optional.of("hello world");
            Optional<String> emptyOptional = Optional.empty();
            
            // When
            optional.ifPresent(s -> System.out.println("Value is present: " + s));
            emptyOptional.ifPresent(s -> System.out.println("This won't print"));
            
            // Then
            String output = outputStream.toString();
            assertTrue(output.contains("Value is present: hello world"));
            assertFalse(output.contains("This won't print"));
        }
        
        @Test
        @DisplayName("ifPresentOrElse should execute different actions")
        void testIfPresentOrElse() {
            // Given
            Optional<String> optional = Optional.of("hello world");
            Optional<String> emptyOptional = Optional.empty();
            
            // When
            optional.ifPresentOrElse(
                s -> System.out.println("Value present: " + s),
                () -> System.out.println("No value present")
            );
            emptyOptional.ifPresentOrElse(
                s -> System.out.println("This won't print"),
                () -> System.out.println("No value present")
            );
            
            // Then
            String output = outputStream.toString();
            assertTrue(output.contains("Value present: hello world"));
            assertTrue(output.contains("No value present"));
            assertFalse(output.contains("This won't print"));
        }
    }
    
    @Nested
    @DisplayName("Optional with Streams Tests")
    class OptionalWithStreamsTests {
        
        @Test
        @DisplayName("Filter out empty optionals and get values")
        void testFilterOutEmptyOptionals() {
            // Given
            List<Optional<String>> optionals = Arrays.asList(
                Optional.of("Alice"),
                Optional.empty(),
                Optional.of("Bob"),
                Optional.empty(),
                Optional.of("Charlie")
            );
            
            // When
            List<String> presentValues = optionals.stream()
                                                 .filter(Optional::isPresent)
                                                 .map(Optional::get)
                                                 .collect(java.util.ArrayList::new, 
                                                         java.util.ArrayList::add, 
                                                         java.util.ArrayList::addAll);
            
            // Then
            List<String> expected = Arrays.asList("Alice", "Bob", "Charlie");
            assertEquals(expected, presentValues);
        }
        
        @Test
        @DisplayName("FlatMap should work with optionals")
        void testFlatMapWithOptionals() {
            // Given
            List<Optional<String>> optionals = Arrays.asList(
                Optional.of("Alice"),
                Optional.empty(),
                Optional.of("Bob"),
                Optional.empty(),
                Optional.of("Charlie")
            );
            
            // When
            List<String> flatMappedValues = optionals.stream()
                                                    .flatMap(opt -> opt.stream())
                                                    .collect(java.util.ArrayList::new, 
                                                            java.util.ArrayList::add, 
                                                            java.util.ArrayList::addAll);
            
            // Then
            List<String> expected = Arrays.asList("Alice", "Bob", "Charlie");
            assertEquals(expected, flatMappedValues);
        }
        
        @Test
        @DisplayName("Find first present value")
        void testFindFirstPresent() {
            // Given
            List<Optional<String>> optionals = Arrays.asList(
                Optional.empty(),
                Optional.of("Alice"),
                Optional.empty(),
                Optional.of("Bob")
            );
            
            // When
            Optional<String> firstPresent = optionals.stream()
                                                    .filter(Optional::isPresent)
                                                    .map(Optional::get)
                                                    .findFirst();
            
            // Then
            assertTrue(firstPresent.isPresent());
            assertEquals("Alice", firstPresent.get());
        }
        
        @Test
        @DisplayName("Count present values")
        void testCountPresentValues() {
            // Given
            List<Optional<String>> optionals = Arrays.asList(
                Optional.of("Alice"),
                Optional.empty(),
                Optional.of("Bob"),
                Optional.empty(),
                Optional.of("Charlie")
            );
            
            // When
            long presentCount = optionals.stream()
                                       .filter(Optional::isPresent)
                                       .count();
            
            // Then
            assertEquals(3, presentCount);
        }
    }
    
    @Nested
    @DisplayName("Real-world Examples Tests")
    class RealWorldExamplesTests {
        
        @Test
        @DisplayName("User lookup example should work correctly")
        void testUserLookupExample() {
            // Given
            Optional<User> user = findUserById(123);
            
            // When
            String userName = user.map(User::getName)
                                 .orElse("Unknown User");
            
            // Then
            assertEquals("John Doe", userName);
        }
        
        @Test
        @DisplayName("User lookup with non-existent ID should return default")
        void testUserLookupNonExistent() {
            // Given
            Optional<User> user = findUserById(999);
            
            // When
            String userName = user.map(User::getName)
                                 .orElse("Unknown User");
            
            // Then
            assertEquals("Unknown User", userName);
        }
        
        @Test
        @DisplayName("Configuration example should work correctly")
        void testConfigurationExample() {
            // Given
            Optional<String> configValue = getConfigValue("database.url");
            
            // When
            String dbUrl = configValue.orElse("localhost:3306");
            
            // Then
            assertEquals("mysql://localhost:3306", dbUrl);
        }
        
        @Test
        @DisplayName("Configuration with non-existent key should return default")
        void testConfigurationNonExistent() {
            // Given
            Optional<String> configValue = getConfigValue("non.existent.key");
            
            // When
            String dbUrl = configValue.orElse("localhost:3306");
            
            // Then
            assertEquals("localhost:3306", dbUrl);
        }
        
        @Test
        @DisplayName("Email validation should work correctly")
        void testEmailValidation() {
            // Given
            Optional<String> email = Optional.of("user@example.com");
            
            // When
            boolean isValidEmail = email.filter(e -> e.contains("@"))
                                       .filter(e -> e.contains("."))
                                       .isPresent();
            
            // Then
            assertTrue(isValidEmail);
        }
        
        @Test
        @DisplayName("Invalid email should be filtered out")
        void testInvalidEmail() {
            // Given
            Optional<String> email = Optional.of("invalid-email");
            
            // When
            boolean isValidEmail = email.filter(e -> e.contains("@"))
                                       .filter(e -> e.contains("."))
                                       .isPresent();
            
            // Then
            assertFalse(isValidEmail);
        }
        
        @Test
        @DisplayName("Chaining operations should work correctly")
        void testChainingOperations() {
            // Given
            Optional<String> result = Optional.of("hello")
                                             .map(String::toUpperCase)
                                             .filter(s -> s.length() > 3)
                                             .map(s -> "Processed: " + s);
            
            // When & Then
            assertTrue(result.isPresent());
            assertEquals("Processed: HELLO", result.get());
        }
    }
    
    @Nested
    @DisplayName("Best Practices Tests")
    class BestPracticesTests {
        
        @Test
        @DisplayName("Optional with collections should work correctly")
        void testOptionalWithCollections() {
            // Given
            List<String> names = Arrays.asList("Alice", "Bob", null, "Charlie");
            
            // When
            List<String> validNames = names.stream()
                                          .map(Optional::ofNullable)
                                          .filter(Optional::isPresent)
                                          .map(Optional::get)
                                          .collect(java.util.ArrayList::new, 
                                                  java.util.ArrayList::add, 
                                                  java.util.ArrayList::addAll);
            
            // Then
            List<String> expected = Arrays.asList("Alice", "Bob", "Charlie");
            assertEquals(expected, validNames);
        }
        
        @Test
        @DisplayName("Optional for default values should work correctly")
        void testOptionalForDefaultValues() {
            // Given
            Optional<String> config = getConfigValue("app.name");
            
            // When
            String appName = config.orElse("Default App");
            
            // Then
            assertEquals("MyApp", appName);
        }
    }
    
    @Nested
    @DisplayName("Advanced Optional Usage Tests")
    class AdvancedOptionalUsageTests {
        
        @Test
        @DisplayName("Optional as monad should work correctly")
        void testOptionalAsMonad() {
            // Given
            Optional<Integer> result = Optional.of(5)
                                              .map(x -> x * 2)
                                              .map(x -> x + 1)
                                              .filter(x -> x > 10);
            
            // When & Then
            assertTrue(result.isPresent());
            assertEquals(11, result.get());
        }
        
        @Test
        @DisplayName("Optional with multiple conditions should work correctly")
        void testOptionalWithMultipleConditions() {
            // Given
            Optional<String> complexResult = Optional.of("test@example.com")
                                                    .filter(email -> email.contains("@"))
                                                    .filter(email -> email.contains("."))
                                                    .map(String::toLowerCase)
                                                    .filter(email -> email.length() > 10);
            
            // When & Then
            assertTrue(complexResult.isPresent());
            assertEquals("test@example.com", complexResult.get());
        }
        
        @Test
        @DisplayName("Optional with custom logic should work correctly")
        void testOptionalWithCustomLogic() {
            // Given
            Optional<String> customResult = Optional.of("input")
                                                   .flatMap(input -> {
                                                       if (input.length() > 3) {
                                                           return Optional.of(input.toUpperCase());
                                                       } else {
                                                           return Optional.empty();
                                                       }
                                                   });
            
            // When & Then
            assertTrue(customResult.isPresent());
            assertEquals("INPUT", customResult.get());
        }
        
        @Test
        @DisplayName("Optional with short input should return empty")
        void testOptionalWithShortInput() {
            // Given
            Optional<String> customResult = Optional.of("abc")
                                                   .flatMap(input -> {
                                                       if (input.length() > 3) {
                                                           return Optional.of(input.toUpperCase());
                                                       } else {
                                                           return Optional.empty();
                                                       }
                                                   });
            
            // When & Then
            assertFalse(customResult.isPresent());
        }
    }
    
    // Helper methods for testing
    private Optional<User> findUserById(int id) {
        if (id == 123) {
            return Optional.of(new User("John Doe", "john@example.com"));
        }
        return Optional.empty();
    }
    
    private Optional<String> getConfigValue(String key) {
        java.util.Map<String, String> config = java.util.Map.of(
            "database.url", "mysql://localhost:3306",
            "app.name", "MyApp"
        );
        return Optional.ofNullable(config.get(key));
    }
}
