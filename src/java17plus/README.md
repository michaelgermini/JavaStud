# Java 17+ Features Module

This module demonstrates the latest features introduced in Java 17 and later versions, showcasing modern Java programming practices and capabilities.

## 🚀 Features Covered

### 1. Records (Java 14+)
- **Immutable Data Classes**: Concise way to create data carriers
- **Automatic Generation**: Constructor, getters, equals(), hashCode(), toString()
- **Compact Constructors**: Built-in validation
- **Nested Records**: Complex data structures

### 2. Pattern Matching (Java 16+)
- **Switch Expressions**: Type-safe pattern matching
- **Guarded Patterns**: Conditional pattern matching with `when` clauses
- **Enhanced instanceof**: Simplified type checking and casting
- **Null Patterns**: Explicit null handling

### 3. Sealed Classes (Java 17+)
- **Controlled Inheritance**: Restrict which classes can extend
- **Exhaustive Pattern Matching**: Compile-time safety
- **Domain Modeling**: Better representation of business domains

### 4. Enhanced Switch Expressions (Java 14+)
- **Expression-based**: Switch as expressions, not just statements
- **Arrow Syntax**: Modern lambda-style syntax
- **Multiple Labels**: Combine multiple cases
- **Exhaustive Matching**: Compile-time completeness checking

### 5. Text Blocks (Java 15+)
- **Multi-line Strings**: Clean, readable string literals
- **HTML/JSON Templates**: Perfect for template generation
- **Preserved Formatting**: Maintains indentation and structure
- **String Interpolation**: Using `.formatted()` method

## 📁 Project Structure

```
src/java17plus/
├── Java17Features.java          # Main demonstration class
├── README.md                    # This file
└── ../../test/java17plus/
    └── Java17FeaturesTest.java  # Comprehensive test suite
```

## 🛠️ Prerequisites

- **Java 17 or later** (LTS recommended)
- **Maven 3.6+** for build management
- **IDE Support**: IntelliJ IDEA, Eclipse, or VS Code

## 🚀 Quick Start

### 1. Compile and Run
```bash
# Compile with Java 17
javac -source 17 -target 17 src/java17plus/Java17Features.java

# Run the demonstration
java -cp src java17plus.Java17Features
```

### 2. Run with Maven
```bash
# Compile and test
mvn clean compile test

# Run specific test class
mvn test -Dtest=Java17FeaturesTest
```

## 📚 Learning Path

### Beginner Level
1. **Records**: Start with simple data classes
   - Basic record declaration
   - Accessing record components
   - Custom methods in records

2. **Text Blocks**: Multi-line string handling
   - Basic text block syntax
   - HTML/JSON template generation
   - String formatting

### Intermediate Level
3. **Enhanced Switch Expressions**: Modern control flow
   - Arrow syntax
   - Multiple case labels
   - Expression-based switches

4. **Enhanced instanceof**: Simplified type checking
   - Pattern matching with instanceof
   - Combined conditions
   - Type casting automation

### Advanced Level
5. **Pattern Matching**: Advanced type handling
   - Switch expressions with patterns
   - Guarded patterns
   - Null pattern handling

6. **Sealed Classes**: Domain modeling
   - Sealed class hierarchy
   - Exhaustive pattern matching
   - Business domain representation

## 🔑 Key Concepts

### Records
```java
// Simple record
public record Person(String name, int age, String email) {
    // Compact constructor for validation
    public Person {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
    
    // Custom method
    public boolean isAdult() {
        return age >= 18;
    }
}
```

### Pattern Matching
```java
// Switch expression with patterns
public String getShapeInfo(Shape shape) {
    return switch (shape) {
        case Circle c -> String.format("Circle with radius %.2f", c.getRadius());
        case Rectangle r -> String.format("Rectangle %sx%s", r.getWidth(), r.getHeight());
        case Triangle t -> String.format("Triangle with perimeter %.2f", t.perimeter());
    };
}
```

### Sealed Classes
```java
// Sealed class hierarchy
public sealed abstract class Shape permits Circle, Rectangle, Triangle {
    public abstract double area();
    public abstract double perimeter();
}

public final class Circle extends Shape {
    // Implementation
}
```

### Text Blocks
```java
// Multi-line string with preserved formatting
String html = """
    <!DOCTYPE html>
    <html>
        <head>
            <title>%s</title>
        </head>
        <body>
            <h1>%s</h1>
        </body>
    </html>
    """.formatted(title, title);
```

## 🧪 Testing

The module includes comprehensive JUnit 5 tests covering:

- **Record Testing**: Creation, validation, equality, nested records
- **Sealed Class Testing**: Polymorphism, inheritance, method calls
- **Pattern Matching Testing**: Switch expressions, instanceof patterns
- **Text Block Testing**: Template generation, formatting
- **Edge Cases**: Boundary conditions, error scenarios
- **Integration Testing**: Complete feature demonstrations

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=Java17FeaturesTest

# Run with coverage
mvn test jacoco:report
```

## 💡 Best Practices

### Records
- Use for immutable data carriers
- Add validation in compact constructors
- Keep records simple and focused
- Use nested records for complex structures

### Pattern Matching
- Prefer switch expressions over traditional switches
- Use guarded patterns for complex conditions
- Handle null cases explicitly
- Ensure exhaustive matching

### Sealed Classes
- Design clear inheritance hierarchies
- Use for domain modeling
- Leverage exhaustive pattern matching
- Keep the hierarchy small and focused

### Text Blocks
- Use for multi-line strings
- Preserve formatting for readability
- Combine with string interpolation
- Perfect for templates and configuration

## 🔍 Code Quality

The module follows modern Java coding standards:

- **JavaDoc**: Comprehensive documentation
- **Naming Conventions**: Clear, descriptive names
- **Error Handling**: Proper exception management
- **Testing**: High test coverage
- **Modularity**: Well-organized code structure

## 📖 Additional Resources

### Official Documentation
- [Java 17 Release Notes](https://openjdk.java.net/projects/jdk/17/)
- [Records Specification](https://openjdk.java.net/jeps/395)
- [Pattern Matching Specification](https://openjdk.java.net/jeps/406)
- [Sealed Classes Specification](https://openjdk.java.net/jeps/409)

### Learning Resources
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [Baeldung Java Guides](https://www.baeldung.com/)
- [Java Code Geeks](https://www.javacodegeeks.com/)

### Tools and IDEs
- **IntelliJ IDEA**: Excellent Java 17+ support
- **Eclipse**: Good support with recent versions
- **VS Code**: Java Extension Pack
- **Maven**: Build automation and dependency management

## 🎯 Next Steps

After mastering Java 17+ features, consider exploring:

1. **Java 21 LTS**: Latest long-term support version
2. **Spring Boot 3**: Modern web development
3. **Microservices**: Distributed system architecture
4. **Reactive Programming**: Non-blocking applications
5. **Cloud Native**: Containerization and orchestration

## 🤝 Contributing

To contribute to this module:

1. Follow the existing code style
2. Add comprehensive tests for new features
3. Update documentation
4. Ensure all tests pass
5. Submit pull requests with clear descriptions

---

**Happy coding with modern Java! 🚀**
