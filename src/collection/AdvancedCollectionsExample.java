package collection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.function.Function;

public class AdvancedCollectionsExample {
    public static void main(String[] args) {
        System.out.println("=== Advanced Collections Features ===\n");
        
        // Create sample data
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30, "Engineering", 75000),
            new Employee("Bob", 25, "Marketing", 65000),
            new Employee("Charlie", 35, "Engineering", 85000),
            new Employee("Diana", 28, "HR", 60000),
            new Employee("Eve", 32, "Engineering", 80000),
            new Employee("Frank", 27, "Marketing", 70000)
        );
        
        System.out.println("=== Custom Comparators ===");
        demonstrateCustomComparators(employees);
        
        System.out.println("\n=== Streams with Collections ===");
        demonstrateStreams(employees);
        
        System.out.println("\n=== Functional Programming with Collections ===");
        demonstrateFunctionalProgramming(employees);
        
        System.out.println("\n=== Collection Utilities ===");
        demonstrateCollectionUtilities();
    }
    
    private static void demonstrateCustomComparators(List<Employee> employees) {
        System.out.println("Original list:");
        employees.forEach(System.out::println);
        
        // Sort by age using lambda comparator
        List<Employee> byAge = new ArrayList<>(employees);
        byAge.sort((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println("\nSorted by age:");
        byAge.forEach(System.out::println);
        
        // Sort by salary (descending) using method reference
        List<Employee> bySalary = new ArrayList<>(employees);
        bySalary.sort(Comparator.comparing(Employee::getSalary).reversed());
        System.out.println("\nSorted by salary (descending):");
        bySalary.forEach(System.out::println);
        
        // Multi-field sorting: department, then salary
        List<Employee> byDeptAndSalary = new ArrayList<>(employees);
        byDeptAndSalary.sort(Comparator
            .comparing(Employee::getDepartment)
            .thenComparing(Employee::getSalary, Comparator.reverseOrder())
        );
        System.out.println("\nSorted by department, then salary (descending):");
        byDeptAndSalary.forEach(System.out::println);
        
        // Custom complex comparator
        List<Employee> customSort = new ArrayList<>(employees);
        customSort.sort((e1, e2) -> {
            // Engineering employees first, then by age (younger first)
            if (e1.getDepartment().equals("Engineering") && !e2.getDepartment().equals("Engineering")) {
                return -1;
            } else if (!e1.getDepartment().equals("Engineering") && e2.getDepartment().equals("Engineering")) {
                return 1;
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        System.out.println("\nCustom sort (Engineering first, then by age):");
        customSort.forEach(System.out::println);
    }
    
    private static void demonstrateStreams(List<Employee> employees) {
        System.out.println("=== Basic Stream Operations ===");
        
        // Filter and map
        List<String> engineeringNames = employees.stream()
            .filter(e -> e.getDepartment().equals("Engineering"))
            .map(Employee::getName)
            .collect(Collectors.toList());
        System.out.println("Engineering employees: " + engineeringNames);
        
        // Aggregation operations
        double avgSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .orElse(0.0);
        System.out.println("Average salary: $" + String.format("%.2f", avgSalary));
        
        // Grouping
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("\nEmployees by department:");
        byDepartment.forEach((dept, emps) -> {
            System.out.println(dept + ": " + emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", ")));
        });
        
        // Partitioning
        Map<Boolean, List<Employee>> highEarners = employees.stream()
            .collect(Collectors.partitioningBy(e -> e.getSalary() > 70000));
        System.out.println("\nHigh earners (>$70k): " + 
            highEarners.get(true).stream().map(Employee::getName).collect(Collectors.joining(", ")));
        System.out.println("Others: " + 
            highEarners.get(false).stream().map(Employee::getName).collect(Collectors.joining(", ")));
        
        // Complex stream operations
        Map<String, Double> deptAvgSalary = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        System.out.println("\nAverage salary by department:");
        deptAvgSalary.forEach((dept, avg) -> 
            System.out.println(dept + ": $" + String.format("%.2f", avg)));
        
        // Parallel streams
        System.out.println("\n=== Parallel Streams ===");
        long startTime = System.nanoTime();
        List<Employee> parallelFiltered = employees.parallelStream()
            .filter(e -> e.getAge() > 30)
            .collect(Collectors.toList());
        long parallelTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        List<Employee> sequentialFiltered = employees.stream()
            .filter(e -> e.getAge() > 30)
            .collect(Collectors.toList());
        long sequentialTime = System.nanoTime() - startTime;
        
        System.out.println("Employees over 30: " + 
            parallelFiltered.stream().map(Employee::getName).collect(Collectors.joining(", ")));
        System.out.println("Parallel time: " + parallelTime + "ns, Sequential time: " + sequentialTime + "ns");
    }
    
    private static void demonstrateFunctionalProgramming(List<Employee> employees) {
        System.out.println("=== Functional Programming Patterns ===");
        
        // Function composition
        Function<Employee, String> getName = Employee::getName;
        Function<Employee, Integer> getAge = Employee::getAge;
        Function<Employee, String> getNameAndAge = getName.andThen(name -> name + " (" + getAge.apply(employees.get(0)) + ")");
        
        // Predicate composition
        Predicate<Employee> isEngineering = e -> e.getDepartment().equals("Engineering");
        Predicate<Employee> isHighEarner = e -> e.getSalary() > 70000;
        Predicate<Employee> isSeniorEngineer = isEngineering.and(isHighEarner);
        
        List<Employee> seniorEngineers = employees.stream()
            .filter(isSeniorEngineer)
            .collect(Collectors.toList());
        System.out.println("Senior engineers: " + 
            seniorEngineers.stream().map(Employee::getName).collect(Collectors.joining(", ")));
        
        // Optional with collections
        Optional<Employee> highestPaid = employees.stream()
            .max(Comparator.comparing(Employee::getSalary));
        
        highestPaid.ifPresent(emp -> 
            System.out.println("Highest paid employee: " + emp.getName() + " ($" + emp.getSalary() + ")"));
        
        // Reduce operation
        double totalSalary = employees.stream()
            .map(Employee::getSalary)
            .reduce(0.0, Double::sum);
        System.out.println("Total salary budget: $" + String.format("%.2f", totalSalary));
        
        // Collectors with custom collectors
        String allNames = employees.stream()
            .map(Employee::getName)
            .collect(Collectors.joining(" | "));
        System.out.println("All employees: " + allNames);
    }
    
    private static void demonstrateCollectionUtilities() {
        System.out.println("=== Collection Utilities ===");
        
        // Collections utility methods
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6);
        System.out.println("Original: " + numbers);
        
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);
        
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);
        
        // Binary search (requires sorted list)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 5);
        System.out.println("Index of 5: " + index);
        
        // Frequency
        int frequency = Collections.frequency(numbers, 1);
        System.out.println("Frequency of 1: " + frequency);
        
        // Min/Max
        System.out.println("Min: " + Collections.min(numbers));
        System.out.println("Max: " + Collections.max(numbers));
        
        // Unmodifiable collections
        List<String> unmodifiable = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
        System.out.println("Unmodifiable list: " + unmodifiable);
        // unmodifiable.add("d"); // This would throw UnsupportedOperationException
        
        // Singleton collections
        Set<String> singleton = Collections.singleton("single");
        System.out.println("Singleton set: " + singleton);
    }
    
    static class Employee {
        private String name;
        private int age;
        private String department;
        private double salary;
        
        public Employee(String name, int age, String department, double salary) {
            this.name = name;
            this.age = age;
            this.department = department;
            this.salary = salary;
        }
        
        // Getters
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
        
        @Override
        public String toString() {
            return String.format("%s (%d, %s, $%.0f)", name, age, department, salary);
        }
    }
}
