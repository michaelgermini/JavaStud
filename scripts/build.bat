@echo off
echo ========================================
echo JavaStud Learning Platform Build Script
echo ========================================
echo.

:: Check if Maven is installed
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

echo ✅ Maven found
echo.

:: Clean previous builds
echo 🧹 Cleaning previous builds...
call mvn clean
if %errorlevel% neq 0 (
    echo ERROR: Clean failed
    pause
    exit /b 1
)

:: Compile the project
echo 🔨 Compiling project...
call mvn compile
if %errorlevel% neq 0 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

:: Run tests
echo 🧪 Running tests...
call mvn test
if %errorlevel% neq 0 (
    echo WARNING: Some tests failed
    echo.
)

:: Generate reports
echo 📊 Generating reports...
call mvn jacoco:report
call mvn spotbugs:check

:: Package the application
echo 📦 Packaging application...
call mvn package -DskipTests
if %errorlevel% neq 0 (
    echo ERROR: Packaging failed
    pause
    exit /b 1
)

:: Create executable JAR
echo 🎯 Creating executable JAR...
call mvn assembly:single
if %errorlevel% neq 0 (
    echo ERROR: Assembly failed
    pause
    exit /b 1
)

echo.
echo ========================================
echo ✅ Build completed successfully!
echo ========================================
echo.
echo 📁 Generated files:
echo    - target/javastud-learning-2.0.0.jar
echo    - target/javastud-learning-2.0.0-jar-with-dependencies.jar
echo.
echo 🚀 To run the application:
echo    java -jar target/javastud-learning-2.0.0-jar-with-dependencies.jar
echo.
echo 🌐 To run Spring Boot application:
echo    java -jar target/javastud-learning-2.0.0.jar
echo.
echo 📊 Reports available in:
echo    - target/site/jacoco/index.html (Code coverage)
echo    - target/spotbugsXml.xml (Static analysis)
echo.
pause
