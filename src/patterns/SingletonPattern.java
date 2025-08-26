package patterns;

import java.io.Serializable;

/**
 * Singleton Design Pattern Examples
 * Demonstrates different ways to implement the Singleton pattern in Java
 */
public class SingletonPattern {
    
    public static void main(String[] args) {
        System.out.println("=== Singleton Design Pattern Examples ===\n");
        
        // Test Eager Singleton
        testEagerSingleton();
        
        // Test Lazy Singleton
        testLazySingleton();
        
        // Test Thread-Safe Singleton
        testThreadSafeSingleton();
        
        // Test Double-Checked Locking Singleton
        testDoubleCheckedSingleton();
        
        // Test Enum Singleton
        testEnumSingleton();
        
        // Test Serialization-Safe Singleton
        testSerializationSafeSingleton();
        
        System.out.println("\n=== All Singleton Tests Completed! ===");
    }
    
    /**
     * Test Eager Singleton (Thread-safe, but loads at startup)
     */
    public static void testEagerSingleton() {
        System.out.println("1. Eager Singleton Test:");
        System.out.println("=========================");
        
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        
        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);
        System.out.println("Same instance: " + (instance1 == instance2));
        System.out.println("Hash codes: " + instance1.hashCode() + " vs " + instance2.hashCode());
        
        instance1.setData("Eager Singleton Data");
        System.out.println("Data from instance 1: " + instance1.getData());
        System.out.println("Data from instance 2: " + instance2.getData());
        System.out.println();
    }
    
    /**
     * Test Lazy Singleton (Not thread-safe)
     */
    public static void testLazySingleton() {
        System.out.println("2. Lazy Singleton Test:");
        System.out.println("=======================");
        
        LazySingleton instance1 = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        
        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);
        System.out.println("Same instance: " + (instance1 == instance2));
        System.out.println("Hash codes: " + instance1.hashCode() + " vs " + instance2.hashCode());
        
        instance1.setData("Lazy Singleton Data");
        System.out.println("Data from instance 1: " + instance1.getData());
        System.out.println("Data from instance 2: " + instance2.getData());
        System.out.println();
    }
    
    /**
     * Test Thread-Safe Singleton
     */
    public static void testThreadSafeSingleton() {
        System.out.println("3. Thread-Safe Singleton Test:");
        System.out.println("==============================");
        
        ThreadSafeSingleton instance1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton instance2 = ThreadSafeSingleton.getInstance();
        
        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);
        System.out.println("Same instance: " + (instance1 == instance2));
        System.out.println("Hash codes: " + instance1.hashCode() + " vs " + instance2.hashCode());
        
        instance1.setData("Thread-Safe Singleton Data");
        System.out.println("Data from instance 1: " + instance1.getData());
        System.out.println("Data from instance 2: " + instance2.getData());
        System.out.println();
    }
    
    /**
     * Test Double-Checked Locking Singleton
     */
    public static void testDoubleCheckedSingleton() {
        System.out.println("4. Double-Checked Locking Singleton Test:");
        System.out.println("=========================================");
        
        DoubleCheckedSingleton instance1 = DoubleCheckedSingleton.getInstance();
        DoubleCheckedSingleton instance2 = DoubleCheckedSingleton.getInstance();
        
        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);
        System.out.println("Same instance: " + (instance1 == instance2));
        System.out.println("Hash codes: " + instance1.hashCode() + " vs " + instance2.hashCode());
        
        instance1.setData("Double-Checked Singleton Data");
        System.out.println("Data from instance 1: " + instance1.getData());
        System.out.println("Data from instance 2: " + instance2.getData());
        System.out.println();
    }
    
    /**
     * Test Enum Singleton (Recommended approach)
     */
    public static void testEnumSingleton() {
        System.out.println("5. Enum Singleton Test:");
        System.out.println("=======================");
        
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        
        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);
        System.out.println("Same instance: " + (instance1 == instance2));
        System.out.println("Hash codes: " + instance1.hashCode() + " vs " + instance2.hashCode());
        
        instance1.setData("Enum Singleton Data");
        System.out.println("Data from instance 1: " + instance1.getData());
        System.out.println("Data from instance 2: " + instance2.getData());
        System.out.println();
    }
    
    /**
     * Test Serialization-Safe Singleton
     */
    public static void testSerializationSafeSingleton() {
        System.out.println("6. Serialization-Safe Singleton Test:");
        System.out.println("=====================================");
        
        SerializationSafeSingleton instance1 = SerializationSafeSingleton.getInstance();
        SerializationSafeSingleton instance2 = SerializationSafeSingleton.getInstance();
        
        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);
        System.out.println("Same instance: " + (instance1 == instance2));
        System.out.println("Hash codes: " + instance1.hashCode() + " vs " + instance2.hashCode());
        
        instance1.setData("Serialization-Safe Singleton Data");
        System.out.println("Data from instance 1: " + instance1.getData());
        System.out.println("Data from instance 2: " + instance2.getData());
        System.out.println();
    }
    
    // ========== Singleton Implementations ==========
    
    /**
     * 1. Eager Singleton - Thread-safe, loads at startup
     */
    static class EagerSingleton {
        private static final EagerSingleton INSTANCE = new EagerSingleton();
        private String data = "Default Data";
        
        // Private constructor prevents instantiation
        private EagerSingleton() {
            System.out.println("EagerSingleton instance created");
        }
        
        public static EagerSingleton getInstance() {
            return INSTANCE;
        }
        
        public String getData() {
            return data;
        }
        
        public void setData(String data) {
            this.data = data;
        }
    }
    
    /**
     * 2. Lazy Singleton - Not thread-safe
     */
    static class LazySingleton {
        private static LazySingleton instance;
        private String data = "Default Data";
        
        // Private constructor prevents instantiation
        private LazySingleton() {
            System.out.println("LazySingleton instance created");
        }
        
        public static LazySingleton getInstance() {
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }
        
        public String getData() {
            return data;
        }
        
        public void setData(String data) {
            this.data = data;
        }
    }
    
    /**
     * 3. Thread-Safe Singleton - Synchronized method
     */
    static class ThreadSafeSingleton {
        private static ThreadSafeSingleton instance;
        private String data = "Default Data";
        
        // Private constructor prevents instantiation
        private ThreadSafeSingleton() {
            System.out.println("ThreadSafeSingleton instance created");
        }
        
        public static synchronized ThreadSafeSingleton getInstance() {
            if (instance == null) {
                instance = new ThreadSafeSingleton();
            }
            return instance;
        }
        
        public String getData() {
            return data;
        }
        
        public void setData(String data) {
            this.data = data;
        }
    }
    
    /**
     * 4. Double-Checked Locking Singleton - Performance optimized
     */
    static class DoubleCheckedSingleton {
        private static volatile DoubleCheckedSingleton instance;
        private String data = "Default Data";
        
        // Private constructor prevents instantiation
        private DoubleCheckedSingleton() {
            System.out.println("DoubleCheckedSingleton instance created");
        }
        
        public static DoubleCheckedSingleton getInstance() {
            if (instance == null) {
                synchronized (DoubleCheckedSingleton.class) {
                    if (instance == null) {
                        instance = new DoubleCheckedSingleton();
                    }
                }
            }
            return instance;
        }
        
        public String getData() {
            return data;
        }
        
        public void setData(String data) {
            this.data = data;
        }
    }
    
    /**
     * 5. Enum Singleton - Recommended approach (thread-safe, serialization-safe)
     */
    enum EnumSingleton {
        INSTANCE;
        
        private String data = "Default Data";
        
        EnumSingleton() {
            System.out.println("EnumSingleton instance created");
        }
        
        public String getData() {
            return data;
        }
        
        public void setData(String data) {
            this.data = data;
        }
    }
    
    /**
     * 6. Serialization-Safe Singleton - Prevents multiple instances during deserialization
     */
    static class SerializationSafeSingleton implements Serializable {
        private static final long serialVersionUID = 1L;
        private static SerializationSafeSingleton instance;
        private String data = "Default Data";
        
        // Private constructor prevents instantiation
        private SerializationSafeSingleton() {
            System.out.println("SerializationSafeSingleton instance created");
        }
        
        public static SerializationSafeSingleton getInstance() {
            if (instance == null) {
                synchronized (SerializationSafeSingleton.class) {
                    if (instance == null) {
                        instance = new SerializationSafeSingleton();
                    }
                }
            }
            return instance;
        }
        
        // Prevents creation of new instance during deserialization
        protected Object readResolve() {
            return getInstance();
        }
        
        public String getData() {
            return data;
        }
        
        public void setData(String data) {
            this.data = data;
        }
    }
}
