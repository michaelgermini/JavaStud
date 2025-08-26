package collection;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapExample {
    
    // Définition d'un enum pour les jours de la semaine
    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    // Définition d'un enum pour les types d'activités
    enum ActivityType {
        WORK, STUDY, EXERCISE, LEISURE, SLEEP
    }
    
    public static void main(String[] args) {
        System.out.println("=== EnumMap Basic Example ===");
        
        // Création d'un EnumMap pour stocker les activités par jour
        EnumMap<DayOfWeek, String> dailyActivities = new EnumMap<>(DayOfWeek.class);
        
        // Ajout d'activités pour chaque jour
        dailyActivities.put(DayOfWeek.MONDAY, "Work");
        dailyActivities.put(DayOfWeek.TUESDAY, "Study");
        dailyActivities.put(DayOfWeek.WEDNESDAY, "Exercise");
        dailyActivities.put(DayOfWeek.THURSDAY, "Work");
        dailyActivities.put(DayOfWeek.FRIDAY, "Leisure");
        dailyActivities.put(DayOfWeek.SATURDAY, "Leisure");
        dailyActivities.put(DayOfWeek.SUNDAY, "Sleep");
        
        System.out.println("Daily Activities:");
        for (Map.Entry<DayOfWeek, String> entry : dailyActivities.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== EnumMap with Complex Values ===");
        
        // Création d'un EnumMap avec des objets complexes
        EnumMap<ActivityType, Activity> activities = new EnumMap<>(ActivityType.class);
        
        activities.put(ActivityType.WORK, new Activity("Programming", 8, "High"));
        activities.put(ActivityType.STUDY, new Activity("Java Learning", 4, "Medium"));
        activities.put(ActivityType.EXERCISE, new Activity("Gym", 1, "High"));
        activities.put(ActivityType.LEISURE, new Activity("Gaming", 2, "Low"));
        activities.put(ActivityType.SLEEP, new Activity("Rest", 8, "Medium"));
        
        System.out.println("Activities with Details:");
        for (Map.Entry<ActivityType, Activity> entry : activities.entrySet()) {
            Activity activity = entry.getValue();
            System.out.printf("%s: %s (%d hours, Priority: %s)%n", 
                entry.getKey(), activity.name, activity.duration, activity.priority);
        }
        
        System.out.println("\n=== EnumMap Performance Benefits ===");
        
        // Démonstration des avantages de performance
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            activities.get(ActivityType.WORK);
        }
        long endTime = System.nanoTime();
        
        System.out.println("Time to access EnumMap 1,000,000 times: " + 
            (endTime - startTime) / 1000000 + " ms");
        
        System.out.println("\n=== EnumMap with Collections ===");
        
        // EnumMap contenant des collections
        EnumMap<DayOfWeek, java.util.List<String>> weeklySchedule = new EnumMap<>(DayOfWeek.class);
        
        weeklySchedule.put(DayOfWeek.MONDAY, java.util.Arrays.asList("Meeting", "Coding", "Lunch"));
        weeklySchedule.put(DayOfWeek.TUESDAY, java.util.Arrays.asList("Planning", "Testing"));
        weeklySchedule.put(DayOfWeek.WEDNESDAY, java.util.Arrays.asList("Review", "Deployment"));
        weeklySchedule.put(DayOfWeek.THURSDAY, java.util.Arrays.asList("Client Call", "Documentation"));
        weeklySchedule.put(DayOfWeek.FRIDAY, java.util.Arrays.asList("Code Review", "Team Lunch"));
        
        System.out.println("Weekly Schedule:");
        for (Map.Entry<DayOfWeek, java.util.List<String>> entry : weeklySchedule.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== EnumMap Operations ===");
        
        // Opérations sur EnumMap
        System.out.println("Contains WORK activity: " + activities.containsKey(ActivityType.WORK));
        System.out.println("Contains SLEEP activity: " + activities.containsKey(ActivityType.SLEEP));
        
        // Suppression d'une activité
        Activity removedActivity = activities.remove(ActivityType.LEISURE);
        System.out.println("Removed activity: " + removedActivity);
        
        // Taille après suppression
        System.out.println("Size after removal: " + activities.size());
        
        // Vérification si vide
        System.out.println("Is empty: " + activities.isEmpty());
        
        // Nettoyage
        activities.clear();
        System.out.println("Size after clear: " + activities.size());
        System.out.println("Is empty: " + activities.isEmpty());
    }
    
    // Classe interne pour représenter une activité
    static class Activity {
        String name;
        int duration;
        String priority;
        
        public Activity(String name, int duration, String priority) {
            this.name = name;
            this.duration = duration;
            this.priority = priority;
        }
        
        @Override
        public String toString() {
            return name + " (" + duration + "h, " + priority + ")";
        }
    }
}
