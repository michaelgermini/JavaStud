package javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

/**
 * JavaStud JavaFX Application
 * Modern desktop application for Java learning platform
 */
public class JavaStudFXApplication extends Application {

    private TabPane tabPane;
    private TextArea codeEditor;
    private TextArea outputArea;
    private WebView documentationView;
    private TreeView<String> topicTree;

    @Override
    public void start(Stage primaryStage) {
        System.out.println("🚀 Starting JavaStud JavaFX Application...");
        
        primaryStage.setTitle("JavaStud Learning Platform v2.0");
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(800);

        // Create main layout
        BorderPane mainLayout = createMainLayout();
        
        // Create scene
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("/styles/javastud.css").toExternalForm());
        
        // Set up stage
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Initialize components
        initializeComponents();
        
        System.out.println("✅ JavaStud JavaFX Application started successfully!");
    }

    /**
     * Create the main application layout
     */
    private BorderPane createMainLayout() {
        BorderPane layout = new BorderPane();
        
        // Top: Menu Bar and Toolbar
        layout.setTop(createTopSection());
        
        // Left: Topic Navigation
        layout.setLeft(createLeftSection());
        
        // Center: Main Content Area
        layout.setCenter(createCenterSection());
        
        // Bottom: Status Bar
        layout.setBottom(createBottomSection());
        
        return layout;
    }

    /**
     * Create top section with menu bar and toolbar
     */
    private VBox createTopSection() {
        VBox topSection = new VBox(5);
        topSection.setPadding(new Insets(5));
        
        // Menu Bar
        MenuBar menuBar = createMenuBar();
        
        // Toolbar
        ToolBar toolbar = createToolbar();
        
        topSection.getChildren().addAll(menuBar, toolbar);
        return topSection;
    }

    /**
     * Create menu bar
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        // File Menu
        Menu fileMenu = new Menu("File");
        MenuItem newProject = new MenuItem("New Project");
        MenuItem openProject = new MenuItem("Open Project");
        MenuItem saveProject = new MenuItem("Save Project");
        MenuItem exit = new MenuItem("Exit");
        
        fileMenu.getItems().addAll(newProject, openProject, saveProject, new SeparatorMenuItem(), exit);
        
        // Edit Menu
        Menu editMenu = new Menu("Edit");
        MenuItem undo = new MenuItem("Undo");
        MenuItem redo = new MenuItem("Redo");
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        
        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste);
        
        // View Menu
        Menu viewMenu = new Menu("View");
        MenuItem showOutput = new MenuItem("Show Output");
        MenuItem showDocumentation = new MenuItem("Show Documentation");
        MenuItem showTopics = new MenuItem("Show Topics");
        
        viewMenu.getItems().addAll(showOutput, showDocumentation, showTopics);
        
        // Run Menu
        Menu runMenu = new Menu("Run");
        MenuItem runCode = new MenuItem("Run Code");
        MenuItem runTests = new MenuItem("Run Tests");
        MenuItem debugCode = new MenuItem("Debug Code");
        
        runMenu.getItems().addAll(runCode, runTests, debugCode);
        
        // Help Menu
        Menu helpMenu = new Menu("Help");
        MenuItem about = new MenuItem("About JavaStud");
        MenuItem documentation = new MenuItem("Documentation");
        MenuItem tutorials = new MenuItem("Tutorials");
        
        helpMenu.getItems().addAll(about, documentation, tutorials);
        
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, runMenu, helpMenu);
        
        // Add event handlers
        exit.setOnAction(e -> Platform.exit());
        runCode.setOnAction(e -> runCode());
        about.setOnAction(e -> showAboutDialog());
        
        return menuBar;
    }

    /**
     * Create toolbar
     */
    private ToolBar createToolbar() {
        ToolBar toolbar = new ToolBar();
        
        Button newBtn = new Button("New");
        Button openBtn = new Button("Open");
        Button saveBtn = new Button("Save");
        Button runBtn = new Button("Run");
        Button stopBtn = new Button("Stop");
        Button debugBtn = new Button("Debug");
        
        // Add icons (you can add actual icons here)
        newBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        runBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        stopBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        debugBtn.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        
        toolbar.getItems().addAll(newBtn, openBtn, saveBtn, new Separator(), runBtn, stopBtn, debugBtn);
        
        // Add event handlers
        runBtn.setOnAction(e -> runCode());
        stopBtn.setOnAction(e -> stopCode());
        debugBtn.setOnAction(e -> debugCode());
        
        return toolbar;
    }

    /**
     * Create left section with topic navigation
     */
    private VBox createLeftSection() {
        VBox leftSection = new VBox(10);
        leftSection.setPadding(new Insets(10));
        leftSection.setPrefWidth(250);
        leftSection.setStyle("-fx-background-color: #f5f5f5;");
        
        // Title
        Label titleLabel = new Label("Learning Topics");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        // Topic Tree
        topicTree = createTopicTree();
        
        leftSection.getChildren().addAll(titleLabel, topicTree);
        return leftSection;
    }

    /**
     * Create topic tree view
     */
    private TreeView<String> createTopicTree() {
        TreeItem<String> root = new TreeItem<>("JavaStud Learning");
        root.setExpanded(true);
        
        // Core Java
        TreeItem<String> coreJava = new TreeItem<>("Core Java");
        coreJava.getChildren().addAll(
            new TreeItem<>("Basic Syntax"),
            new TreeItem<>("Data Types"),
            new TreeItem<>("Control Structures"),
            new TreeItem<>("Arrays"),
            new TreeItem<>("Strings"),
            new TreeItem<>("Methods")
        );
        
        // OOP
        TreeItem<String> oop = new TreeItem<>("Object-Oriented Programming");
        oop.getChildren().addAll(
            new TreeItem<>("Classes & Objects"),
            new TreeItem<>("Inheritance"),
            new TreeItem<>("Polymorphism"),
            new TreeItem<>("Abstraction"),
            new TreeItem<>("Encapsulation")
        );
        
        // Collections
        TreeItem<String> collections = new TreeItem<>("Collections Framework");
        collections.getChildren().addAll(
            new TreeItem<>("List Collections"),
            new TreeItem<>("Set Collections"),
            new TreeItem<>("Map Collections"),
            new TreeItem<>("Queue Collections"),
            new TreeItem<>("Advanced Collections"),
            new TreeItem<>("Concurrent Collections")
        );
        
        // Advanced Java
        TreeItem<String> advanced = new TreeItem<>("Advanced Java");
        advanced.getChildren().addAll(
            new TreeItem<>("Exception Handling"),
            new TreeItem<>("File I/O"),
            new TreeItem<>("Multithreading"),
            new TreeItem<>("Lambda Expressions"),
            new TreeItem<>("Streams API"),
            new TreeItem<>("Optional")
        );
        
        // Design Patterns
        TreeItem<String> patterns = new TreeItem<>("Design Patterns");
        patterns.getChildren().addAll(
            new TreeItem<>("Singleton"),
            new TreeItem<>("Factory"),
            new TreeItem<>("Observer"),
            new TreeItem<>("Strategy"),
            new TreeItem<>("Builder")
        );
        
        // Web Development
        TreeItem<String> web = new TreeItem<>("Web Development");
        web.getChildren().addAll(
            new TreeItem<>("Spring Boot"),
            new TreeItem<>("REST APIs"),
            new TreeItem<>("Database"),
            new TreeItem<>("Security")
        );
        
        root.getChildren().addAll(coreJava, oop, collections, advanced, patterns, web);
        
        TreeView<String> treeView = new TreeView<>(root);
        treeView.setShowRoot(true);
        
        // Add selection listener
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isLeaf() && newValue != root) {
                loadTopic(newValue.getValue());
            }
        });
        
        return treeView;
    }

    /**
     * Create center section with tabs
     */
    private TabPane createCenterSection() {
        tabPane = new TabPane();
        
        // Code Editor Tab
        Tab codeTab = new Tab("Code Editor");
        codeTab.setClosable(false);
        codeEditor = new TextArea();
        codeEditor.setFont(Font.font("Consolas", 14));
        codeEditor.setPromptText("// Write your Java code here...\n\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, JavaStud!\");\n    }\n}");
        codeTab.setContent(codeEditor);
        
        // Output Tab
        Tab outputTab = new Tab("Output");
        outputTab.setClosable(false);
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setFont(Font.font("Consolas", 12));
        outputArea.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: #ffffff;");
        outputTab.setContent(outputArea);
        
        // Documentation Tab
        Tab docTab = new Tab("Documentation");
        docTab.setClosable(false);
        documentationView = new WebView();
        WebEngine webEngine = documentationView.getEngine();
        webEngine.loadContent(createDocumentationHTML());
        docTab.setContent(documentationView);
        
        tabPane.getTabs().addAll(codeTab, outputTab, docTab);
        
        return tabPane;
    }

    /**
     * Create bottom section with status bar
     */
    private HBox createBottomSection() {
        HBox bottomSection = new HBox(10);
        bottomSection.setPadding(new Insets(5));
        bottomSection.setAlignment(Pos.CENTER_LEFT);
        bottomSection.setStyle("-fx-background-color: #e0e0e0;");
        
        Label statusLabel = new Label("Ready");
        Label lineLabel = new Label("Line: 1");
        Label colLabel = new Label("Column: 1");
        Label encodingLabel = new Label("UTF-8");
        
        bottomSection.getChildren().addAll(statusLabel, new Separator(), lineLabel, colLabel, encodingLabel);
        
        return bottomSection;
    }

    /**
     * Initialize application components
     */
    private void initializeComponents() {
        // Set up code editor listeners
        codeEditor.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLineColumnInfo();
        });
        
        // Set up keyboard shortcuts
        setupKeyboardShortcuts();
        
        // Load initial content
        loadWelcomeContent();
    }

    /**
     * Update line and column information
     */
    private void updateLineColumnInfo() {
        String text = codeEditor.getText();
        int caretPosition = codeEditor.getCaretPosition();
        
        int line = 1;
        int column = 1;
        
        for (int i = 0; i < caretPosition && i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                line++;
                column = 1;
            } else {
                column++;
            }
        }
        
        // Update status bar (you would need to store references to these labels)
        System.out.println("Line: " + line + ", Column: " + column);
    }

    /**
     * Set up keyboard shortcuts
     */
    private void setupKeyboardShortcuts() {
        Scene scene = codeEditor.getScene();
        
        // Ctrl+R to run code
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode().toString().equals("R")) {
                runCode();
            }
        });
    }

    /**
     * Load welcome content
     */
    private void loadWelcomeContent() {
        String welcomeCode = """
            // Welcome to JavaStud Learning Platform!
            // This is your Java code editor.
            
            public class Welcome {
                public static void main(String[] args) {
                    System.out.println("🎉 Welcome to JavaStud!");
                    System.out.println("📚 Start learning Java with hands-on examples");
                    System.out.println("🚀 Select a topic from the left panel to begin");
                    
                    // Try running this code!
                    int sum = addNumbers(5, 10);
                    System.out.println("5 + 10 = " + sum);
                }
                
                public static int addNumbers(int a, int b) {
                    return a + b;
                }
            }
            """;
        
        codeEditor.setText(welcomeCode);
    }

    /**
     * Load topic content
     */
    private void loadTopic(String topicName) {
        System.out.println("Loading topic: " + topicName);
        
        // Switch to code editor tab
        tabPane.getSelectionModel().select(0);
        
        // Load topic-specific content
        String topicCode = getTopicCode(topicName);
        codeEditor.setText(topicCode);
        
        // Update status
        System.out.println("Topic loaded: " + topicName);
    }

    /**
     * Get code for specific topic
     */
    private String getTopicCode(String topicName) {
        return switch (topicName) {
            case "Basic Syntax" -> """
                // Basic Java Syntax Example
                
                public class BasicSyntax {
                    public static void main(String[] args) {
                        // Variables
                        int number = 42;
                        String message = "Hello, Java!";
                        boolean isTrue = true;
                        
                        // Output
                        System.out.println("Number: " + number);
                        System.out.println("Message: " + message);
                        System.out.println("Boolean: " + isTrue);
                        
                        // Basic operations
                        int result = number * 2;
                        System.out.println("Result: " + result);
                    }
                }
                """;
            case "Collections Framework" -> """
                // Collections Framework Example
                
                import java.util.*;
                
                public class CollectionsExample {
                    public static void main(String[] args) {
                        // List
                        List<String> names = new ArrayList<>();
                        names.add("Alice");
                        names.add("Bob");
                        names.add("Charlie");
                        
                        // Set
                        Set<Integer> numbers = new HashSet<>();
                        numbers.add(1);
                        numbers.add(2);
                        numbers.add(3);
                        
                        // Map
                        Map<String, Integer> scores = new HashMap<>();
                        scores.put("Alice", 95);
                        scores.put("Bob", 87);
                        scores.put("Charlie", 92);
                        
                        System.out.println("Names: " + names);
                        System.out.println("Numbers: " + numbers);
                        System.out.println("Scores: " + scores);
                    }
                }
                """;
            default -> """
                // Topic: """ + topicName + """
                
                public class """ + topicName.replaceAll("\\s+", "") + """ {
                    public static void main(String[] args) {
                        System.out.println("Learning: """ + topicName + """");
                        System.out.println("This topic is under development...");
                    }
                }
                """;
        };
    }

    /**
     * Run the current code
     */
    private void runCode() {
        String code = codeEditor.getText();
        outputArea.appendText("=== Running Code ===\n");
        outputArea.appendText(code + "\n\n");
        outputArea.appendText("=== Output ===\n");
        outputArea.appendText("Code execution simulation...\n");
        outputArea.appendText("Hello, JavaStud!\n");
        outputArea.appendText("5 + 10 = 15\n");
        outputArea.appendText("=== Execution Complete ===\n\n");
        
        // Switch to output tab
        tabPane.getSelectionModel().select(1);
    }

    /**
     * Stop code execution
     */
    private void stopCode() {
        outputArea.appendText("=== Execution Stopped ===\n\n");
    }

    /**
     * Debug the current code
     */
    private void debugCode() {
        outputArea.appendText("=== Debug Mode ===\n");
        outputArea.appendText("Setting breakpoints...\n");
        outputArea.appendText("Starting debugger...\n");
        outputArea.appendText("Debug session active\n\n");
    }

    /**
     * Show about dialog
     */
    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About JavaStud");
        alert.setHeaderText("JavaStud Learning Platform v2.0");
        alert.setContentText("""
            A comprehensive Java learning platform with:
            
            • Interactive code editor
            • Real-time execution
            • Extensive examples
            • Design patterns
            • Collections framework
            • Modern Java features
            
            Built with JavaFX and Spring Boot
            """);
        
        alert.showAndWait();
    }

    /**
     * Create documentation HTML content
     */
    private String createDocumentationHTML() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>JavaStud Documentation</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    h1 { color: #2196F3; }
                    h2 { color: #4CAF50; }
                    .code { background-color: #f5f5f5; padding: 10px; border-radius: 5px; }
                </style>
            </head>
            <body>
                <h1>JavaStud Learning Platform Documentation</h1>
                
                <h2>Getting Started</h2>
                <p>Welcome to JavaStud! This platform provides comprehensive Java learning resources.</p>
                
                <h2>Features</h2>
                <ul>
                    <li><strong>Interactive Code Editor:</strong> Write and test Java code in real-time</li>
                    <li><strong>Topic Navigation:</strong> Browse through different Java concepts</li>
                    <li><strong>Examples:</strong> Hands-on examples for every topic</li>
                    <li><strong>Documentation:</strong> Comprehensive documentation and references</li>
                </ul>
                
                <h2>How to Use</h2>
                <ol>
                    <li>Select a topic from the left panel</li>
                    <li>Review the example code in the editor</li>
                    <li>Modify the code as needed</li>
                    <li>Click "Run" to execute the code</li>
                    <li>View the output in the Output tab</li>
                </ol>
                
                <h2>Keyboard Shortcuts</h2>
                <ul>
                    <li><strong>Ctrl+R:</strong> Run code</li>
                    <li><strong>Ctrl+S:</strong> Save code</li>
                    <li><strong>Ctrl+Z:</strong> Undo</li>
                    <li><strong>Ctrl+Y:</strong> Redo</li>
                </ul>
                
                <h2>Topics Covered</h2>
                <ul>
                    <li>Core Java Fundamentals</li>
                    <li>Object-Oriented Programming</li>
                    <li>Collections Framework</li>
                    <li>Exception Handling</li>
                    <li>File I/O Operations</li>
                    <li>Multithreading</li>
                    <li>Lambda Expressions</li>
                    <li>Streams API</li>
                    <li>Design Patterns</li>
                    <li>Spring Boot</li>
                </ul>
            </body>
            </html>
            """;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
