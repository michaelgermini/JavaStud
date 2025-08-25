# Modern Java Module (Java 8+)

This module contains comprehensive examples of modern Java features introduced in Java 8 and later versions. These examples demonstrate functional programming concepts and modern Java best practices.

## 🚀 Features Covered

### 1. **Lambda Expressions** (`LambdaExpressions.java`)
- Basic lambda syntax
- Built-in functional interfaces (Predicate, Function, Consumer, Supplier)
- Method references
- Custom functional interfaces
- Lambda with collections and streams

### 2. **Streams API** (`StreamsAPI.java`)
- Stream creation and operations
- Intermediate operations (filter, map, flatMap, distinct, sorted)
- Terminal operations (collect, reduce, forEach, findFirst)
- Stream collectors
- Parallel streams
- Custom collectors
- Advanced stream operations

### 3. **Optional Class** (`OptionalExamples.java`)
- Creating Optional objects
- Safe value access methods
- Functional operations with Optional
- Optional with streams
- Real-world use cases
- Best practices for null handling
- Advanced Optional usage patterns

## 📋 Prerequisites

- **Java 8 or higher** (recommended: Java 11 LTS or Java 17 LTS)
- **Maven** (for dependency management)
- **IDE** with Java 8+ support (Eclipse, IntelliJ IDEA, VS Code)

## 🛠️ Setup

### Using Maven
```bash
# Navigate to project root
cd JavaStud

# Compile the project
mvn compile

# Run specific examples
mvn exec:java -Dexec.mainClass="modernjava.LambdaExpressions"
mvn exec:java -Dexec.mainClass="modernjava.StreamsAPI"
mvn exec:java -Dexec.mainClass="modernjava.OptionalExamples"
```

### Using IDE
1. Import the project into your IDE
2. Ensure Java 8+ is configured as the project SDK
3. Run any of the main classes in the `modernjava` package

## 📚 Learning Path

### Beginner Level
1. Start with `LambdaExpressions.java` - Basic lambda syntax
2. Learn about functional interfaces
3. Understand method references

### Intermediate Level
1. Study `StreamsAPI.java` - Stream operations
2. Practice with intermediate and terminal operations
3. Learn about collectors and parallel streams

### Advanced Level
1. Master `OptionalExamples.java` - Null safety
2. Understand Optional as a monad
3. Practice advanced patterns and best practices

## 🎯 Key Concepts

### Functional Programming
- **Immutability**: Operations don't modify original data
- **Pure Functions**: Same input always produces same output
- **Higher-order Functions**: Functions that take/return other functions

### Stream Operations
- **Lazy Evaluation**: Operations are only executed when needed
- **Pipeline**: Chain multiple operations together
- **Parallel Processing**: Automatic parallelization with `.parallelStream()`

### Null Safety
- **Explicit Handling**: Optional forces explicit null handling
- **Functional Approach**: Use functional methods instead of null checks
- **Composability**: Chain operations safely

## 💡 Best Practices

### Lambda Expressions
```java
// ✅ Good - Clear and concise
names.stream()
     .filter(name -> name.length() > 3)
     .map(String::toUpperCase)
     .forEach(System.out::println);

// ❌ Avoid - Overly complex lambdas
names.stream().forEach(name -> {
    if (name.length() > 3) {
        String upper = name.toUpperCase();
        System.out.println(upper);
    }
});
```

### Streams API
```java
// ✅ Good - Use appropriate collectors
List<String> result = stream.collect(Collectors.toList());

// ✅ Good - Use method references when possible
stream.map(String::toUpperCase).forEach(System.out::println);

// ❌ Avoid - Don't use streams for simple operations
stream.forEach(item -> list.add(item)); // Use collect() instead
```

### Optional
```java
// ✅ Good - Use Optional for method returns
public Optional<User> findUser(int id) {
    return Optional.ofNullable(userRepository.findById(id));
}

// ✅ Good - Chain operations safely
user.flatMap(User::getEmail)
    .filter(email -> email.contains("@"))
    .ifPresent(System.out::println);

// ❌ Avoid - Don't use Optional for simple null checks
Optional.ofNullable(simpleString).ifPresent(System.out::println);
```

## 🔧 Examples Usage

### Running Examples
```bash
# Run all examples
java -cp target/classes modernjava.LambdaExpressions
java -cp target/classes modernjava.StreamsAPI
java -cp target/classes modernjava.OptionalExamples
```

### Expected Output
Each example will demonstrate:
- Clear explanations of concepts
- Practical code examples
- Expected output for each operation
- Best practices and common pitfalls

## 📖 Additional Resources

### Official Documentation
- [Java 8 Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Java 8 Streams](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
- [Java 8 Optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)

### Books
- "Java 8 in Action" by Raoul-Gabriel Urma, Mario Fusco, and Alan Mycroft
- "Effective Java" by Joshua Bloch (3rd Edition)
- "Functional Programming in Java" by Pierre-Yves Saumont

### Online Courses
- [Java 8 Lambda and Streams](https://www.udemy.com/course/java-8-lambda-streams/)
- [Modern Java Development](https://www.coursera.org/specializations/java-programming)

## 🤝 Contributing

To add more modern Java examples:

1. Create a new Java file in the `modernjava` package
2. Follow the existing naming convention
3. Include comprehensive JavaDoc comments
4. Add practical examples with clear explanations
5. Update this README with new features

## 🚀 Next Steps

After mastering these concepts, explore:

1. **Java 9+ Features**:
   - Modules system
   - Process API
   - HTTP Client
   - Collection factory methods

2. **Java 11+ Features**:
   - Local Variable Syntax for Lambda Parameters
   - String methods
   - HTTP Client (stable)

3. **Java 17+ Features**:
   - Pattern Matching for switch
   - Sealed Classes
   - Record Classes
   - Text Blocks

4. **Advanced Topics**:
   - Reactive Programming
   - CompletableFuture
   - Custom collectors
   - Performance optimization

---

**Happy Learning! 🎉**

*This module is designed to help you master modern Java programming concepts and prepare you for contemporary Java development practices.*
