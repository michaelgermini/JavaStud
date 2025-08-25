package modernjava;

import java.util.*;
import java.util.function.*;

/**
 * Modern Java 8+ Lambda Expressions Examples
 * 
 * This class demonstrates various uses of lambda expressions including:
 * - Functional interfaces
 * - Method references
 * - Built-in functional interfaces
 * - Custom functional interfaces
 * 
 * @author JavaStud Team
 * @version 2.0
 * @since Java 8
 */
public class LambdaExpressions {
    
    public static void main(String[] args) {
        System.out.println("=== Lambda Expressions Examples ===\n");
        
        // 1. Basic Lambda Expressions
        basicLambdaExamples();
        
        // 2. Built-in Functional Interfaces
        builtInFunctionalInterfaces();
        
        // 3. Method References
        methodReferences();
        
        // 4. Custom Functional Interfaces
        customFunctionalInterfaces();
        
        // 5. Lambda with Collections
        lambdaWithCollections();
        
        // 6. Lambda with Streams
        lambdaWithStreams();
    }
    
    /**
     * Demonstrates basic lambda expression syntax
     */
    private static void basicLambdaExamples() {
        System.out.println("1. Basic Lambda Expressions:");
        
        // Runnable with lambda
        Runnable runnable = () -> System.out.println("Hello from lambda!");
        runnable.run();
        
        // Comparator with lambda
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted names: " + names);
        
        // Custom functional interface
        MathOperation addition = (a, b) -> a + b;
        MathOperation multiplication = (a, b) -> a * b;
        
        System.out.println("10 + 5 = " + addition.operate(10, 5));
        System.out.println("10 * 5 = " + multiplication.operate(10, 5));
        
        System.out.println();
    }
    
    /**
     * Demonstrates built-in functional interfaces
     */
    private static void builtInFunctionalInterfaces() {
        System.out.println("2. Built-in Functional Interfaces:");
        
        // Predicate - tests a condition
        Predicate<String> isLongerThan5 = str -> str.length() > 5;
        System.out.println("'Hello' is longer than 5: " + isLongerThan5.test("Hello"));
        System.out.println("'World' is longer than 5: " + isLongerThan5.test("World"));
        
        // Function - transforms input to output
        Function<String, Integer> getLength = String::length;
        System.out.println("Length of 'Lambda': " + getLength.apply("Lambda"));
        
        // Consumer - consumes input, no output
        Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
        printUpperCase.accept("hello world");
        
        // Supplier - provides output, no input
        Supplier<Double> randomNumber = Math::random;
        System.out.println("Random number: " + randomNumber.get());
        
        // BiFunction - two inputs, one output
        BiFunction<String, String, String> concatenate = (a, b) -> a + " " + b;
        System.out.println("Concatenated: " + concatenate.apply("Hello", "Lambda"));
        
        System.out.println();
    }
    
    /**
     * Demonstrates method references
     */
    private static void methodReferences() {
        System.out.println("3. Method References:");
        
        List<String> names = Arrays.asList("alice", "bob", "charlie", "david");
        
        // Static method reference
        names.forEach(System.out::println);
        
        // Instance method reference
        names.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);
        
        // Constructor reference
        List<String> upperNames = names.stream()
                                      .map(String::toUpperCase)
                                      .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
        System.out.println("Upper names: " + upperNames);
        System.out.println();
    }
    
    /**
     * Demonstrates custom functional interfaces
     */
    private static void customFunctionalInterfaces() {
        System.out.println("4. Custom Functional Interfaces:");
        
        // Custom functional interface
        Validator<String> emailValidator = email -> email.contains("@");
        Validator<String> passwordValidator = password -> password.length() >= 8;
        
        System.out.println("Email valid: " + emailValidator.isValid("user@example.com"));
        System.out.println("Email valid: " + emailValidator.isValid("invalid-email"));
        System.out.println("Password valid: " + passwordValidator.isValid("password123"));
        System.out.println("Password valid: " + passwordValidator.isValid("123"));
        
        // Generic functional interface
        Transformer<String, Integer> stringToLength = String::length;
        Transformer<Integer, String> numberToString = Object::toString;
        
        System.out.println("String length: " + stringToLength.transform("Hello"));
        System.out.println("Number as string: " + numberToString.transform(42));
        
        System.out.println();
    }
    
    /**
     * Demonstrates lambda expressions with collections
     */
    private static void lambdaWithCollections() {
        System.out.println("5. Lambda with Collections:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter even numbers
        List<Integer> evenNumbers = new ArrayList<>();
        numbers.forEach(n -> {
            if (n % 2 == 0) {
                evenNumbers.add(n);
            }
        });
        System.out.println("Even numbers: " + evenNumbers);
        
        // Map to squares
        List<Integer> squares = new ArrayList<>();
        numbers.forEach(n -> squares.add(n * n));
        System.out.println("Squares: " + squares);
        
        // Reduce to sum
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);
        
        System.out.println();
    }
    
    /**
     * Demonstrates lambda expressions with streams
     */
    private static void lambdaWithStreams() {
        System.out.println("6. Lambda with Streams:");
        
        List<String> words = Arrays.asList("hello", "world", "java", "lambda", "streams");
        
        // Filter and map with lambda
        List<String> longWords = words.stream()
                                     .filter(word -> word.length() > 4)
                                     .map(String::toUpperCase)
                                     .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
        System.out.println("Long words in uppercase: " + longWords);
        
        // Count words starting with specific letter
        long countH = words.stream()
                          .filter(word -> word.startsWith("h"))
                          .count();
        System.out.println("Words starting with 'h': " + countH);
        
        // Find first word matching condition
        Optional<String> firstLongWord = words.stream()
                                             .filter(word -> word.length() > 5)
                                             .findFirst();
        
        firstLongWord.ifPresent(word -> System.out.println("First long word: " + word));
        
        System.out.println();
    }
}

/**
 * Custom functional interface for mathematical operations
 */
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

/**
 * Custom functional interface for validation
 */
@FunctionalInterface
interface Validator<T> {
    boolean isValid(T t);
}

/**
 * Generic functional interface for transformation
 */
@FunctionalInterface
interface Transformer<T, R> {
    R transform(T t);
}
