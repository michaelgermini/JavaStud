package java17plus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * JUnit 5 Tests for Java 17+ Features
 * 
 * This test class demonstrates testing of modern Java features:
 * - Records testing
 * - Sealed classes testing
 * - Pattern matching testing
 * - Switch expressions testing
 * - Text blocks testing
 * - Enhanced instanceof testing
 */
class Java17FeaturesTest {
    
    private Java17Features demo;
    
    @BeforeEach
    void setUp() {
        demo = new Java17Features();
    }
    
    // ==================== RECORDS TESTS ====================
    
    @Test
    @DisplayName("Test Person record creation and access")
    void testPersonRecord() {
        Java17Features.Person person = new Java17Features.Person("Alice", 25, "alice@example.com");
        
        assertEquals("Alice", person.name());
        assertEquals(25, person.age());
        assertEquals("alice@example.com", person.email());
        assertTrue(person.isAdult());
        
        // Test toString
        assertTrue(person.toString().contains("Alice"));
        assertTrue(person.toString().contains("25"));
    }
    
    @Test
    @DisplayName("Test Person record validation")
    void testPersonRecordValidation() {
        // Test null name
        assertThrows(IllegalArgumentException.class, () -> {
            new Java17Features.Person(null, 25, "alice@example.com");
        });
        
        // Test empty name
        assertThrows(IllegalArgumentException.class, () -> {
            new Java17Features.Person("", 25, "alice@example.com");
        });
        
        // Test negative age
        assertThrows(IllegalArgumentException.class, () -> {
            new Java17Features.Person("Alice", -5, "alice@example.com");
        });
    }
    
    @Test
    @DisplayName("Test Address record")
    void testAddressRecord() {
        Java17Features.Address address = new Java17Features.Address("123 Main St", "Anytown", "USA");
        
        assertEquals("123 Main St", address.street());
        assertEquals("Anytown", address.city());
        assertEquals("USA", address.country());
    }
    
    @Test
    @DisplayName("Test Employee record with nested records")
    void testEmployeeRecord() {
        Java17Features.Person person = new Java17Features.Person("Bob", 30, "bob@example.com");
        Java17Features.Address address = new Java17Features.Address("456 Oak Ave", "Somewhere", "Canada");
        Java17Features.Employee employee = new Java17Features.Employee(person, address, 80000.0);
        
        assertEquals(person, employee.person());
        assertEquals(address, employee.address());
        assertEquals(80000.0, employee.salary());
        assertEquals("456 Oak Ave, Somewhere, Canada", employee.getFullAddress());
    }
    
    @Test
    @DisplayName("Test record equality")
    void testRecordEquality() {
        Java17Features.Person person1 = new Java17Features.Person("Alice", 25, "alice@example.com");
        Java17Features.Person person2 = new Java17Features.Person("Alice", 25, "alice@example.com");
        Java17Features.Person person3 = new Java17Features.Person("Bob", 25, "alice@example.com");
        
        assertEquals(person1, person2);
        assertNotEquals(person1, person3);
        assertEquals(person1.hashCode(), person2.hashCode());
    }
    
    // ==================== SEALED CLASSES TESTS ====================
    
    @Test
    @DisplayName("Test Circle sealed class")
    void testCircle() {
        Java17Features.Circle circle = new Java17Features.Circle(5.0);
        
        assertEquals(5.0, circle.getRadius());
        assertEquals(Math.PI * 25.0, circle.area(), 0.001);
        assertEquals(2 * Math.PI * 5.0, circle.perimeter(), 0.001);
    }
    
    @Test
    @DisplayName("Test Rectangle sealed class")
    void testRectangle() {
        Java17Features.Rectangle rectangle = new Java17Features.Rectangle(4.0, 6.0);
        
        assertEquals(4.0, rectangle.getWidth());
        assertEquals(6.0, rectangle.getHeight());
        assertEquals(24.0, rectangle.area(), 0.001);
        assertEquals(20.0, rectangle.perimeter(), 0.001);
    }
    
    @Test
    @DisplayName("Test Triangle sealed class")
    void testTriangle() {
        Java17Features.Triangle triangle = new Java17Features.Triangle(3.0, 4.0, 5.0);
        
        assertEquals(12.0, triangle.perimeter(), 0.001);
        assertEquals(6.0, triangle.area(), 0.001); // 3-4-5 triangle has area 6
    }
    
    @Test
    @DisplayName("Test Shape polymorphism")
    void testShapePolymorphism() {
        Java17Features.Shape circle = new Java17Features.Circle(3.0);
        Java17Features.Shape rectangle = new Java17Features.Rectangle(2.0, 3.0);
        Java17Features.Shape triangle = new Java17Features.Triangle(3.0, 4.0, 5.0);
        
        assertTrue(circle instanceof Java17Features.Circle);
        assertTrue(rectangle instanceof Java17Features.Rectangle);
        assertTrue(triangle instanceof Java17Features.Triangle);
        assertTrue(circle instanceof Java17Features.Shape);
    }
    
    // ==================== PATTERN MATCHING TESTS ====================
    
    @Test
    @DisplayName("Test pattern matching with shapes")
    void testShapePatternMatching() {
        Java17Features.Shape circle = new Java17Features.Circle(5.0);
        Java17Features.Shape rectangle = new Java17Features.Rectangle(4.0, 6.0);
        Java17Features.Shape triangle = new Java17Features.Triangle(3.0, 4.0, 5.0);
        
        String circleInfo = demo.getShapeInfo(circle);
        String rectangleInfo = demo.getShapeInfo(rectangle);
        String triangleInfo = demo.getShapeInfo(triangle);
        
        assertTrue(circleInfo.contains("Circle"));
        assertTrue(circleInfo.contains("5.00"));
        assertTrue(rectangleInfo.contains("Rectangle"));
        assertTrue(rectangleInfo.contains("4.0x6.0"));
        assertTrue(triangleInfo.contains("Triangle"));
    }
    
    @Test
    @DisplayName("Test pattern matching with different object types")
    void testObjectPatternMatching() {
        assertEquals("Long string: Hello Worl...", demo.processObject("Hello World"));
        assertEquals("Short string: Hi", demo.processObject("Hi"));
        assertEquals("Positive integer: 42", demo.processObject(42));
        assertEquals("Negative integer: -10", demo.processObject(-10));
        assertEquals("Zero", demo.processObject(0));
        assertEquals("List with 3 elements", demo.processObject(List.of("a", "b", "c")));
        assertEquals("Empty list", demo.processObject(List.of()));
        assertEquals("Null object", demo.processObject(null));
    }
    
    // ==================== SWITCH EXPRESSIONS TESTS ====================
    
    @Test
    @DisplayName("Test switch expressions for day types")
    void testDayTypeSwitch() {
        assertEquals("Weekday", demo.getDayType("Monday"));
        assertEquals("Weekday", demo.getDayType("Tuesday"));
        assertEquals("Weekday", demo.getDayType("Wednesday"));
        assertEquals("Weekday", demo.getDayType("Thursday"));
        assertEquals("Weekday", demo.getDayType("Friday"));
        assertEquals("Weekend", demo.getDayType("Saturday"));
        assertEquals("Weekend", demo.getDayType("Sunday"));
        assertEquals("Unknown day", demo.getDayType("Invalid"));
    }
    
    @Test
    @DisplayName("Test switch expressions for month days")
    void testMonthDaysSwitch() {
        assertEquals(31, demo.getDaysInMonth("January"));
        assertEquals(31, demo.getDaysInMonth("March"));
        assertEquals(31, demo.getDaysInMonth("May"));
        assertEquals(31, demo.getDaysInMonth("July"));
        assertEquals(31, demo.getDaysInMonth("August"));
        assertEquals(31, demo.getDaysInMonth("October"));
        assertEquals(31, demo.getDaysInMonth("December"));
        
        assertEquals(30, demo.getDaysInMonth("April"));
        assertEquals(30, demo.getDaysInMonth("June"));
        assertEquals(30, demo.getDaysInMonth("September"));
        assertEquals(30, demo.getDaysInMonth("November"));
        
        assertEquals(28, demo.getDaysInMonth("February"));
        
        assertThrows(IllegalArgumentException.class, () -> {
            demo.getDaysInMonth("InvalidMonth");
        });
    }
    
    // ==================== TEXT BLOCKS TESTS ====================
    
    @Test
    @DisplayName("Test HTML template generation with text blocks")
    void testHtmlTemplateGeneration() {
        String html = demo.generateHtmlTemplate("Test Page", "Test Content");
        
        assertTrue(html.contains("<!DOCTYPE html>"));
        assertTrue(html.contains("<title>Test Page</title>"));
        assertTrue(html.contains("<h1>Test Page</h1>"));
        assertTrue(html.contains("Test Content"));
        assertTrue(html.contains("</html>"));
    }
    
    @Test
    @DisplayName("Test JSON template generation with text blocks")
    void testJsonTemplateGeneration() {
        String json = demo.generateJsonTemplate();
        
        assertTrue(json.contains("\"name\": \"John Doe\""));
        assertTrue(json.contains("\"age\": 30"));
        assertTrue(json.contains("\"email\": \"john.doe@example.com\""));
        assertTrue(json.contains("\"street\": \"123 Main St\""));
        assertTrue(json.contains("\"hobbies\":"));
        assertTrue(json.contains("reading"));
        assertTrue(json.contains("swimming"));
        assertTrue(json.contains("coding"));
    }
    
    // ==================== ENHANCED INSTANCEOF TESTS ====================
    
    @Test
    @DisplayName("Test enhanced instanceof with strings")
    void testEnhancedInstanceofWithStrings() {
        assertEquals("Long string: HELLO WORLD", demo.processValue("Hello World"));
        assertEquals("Short string: hi", demo.processValue("Hi"));
    }
    
    @Test
    @DisplayName("Test enhanced instanceof with integers")
    void testEnhancedInstanceofWithIntegers() {
        assertEquals("Large number: 300", demo.processValue(150));
        assertEquals("Small number: 50", demo.processValue(50));
    }
    
    @Test
    @DisplayName("Test enhanced instanceof with lists")
    void testEnhancedInstanceofWithLists() {
        assertEquals("Non-empty list with 2 elements", demo.processValue(List.of("a", "b")));
        assertEquals("Empty list", demo.processValue(List.of()));
    }
    
    @Test
    @DisplayName("Test enhanced instanceof with unknown types")
    void testEnhancedInstanceofWithUnknownTypes() {
        assertEquals("Unknown type: Double", demo.processValue(3.14));
        assertEquals("Unknown type: Boolean", demo.processValue(true));
    }
    
    // ==================== INTEGRATION TESTS ====================
    
    @Test
    @DisplayName("Test complete demonstration flow")
    void testCompleteDemonstration() {
        // This test ensures all demonstration methods run without exceptions
        assertDoesNotThrow(() -> {
            demo.demonstrateRecords();
            demo.demonstrateSealedClasses();
            demo.demonstratePatternMatching();
            demo.demonstrateSwitchExpressions();
            demo.demonstrateTextBlocks();
            demo.demonstrateEnhancedInstanceof();
        });
    }
    
    @Test
    @DisplayName("Test main method execution")
    void testMainMethod() {
        assertDoesNotThrow(() -> {
            Java17Features.main(new String[]{});
        });
    }
    
    // ==================== EDGE CASES TESTS ====================
    
    @Test
    @DisplayName("Test edge cases for records")
    void testRecordEdgeCases() {
        // Test boundary age values
        Java17Features.Person youngPerson = new Java17Features.Person("Young", 17, "young@example.com");
        Java17Features.Person adultPerson = new Java17Features.Person("Adult", 18, "adult@example.com");
        
        assertFalse(youngPerson.isAdult());
        assertTrue(adultPerson.isAdult());
    }
    
    @Test
    @DisplayName("Test edge cases for shapes")
    void testShapeEdgeCases() {
        // Test zero radius circle
        Java17Features.Circle zeroCircle = new Java17Features.Circle(0.0);
        assertEquals(0.0, zeroCircle.area(), 0.001);
        assertEquals(0.0, zeroCircle.perimeter(), 0.001);
        
        // Test zero dimensions rectangle
        Java17Features.Rectangle zeroRect = new Java17Features.Rectangle(0.0, 0.0);
        assertEquals(0.0, zeroRect.area(), 0.001);
        assertEquals(0.0, zeroRect.perimeter(), 0.001);
    }
    
    @Test
    @DisplayName("Test case sensitivity in switch expressions")
    void testSwitchCaseSensitivity() {
        assertEquals("Weekday", demo.getDayType("MONDAY"));
        assertEquals("Weekday", demo.getDayType("monday"));
        assertEquals("Weekend", demo.getDayType("SATURDAY"));
        assertEquals("Weekend", demo.getDayType("saturday"));
    }
}
