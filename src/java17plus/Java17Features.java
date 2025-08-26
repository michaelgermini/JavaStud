package java17plus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Java 17+ Features Examples
 * 
 * This class demonstrates the key features introduced in Java 17 and later versions:
 * - Records (immutable data classes)
 * - Pattern Matching for switch expressions
 * - Sealed Classes and Interfaces
 * - Enhanced Switch Expressions
 * - Text Blocks (Java 15+)
 * - Foreign Function & Memory API (preview)
 * - Vector API (preview)
 */
public class Java17Features {
    
    // ==================== RECORDS ====================
    
    /**
     * Records provide a concise way to create immutable data classes.
     * They automatically generate constructor, getters, equals(), hashCode(), and toString().
     */
    public record Person(String name, int age, String email) {
        // Compact constructor for validation
        public Person {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }
        
        // Custom method in record
        public boolean isAdult() {
            return age >= 18;
        }
    }
    
    public record Address(String street, String city, String country) {}
    
    public record Employee(Person person, Address address, double salary) {
        // Nested record usage
        public String getFullAddress() {
            return String.format("%s, %s, %s", address.street(), address.city(), address.country());
        }
    }
    
    // ==================== SEALED CLASSES ====================
    
    /**
     * Sealed classes restrict which classes can extend them.
     * This provides better control over inheritance hierarchies.
     */
    public sealed abstract class Shape permits Circle, Rectangle, Triangle {
        public abstract double area();
        public abstract double perimeter();
    }
    
    public final class Circle extends Shape {
        private final double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
        
        @Override
        public double perimeter() {
            return 2 * Math.PI * radius;
        }
        
        public double getRadius() {
            return radius;
        }
    }
    
    public final class Rectangle extends Shape {
        private final double width;
        private final double height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public double area() {
            return width * height;
        }
        
        @Override
        public double perimeter() {
            return 2 * (width + height);
        }
        
        public double getWidth() { return width; }
        public double getHeight() { return height; }
    }
    
    public final class Triangle extends Shape {
        private final double a, b, c;
        
        public Triangle(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public double area() {
            // Heron's formula
            double s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
        
        @Override
        public double perimeter() {
            return a + b + c;
        }
    }
    
    // ==================== PATTERN MATCHING ====================
    
    /**
     * Pattern matching simplifies type checking and casting.
     * Available in switch expressions and instanceof checks.
     */
    public String getShapeInfo(Shape shape) {
        return switch (shape) {
            case Circle c -> String.format("Circle with radius %.2f, area: %.2f", 
                                         c.getRadius(), c.area());
            case Rectangle r -> String.format("Rectangle %sx%s, area: %.2f", 
                                            r.getWidth(), r.getHeight(), r.area());
            case Triangle t -> String.format("Triangle with perimeter %.2f, area: %.2f", 
                                           t.perimeter(), t.area());
        };
    }
    
    public String processObject(Object obj) {
        return switch (obj) {
            case String s when s.length() > 10 -> "Long string: " + s.substring(0, 10) + "...";
            case String s -> "Short string: " + s;
            case Integer i when i > 0 -> "Positive integer: " + i;
            case Integer i when i < 0 -> "Negative integer: " + i;
            case Integer i -> "Zero";
            case List<?> list when list.isEmpty() -> "Empty list";
            case List<?> list -> "List with " + list.size() + " elements";
            case null -> "Null object";
            default -> "Unknown object: " + obj.getClass().getSimpleName();
        };
    }
    
    // ==================== ENHANCED SWITCH EXPRESSIONS ====================
    
    public String getDayType(String day) {
        return switch (day.toLowerCase()) {
            case "monday", "tuesday", "wednesday", "thursday", "friday" -> "Weekday";
            case "saturday", "sunday" -> "Weekend";
            default -> "Unknown day";
        };
    }
    
    public int getDaysInMonth(String month) {
        return switch (month.toLowerCase()) {
            case "january", "march", "may", "july", "august", "october", "december" -> 31;
            case "april", "june", "september", "november" -> 30;
            case "february" -> 28; // Simplified, ignoring leap years
            default -> throw new IllegalArgumentException("Unknown month: " + month);
        };
    }
    
    // ==================== TEXT BLOCKS ====================
    
    /**
     * Text blocks provide a cleaner way to write multi-line strings.
     * Available since Java 15.
     */
    public String generateHtmlTemplate(String title, String content) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>%s</title>
                <meta charset="UTF-8">
            </head>
            <body>
                <h1>%s</h1>
                <div class="content">
                    %s
                </div>
            </body>
            </html>
            """.formatted(title, title, content);
    }
    
    public String generateJsonTemplate() {
        return """
            {
                "name": "John Doe",
                "age": 30,
                "email": "john.doe@example.com",
                "address": {
                    "street": "123 Main St",
                    "city": "Anytown",
                    "country": "USA"
                },
                "hobbies": [
                    "reading",
                    "swimming",
                    "coding"
                ]
            }
            """;
    }
    
    // ==================== ENHANCED INSTANCEOF ====================
    
    public String processValue(Object value) {
        if (value instanceof String s && s.length() > 5) {
            return "Long string: " + s.toUpperCase();
        } else if (value instanceof String s) {
            return "Short string: " + s.toLowerCase();
        } else if (value instanceof Integer i && i > 100) {
            return "Large number: " + (i * 2);
        } else if (value instanceof Integer i) {
            return "Small number: " + i;
        } else if (value instanceof List<?> list && !list.isEmpty()) {
            return "Non-empty list with " + list.size() + " elements";
        } else if (value instanceof List<?> list) {
            return "Empty list";
        }
        return "Unknown type: " + (value != null ? value.getClass().getSimpleName() : "null");
    }
    
    // ==================== PRACTICAL EXAMPLES ====================
    
    public void demonstrateRecords() {
        System.out.println("=== Records Demo ===");
        
        Person person = new Person("Alice", 25, "alice@example.com");
        Address address = new Address("123 Main St", "Anytown", "USA");
        Employee employee = new Employee(person, address, 75000.0);
        
        System.out.println("Person: " + person);
        System.out.println("Is adult: " + person.isAdult());
        System.out.println("Employee address: " + employee.getFullAddress());
        System.out.println();
    }
    
    public void demonstrateSealedClasses() {
        System.out.println("=== Sealed Classes Demo ===");
        
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0, 5.0);
        
        System.out.println(getShapeInfo(circle));
        System.out.println(getShapeInfo(rectangle));
        System.out.println(getShapeInfo(triangle));
        System.out.println();
    }
    
    public void demonstratePatternMatching() {
        System.out.println("=== Pattern Matching Demo ===");
        
        System.out.println(processObject("Hello World"));
        System.out.println(processObject("Hi"));
        System.out.println(processObject(42));
        System.out.println(processObject(-10));
        System.out.println(processObject(List.of("a", "b", "c")));
        System.out.println(processObject(List.of()));
        System.out.println();
    }
    
    public void demonstrateSwitchExpressions() {
        System.out.println("=== Switch Expressions Demo ===");
        
        System.out.println("Monday is a: " + getDayType("Monday"));
        System.out.println("Saturday is a: " + getDayType("Saturday"));
        System.out.println("January has " + getDaysInMonth("January") + " days");
        System.out.println("February has " + getDaysInMonth("February") + " days");
        System.out.println();
    }
    
    public void demonstrateTextBlocks() {
        System.out.println("=== Text Blocks Demo ===");
        
        String html = generateHtmlTemplate("My Page", "This is the content");
        System.out.println("Generated HTML:");
        System.out.println(html);
        
        String json = generateJsonTemplate();
        System.out.println("Generated JSON:");
        System.out.println(json);
        System.out.println();
    }
    
    public void demonstrateEnhancedInstanceof() {
        System.out.println("=== Enhanced instanceof Demo ===");
        
        System.out.println(processValue("Hello World"));
        System.out.println(processValue("Hi"));
        System.out.println(processValue(150));
        System.out.println(processValue(50));
        System.out.println(processValue(List.of("a", "b")));
        System.out.println(processValue(List.of()));
        System.out.println();
    }
    
    // ==================== MAIN METHOD ====================
    
    public static void main(String[] args) {
        Java17Features demo = new Java17Features();
        
        demo.demonstrateRecords();
        demo.demonstrateSealedClasses();
        demo.demonstratePatternMatching();
        demo.demonstrateSwitchExpressions();
        demo.demonstrateTextBlocks();
        demo.demonstrateEnhancedInstanceof();
        
        System.out.println("Java 17+ Features demonstration completed!");
    }
}
