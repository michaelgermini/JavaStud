package modernjava;

import java.util.*;
import java.util.function.*;

/**
 * Modern Java 8+ Optional Examples
 * 
 * This class demonstrates the Optional class including:
 * - Creating Optional objects
 * - Safe value access methods
 * - Functional operations with Optional
 * - Best practices for null handling
 * - Real-world use cases
 * 
 * @author JavaStud Team
 * @version 2.0
 * @since Java 8
 */
public class OptionalExamples {
    
    public static void main(String[] args) {
        System.out.println("=== Optional Examples ===\n");
        
        // 1. Creating Optional Objects
        creatingOptionals();
        
        // 2. Safe Value Access
        safeValueAccess();
        
        // 3. Functional Operations
        functionalOperations();
        
        // 4. Optional with Streams
        optionalWithStreams();
        
        // 5. Real-world Examples
        realWorldExamples();
        
        // 6. Best Practices
        bestPractices();
        
        // 7. Advanced Optional Usage
        advancedOptionalUsage();
    }
    
    /**
     * Demonstrates different ways to create Optional objects
     */
    private static void creatingOptionals() {
        System.out.println("1. Creating Optional Objects:");
        
        // Empty Optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Empty optional: " + emptyOptional);
        
        // Optional with value
        Optional<String> presentOptional = Optional.of("Hello Optional!");
        System.out.println("Present optional: " + presentOptional);
        
        // Optional.ofNullable - handles null values
        String nullString = null;
        Optional<String> nullableOptional = Optional.ofNullable(nullString);
        System.out.println("Nullable optional (null): " + nullableOptional);
        
        String nonNullString = "Not null";
        Optional<String> nonNullOptional = Optional.ofNullable(nonNullString);
        System.out.println("Nullable optional (not null): " + nonNullOptional);
        
        System.out.println();
    }
    
    /**
     * Demonstrates safe ways to access Optional values
     */
    private static void safeValueAccess() {
        System.out.println("2. Safe Value Access:");
        
        Optional<String> presentOptional = Optional.of("Hello World");
        Optional<String> emptyOptional = Optional.empty();
        
        // isPresent() - check if value exists
        System.out.println("Present optional has value: " + presentOptional.isPresent());
        System.out.println("Empty optional has value: " + emptyOptional.isPresent());
        
        // isEmpty() - check if value is missing (Java 11+)
        System.out.println("Present optional is empty: " + presentOptional.isEmpty());
        System.out.println("Empty optional is empty: " + emptyOptional.isEmpty());
        
        // get() - unsafe access (throws exception if empty)
        try {
            String value = presentOptional.get();
            System.out.println("Got value: " + value);
            
            // This will throw NoSuchElementException
            String emptyValue = emptyOptional.get();
        } catch (NoSuchElementException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        
        // orElse() - provide default value
        String presentValue = presentOptional.orElse("Default Value");
        String emptyValue = emptyOptional.orElse("Default Value");
        System.out.println("Present orElse: " + presentValue);
        System.out.println("Empty orElse: " + emptyValue);
        
        // orElseGet() - provide default value via supplier
        String presentValueGet = presentOptional.orElseGet(() -> "Computed Default");
        String emptyValueGet = emptyOptional.orElseGet(() -> "Computed Default");
        System.out.println("Present orElseGet: " + presentValueGet);
        System.out.println("Empty orElseGet: " + emptyValueGet);
        
        // orElseThrow() - throw exception if empty
        try {
            String presentValueThrow = presentOptional.orElseThrow(() -> 
                new RuntimeException("Value not found"));
            System.out.println("Present orElseThrow: " + presentValueThrow);
            
            String emptyValueThrow = emptyOptional.orElseThrow(() -> 
                new RuntimeException("Value not found"));
        } catch (RuntimeException e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates functional operations with Optional
     */
    private static void functionalOperations() {
        System.out.println("3. Functional Operations:");
        
        Optional<String> optional = Optional.of("hello world");
        Optional<String> emptyOptional = Optional.empty();
        
        // map() - transform value if present
        Optional<String> upperCase = optional.map(String::toUpperCase);
        Optional<String> emptyUpperCase = emptyOptional.map(String::toUpperCase);
        System.out.println("Mapped to uppercase: " + upperCase);
        System.out.println("Empty mapped: " + emptyUpperCase);
        
        // flatMap() - transform to another Optional
        Optional<Integer> length = optional.flatMap(s -> Optional.of(s.length()));
        Optional<Integer> emptyLength = emptyOptional.flatMap(s -> Optional.of(s.length()));
        System.out.println("Flat mapped length: " + length);
        System.out.println("Empty flat mapped: " + emptyLength);
        
        // filter() - filter based on condition
        Optional<String> filtered = optional.filter(s -> s.length() > 5);
        Optional<String> emptyFiltered = emptyOptional.filter(s -> s.length() > 5);
        System.out.println("Filtered (length > 5): " + filtered);
        System.out.println("Empty filtered: " + emptyFiltered);
        
        // ifPresent() - execute action if value present
        optional.ifPresent(s -> System.out.println("Value is present: " + s));
        emptyOptional.ifPresent(s -> System.out.println("This won't print"));
        
        // ifPresentOrElse() - execute different actions (Java 9+)
        optional.ifPresentOrElse(
            s -> System.out.println("Value present: " + s),
            () -> System.out.println("No value present")
        );
        emptyOptional.ifPresentOrElse(
            s -> System.out.println("This won't print"),
            () -> System.out.println("No value present")
        );
        
        System.out.println();
    }
    
    /**
     * Demonstrates Optional with Streams
     */
    private static void optionalWithStreams() {
        System.out.println("4. Optional with Streams:");
        
        List<Optional<String>> optionals = Arrays.asList(
            Optional.of("Alice"),
            Optional.empty(),
            Optional.of("Bob"),
            Optional.empty(),
            Optional.of("Charlie")
        );
        
        // Filter out empty optionals and get values
        List<String> presentValues = optionals.stream()
                                             .filter(Optional::isPresent)
                                             .map(Optional::get)
                                             .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Present values: " + presentValues);
        
        // Alternative using flatMap
        List<String> flatMappedValues = optionals.stream()
                                                .flatMap(opt -> opt.stream())
                                                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Flat mapped values: " + flatMappedValues);
        
        // Find first present value
        Optional<String> firstPresent = optionals.stream()
                                                .filter(Optional::isPresent)
                                                .map(Optional::get)
                                                .findFirst();
        firstPresent.ifPresent(s -> System.out.println("First present: " + s));
        
        // Count present values
        long presentCount = optionals.stream()
                                   .filter(Optional::isPresent)
                                   .count();
        System.out.println("Present count: " + presentCount);
        
        System.out.println();
    }
    
    /**
     * Demonstrates real-world use cases
     */
    private static void realWorldExamples() {
        System.out.println("5. Real-world Examples:");
        
        // User lookup example
        Optional<User> user = findUserById(123);
        String userName = user.map(User::getName)
                             .orElse("Unknown User");
        System.out.println("User name: " + userName);
        
        // Configuration example
        Optional<String> configValue = getConfigValue("database.url");
        String dbUrl = configValue.orElse("localhost:3306");
        System.out.println("Database URL: " + dbUrl);
        
        // Validation example
        Optional<String> email = Optional.of("user@example.com");
        boolean isValidEmail = email.filter(e -> e.contains("@"))
                                   .filter(e -> e.contains("."))
                                   .isPresent();
        System.out.println("Valid email: " + isValidEmail);
        
        // Chaining operations
        Optional<String> result = Optional.of("hello")
                                         .map(String::toUpperCase)
                                         .filter(s -> s.length() > 3)
                                         .map(s -> "Processed: " + s);
        result.ifPresent(System.out::println);
        
        System.out.println();
    }
    
    /**
     * Demonstrates best practices for using Optional
     */
    private static void bestPractices() {
        System.out.println("6. Best Practices:");
        
        // ❌ Don't use Optional for simple null checks
        String simpleString = "hello";
        // Bad: Optional.ofNullable(simpleString).ifPresent(System.out::println);
        // Good: 
        if (simpleString != null) {
            System.out.println(simpleString);
        }
        
        // ✅ Use Optional for method return values
        Optional<String> result = processData("input");
        result.ifPresent(System.out::println);
        
        // ✅ Use Optional for chaining operations
        Optional<String> chained = Optional.of("data")
                                          .map(String::toUpperCase)
                                          .filter(s -> s.length() > 2)
                                          .map(s -> "Result: " + s);
        chained.ifPresent(System.out::println);
        
        // ✅ Use Optional with collections
        List<String> names = Arrays.asList("Alice", "Bob", null, "Charlie");
        List<String> validNames = names.stream()
                                      .map(Optional::ofNullable)
                                      .filter(Optional::isPresent)
                                      .map(Optional::get)
                                      .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Valid names: " + validNames);
        
        // ✅ Use Optional for default values
        String config = getConfigValue("app.name").orElse("Default App");
        System.out.println("App name: " + config);
        
        System.out.println();
    }
    
    /**
     * Demonstrates advanced Optional usage patterns
     */
    private static void advancedOptionalUsage() {
        System.out.println("7. Advanced Optional Usage:");
        
        // Optional as a monad
        Optional<Integer> result = Optional.of(5)
                                          .map(x -> x * 2)
                                          .map(x -> x + 1)
                                          .filter(x -> x > 10);
        result.ifPresent(x -> System.out.println("Monad result: " + x));
        
        // Optional with multiple conditions
        Optional<String> complexResult = Optional.of("test@example.com")
                                                .filter(email -> email.contains("@"))
                                                .filter(email -> email.contains("."))
                                                .map(String::toLowerCase)
                                                .filter(email -> email.length() > 10);
        complexResult.ifPresent(email -> System.out.println("Valid email: " + email));
        
        // Optional with exception handling
        Optional<String> safeResult = Optional.of("risky operation")
                                             .map(s -> {
                                                 if (s.contains("risky")) {
                                                     throw new RuntimeException("Risky operation failed");
                                                 }
                                                 return s;
                                             })
                                             .or(() -> Optional.of("fallback"));
        safeResult.ifPresent(System.out::println);
        
        // Optional with custom logic
        Optional<String> customResult = Optional.of("input")
                                               .flatMap(input -> {
                                                   if (input.length() > 3) {
                                                       return Optional.of(input.toUpperCase());
                                                   } else {
                                                       return Optional.empty();
                                                   }
                                               });
        customResult.ifPresent(System.out::println);
        
        System.out.println();
    }
    
    // Helper methods for examples
    private static Optional<User> findUserById(int id) {
        // Simulate database lookup
        if (id == 123) {
            return Optional.of(new User("John Doe", "john@example.com"));
        }
        return Optional.empty();
    }
    
    private static Optional<String> getConfigValue(String key) {
        // Simulate configuration lookup
        Map<String, String> config = Map.of(
            "database.url", "mysql://localhost:3306",
            "app.name", "MyApp"
        );
        return Optional.ofNullable(config.get(key));
    }
    
    private static Optional<String> processData(String input) {
        // Simulate data processing
        if (input != null && !input.isEmpty()) {
            return Optional.of("Processed: " + input);
        }
        return Optional.empty();
    }
}

/**
 * User class for demonstrating Optional usage
 */
class User {
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public String getName() { return name; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }
}
