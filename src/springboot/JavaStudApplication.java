package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * JavaStud Spring Boot Application
 * Main entry point for the JavaStud learning platform web application
 */
@SpringBootApplication
public class JavaStudApplication {

    public static void main(String[] args) {
        System.out.println("🚀 Starting JavaStud Learning Platform...");
        System.out.println("📚 Comprehensive Java Learning with Spring Boot");
        System.out.println("🌐 Web Interface: http://localhost:8080");
        System.out.println("📖 API Documentation: http://localhost:8080/swagger-ui.html");
        System.out.println("🔍 Health Check: http://localhost:8080/actuator/health");
        
        SpringApplication.run(JavaStudApplication.class, args);
        
        System.out.println("✅ JavaStud Application started successfully!");
        System.out.println("🎯 Ready to learn Java with hands-on examples!");
    }

    /**
     * Configure CORS for web application
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
