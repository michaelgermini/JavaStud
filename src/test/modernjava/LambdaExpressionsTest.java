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
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BiFunction;

/**
 * Unit tests for LambdaExpressions class
 * 
 * @author JavaStud Team
 * @version 2.0
 * @since Java 8
 */
@DisplayName("Lambda Expressions Tests")
class LambdaExpressionsTest {
    
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @Nested
    @DisplayName("Basic Lambda Expressions Tests")
    class BasicLambdaTests {
        
        @Test
        @DisplayName("Runnable lambda should execute correctly")
        void testRunnableLambda() {
            // Given
            Runnable runnable = () -> System.out.println("Hello from lambda!");
            
            // When
            runnable.run();
            
            // Then
            String output = outputStream.toString();
            assertTrue(output.contains("Hello from lambda!"));
        }
        
        @Test
        @DisplayName("Comparator lambda should sort correctly")
        void testComparatorLambda() {
            // Given
            List<String> names = Arrays.asList("Charlie", "Alice", "David", "Bob");
            
            // When
            names.sort((a, b) -> a.compareTo(b));
            
            // Then
            List<String> expected = Arrays.asList("Alice", "Bob", "Charlie", "David");
            assertEquals(expected, names);
        }
        
        @Test
        @DisplayName("Custom functional interface should work correctly")
        void testCustomFunctionalInterface() {
            // Given
            MathOperation addition = (a, b) -> a + b;
            MathOperation multiplication = (a, b) -> a * b;
            
            // When & Then
            assertEquals(15, addition.operate(10, 5));
            assertEquals(50, multiplication.operate(10, 5));
        }
    }
    
    @Nested
    @DisplayName("Built-in Functional Interfaces Tests")
    class BuiltInFunctionalInterfaceTests {
        
        @Test
        @DisplayName("Predicate should test conditions correctly")
        void testPredicate() {
            // Given
            Predicate<String> isLongerThan5 = str -> str.length() > 5;
            
            // When & Then
            assertTrue(isLongerThan5.test("Hello World"));
            assertFalse(isLongerThan5.test("Hello"));
        }
        
        @Test
        @DisplayName("Function should transform input to output")
        void testFunction() {
            // Given
            Function<String, Integer> getLength = String::length;
            
            // When & Then
            assertEquals(5, getLength.apply("Hello"));
            assertEquals(0, getLength.apply(""));
        }
        
        @Test
        @DisplayName("Consumer should consume input without output")
        void testConsumer() {
            // Given
            Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
            
            // When
            printUpperCase.accept("hello world");
            
            // Then
            String output = outputStream.toString();
            assertTrue(output.contains("HELLO WORLD"));
        }
        
        @Test
        @DisplayName("Supplier should provide output without input")
        void testSupplier() {
            // Given
            Supplier<Double> randomNumber = Math::random;
            
            // When
            Double result = randomNumber.get();
            
            // Then
            assertNotNull(result);
            assertTrue(result >= 0.0 && result < 1.0);
        }
        
        @Test
        @DisplayName("BiFunction should handle two inputs and one output")
        void testBiFunction() {
            // Given
            BiFunction<String, String, String> concatenate = (a, b) -> a + " " + b;
            
            // When & Then
            assertEquals("Hello Lambda", concatenate.apply("Hello", "Lambda"));
        }
    }
    
    @Nested
    @DisplayName("Method References Tests")
    class MethodReferencesTests {
        
        @Test
        @DisplayName("Static method reference should work")
        void testStaticMethodReference() {
            // Given
            List<String> names = Arrays.asList("alice", "bob", "charlie");
            
            // When
            names.forEach(System.out::println);
            
            // Then
            String output = outputStream.toString();
            assertTrue(output.contains("alice"));
            assertTrue(output.contains("bob"));
            assertTrue(output.contains("charlie"));
        }
        
        @Test
        @DisplayName("Instance method reference should work")
        void testInstanceMethodReference() {
            // Given
            List<String> names = Arrays.asList("alice", "bob", "charlie");
            
            // When
            List<String> upperNames = names.stream()
                                          .map(String::toUpperCase)
                                          .collect(java.util.ArrayList::new, 
                                                  java.util.ArrayList::add, 
                                                  java.util.ArrayList::addAll);
            
            // Then
            List<String> expected = Arrays.asList("ALICE", "BOB", "CHARLIE");
            assertEquals(expected, upperNames);
        }
    }
    
    @Nested
    @DisplayName("Custom Functional Interfaces Tests")
    class CustomFunctionalInterfaceTests {
        
        @Test
        @DisplayName("Validator should validate correctly")
        void testValidator() {
            // Given
            Validator<String> emailValidator = email -> email.contains("@");
            Validator<String> passwordValidator = password -> password.length() >= 8;
            
            // When & Then
            assertTrue(emailValidator.isValid("user@example.com"));
            assertFalse(emailValidator.isValid("invalid-email"));
            assertTrue(passwordValidator.isValid("password123"));
            assertFalse(passwordValidator.isValid("123"));
        }
        
        @Test
        @DisplayName("Transformer should transform correctly")
        void testTransformer() {
            // Given
            Transformer<String, Integer> stringToLength = String::length;
            Transformer<Integer, String> numberToString = Object::toString;
            
            // When & Then
            assertEquals(5, stringToLength.transform("Hello"));
            assertEquals("42", numberToString.transform(42));
        }
    }
    
    @Nested
    @DisplayName("Lambda with Collections Tests")
    class LambdaWithCollectionsTests {
        
        @Test
        @DisplayName("Lambda should filter even numbers correctly")
        void testFilterEvenNumbers() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            
            // When
            List<Integer> evenNumbers = new java.util.ArrayList<>();
            numbers.forEach(n -> {
                if (n % 2 == 0) {
                    evenNumbers.add(n);
                }
            });
            
            // Then
            List<Integer> expected = Arrays.asList(2, 4, 6, 8, 10);
            assertEquals(expected, evenNumbers);
        }
        
        @Test
        @DisplayName("Lambda should map to squares correctly")
        void testMapToSquares() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            
            // When
            List<Integer> squares = new java.util.ArrayList<>();
            numbers.forEach(n -> squares.add(n * n));
            
            // Then
            List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25);
            assertEquals(expected, squares);
        }
        
        @Test
        @DisplayName("Stream reduce should sum correctly")
        void testStreamReduce() {
            // Given
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            
            // When
            int sum = numbers.stream().reduce(0, (a, b) -> a + b);
            
            // Then
            assertEquals(15, sum);
        }
    }
    
    @Nested
    @DisplayName("Lambda with Streams Tests")
    class LambdaWithStreamsTests {
        
        @Test
        @DisplayName("Stream filter and map should work correctly")
        void testStreamFilterAndMap() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "lambda", "streams");
            
            // When
            List<String> longWords = words.stream()
                                         .filter(word -> word.length() > 4)
                                         .map(String::toUpperCase)
                                         .collect(java.util.ArrayList::new, 
                                                 java.util.ArrayList::add, 
                                                 java.util.ArrayList::addAll);
            
            // Then
            List<String> expected = Arrays.asList("HELLO", "WORLD", "LAMBDA", "STREAMS");
            assertEquals(expected, longWords);
        }
        
        @Test
        @DisplayName("Stream count should work correctly")
        void testStreamCount() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "lambda", "streams");
            
            // When
            long countH = words.stream()
                              .filter(word -> word.startsWith("h"))
                              .count();
            
            // Then
            assertEquals(1, countH);
        }
        
        @Test
        @DisplayName("Stream findFirst should work correctly")
        void testStreamFindFirst() {
            // Given
            List<String> words = Arrays.asList("hello", "world", "java", "lambda", "streams");
            
            // When
            java.util.Optional<String> firstLongWord = words.stream()
                                                           .filter(word -> word.length() > 5)
                                                           .findFirst();
            
            // Then
            assertTrue(firstLongWord.isPresent());
            assertEquals("lambda", firstLongWord.get());
        }
    }
    
    @Nested
    @DisplayName("Functional Interface Annotation Tests")
    class FunctionalInterfaceAnnotationTests {
        
        @Test
        @DisplayName("MathOperation should be a functional interface")
        void testMathOperationFunctionalInterface() {
            // Given
            MathOperation operation = (a, b) -> a + b;
            
            // When & Then
            assertNotNull(operation);
            assertEquals(10, operation.operate(5, 5));
        }
        
        @Test
        @DisplayName("Validator should be a functional interface")
        void testValidatorFunctionalInterface() {
            // Given
            Validator<String> validator = str -> str != null && !str.isEmpty();
            
            // When & Then
            assertNotNull(validator);
            assertTrue(validator.isValid("test"));
            assertFalse(validator.isValid(""));
        }
        
        @Test
        @DisplayName("Transformer should be a functional interface")
        void testTransformerFunctionalInterface() {
            // Given
            Transformer<String, Integer> transformer = String::length;
            
            // When & Then
            assertNotNull(transformer);
            assertEquals(4, transformer.transform("test"));
        }
    }
}
