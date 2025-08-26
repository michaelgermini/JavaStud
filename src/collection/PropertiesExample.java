package collection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesExample {
    
    public static void main(String[] args) {
        System.out.println("=== Properties Basic Example ===");
        
        // Création d'un objet Properties
        Properties config = new Properties();
        
        // Ajout de propriétés
        config.setProperty("database.url", "localhost:3306");
        config.setProperty("database.user", "admin");
        config.setProperty("database.password", "secret123");
        config.setProperty("app.port", "8080");
        config.setProperty("app.debug", "true");
        config.setProperty("app.timeout", "30");
        
        System.out.println("Configuration properties:");
        for (String key : config.stringPropertyNames()) {
            System.out.println(key + " = " + config.getProperty(key));
        }
        
        System.out.println("\n=== Properties with Defaults ===");
        
        // Création de propriétés avec des valeurs par défaut
        Properties defaults = new Properties();
        defaults.setProperty("app.name", "DefaultApp");
        defaults.setProperty("app.version", "1.0.0");
        defaults.setProperty("app.environment", "development");
        
        Properties appConfig = new Properties(defaults);
        appConfig.setProperty("app.name", "MyApplication");
        appConfig.setProperty("database.url", "prod-server:5432");
        
        System.out.println("App configuration with defaults:");
        for (String key : appConfig.stringPropertyNames()) {
            String value = appConfig.getProperty(key);
            String source = appConfig.getProperty(key, defaults) != null ? "custom" : "default";
            System.out.println(key + " = " + value + " (" + source + ")");
        }
        
        System.out.println("\n=== Properties Methods ===");
        
        // Méthodes de base
        System.out.println("Size: " + config.size());
        System.out.println("Is empty: " + config.isEmpty());
        System.out.println("Contains 'database.url': " + config.containsKey("database.url"));
        System.out.println("Contains value 'admin': " + config.containsValue("admin"));
        
        // Récupération de valeurs
        System.out.println("Database URL: " + config.getProperty("database.url"));
        System.out.println("App Port: " + config.getProperty("app.port"));
        System.out.println("Non-existent property: " + config.getProperty("nonexistent", "default_value"));
        
        // Suppression
        String removedValue = config.remove("app.debug").toString();
        System.out.println("Removed 'app.debug': " + removedValue);
        System.out.println("Size after removal: " + config.size());
        
        // Remplacement
        String oldValue = config.setProperty("app.port", "9090");
        System.out.println("Old port value: " + oldValue);
        System.out.println("New port value: " + config.getProperty("app.port"));
        
        System.out.println("\n=== Properties File Operations ===");
        
        // Sauvegarde dans un fichier
        try {
            FileOutputStream out = new FileOutputStream("config.properties");
            config.store(out, "Application Configuration");
            out.close();
            System.out.println("Configuration saved to config.properties");
        } catch (IOException e) {
            System.err.println("Error saving properties: " + e.getMessage());
        }
        
        // Chargement depuis un fichier
        Properties loadedConfig = new Properties();
        try {
            FileInputStream in = new FileInputStream("config.properties");
            loadedConfig.load(in);
            in.close();
            System.out.println("Configuration loaded from file:");
            for (String key : loadedConfig.stringPropertyNames()) {
                System.out.println(key + " = " + loadedConfig.getProperty(key));
            }
        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
        }
        
        System.out.println("\n=== Properties with XML ===");
        
        // Sauvegarde en XML
        try {
            FileOutputStream xmlOut = new FileOutputStream("config.xml");
            config.storeToXML(xmlOut, "Application Configuration XML");
            xmlOut.close();
            System.out.println("Configuration saved to config.xml");
        } catch (IOException e) {
            System.err.println("Error saving XML properties: " + e.getMessage());
        }
        
        // Chargement depuis XML
        Properties xmlConfig = new Properties();
        try {
            FileInputStream xmlIn = new FileInputStream("config.xml");
            xmlConfig.loadFromXML(xmlIn);
            xmlIn.close();
            System.out.println("Configuration loaded from XML:");
            for (String key : xmlConfig.stringPropertyNames()) {
                System.out.println(key + " = " + xmlConfig.getProperty(key));
            }
        } catch (IOException e) {
            System.err.println("Error loading XML properties: " + e.getMessage());
        }
        
        System.out.println("\n=== Properties Use Cases ===");
        
        // Cas d'usage: Configuration d'application
        Properties appSettings = new Properties();
        appSettings.setProperty("ui.theme", "dark");
        appSettings.setProperty("ui.language", "en");
        appSettings.setProperty("ui.font.size", "12");
        appSettings.setProperty("ui.window.width", "1024");
        appSettings.setProperty("ui.window.height", "768");
        
        System.out.println("Application settings:");
        for (String key : appSettings.stringPropertyNames()) {
            System.out.println(key + " = " + appSettings.getProperty(key));
        }
        
        // Cas d'usage: Configuration de base de données
        Properties dbConfig = new Properties();
        dbConfig.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
        dbConfig.setProperty("db.url", "jdbc:mysql://localhost:3306/mydb");
        dbConfig.setProperty("db.username", "user");
        dbConfig.setProperty("db.password", "password");
        dbConfig.setProperty("db.pool.size", "10");
        dbConfig.setProperty("db.timeout", "30");
        
        System.out.println("\nDatabase configuration:");
        for (String key : dbConfig.stringPropertyNames()) {
            String value = key.contains("password") ? "*****" : dbConfig.getProperty(key);
            System.out.println(key + " = " + value);
        }
        
        System.out.println("\n=== Properties with System Properties ===");
        
        // Utilisation des propriétés système
        Properties systemProps = System.getProperties();
        System.out.println("System properties (first 10):");
        int count = 0;
        for (String key : systemProps.stringPropertyNames()) {
            if (count++ < 10) {
                System.out.println(key + " = " + systemProps.getProperty(key));
            } else {
                break;
            }
        }
        
        // Ajout de propriétés système personnalisées
        System.setProperty("app.custom.property", "custom_value");
        System.out.println("Custom system property: " + System.getProperty("app.custom.property"));
        
        System.out.println("\n=== Properties Collections ===");
        
        // Conversion en autres collections
        Set<String> propertyNames = config.stringPropertyNames();
        System.out.println("Property names as Set: " + propertyNames);
        
        // Itération avec forEach
        System.out.println("Iterating with forEach:");
        config.forEach((key, value) -> System.out.println(key + " -> " + value));
        
        // Conversion en Map
        java.util.Map<String, String> configMap = new java.util.HashMap<>(config);
        System.out.println("As Map: " + configMap);
        
        System.out.println("\n=== Properties Best Practices ===");
        
        System.out.println("✅ Use Properties when:");
        System.out.println("   - Storing configuration settings");
        System.out.println("   - Working with key-value pairs of strings");
        System.out.println("   - Need to save/load configuration from files");
        System.out.println("   - Want to use system properties");
        System.out.println("   - Need hierarchical configuration with defaults");
        
        System.out.println("\n❌ Consider alternatives when:");
        System.out.println("   - You need non-string values");
        System.out.println("   - Performance is critical");
        System.out.println("   - You need complex data structures");
        System.out.println("   - You need type safety");
        
        System.out.println("\n=== Properties Performance ===");
        
        // Test de performance
        Properties perfProps = new Properties();
        
        // Test d'ajout
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfProps.setProperty("key" + i, "value" + i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 10,000 properties: " + (endTime - startTime) / 1000000 + " ms");
        
        // Test de récupération
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfProps.getProperty("key" + i);
        }
        endTime = System.nanoTime();
        System.out.println("Time to get 10,000 properties: " + (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("Final properties size: " + perfProps.size());
    }
}
