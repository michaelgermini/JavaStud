package collection;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class LinkedListExample {
    public static void main(String[] args) {
        System.out.println("=== LinkedList Basic Operations ===");
        
        LinkedList<String> linkedList = new LinkedList<>();
        
        // Adding elements
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");
        
        System.out.println("Initial list: " + linkedList);
        
        // LinkedList-specific operations
        linkedList.addFirst("Zero");  // Add at beginning
        linkedList.addLast("Fourth"); // Add at end
        
        System.out.println("After addFirst/addLast: " + linkedList);
        
        // Get first and last elements
        System.out.println("First element: " + linkedList.getFirst());
        System.out.println("Last element: " + linkedList.getLast());
        
        // Remove first and last elements
        String removedFirst = linkedList.removeFirst();
        String removedLast = linkedList.removeLast();
        
        System.out.println("Removed first: " + removedFirst);
        System.out.println("Removed last: " + removedLast);
        System.out.println("After removals: " + linkedList);
        
        System.out.println("\n=== LinkedList as Queue ===");
        
        LinkedList<Integer> queue = new LinkedList<>();
        
        // Queue operations
        queue.offer(10);  // Add to end
        queue.offer(20);
        queue.offer(30);
        
        System.out.println("Queue: " + queue);
        System.out.println("Peek (first element): " + queue.peek());
        System.out.println("Poll (remove first): " + queue.poll());
        System.out.println("Queue after poll: " + queue);
        
        System.out.println("\n=== LinkedList as Stack ===");
        
        LinkedList<String> stack = new LinkedList<>();
        
        // Stack operations
        stack.push("Bottom");  // Push to top
        stack.push("Middle");
        stack.push("Top");
        
        System.out.println("Stack: " + stack);
        System.out.println("Peek (top element): " + stack.peek());
        System.out.println("Pop (remove top): " + stack.pop());
        System.out.println("Stack after pop: " + stack);
        
        System.out.println("\n=== Performance Comparison Demo ===");
        
        // Demonstrate when LinkedList is better than ArrayList
        LinkedList<Integer> linkedNumbers = new LinkedList<>();
        List<Integer> arrayNumbers = new ArrayList<>();
        
        // Adding elements at the beginning (LinkedList is more efficient)
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            linkedNumbers.addFirst(i);
        }
        long linkedTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            arrayNumbers.add(0, i);  // Adding at index 0 (inefficient for ArrayList)
        }
        long arrayTime = System.nanoTime() - startTime;
        
        System.out.println("Time to add 10000 elements at beginning:");
        System.out.println("LinkedList: " + linkedTime + " nanoseconds");
        System.out.println("ArrayList:  " + arrayTime + " nanoseconds");
        System.out.println("LinkedList is " + (arrayTime / linkedTime) + "x faster for this operation");
    }
}
