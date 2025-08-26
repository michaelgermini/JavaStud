package java11plus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Java 11+ Features Examples
 * 
 * This class demonstrates modern Java 11+ features including:
 * - var keyword (Local Variable Type Inference)
 * - HTTP Client
 * - New String methods
 * - Collection factory methods
 * - Files.readString/writeString
 * - Optional.isEmpty()
 * 
 * @author JavaStud Team
 * @version 2.0
 * @since Java 11
 */
public class Java11Features {
    
    public static void main(String[] args) {
        System.out.println("=== Java 11+ Features Examples ===\n");
        
        // 1. Local Variable Type Inference (var)
        localVariableTypeInference();
        
        // 2. HTTP Client
        httpClientExamples();
        
        // 3. New String Methods
        newStringMethods();
        
        // 4. Collection Factory Methods
        collectionFactoryMethods();
        
        // 5. Files.readString/writeString
        filesExamples();
        
        // 6. Optional.isEmpty()
        optionalIsEmpty();
        
        // 7. Lambda Parameter Local Variable Syntax
        lambdaParameterLocalVariableSyntax();
        
        // 8. Nest-Based Access Control
        nestBasedAccessControl();
    }
    
    /**
     * Demonstrates Local Variable Type Inference (var keyword)
     */
    private static void localVariableTypeInference() {
        System.out.println("1. Local Variable Type Inference (var):");
        
        // Basic type inference
        var message = "Hello, Java 11!";
        var number = 42;
        var decimal = 3.14;
        var flag = true;
        
        System.out.println("Message: " + message + " (type: " + message.getClass().getSimpleName() + ")");
        System.out.println("Number: " + number + " (type: " + ((Object) number).getClass().getSimpleName() + ")");
        System.out.println("Decimal: " + decimal + " (type: " + ((Object) decimal).getClass().getSimpleName() + ")");
        System.out.println("Flag: " + flag + " (type: " + ((Object) flag).getClass().getSimpleName() + ")");
        
        // Collections with var
        var names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        var numbers = List.of(1, 2, 3, 4, 5);
        var map = java.util.Map.of("key1", "value1", "key2", "value2");
        
        System.out.println("Names: " + names + " (type: " + names.getClass().getSimpleName() + ")");
        System.out.println("Numbers: " + numbers + " (type: " + numbers.getClass().getSimpleName() + ")");
        System.out.println("Map: " + map + " (type: " + map.getClass().getSimpleName() + ")");
        
        // Streams with var
        var filteredNames = names.stream()
                                .filter(name -> name.length() > 4)
                                .collect(Collectors.toList());
        
        System.out.println("Filtered names: " + filteredNames);
        
        // Lambda expressions with var
        var predicate = (Predicate<String>) str -> str.startsWith("A");
        var filtered = names.stream().filter(predicate).collect(Collectors.toList());
        
        System.out.println("Names starting with 'A': " + filtered);
        
        System.out.println();
    }
    
    /**
     * Demonstrates HTTP Client (Java 11+)
     */
    private static void httpClientExamples() {
        System.out.println("2. HTTP Client Examples:");
        
        try {
            // Create HTTP client
            var client = HttpClient.newBuilder()
                                 .connectTimeout(Duration.ofSeconds(10))
                                 .build();
            
            // GET request
            var request = HttpRequest.newBuilder()
                                   .uri(URI.create("https://httpbin.org/get"))
                                   .timeout(Duration.ofSeconds(30))
                                   .header("User-Agent", "JavaStud/2.0")
                                   .GET()
                                   .build();
            
            // Send request
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("HTTP Status: " + response.statusCode());
            System.out.println("Response Headers: " + response.headers());
            System.out.println("Response Body (first 200 chars): " + 
                             response.body().substring(0, Math.min(200, response.body().length())) + "...");
            
            // POST request example
            var postRequest = HttpRequest.newBuilder()
                                       .uri(URI.create("https://httpbin.org/post"))
                                       .header("Content-Type", "application/json")
                                       .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"JavaStud\",\"version\":\"2.0\"}"))
                                       .build();
            
            var postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("POST Status: " + postResponse.statusCode());
            
        } catch (Exception e) {
            System.out.println("HTTP Client error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates new String methods in Java 11
     */
    private static void newStringMethods() {
        System.out.println("3. New String Methods:");
        
        var text = "  Hello, Java 11!  ";
        
        // strip() - removes leading and trailing whitespace
        var stripped = text.strip();
        System.out.println("Original: '" + text + "'");
        System.out.println("Stripped: '" + stripped + "'");
        
        // stripLeading() and stripTrailing()
        var leadingStripped = text.stripLeading();
        var trailingStripped = text.stripTrailing();
        System.out.println("Leading stripped: '" + leadingStripped + "'");
        System.out.println("Trailing stripped: '" + trailingStripped + "'");
        
        // isBlank() - checks if string is empty or contains only whitespace
        var blankString = "   \t\n   ";
        var nonBlankString = "Hello";
        System.out.println("Is blank: " + blankString.isBlank());
        System.out.println("Is not blank: " + nonBlankString.isBlank());
        
        // isEmpty() - checks if string is empty (existed before, but commonly used with isBlank)
        var emptyString = "";
        System.out.println("Is empty: " + emptyString.isEmpty());
        System.out.println("Is blank: " + emptyString.isBlank());
        
        // lines() - returns stream of lines
        var multiLineText = "Line 1\nLine 2\nLine 3";
        var lines = multiLineText.lines().collect(Collectors.toList());
        System.out.println("Lines: " + lines);
        
        // repeat() - repeats string n times
        var repeated = "Java ".repeat(3);
        System.out.println("Repeated: '" + repeated + "'");
        
        // indent() - adds indentation
        var indented = "Hello\nWorld".indent(4);
        System.out.println("Indented:\n" + indented);
        
        // transform() - applies function to string
        var transformed = text.transform(String::toUpperCase)
                             .transform(s -> s.replace("JAVA", "MODERN JAVA"));
        System.out.println("Transformed: '" + transformed + "'");
        
        System.out.println();
    }
    
    /**
     * Demonstrates Collection Factory Methods
     */
    private static void collectionFactoryMethods() {
        System.out.println("4. Collection Factory Methods:");
        
        // List.of() - creates immutable list
        var immutableList = List.of("Alice", "Bob", "Charlie");
        System.out.println("Immutable list: " + immutableList);
        
        // Set.of() - creates immutable set
        var immutableSet = java.util.Set.of("Apple", "Banana", "Cherry");
        System.out.println("Immutable set: " + immutableSet);
        
        // Map.of() - creates immutable map
        var immutableMap = java.util.Map.of(
            "name", "JavaStud",
            "version", "2.0",
            "language", "Java"
        );
        System.out.println("Immutable map: " + immutableMap);
        
        // Map.ofEntries() - for more than 10 entries
        var largeMap = java.util.Map.ofEntries(
            java.util.Map.entry("key1", "value1"),
            java.util.Map.entry("key2", "value2"),
            java.util.Map.entry("key3", "value3"),
            java.util.Map.entry("key4", "value4"),
            java.util.Map.entry("key5", "value5")
        );
        System.out.println("Large map: " + largeMap);
        
        // Copy of existing collections
        var originalList = Arrays.asList("A", "B", "C");
        var copyList = List.copyOf(originalList);
        System.out.println("Copy of list: " + copyList);
        
        System.out.println();
    }
    
    /**
     * Demonstrates Files.readString/writeString
     */
    private static void filesExamples() {
        System.out.println("5. Files.readString/writeString:");
        
        try {
            // Write string to file
            var content = "Hello from Java 11!\nThis is a test file.\nCreated with Files.writeString().";
            var path = java.nio.file.Path.of("test_file.txt");
            
            java.nio.file.Files.writeString(path, content);
            System.out.println("File written successfully: " + path.toAbsolutePath());
            
            // Read string from file
            var readContent = java.nio.file.Files.readString(path);
            System.out.println("File content:\n" + readContent);
            
            // Read with charset
            var readContentUtf8 = java.nio.file.Files.readString(path, java.nio.charset.StandardCharsets.UTF_8);
            System.out.println("File content (UTF-8):\n" + readContentUtf8);
            
            // Clean up
            java.nio.file.Files.deleteIfExists(path);
            System.out.println("Test file cleaned up.");
            
        } catch (Exception e) {
            System.out.println("Files operation error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates Optional.isEmpty()
     */
    private static void optionalIsEmpty() {
        System.out.println("6. Optional.isEmpty():");
        
        var presentOptional = java.util.Optional.of("Hello");
        var emptyOptional = java.util.Optional.empty();
        
        // isEmpty() - Java 11 addition
        System.out.println("Present optional is empty: " + presentOptional.isEmpty());
        System.out.println("Empty optional is empty: " + emptyOptional.isEmpty());
        
        // Comparison with isPresent()
        System.out.println("Present optional is present: " + presentOptional.isPresent());
        System.out.println("Empty optional is present: " + emptyOptional.isPresent());
        
        // Practical usage
        var names = Arrays.asList("Alice", null, "Bob", null, "Charlie");
        var validNames = names.stream()
                             .map(java.util.Optional::ofNullable)
                             .filter(opt -> !opt.isEmpty()) // More readable than !opt.isPresent()
                             .map(java.util.Optional::get)
                             .collect(Collectors.toList());
        
        System.out.println("Valid names: " + validNames);
        
        System.out.println();
    }
    
    /**
     * Demonstrates Lambda Parameter Local Variable Syntax
     */
    private static void lambdaParameterLocalVariableSyntax() {
        System.out.println("7. Lambda Parameter Local Variable Syntax:");
        
        var numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Traditional lambda
        var traditionalSum = numbers.stream()
                                   .filter(n -> n % 2 == 0)
                                   .mapToInt(n -> n * n)
                                   .sum();
        
        // Lambda with var (Java 11+)
        var modernSum = numbers.stream()
                              .filter((var n) -> n % 2 == 0)
                              .mapToInt((var n) -> n * n)
                              .sum();
        
        System.out.println("Traditional sum: " + traditionalSum);
        System.out.println("Modern sum: " + modernSum);
        
        // Multiple parameters with var
        var pairs = Arrays.asList(
            new Pair("Alice", 25),
            new Pair("Bob", 30),
            new Pair("Charlie", 35)
        );
        
        var filteredPairs = pairs.stream()
                                .filter((var p) -> p.age > 25)
                                .map((var p) -> p.name.toUpperCase())
                                .collect(Collectors.toList());
        
        System.out.println("Filtered pairs: " + filteredPairs);
        
        System.out.println();
    }
    
    /**
     * Demonstrates Nest-Based Access Control
     */
    private static void nestBasedAccessControl() {
        System.out.println("8. Nest-Based Access Control:");
        
        // Create instances of nested classes
        var outer = new OuterClass();
        var inner = outer.new InnerClass();
        var nested = new OuterClass.NestedClass();
        
        // Access private members from nested classes
        outer.testNestAccess();
        inner.accessOuterPrivate();
        nested.accessOuterPrivate();
        
        System.out.println();
    }
    
    /**
     * Helper class for lambda examples
     */
    private static class Pair {
        String name;
        int age;
        
        Pair(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
    
    /**
     * Example class demonstrating nest-based access control
     */
    private static class OuterClass {
        private String privateField = "Private field from OuterClass";
        
        public void testNestAccess() {
            System.out.println("OuterClass can access its own private field: " + privateField);
        }
        
        class InnerClass {
            public void accessOuterPrivate() {
                System.out.println("InnerClass can access outer private field: " + privateField);
            }
        }
        
        static class NestedClass {
            public void accessOuterPrivate() {
                // This would not work - static nested class cannot access instance members
                // System.out.println("NestedClass cannot access outer private field: " + privateField);
                System.out.println("NestedClass is static and cannot access instance members");
            }
        }
    }
}
