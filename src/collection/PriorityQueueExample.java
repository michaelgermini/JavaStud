package collection;

import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueExample {
    public static void main(String[] args) {
        System.out.println("=== PriorityQueue with Natural Ordering ===");
        
        // PriorityQueue with natural ordering (min-heap)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(15);
        minHeap.offer(8);
        minHeap.offer(25);
        minHeap.offer(3);
        minHeap.offer(12);
        
        System.out.println("Min Heap: " + minHeap);
        System.out.println("Peek (smallest element): " + minHeap.peek());
        
        System.out.println("\nRemoving elements in order:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        
        System.out.println("\n\n=== PriorityQueue with Custom Comparator (Max Heap) ===");
        
        // PriorityQueue with custom comparator (max-heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(15);
        maxHeap.offer(8);
        maxHeap.offer(25);
        maxHeap.offer(3);
        maxHeap.offer(12);
        
        System.out.println("Max Heap: " + maxHeap);
        System.out.println("Peek (largest element): " + maxHeap.peek());
        
        System.out.println("\nRemoving elements in order:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        
        System.out.println("\n\n=== PriorityQueue with Custom Objects ===");
        
        // PriorityQueue with custom objects
        PriorityQueue<Student> studentQueue = new PriorityQueue<>(
            Comparator.comparing(Student::getGrade).reversed()
        );
        
        studentQueue.offer(new Student("Alice", 85));
        studentQueue.offer(new Student("Bob", 92));
        studentQueue.offer(new Student("Charlie", 78));
        studentQueue.offer(new Student("Diana", 95));
        
        System.out.println("Students by grade (highest first):");
        while (!studentQueue.isEmpty()) {
            Student student = studentQueue.poll();
            System.out.println(student.getName() + " - Grade: " + student.getGrade());
        }
    }
    
    static class Student {
        private String name;
        private int grade;
        
        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }
        
        public String getName() { return name; }
        public int getGrade() { return grade; }
        
        @Override
        public String toString() {
            return name + "(" + grade + ")";
        }
    }
}
