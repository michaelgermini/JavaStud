package springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.model.*;
import springboot.service.LearningService;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for JavaStud Learning Platform
 * Provides APIs for accessing learning content and exercises
 */
@RestController
@RequestMapping("/api/learning")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
public class LearningController {

    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    /**
     * Get all available topics
     */
    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = learningService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    /**
     * Get topic by ID
     */
    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Topic topic = learningService.getTopicById(id);
        if (topic != null) {
            return ResponseEntity.ok(topic);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get all exercises for a topic
     */
    @GetMapping("/topics/{topicId}/exercises")
    public ResponseEntity<List<Exercise>> getExercisesByTopic(@PathVariable Long topicId) {
        List<Exercise> exercises = learningService.getExercisesByTopic(topicId);
        return ResponseEntity.ok(exercises);
    }

    /**
     * Get exercise by ID
     */
    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = learningService.getExerciseById(id);
        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Submit exercise solution
     */
    @PostMapping("/exercises/{id}/submit")
    public ResponseEntity<SubmissionResult> submitExercise(
            @PathVariable Long id,
            @RequestBody ExerciseSubmission submission) {
        SubmissionResult result = learningService.submitExercise(id, submission);
        return ResponseEntity.ok(result);
    }

    /**
     * Get user progress
     */
    @GetMapping("/progress/{userId}")
    public ResponseEntity<UserProgress> getUserProgress(@PathVariable String userId) {
        UserProgress progress = learningService.getUserProgress(userId);
        return ResponseEntity.ok(progress);
    }

    /**
     * Get learning path
     */
    @GetMapping("/path")
    public ResponseEntity<List<LearningPath>> getLearningPath() {
        List<LearningPath> path = learningService.getLearningPath();
        return ResponseEntity.ok(path);
    }

    /**
     * Get collection examples
     */
    @GetMapping("/collections")
    public ResponseEntity<Map<String, Object>> getCollectionExamples() {
        Map<String, Object> examples = learningService.getCollectionExamples();
        return ResponseEntity.ok(examples);
    }

    /**
     * Get design patterns
     */
    @GetMapping("/patterns")
    public ResponseEntity<List<DesignPattern>> getDesignPatterns() {
        List<DesignPattern> patterns = learningService.getDesignPatterns();
        return ResponseEntity.ok(patterns);
    }

    /**
     * Get pattern by name
     */
    @GetMapping("/patterns/{name}")
    public ResponseEntity<DesignPattern> getPatternByName(@PathVariable String name) {
        DesignPattern pattern = learningService.getPatternByName(name);
        if (pattern != null) {
            return ResponseEntity.ok(pattern);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Search learning content
     */
    @GetMapping("/search")
    public ResponseEntity<List<SearchResult>> searchContent(@RequestParam String query) {
        List<SearchResult> results = learningService.searchContent(query);
        return ResponseEntity.ok(results);
    }

    /**
     * Get statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<LearningStats> getLearningStats() {
        LearningStats stats = learningService.getLearningStats();
        return ResponseEntity.ok(stats);
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "JavaStud Learning Platform",
            "version", "2.0.0"
        ));
    }
}
